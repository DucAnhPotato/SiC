package vn.sic.gateway.infrastructure.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vn.sic.gateway.infrastructure.validate.RouterValidator;
import vn.sic.gateway.infrastructure.util.JWTUtil;

/**
 * Authentication filter.
 *
 * @author NinhNH
 */
@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {
    /**
     * {@link RouterValidator}.
     */
    @Autowired
    private RouterValidator routerValidator;

    /**
     * {@link JWTUtil}.
     */
    @Autowired
    private JWTUtil jwtUtil;

    /**
     * Authentication filter
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.getIsSecured().test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                return this.onError(exchange);
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

            if (!jwtUtil.validateToken(token)) {
                return this.onError(exchange);
            }

            this.populateRequestWithHeaders(exchange, token);
        }

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest()
                .mutate()
                .header("username", String.valueOf(claims.get("username")))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }

}

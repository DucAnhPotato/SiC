package vn.sic.gateway.infrastructure.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.sic.gateway.infrastructure.filter.AuthenticationFilter;

/**
 * Gateway config.
 *
 * @author NinhNH
 */
@Configuration
public class GatewayConfig {
    /**
     * Gateway route builder.
     *
     * @param routeLocatorBuilder route builder
     * @return RouteLocator
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder, AuthenticationFilter filter) {
        return routeLocatorBuilder.routes()
                .route("auth-service", r -> r
                        .path("/api/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8081"))
                .route("payment-service", r -> r
                        .path("/api/payment/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8082"))
                .route("project-service", r -> r
                        .path("/api/project/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8083"))
                .build();
    }
}

package vn.sic.gateway.infrastructure.validate;

import lombok.Getter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * Router validator.
 *
 * @author NinhNH
 */
@Component
@Getter
public class RouterValidator {
    /**
     * Api list non-authentication.
     */
    public static final List<String> OPEN_API_ENDPOINTS = List.of("/auth");

    /**
     * Check authentication.
     */
    private final Predicate<ServerHttpRequest> isSecured = request ->
            OPEN_API_ENDPOINTS
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}

package vn.sic.gateway.infrastructure.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JWT Util class
 *
 * @author NinhNH
 */
@Component
public class JWTUtil {
    /**
     * Secret.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Key.
     */
    private Key key;

    /**
     * Init method.
     */
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Get info from token.
     *
     * @param token access token
     * @return Claims
     */
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * Get expired time for token.
     *
     * @param token access token
     * @return Date
     */
    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Validate token expired.
     *
     * @param token access token
     * @return boolean
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}

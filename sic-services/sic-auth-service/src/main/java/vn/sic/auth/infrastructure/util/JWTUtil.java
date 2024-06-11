package vn.sic.auth.infrastructure.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.sic.auth.domain.dto.UserDTO;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Util.
 *
 * @author NinhNH
 */
@Component
public class JWTUtil {
    /**
     * Calculator second form milliseconds.
     */
    private static final int MILLISECONDS = 1000;

    /**
     * Time expiration.
     */
    private static final int TIME_EXPIRED = 5;

    /**
     * Secret.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * token expiration time.
     */
    @Value("${jwt.expiration}")
    private String expirationTime;

    /**
     * Key.
     */
    private Key key;

    /**
     * init.
     */
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Get token info.
     *
     * @param token access token
     * @return Claims
     */
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * Get expiration token.
     *
     * @param token access token
     * @return date
     */
    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generate token.
     *
     * @param userDTO logged in user
     * @param type    token type
     * @return string token
     */
    public String generate(UserDTO userDTO, String type) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDTO.getUsername());
        claims.put("role", userDTO.getRole());
        long expirationTimeLong;
        if ("ACCESS".equals(type)) {
            expirationTimeLong = Long.parseLong(expirationTime) * MILLISECONDS;
        } else {
            expirationTimeLong = Long.parseLong(expirationTime) * MILLISECONDS * TIME_EXPIRED;
        }
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDTO.getUsername())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    /**
     * validate token.
     *
     * @param token access token
     * @return boolean
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}

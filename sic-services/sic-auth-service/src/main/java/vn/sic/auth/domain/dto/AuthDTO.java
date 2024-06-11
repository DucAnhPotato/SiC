package vn.sic.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * information about UserDTO.
 *
 * @author NinhNH
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthDTO {
    /**
     * Access token
     */
    private String accessToken;

    /**
     * Refresh token.
     */
    private String refreshToken;
}

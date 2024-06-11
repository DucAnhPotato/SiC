package vn.sic.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User DTO.
 *
 * @author NinhNH
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {
    /**
     * username.
     */
    private String username;
    /**
     * role.
     */
    private String role;
}

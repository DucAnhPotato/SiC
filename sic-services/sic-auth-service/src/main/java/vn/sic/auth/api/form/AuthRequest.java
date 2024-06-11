package vn.sic.auth.api.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Authentication request.
 *
 * @author NinhNH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}

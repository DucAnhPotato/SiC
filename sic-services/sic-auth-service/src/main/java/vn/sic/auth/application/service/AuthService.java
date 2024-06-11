package vn.sic.auth.application.service;

import vn.sic.auth.api.form.AuthRequest;
import vn.sic.auth.domain.dto.AuthDTO;

/**
 * Authentication service.
 *
 * @author NinhNH
 */
public interface AuthService {
    /**
     * Authentication.
     *
     * @param authRequest request
     * @return AuthDTO
     */
    AuthDTO auth(AuthRequest authRequest);
}

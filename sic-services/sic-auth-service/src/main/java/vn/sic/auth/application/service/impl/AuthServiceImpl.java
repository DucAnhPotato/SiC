package vn.sic.auth.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sic.auth.api.form.AuthRequest;
import vn.sic.auth.application.service.AuthService;
import vn.sic.auth.domain.dto.AuthDTO;
import vn.sic.auth.domain.dto.UserDTO;
import vn.sic.auth.infrastructure.util.JWTUtil;

/**
 * Authentication service implement.
 *
 * @author NinhNH
 */
@Service
public class AuthServiceImpl implements AuthService {
    /**
     * {@link JWTUtil}.
     */
    @Autowired
    private JWTUtil jwtUtil;

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthDTO auth(AuthRequest authRequest) {
        if (authRequest.getUsername().equals("admin") && authRequest.getPassword().equals("admin")) {
            UserDTO userDTO = UserDTO.builder()
                    .username("admin")
                    .role("ADMIN")
                    .build();

            return AuthDTO.builder()
                    .accessToken(jwtUtil.generate(userDTO, "ACCESS"))
                    .refreshToken(jwtUtil.generate(userDTO, "REFRESH"))
                    .build();
        }

        return null;
    }
}

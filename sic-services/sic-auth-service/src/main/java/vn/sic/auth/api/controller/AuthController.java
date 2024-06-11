package vn.sic.auth.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.sic.auth.api.form.AuthRequest;
import vn.sic.auth.api.reponse.Translator;
import vn.sic.auth.application.service.AuthService;
import vn.sic.auth.infrastructure.constant.MessageCode;
import vn.sic.core.common.reponse.ApiResponse;
import vn.sic.core.common.reponse.SuccessResponse;

/**
 * Authentication controller.
 *
 * @author NinhNH
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    /**
     * {@link AuthService}.
     */
    @Autowired
    private AuthService authService;

    /**
     * authentication.
     *
     * @param authRequest request
     * @return SuccessResponse
     */
    @PostMapping
    public ApiResponse auth(@Valid @RequestBody AuthRequest authRequest) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), authService.auth(authRequest));
    }
}

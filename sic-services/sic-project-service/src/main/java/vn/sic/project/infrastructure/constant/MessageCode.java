package vn.sic.project.infrastructure.constant;

import lombok.Getter;

/**
 * The class MessageCode declare message code for response
 *
 * @author NinhNH
 */
@Getter
public enum MessageCode {
    OK(200, "success"),
    BAD_REQUEST(400, "error.400"),
    UNAUTHORIZED(401, "error.401"),
    FORBIDDEN(403, "error.403"),
    NOT_FOUND(404, "error.404"),
    METHOD_NOT_ALLOWED(405, "error.405"),
    INTERNAL_SERVER_ERROR(500, "error.500");

    private final int code;
    private final String message;

    MessageCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

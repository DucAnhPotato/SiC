package vn.sic.core.common.reponse;

import org.springframework.http.HttpStatus;

/**
 * The class SuccessResponse implements received result success from server
 *
 * @author NinhNH
 */
public class ErrorResponse extends ApiResponse {
    /**
     * Create a new {@code ErrorResponse} with message.
     *
     * @param message response message
     */
    public ErrorResponse(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * Create a new {@code SuccessResponse} with the given data.
     *
     * @param message response message
     * @param data    response data
     */
    public ErrorResponse(String message, Object data) {
        super(HttpStatus.BAD_REQUEST.value(), message, data);
    }

    /**
     * Create a new {@code SuccessResponse} with the given data.
     *
     * @param code    response code
     * @param message message
     * @param data    data
     */
    public ErrorResponse(int code, String message, Object data) {
        super(code, message, data);
    }
}

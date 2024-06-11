package vn.sic.core.common.reponse;

import org.springframework.http.HttpStatus;

/**
 * The class SuccessResponse implements received result success from server
 *
 * @author NinhNH
 */
public class SuccessResponse extends ApiResponse {
    /**
     * Create a new {@code SuccessResponse} with message.
     *
     * @param message response message
     */
    public SuccessResponse(String message) {
        super(HttpStatus.OK.value(), message);
    }

    /**
     * Create a new {@code SuccessResponse} with the given data.
     *
     * @param message response message
     * @param data    response data
     */
    public SuccessResponse(String message, Object data) {
        super(HttpStatus.OK.value(), message, data);
    }
}

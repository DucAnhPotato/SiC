package vn.sic.core.common.exception;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * An exception class used to handle service error.
 *
 * @author NinhNH
 */
@Getter
public class ServiceException extends SystemException implements Serializable {
    /**
     * serialVersionUID.
     */
    @Serial
    private static final long serialVersionUID = 6073787403068176140L;

    /**
     * Error code
     */
    private final int code;

    /**
     * Constructs a new system exception with the specified detail message.
     *
     * @param code    the error code.
     * @param message the detail message.
     */
    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }
}

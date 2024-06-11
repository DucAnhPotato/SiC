package vn.sic.core.common.exception;

import java.io.Serial;
import java.io.Serializable;

/**
 * An exception class used to handle system error.
 *
 * @author NinhNH
 */
public class SystemException extends RuntimeException implements Serializable {

    /**
     * serialVersionUID.
     */
    @Serial
    private static final long serialVersionUID = 9134185463781926096L;

    /**
     * Constructs a new system exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * Constructs a new system exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new system exception with the specified cause.
     *
     * @param cause the cause
     */
    public SystemException(Throwable cause) {
        super(cause);
    }
}

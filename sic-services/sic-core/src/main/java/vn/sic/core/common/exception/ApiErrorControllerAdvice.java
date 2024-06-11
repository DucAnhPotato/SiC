package vn.sic.core.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import vn.sic.core.common.reponse.ApiResponse;
import vn.sic.core.common.reponse.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Api error controller advice (Exception Handler)
 *
 * @author NinhNH
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ApiErrorControllerAdvice {
    /**
     * Handle resource not found exception.
     *
     * @param e the exception
     * @return {@code ApiResponse}
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ApiResponse handleNotFoundException(NoHandlerFoundException e) {
        log.error("{}: {}", "Not found", e.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Resource not found", e.getDetailMessageArguments());
    }

    /**
     * Handle internal server error exception.
     *
     * @param e the exception
     * @return {@code ApiResponse}
     */
    @ExceptionHandler
    public ApiResponse handleUnknownException(Exception e) {
        log.error("{}: {} - {}", "Internal server error", e.getClass(), e.getMessage());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "The system is temporarily interrupted. Please try again later", null);
    }

    /**
     * Handle service exception.
     *
     * @param e the exception
     * @return {@code ApiResponse}
     */
    @ExceptionHandler({ServiceException.class})
    public ApiResponse handleServiceException(ServiceException e) {
        log.error("{}: {}", "Service error", e.getMessage());
        return new ErrorResponse(e.getCode(), e.getMessage(), null);
    }

    /**
     * Handle input argument exception.
     *
     * @param e the exception
     * @return {@code ApiResponse}
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ApiResponse handleInputArgumentException(Exception e) {
        List<ObjectError> objectErrorList = new ArrayList<>();
        if (e instanceof MethodArgumentNotValidException) {
            objectErrorList = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
        } else if (e instanceof BindException) {
            objectErrorList = ((BindException) e).getAllErrors();
        }

        log.error("{}: {}", "Invalid input", e.getMessage());

        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid input", objectErrorList);
    }

    /**
     * Handle missing servlet request parameter exception.
     *
     * @param e the exception
     * @return {@code ApiResponse}
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ApiResponse handleInputArgumentException(MissingServletRequestParameterException e) {
        log.error("{}: {}", "Missing servlet request parameter", e.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getDetailMessageArguments());
    }
}

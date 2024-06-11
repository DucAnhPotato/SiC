package vn.sic.log.application.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.sic.core.common.util.UrlUtils;
import vn.sic.log.application.service.LogService;
import vn.sic.log.domain.model.TransactionLogRequest;

import java.time.LocalDateTime;

@Aspect
@Component
public class TransactionLogAspect {
    @Autowired
    private LogService<TransactionLogRequest> logService;

    /**
     * Pointcut for LoggableRequest annotation
     */
    @Pointcut("@within(vn.sic.log.infrastructure.annotation.LoggableRequest)")
    public void loggableRequest() {
    }

    /**
     * Log transaction request and response
     *
     * @param proceedingJoinPoint Proceeding Join Point
     * @return Object
     */
    @Around("loggableRequest()")
    public Object logRequestProceeding(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        TransactionLogRequest transactionRequest = new TransactionLogRequest();

        transactionRequest.setCreatedAt(LocalDateTime.now().toString());
        transactionRequest.setRequestParam(proceedingJoinPoint.getArgs());
        transactionRequest.setClassName(proceedingJoinPoint.getSignature().getDeclaringTypeName());
        transactionRequest.setMethodName(proceedingJoinPoint.getSignature().getName());

        try {
            HttpServletRequest httpRequest = ((ServletRequestAttributes)
                    RequestContextHolder.currentRequestAttributes()).getRequest();
            transactionRequest.setRequestURL(UrlUtils.urlParseString(httpRequest));

            Object response = proceedingJoinPoint.proceed();
            transactionRequest.setResponse(response);

            return response;
        } finally {
            logService.logRequest(transactionRequest, TransactionLogRequest.class);
        }
    }
}

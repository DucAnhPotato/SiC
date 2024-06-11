package vn.sic.log.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sic.log.application.service.LogService;
import vn.sic.log.domain.model.TransactionLogRequest;
import vn.sic.log.domain.repository.TransactionLogRepository;

@Service
public class LogServiceImpl<T> implements LogService<T> {
    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Override
    public void logRequest(T request, Class<T> clazz) {
        if (clazz.isAssignableFrom(TransactionLogRequest.class)) {
            transactionLogRepository.save((TransactionLogRequest) request);
        }
    }
}

package vn.sic.log.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.sic.log.domain.model.TransactionLogRequest;

@Repository
public interface TransactionLogRepository extends MongoRepository<TransactionLogRequest, String> {
}

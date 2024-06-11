package vn.sic.payment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sic.payment.domain.entity.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByRefTraceOrInvoiceNo(String refTrace, String invoiceNo);
    Transaction findByRefTrace(String refTrace);
    List<Transaction> getTransactionsByTransactionStatusIsIn(List<String> status);

}

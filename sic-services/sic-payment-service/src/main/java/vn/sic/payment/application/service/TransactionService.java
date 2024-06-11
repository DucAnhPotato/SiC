package vn.sic.payment.application.service;

import vn.sic.payment.api.form.CreateTransactionRequest;
import vn.sic.payment.domain.dto.DetailTransactionDTO;
import vn.sic.payment.domain.dto.RefundTransactionInquireDTO;
import vn.sic.payment.domain.dto.TransactionInquireDTO;
import vn.sic.payment.domain.dto.StatusTransactionDTO;

import java.util.List;

public interface TransactionService {
    /**
     * Create a transaction
     *
     * @param request Create transaction request
     * @return redirect URL
     */
    String createTransaction(CreateTransactionRequest request);

    /**
     * Transaction inquire
     *
     * @param invoiceNo invoice number
     * @return InquireTransactionDTO
     */
    TransactionInquireDTO transactionInquire(String invoiceNo);

    /**
     * Get transaction status
     *
     * @param status list of status
     * @return List<StatusTransactionDTO>
     */
    List<StatusTransactionDTO> getTransactionsByStatus(List<String> status);

    /**
     * Get transaction detail
     *
     * @param id id number
     * @return DetailTransactionDTO
     */
    DetailTransactionDTO getTransactionById(Long id);

    /**
     * Refund transaction inquire
     *
     * @param requestId requestId number
     * @return RefundTransactionInquireDTO
     */
    RefundTransactionInquireDTO refundTransactionInquire(String requestId);
}

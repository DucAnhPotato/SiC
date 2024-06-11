package vn.sic.payment.domain.dto;

public record StatusTransactionDTO(
        String merchantId,
        String customerId,
        String refTrace,
        String invoiceNo,
        Long debitAmount,
        Long amount,
        Long fee,
        String currency,
        String hashKey,
        String timestamp,
        String callbackURL,
        String description,
        String reqDomain,
        String paymentMethod,
        String transactionStatus
) {
}

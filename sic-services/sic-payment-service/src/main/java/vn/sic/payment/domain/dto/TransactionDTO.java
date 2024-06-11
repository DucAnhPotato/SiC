package vn.sic.payment.domain.dto;

public record TransactionDTO(
        String merchantId,
        String customerId,
        String refTrace,
        String invoiceNo,
        String orderNo,
        Long debitAmount,
        Long amount,
        Long fee,
        String currency,
        String hashKey,
        String timestamp,
        String callbackURL,
        String description,
        String reqDomain,
        String paymentMethod
) {
}

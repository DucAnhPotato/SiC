package vn.sic.payment.domain.dto;

public record DetailTransactionDTO(
        String merchantId,
        String customerId,
        String refTrace,
        String invoiceNo,
        Long debitAmount,
        Long amount,
        Long fee,
        String currency,
        String hashKey,
        String timeStamp,
        String callbackURL,
        String description,
        String reqDomain,
        String paymentMethod,
        String createdDate,
        String updatedDate,
        String transactionType,
        String updateType,
        String note,
        String updater,
        String transactionStatus
) {
}

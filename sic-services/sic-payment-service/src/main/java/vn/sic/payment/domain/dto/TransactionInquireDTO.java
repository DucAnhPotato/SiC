package vn.sic.payment.domain.dto;

import com.google.gson.annotations.SerializedName;

public record TransactionInquireDTO(
        @SerializedName("payment_no")
        Long paymentNo,
        @SerializedName("invoice_no")
        String invoiceNo,
        String currency,
        int amount,
        String description,
        String method,
        @SerializedName("card_brand")
        String cardBrand,
        String status,
        @SerializedName("failure_reason")
        String failureReason,
        @SerializedName("created_at")
        String createdAt
) {
}

package vn.sic.payment.domain.dto;

import com.google.gson.annotations.SerializedName;

public record TempTransactionInquireDTO(
        Long paymentNo,
        @SerializedName("invoice_no")
        String invoiceNo,
        String currency,
        int amount,
        String description,
        String method,
        String cardBrand,
        String status,
        String failureReason,
        @SerializedName("created_at")
        String createdAt
) {
}

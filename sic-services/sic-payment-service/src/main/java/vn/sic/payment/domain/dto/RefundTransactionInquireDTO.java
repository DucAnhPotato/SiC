package vn.sic.payment.domain.dto;

import com.google.gson.annotations.SerializedName;

public record RefundTransactionInquireDTO(
        int status,
        @SerializedName("error_code")
        int errorCode,
        @SerializedName("failure_reason")
        String failureReason,
        String message,
        @SerializedName("payment_no")
        Long paymentNo,
        @SerializedName("refund_no")
        Long refundNo,
        @SerializedName("request_id")
        String requestId,
        Long amount

) {
}

package vn.sic.payment.api.form;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;
import vn.sic.core.common.validation.NotBlank;
import vn.sic.core.common.validation.ValidTimestamp;
import vn.sic.core.common.validation.ValueOfEnum;
import vn.sic.payment.infrastructure.constant.Currency;
import vn.sic.payment.infrastructure.constant.PaymentMethod;

@Getter
@Setter
public class CreateTransactionRequest {
    @NotEmpty
    @Size(max = 6,min =1)
    String merchantId;
    @NotEmpty
    String customerId;
    @NotEmpty
    String refTrace;
    @NotEmpty
    String orderNo;
    @NotNull
    @Digits(integer = 18, fraction = 2)
    @DecimalMin(value = "0")
    Long debitAmount;
    @NotNull
    @Digits(integer = 18, fraction = 2)
    @DecimalMin(value = "10000")
    @DecimalMax(value = "200000000")
    Long amount;
    @NotNull
    @Digits(integer = 18, fraction = 2)
    @DecimalMin(value = "0")
    Long fee;
    @NotEmpty
    @ValueOfEnum(enumClass = Currency.class)
    String currency;
    @NotEmpty
    String hashKey;
    @NotEmpty
    @ValidTimestamp
    String timestamp;
    @NotEmpty
    String callbackURL;
    @Size(max = 100, message = "Description length exceeded !")
    String description;
    @NotEmpty
    String reqDomain;
    @ValueOfEnum(enumClass = PaymentMethod.class)
    String paymentMethod;
    @NotNull
    @Min(0)
    @Max(1)
    Integer saveToken;
    String cardToken;

}

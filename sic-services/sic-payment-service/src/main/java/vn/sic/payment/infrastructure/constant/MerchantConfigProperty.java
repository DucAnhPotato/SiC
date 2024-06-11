package vn.sic.payment.infrastructure.constant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Component
public final class MerchantConfigProperty {

    @Value("${merchant.merchant-key}")
    private String merchantKey;

    @Value("${merchant.merchant-secret-key}")
    private String merchantSecretKey;

    @Value("${merchant.endpoint}")
    private String endPoint;

}
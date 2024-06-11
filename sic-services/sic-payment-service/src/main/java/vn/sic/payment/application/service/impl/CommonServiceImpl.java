package vn.sic.payment.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sic.core.common.util.UrlUtils;
import vn.sic.payment.application.service.CommonService;
import vn.sic.payment.infrastructure.constant.MerchantConfigProperty;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private MerchantConfigProperty merchantConfigProperty;

    /**
     * {@inheritDoc}
     */
    @Override
    public String signature(Map<String, String> paramMap, String method, String action, String time) {
        try {
            StringBuilder sb = new StringBuilder();

            sb.append(method);
            sb.append("\n");
            sb.append(merchantConfigProperty.getEndPoint()).append(action);
            sb.append("\n");
            sb.append(time);
            if (paramMap != null) {
                sb.append("\n");
                String encodedParameter = UrlUtils.urlEncode(paramMap);
                sb.append(encodedParameter);
            }
            String message = sb.toString();
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(
                    merchantConfigProperty.getMerchantSecretKey().getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] hmac = sha256_HMAC.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(hmac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

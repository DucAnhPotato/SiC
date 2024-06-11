package vn.sic.payment.application.service;

import java.util.Map;

public interface CommonService {
    /**
     * Create 9PAY digital signature
     *
     * @param paramMap parameters map
     * @param method method
     * @param action endpoint
     * @param time time
     * @return digital signature
     */
    String signature(Map<String, String> paramMap, String method, String action, String time);
}

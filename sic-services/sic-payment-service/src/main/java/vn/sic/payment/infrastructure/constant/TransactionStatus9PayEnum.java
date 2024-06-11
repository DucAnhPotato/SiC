package vn.sic.payment.infrastructure.constant;

import java.util.HashMap;
import java.util.Map;

public enum TransactionStatus9PayEnum {
    INIT(1, "Transaction has been initiated"),
    PENDING(2, "Transaction is pending"),
    CHECK(3, "Transaction will be checked for being suspicious"),
    IMPENDING_SUCCESS(4, "Transaction was success but merchant balance hasn't changed"),
    SUCCESS(5, "Transaction was success"),
    FAIL(6, "Transaction failed"),
    REPAY(7, "Transaction has been repay"),
    CANCEL(8, "Transaction was cancelled"),
    DENY(9, "Transaction has been denied"),
    REFUND(10, "Transaction has been refunded"),
    HELD(12, "Transaction is held for being suspicious"),
    ERROR(14, "Transaction encounter an error"),
    TIMEOUT(15, "Transaction timeout");

    private final int status;
    private final String description;
    private static final Map<Integer, String> statusMap = new HashMap<>();

    static {
        for (TransactionStatus9PayEnum code : TransactionStatus9PayEnum.values()) {
            statusMap.put(code.status, code.description);
        }
    }

    TransactionStatus9PayEnum(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public static String getDescriptionByStatus(int status) {
        return statusMap.get(status);
    }
}
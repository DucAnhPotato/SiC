package vn.sic.payment.infrastructure.constant;

public enum PaymentMethod {
    BANK_TRANSFER,
    QR_PAY,
    ATM_CARD;

    public static boolean contains(String method) {

        for (PaymentMethod p : PaymentMethod.values()) {
            if (p.name().equals(method)) {
                return true;
            }
        }

        return false;
    }
}

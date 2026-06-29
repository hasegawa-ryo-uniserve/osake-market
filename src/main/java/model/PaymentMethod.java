package model;

/**
 * 支払方法を列挙型で定義したクラス
 */
public enum PaymentMethod {
	CREDIT_CARD(1, "クレジットカード"),
    BANK(2, "銀行振込");

    private final int value;
    private final String label;

    PaymentMethod(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }
    
    public String getLabel() {
        return label;
    }
    
    public static PaymentMethod fromString(String value) {
        return PaymentMethod.valueOf(value);
    }
}

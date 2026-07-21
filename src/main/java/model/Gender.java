package model;

/**
 * 性別を列挙型で定義したクラス
 */
public enum Gender {
	MALE(1),
    FEMALE(2),
    OTHER(3);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

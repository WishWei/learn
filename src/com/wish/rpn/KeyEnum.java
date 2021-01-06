package com.wish.rpn;

public enum KeyEnum {

    KEY_ZERO(KeyTypeEnum.NUM, "0"),
    KEY_ONE(KeyTypeEnum.NUM, "1"),
    KEY_TWO(KeyTypeEnum.NUM, "2"),
    KEY_THREE(KeyTypeEnum.NUM, "3"),
    KEY_FOR(KeyTypeEnum.NUM, "4"),
    KEY_FIVE(KeyTypeEnum.NUM, "5"),
    KEY_SIX(KeyTypeEnum.NUM, "6"),
    KEY_SEVEN(KeyTypeEnum.NUM, "7"),
    KEY_EIGHT(KeyTypeEnum.NUM, "8"),
    KEY_NINE(KeyTypeEnum.NUM, "9"),
    KEY_ADD(KeyTypeEnum.OPPERATOR, "+"),
    KEY_SUB(KeyTypeEnum.OPPERATOR, "-"),
    KEY_MUL(KeyTypeEnum.OPPERATOR, "*"),
    KEY_DIV(KeyTypeEnum.OPPERATOR, "/");

    private KeyTypeEnum keyTypeEnum;

    private String value;

    KeyEnum(KeyTypeEnum keyTypeEnum, String value) {
        this.keyTypeEnum = keyTypeEnum;
        this.value = value;
    }

    /**
     * 通过value获取枚举
     * @param value
     * @return
     */
    public static KeyEnum getKeyEnumByValue(String value) {
        if(value == null) {
            return null;
        }
        for (KeyEnum keyEnum : KeyEnum.values()) {
            if(keyEnum.value.equals(value)) {
                return keyEnum;
            }
        }
        return null;
    }

    public KeyTypeEnum getKeyTypeEnum() {
        return keyTypeEnum;
    }

    public void setKeyTypeEnum(KeyTypeEnum keyTypeEnum) {
        this.keyTypeEnum = keyTypeEnum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package com.wish.rpn;

public enum KeyTypeEnum {
    NUM(1, "数字"),
    OPPERATOR(2, "操作符号");


    private int type;
    private String describe;

    KeyTypeEnum(int type, String describe){
        this.type = type;
        this.describe = describe;
    }
}

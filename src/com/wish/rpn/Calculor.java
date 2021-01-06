package com.wish.rpn;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 */
public class Calculor {
    //计算栈
    private List<KeyEnum> list = new LinkedList<>();

//    /**
//     * 按键输入
//     * @param keyValue
//     */
//    public void input(String keyValue){
//        KeyEnum keyEnum = KeyEnum.getKeyEnumByValue(keyValue);
//        if(keyValue == null) {
//            System.out.println("输入字符:" + keyValue + "无效");
//            return;
//        }
//        list.add(keyEnum);
//    }
//
//    /**
//     * 重置归零
//     */
//    public void reset() {
//        this.list = new Stack<>();
//    }

    public BigDecimal calculate(List<StackElement> elements) {
        if(elements.size() == 0) {
            return BigDecimal.ZERO;
        }

        Stack<StackElement> stack = new Stack<>();
        for (StackElement e : elements) {
            if(e.getKeyTypeEnum().equals(KeyTypeEnum.NUM)){
                stack.push(e);
            }else {
                StackElement right = stack.pop();
                StackElement left = stack.pop();
                if(e.getOperator().equals(KeyEnum.KEY_ADD)) {
                    BigDecimal result = left.value.add(right.value);
                    stack.push(new StackElement(result));
                }else if(e.getOperator().equals(KeyEnum.KEY_SUB)) {
                    BigDecimal result = left.value.subtract(right.value);
                    stack.push(new StackElement(result));
                }else if(e.getOperator().equals(KeyEnum.KEY_MUL)) {
                    BigDecimal result = left.value.multiply(right.value);
                    stack.push(new StackElement(result));
                } else if(e.getOperator().equals(KeyEnum.KEY_DIV)) {
                    BigDecimal result = left.value.divide(right.value);
                    stack.push(new StackElement(result));
                }else {
                    throw new RuntimeException("invalid operator：" + e.getOperator());
                }
            }
        }
        return stack.pop().getValue();
    }

    public static class StackElement{
        private KeyTypeEnum keyTypeEnum;
        private BigDecimal value;
        private KeyEnum operator;

        public StackElement(BigDecimal bigDecimal) {
            this.value = bigDecimal;
            this.keyTypeEnum = KeyTypeEnum.NUM;
        }

        public StackElement(KeyEnum keyEnum) {
            this.operator = keyEnum;
            this.keyTypeEnum = KeyTypeEnum.OPPERATOR;
        }

        public KeyTypeEnum getKeyTypeEnum() {
            return keyTypeEnum;
        }

        public void setKeyTypeEnum(KeyTypeEnum keyTypeEnum) {
            this.keyTypeEnum = keyTypeEnum;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }

        public KeyEnum getOperator() {
            return operator;
        }

        public void setOperator(KeyEnum operator) {
            this.operator = operator;
        }
    }
}

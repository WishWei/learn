package com.wish.rpn;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Calculor calculor = new Calculor();
        List<Calculor.StackElement> elements = new LinkedList<>();
        elements.add(new Calculor.StackElement(new BigDecimal(2)));
        elements.add(new Calculor.StackElement(new BigDecimal(3)));
        elements.add(new Calculor.StackElement(new BigDecimal(4)));
        elements.add(new Calculor.StackElement(KeyEnum.KEY_MUL));
        elements.add(new Calculor.StackElement(KeyEnum.KEY_SUB));
        elements.add(new Calculor.StackElement(new BigDecimal(5)));
        elements.add(new Calculor.StackElement(KeyEnum.KEY_ADD));

        BigDecimal result = calculor.calculate(elements);
        System.out.printf("expression: ");
        for (Calculor.StackElement element : elements) {
            if(element.getKeyTypeEnum().equals(KeyTypeEnum.OPPERATOR)) {
                System.out.printf(element.getOperator().getValue());
            }else {
                System.out.printf(element.getValue().toString());
            }
            System.out.printf(",");
        }
        System.out.println();
        System.out.println("result: " + result);
    }
}

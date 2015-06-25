package com.mogsev.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zhenya on 23.06.2015.
 */
public class RandomValue implements Serializable {
    private Random random;
    private Integer number1, number2, result;
    private ArrayList<Integer> list;
    private int operation, level;
    private String expression;

    /**
     *
     * @param level
     * @param operation
     */
    public RandomValue(int level, int operation) {
        list = new ArrayList<Integer>();
        random = new Random();
        this.level = level;
        this.operation = operation;
    }

    private void generateData() {
        number1 = random.nextInt(level);
        number2 = random.nextInt(level);
    }

    /**
     * Generate new data
     */
    public void generateExpression() {
        generateData();
        switch (operation) {
            case MathOperation.SUM:
                result = number1 + number2;
                expression = number1 + " + " + number2;
                break;
            case MathOperation.SUBTRACTION:
                if (number1 >= number2) {
                    result = number1 - number2;
                    expression = number1 + " - " + number2;
                } else {
                    result = number2 - number1;
                    expression = number2 + " - " + number1;
                }
                break;
            case MathOperation.MULTIPLICATION:
                result = number1 * number2;
                expression = number1 + " * " + number2;
                break;
            case MathOperation.DIVISION:
                getDivisionExpression();
                break;
        }
    }

    /**
     *
     */
    private void getDivisionExpression() {
        generateData();
        if ( (number2 != 0) && ((number1 / number2) > 0)&& (number1%number2 == 0) ) {
            result = number1 / number2;
            expression = number1 + " / " + number2;
        } else {
            getDivisionExpression();
        }
    }

    /**
     * Returns ArrayList that contains data
     * @return
     */
    public ArrayList<Integer> getList() {
        if (!list.isEmpty()) {
            list.clear();
        }
        list.add(result+random.nextInt(level/5));
        list.add(result-random.nextInt(level/5));
        list.add(result);
        int step = random.nextInt(10);
        for (int i = 0; i < step; i++ ) {
            int rest = list.get(0);
            list.set(0, list.get(1));
            list.set(1, list.get(2));
            list.set(2, rest);
        }
        return list;
    }

    /**
     * Returns result of expression
     * @return Integer result
     */
    public Integer getResult() {
        return result;
    }

    /**
     * Returns expression
     * @return String expression
     */
    public String getExpression() {
        return expression;
    }

}

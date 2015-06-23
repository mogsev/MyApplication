package com.mogsev.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zhenya on 23.06.2015.
 */
public class RandomValue {
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

    /**
     *
     */
    private void generate() {
        number1 = random.nextInt(level);
        number2 = random.nextInt(level);
        switch (operation) {
            case MathOperation.SUM:
                result = number1 + number2;
                expression = number1 + " + " + number2;
                break;
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getList() {
        generate();
        if (!list.isEmpty()) {
            list.clear();
        }
        list.add(number1);
        list.add(number2);
        list.add(result);
        int value = random.nextInt(10);
        for (int i = 0; i < value; i++ ) {
            int rest = list.get(0);
            list.set(0, list.get(1));
            list.set(1, list.get(2));
            list.set(2, rest);
        }
        return list;
    }

    /**
     *
     * @return result
     */
    public Integer getResult() {
        return result;
    }

    public String getExpression() {
        return expression;
    }

}

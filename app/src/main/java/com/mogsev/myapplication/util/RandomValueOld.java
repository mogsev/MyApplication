package com.mogsev.myapplication.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by zhenya on 23.06.2015.
 */
public class RandomValueOld implements Serializable {
    private Random random;
    private Integer number1, number2, result;
    private ArrayList<Integer> list;
    private int operation;
    private String expression;

    /**
     * @param operation
     */
    public RandomValueOld(int operation) {
        list = new ArrayList<Integer>();
        random = new Random();
        this.operation = operation;
    }

    /**
     * @param level
     */
    private void generateData(int level) {
        number1 = random.nextInt(level * 10);
        number2 = random.nextInt(level * 10);
    }

    /**
     * Generate new data
     */
    public void generateExpression(int level) {
        switch (operation) {
            // Generate result and expression for Sum
            case MathOperation.SUM:
                generateData(level);
                result = number1 + number2;
                expression = number1 + " + " + number2;
                break;
            // Generate result and expression for Subtraction
            case MathOperation.SUBTRACTION:
                generateData(level);
                if (number1 >= number2) {
                    result = number1 - number2;
                    expression = number1 + " - " + number2;
                } else {
                    result = number2 - number1;
                    expression = number2 + " - " + number1;
                }
                break;
            // Generate result and expression for Multiplication
            case MathOperation.MULTIPLICATION:
                generateData(level);
                result = number1 * number2;
                expression = number1 + " * " + number2;
                break;
            //Generate result and expression for Division
            case MathOperation.DIVISION:
                getDivisionExpression(level);
                break;
            // Generate result and expression for Table Multiplication
            case MathOperation.TABLE_MULTIPLICATION:
                getTableMultiplicationExpression();
                break;
        }
    }

    /**
     * Generate result and expression for Table Multiplication
     */
    private void getTableMultiplicationExpression() {
        generateData(1);
        if ((number1 != 0) && (number2 != 0)) {
            result = number1 * number2;
            expression = number1 + " * " + number2;
        } else {
            getTableMultiplicationExpression();
        }
    }

    /**
     * Generate result and expression for Division
     */
    private void getDivisionExpression(int level) {
        generateData(level);
        if ((number2 != 0) && ((number1 / number2) > 0) && (number1 % number2 == 0)) {
            result = number1 / number2;
            expression = number1 + " / " + number2;
        } else {
            getDivisionExpression(level);
        }
    }

    /**
     * Returns ArrayList that contains data
     *
     * @return
     */
    public ArrayList<Integer> getList() {
        if (!list.isEmpty()) {
            list.clear();
        }
        getRandomNum(result);
        getRandomNum(result);
        list.add(result);
        int step = random.nextInt(10);
        for (int i = 0; i < step; i++) {
            int rest = list.get(0);
            list.set(0, list.get(1));
            list.set(1, list.get(2));
            list.set(2, rest);
        }
        return list;
    }

    /**
     * Returns result of expression
     *
     * @return Integer result
     */
    public Integer getResult() {
        return result;
    }

    /**
     * Returns expression
     *
     * @return String expression
     */
    public String getExpression() {
        return expression;
    }

    public String getFullExpression() {
        return expression + " = " + result;
    }

    /**
     * @param result
     */
    private void getRandomNum(int result) {
        if (result != 0) {
            int num = random.nextInt(result + result);
            if (num == result) {
                getRandomNum(result);
            } else {
                list.add(num);
            }
        } else {
            list.add(random.nextInt(10));
        }
    }
}

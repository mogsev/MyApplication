package com.mogsev.myapplication.util;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by zhenya on 23.06.2015.
 */
public class RandomValue implements Serializable {
    private Random random;
    private Integer number1, number2, number3, number4, result;
    private ArrayList<Integer> listAnswer;
    private ArrayList<Integer> listNumber;
    private int operation;
    private String expression;

    /**
     * @param operation
     */
    public RandomValue(int operation) {
        listAnswer = new ArrayList<Integer>();
        listNumber = new ArrayList<Integer>();
        random = new Random();
        this.operation = operation;
    }

    /**
     * @param level
     */
    private void generateData(int level) {
        switch (level) {
            case 6:
                generateData(10, 3);
                break;
            case 7:
                generateData(20, 3);
                break;
            case 8:
                generateData(30, 3);
                break;
            case 9:
                generateData(40, 3);
                break;
            case 10:
                generateData(50, 3);
                break;
            case 11:
                generateData(10, 4);
                break;
            case 12:
                generateData(20, 4);
                break;
            case 13:
                generateData(30, 4);
                break;
            case 14:
                generateData(40, 4);
                break;
            case 15:
                generateData(50, 4);
                break;
            default:
                generateData(level*10, 2);
                //number1 = random.nextInt(level * 10);
                //number2 = random.nextInt(level * 10);
                break;
        }
    }

    /**
     *
     * @param var
     * @param count
     */
    private void generateData(int var, int count) {
        if (!listNumber.isEmpty()) {
            listNumber.clear();
        }
        for (int i = 0; i < count; i++) {
            int num = random.nextInt(var);
            listNumber.add(num);
        }
    }

    /**
     * Generate new data
     */
    public void generateExpression(int level) {
        result = 0;
        expression = "";
        switch (operation) {
            // Generate result and expression for Sum
            case MathOperation.SUM:
                generateData(level);
                for (int i = 0; i < listNumber.size(); i++) {
                    result += listNumber.get(i);
                    if (i != (listNumber.size() - 1)) {
                        expression += listNumber.get(i) + " + ";
                    } else {
                        expression += listNumber.get(i);
                    }
                }
                break;
            // Generate result and expression for Subtraction
            case MathOperation.SUBTRACTION:
                generateData(level);
                result = listNumber.get(0);
                expression = listNumber.get(0) + " - ";
                for (int i = 1; i < listNumber.size(); i++) {
                    result -= listNumber.get(i);
                    if (i != (listNumber.size() - 1)) {
                        expression += listNumber.get(i) + " - ";
                    } else {
                        expression += listNumber.get(i);
                    }
                }
                /**
                if (number1 >= number2) {
                    result = number1 - number2;
                    expression = number1 + " - " + number2;
                } else {
                    result = number2 - number1;
                    expression = number2 + " - " + number1;
                }*/
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
    public ArrayList<Integer> getListAnswer() {
        if (!listAnswer.isEmpty()) {
            listAnswer.clear();
        }
        getRandomNum(result);
        getRandomNum(result);
        listAnswer.add(result);
        int step = random.nextInt(10);
        for (int i = 0; i < step; i++) {
            int rest = listAnswer.get(0);
            listAnswer.set(0, listAnswer.get(1));
            listAnswer.set(1, listAnswer.get(2));
            listAnswer.set(2, rest);
        }
        return listAnswer;
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
        if (result <= 0) {
            listAnswer.add(random.nextInt(10));
        } else {
            int num = random.nextInt(result + result);
            if (num == result) {
                getRandomNum(result);
            } else {
                listAnswer.add(num);
            }
        }
    }
}

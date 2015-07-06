package com.mogsev.myapplication.util;

import java.io.Serializable;

/**
 * Created by zhenya on 03.07.2015.
 */
public class MathResult implements Serializable {
    private int numPositiveAnswer;
    private int numNegativeAnswer;
    private int numAnswer;
    private int totalQuestion;
    private int numLevel;
    private int operation;

    /**
     * Empty constructor
     */
    public MathResult() {

    }

    /**
     * Constructor
     * @param operation
     */
    public MathResult(int operation) {
        this.operation = operation;
        numLevel = 1;
        totalQuestion = 10;
    }

    /**
     * Return amount positive answers
     * @return
     */
    public int getNumPositiveAnswer() {
        return numPositiveAnswer;
    }

    /**
     *
     * @param numPositiveAnswer
     */
    public void setNumPositiveAnswer(int numPositiveAnswer) {
        this.numPositiveAnswer = numPositiveAnswer;
    }

    /**
     * Increase positive answer
     * @return
     */
    public int increaseNumPositiveAnswer() {
        numPositiveAnswer++;
        return numPositiveAnswer;
    }

    /**
     * Return amount negative answers
     * @return
     */
    public int getNumNegativeAnswer() {
        return numNegativeAnswer;
    }

    /**
     *
     * @param numNegativeAnswer
     */
    public void setNumNegativeAnswer(int numNegativeAnswer) {
        this.numNegativeAnswer = numNegativeAnswer;
    }

    /**
     * Increase negative answer
     * @return
     */
    public int increaseNumNegativeAnswer() {
        numNegativeAnswer++;
        return numNegativeAnswer;
    }

    /**
     * Return number math operation
     * @return
     */
    public int getOperation() {
        return operation;
    }

    /**
     * Return number level
     * @return
     */
    public int getNumLevel() {
        return numLevel;
    }

    /**
     * Increase number level
     */
    public void increaseNumLevel() {
        if (operation != MathOperation.TABLE_MULTIPLICATION) {
            numLevel++;
        }
    }

    /**
     * Decrease number level
     */
    public void decreaseNumLevel() {
        if (!(numLevel <= 1)) {
                numLevel--;
        }
    }

    /**
     * It returns count for random ranges
     * @return int
     */
   /** public int getCountRandom() {
        return numLevel*10;
    }*/

    public void setNumAnswer(int numAnswer) {
        this.numAnswer = numAnswer;
    }

    public int getNumAnswer() {
        return numAnswer;
    }

    public int increaseNumAnswer() {
        numAnswer++;
        return numAnswer;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    /**
     * Cleans result of expression
     */
    public void cleanOutResult() {
        this.setTotalQuestion(10);
        this.setNumAnswer(0);
        this.setNumNegativeAnswer(0);
        this.setNumPositiveAnswer(0);
    }
}

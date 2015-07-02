package com.mogsev.myapplication.util;

/**
 * Created by zhenya on 03.07.2015.
 */
public class MathResult {
    private int numPositiveAnswer;
    private int numNegativeAnswer;

    /**
     *
     */
    public MathResult() {

    }

    /**
     *
     * @param operation
     */
    public MathResult(int operation) {

    }

    /**
     *
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
     *
     * @return
     */
    public int increaseNumPositiveAnswer() {
        numPositiveAnswer++;
        return numPositiveAnswer;
    }

    /**
     *
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

    public int increaseNumNegativeAnswer() {
        numNegativeAnswer++;
        return numNegativeAnswer;
    }
}

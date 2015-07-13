package com.mogsev.myapplication.util;

import java.io.Serializable;

/**
 * Created by zhenya on 03.07.2015.
 */
public class MathResult implements Serializable {
    private int score;
    private int totalQuestions;
    private int totalPositiveAnswers;
    private int totalNegativeAnswers;
    private int numPositiveAnswers;
    private int numNegativeAnswers;
    private int numAnswer;
    private int questions;
    private int numLevel;
    private int operation;
    private boolean checkAnswer;

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
        questions = 10;
        checkAnswer = false;
    }

    /**
     * Return amount positive answers
     * @return
     */
    public int getNumPositiveAnswers() {
        return numPositiveAnswers;
    }

    /**
     *
     * @param numPositiveAnswers
     */
    public void setNumPositiveAnswers(int numPositiveAnswers) {
        this.numPositiveAnswers = numPositiveAnswers;
    }

    /**
     * Increase positive answer
     * @return
     */
    public int increaseNumPositiveAnswer() {
        numPositiveAnswers++;
        return numPositiveAnswers;
    }

    /**
     * Return amount negative answers
     * @return
     */
    public int getNumNegativeAnswers() {
        return numNegativeAnswers;
    }

    /**
     *
     * @param numNegativeAnswers
     */
    public void setNumNegativeAnswers(int numNegativeAnswers) {
        this.numNegativeAnswers = numNegativeAnswers;
    }

    /**
     * Increase negative answer
     * @return
     */
    public int increaseNumNegativeAnswer() {
        numNegativeAnswers++;
        return numNegativeAnswers;
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

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getQuestions() {
        return questions;
    }

    /**
     * Cleans result of expression
     */
    public void cleanOutResult() {
        this.setQuestions(10);
        this.setNumAnswer(0);
        this.setNumNegativeAnswers(0);
        this.setNumPositiveAnswers(0);
    }

    public boolean isCheckAnswer() {
        return checkAnswer;
    }

    public void setCheckAnswer(boolean checkAnswer) {
        this.checkAnswer = checkAnswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getTotalPositiveAnswers() {
        return totalPositiveAnswers;
    }

    public void setTotalPositiveAnswers(int totalPositiveAnswers) {
        this.totalPositiveAnswers = totalPositiveAnswers;
    }

    public int getTotalNegativeAnswers() {
        return totalNegativeAnswers;
    }

    public void setTotalNegativeAnswers(int totalNegativeAnswers) {
        this.totalNegativeAnswers = totalNegativeAnswers;
    }
}

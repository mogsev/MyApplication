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
    private int level;
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
        level = 1;
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
     * Set number level
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Return number level
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Increase number level
     */
    public void increaseNumLevel() {
        level++;
    }

    /**
     * Decrease number level
     */
    public void decreaseNumLevel() {
        if (!(level <= 1)) {
                level--;
        }
    }

    /**
     *
     * @param numAnswer
     */
    public void setNumAnswer(int numAnswer) {
        this.numAnswer = numAnswer;
    }

    /**
     *
     * @return
     */
    public int getNumAnswer() {
        return numAnswer;
    }

    /**
     *
     * @return
     */
    public int increaseNumAnswer() {
        numAnswer++;
        return numAnswer;
    }

    /**
     *
     * @param questions
     */
    public void setQuestions(int questions) {
        this.questions = questions;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public boolean isCheckAnswer() {
        return checkAnswer;
    }

    /**
     *
     * @param checkAnswer
     */
    public void setCheckAnswer(boolean checkAnswer) {
        this.checkAnswer = checkAnswer;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return
     */
    public int getTotalQuestions() {
        return totalQuestions;
    }

    /**
     *
     * @param totalQuestions
     */
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    /**
     *
     * @return
     */
    public int getTotalPositiveAnswers() {
        return totalPositiveAnswers;
    }

    /**
     *
     * @param totalPositiveAnswers
     */
    public void setTotalPositiveAnswers(int totalPositiveAnswers) {
        this.totalPositiveAnswers = totalPositiveAnswers;
    }

    /**
     *
     * @return
     */
    public int getTotalNegativeAnswers() {
        return totalNegativeAnswers;
    }

    /**
     *
     * @param totalNegativeAnswers
     */
    public void setTotalNegativeAnswers(int totalNegativeAnswers) {
        this.totalNegativeAnswers = totalNegativeAnswers;
    }

    /**
     *
     */
    public void resultHandler() {
        score += numPositiveAnswers;
        totalQuestions += questions;
        totalPositiveAnswers += numPositiveAnswers;
        totalNegativeAnswers += numNegativeAnswers;
    }
}

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
    private int positiveAnswers;
    private int negativeAnswers;
    private int answers;
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
     *
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
     *
     * @return
     */
    public int getPositiveAnswers() {
        return positiveAnswers;
    }

    /**
     * @param positiveAnswers
     */
    public void setPositiveAnswers(int positiveAnswers) {
        this.positiveAnswers = positiveAnswers;
    }

    /**
     * Increase positive answers
     *
     * @return
     */
    public int increaseNumPositiveAnswers() {
        positiveAnswers++;
        return positiveAnswers;
    }

    /**
     * Return amount negative answers
     *
     * @return
     */
    public int getNegativeAnswers() {
        return negativeAnswers;
    }

    /**
     * @param negativeAnswers
     */
    public void setNegativeAnswers(int negativeAnswers) {
        this.negativeAnswers = negativeAnswers;
    }

    /**
     * Increase negative answers
     *
     * @return
     */
    public int increaseNumNegativeAnswers() {
        negativeAnswers++;
        return negativeAnswers;
    }

    /**
     * Return number math operation
     *
     * @return
     */
    public int getOperation() {
        return operation;
    }

    /**
     * Set number level
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Return number level
     *
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
     * @param answers
     */
    public void setAnswers(int answers) {
        this.answers = answers;
    }

    /**
     * @return
     */
    public int getAnswers() {
        return answers;
    }

    /**
     * @return
     */
    public int increaseNumAnswers() {
        answers++;
        return answers;
    }

    /**
     * @param questions
     */
    public void setQuestions(int questions) {
        this.questions = questions;
    }

    /**
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
        this.setAnswers(0);
        this.setNegativeAnswers(0);
        this.setPositiveAnswers(0);
    }

    /**
     * @return
     */
    public boolean isCheckAnswer() {
        return checkAnswer;
    }

    /**
     * @param checkAnswer
     */
    public void setCheckAnswer(boolean checkAnswer) {
        this.checkAnswer = checkAnswer;
    }

    /**
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return
     */
    public int getTotalQuestions() {
        return totalQuestions;
    }

    /**
     * @param totalQuestions
     */
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    /**
     * @return
     */
    public int getTotalPositiveAnswers() {
        return totalPositiveAnswers;
    }

    /**
     * @param totalPositiveAnswers
     */
    public void setTotalPositiveAnswers(int totalPositiveAnswers) {
        this.totalPositiveAnswers = totalPositiveAnswers;
    }

    /**
     * @return
     */
    public int getTotalNegativeAnswers() {
        return totalNegativeAnswers;
    }

    /**
     * @param totalNegativeAnswers
     */
    public void setTotalNegativeAnswers(int totalNegativeAnswers) {
        this.totalNegativeAnswers = totalNegativeAnswers;
    }

    /**
     *
     */
    public void resultHandler() {
        score += positiveAnswers;
        totalQuestions += questions;
        totalPositiveAnswers += positiveAnswers;
        totalNegativeAnswers += negativeAnswers;
    }
}

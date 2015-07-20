package com.mogsev.myapplication.util;

import java.io.Serializable;

/**
 * Created by zhenya on 20.07.2015.
 */
public class MathMulti implements Serializable {
    private boolean sum;
    private boolean subtraction;
    private boolean multiplication;
    private boolean division;

    public MathMulti() {

    }

    public boolean isDivision() {
        return division;
    }

    public void setDivision(boolean division) {
        this.division = division;
    }

    public boolean isSum() {
        return sum;
    }

    public void setSum(boolean sum) {
        this.sum = sum;
    }

    public boolean isSubtraction() {
        return subtraction;
    }

    public void setSubtraction(boolean subtraction) {
        this.subtraction = subtraction;
    }

    public boolean isMultiplication() {
        return multiplication;
    }

    public void setMultiplication(boolean multiplication) {
        this.multiplication = multiplication;
    }
}

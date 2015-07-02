package com.mogsev.myapplication.activity;

import android.os.Bundle;

import com.mogsev.myapplication.R;
import com.mogsev.myapplication.util.MathOperation;
import com.mogsev.myapplication.util.MathTraining;
import com.mogsev.myapplication.util.RandomValue;

public class SubtractionActivity extends MathTraining {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        //The application was lunched?
        if (savedInstanceState == null) {
            randomValue = new RandomValue(20, MathOperation.SUBTRACTION);
            randomValue.generateExpression();
            list = randomValue.getList();
        } else {
            randomValue = (RandomValue) savedInstanceState.get(RANDOM_VALUE);
            list = randomValue.getList();
        }

        //Initialize links for objects
        initElements();

        //filling Activity
        fillingActivity();
    }
}

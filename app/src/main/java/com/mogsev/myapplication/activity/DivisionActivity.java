package com.mogsev.myapplication.activity;

import android.os.Bundle;

import com.mogsev.myapplication.R;
import com.mogsev.myapplication.util.MathOperation;
import com.mogsev.myapplication.util.MathResult;
import com.mogsev.myapplication.util.MathTraining;
import com.mogsev.myapplication.util.RandomValue;

public class DivisionActivity extends MathTraining {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        //Initialize links for objects
        initElements();

        //The application was lunched?
        if (savedInstanceState == null) {
            mathResult = new MathResult(MathOperation.DIVISION);
            randomValue = new RandomValue(MathOperation.DIVISION);
            randomValue.generateExpression(mathResult.getLevel());
        } else {
            randomValue = (RandomValue) savedInstanceState.get(RANDOM_VALUE);
            mathResult = (MathResult) savedInstanceState.get(MATH_RESULT);
        }
        list = randomValue.getList();

        loadPreferences(MathOperation.DIVISION);

        //filling Activity
        fillingActivity();
    }
}

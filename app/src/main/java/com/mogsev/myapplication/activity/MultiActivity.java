package com.mogsev.myapplication.activity;

import android.os.Bundle;

import com.mogsev.myapplication.R;
import com.mogsev.myapplication.util.MathOperation;
import com.mogsev.myapplication.util.MathResult;
import com.mogsev.myapplication.util.MathTraining;
import com.mogsev.myapplication.util.RandomValue;

public class MultiActivity extends MathTraining {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        //Initialize links for objects
        initElements();



        //The application was lunched?
        if (savedInstanceState == null) {
            mathResult = new MathResult(MathOperation.MULTI);
            randomValue = new RandomValue(MathOperation.MULTI);
            loadPreferences(MathOperation.MULTI);
            randomValue.generateMultiExpression(mathResult.getLevel(), mathMulti);
        } else {
            randomValue = (RandomValue) savedInstanceState.get(RANDOM_VALUE);
            mathResult = (MathResult) savedInstanceState.get(MATH_RESULT);
            startTimer = (int) savedInstanceState.getInt(START_TIMER);

            // The dialog of results was shown?
            if (savedInstanceState.getBoolean("dialogResults")) {
                showResult();
            }
        }
        list = randomValue.getListAnswer();



        //filling Activity
        fillingActivity();

    }
}

package com.mogsev.myapplication.activity;

import android.os.Bundle;

import com.mogsev.myapplication.R;
import com.mogsev.myapplication.util.MathOperation;
import com.mogsev.myapplication.util.MathResult;
import com.mogsev.myapplication.util.MathTraining;
import com.mogsev.myapplication.util.RandomValue;

public class SumActivity extends MathTraining {
    private static final String TAG = "SumActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);

        //Initialize links for objects
        initElements();

        //The application was lunched?
        if (savedInstanceState == null) {
            mathResult = new MathResult(MathOperation.SUM);
            randomValue = new RandomValue(MathOperation.SUM);
            randomValue.generateExpression(mathResult.getLevel());
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

        loadPreferences(MathOperation.SUM);

        //filling Activity
        fillingActivity();
    }
}
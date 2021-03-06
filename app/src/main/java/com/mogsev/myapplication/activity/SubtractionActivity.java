package com.mogsev.myapplication.activity;

import android.os.Bundle;

import com.mogsev.myapplication.R;
import com.mogsev.myapplication.util.MathOperation;
import com.mogsev.myapplication.util.MathResult;
import com.mogsev.myapplication.util.MathTraining;
import com.mogsev.myapplication.util.RandomValue;

public class SubtractionActivity extends MathTraining {
    private static final String TAG = "SubtractionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        //Initialize links for objects
        initElements();

        //The application was lunched?
        if (savedInstanceState == null) {
            mathResult = new MathResult(MathOperation.SUBTRACTION);
            randomValue = new RandomValue(MathOperation.SUBTRACTION);
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

        loadPreferences(MathOperation.SUBTRACTION);

        //filling Activity
        fillingActivity();
    }
}

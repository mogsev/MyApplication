package com.mogsev.myapplication.activity;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.mogsev.myapplication.R;
import com.mogsev.myapplication.util.MathOperation;
import com.mogsev.myapplication.util.MathResult;
import com.mogsev.myapplication.util.MathTraining;
import com.mogsev.myapplication.util.RandomValue;

public class DivisionActivity extends MathTraining {
    private static final String TAG = "DivisionActivity";

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
            startTimer = (int) savedInstanceState.getInt(START_TIMER);

            // The dialog of results was shown?
            if (savedInstanceState.getBoolean("dialogResults")) {
                showResult();
            }
        }
        list = randomValue.getListAnswer();

        loadPreferences(MathOperation.DIVISION);

        //filling Activity
        fillingActivity();
    }
}

package com.mogsev.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.mogsev.myapplication.R;
import java.util.ArrayList;

/**
 * Created by zhenya on 24.06.2015.
 */
public abstract class MathTraining extends Activity {
    public static final String RANDOM_VALUE = "RANDOM_VALUE";
    public Integer answer;
    public ArrayList<Integer> list;
    public Button buttonClick;
    public Button answer1;
    public Button answer2;
    public Button answer3;
    public Button buttonProceed;
    public TextView textViewAnswer;
    public TextView textViewExpression;
    public RandomValue randomValue;

    public void initElements() {
        textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        buttonProceed = (Button) findViewById(R.id.buttonProceed);
        textViewExpression = (TextView) findViewById(R.id.textViewExpression);
    }

    /**
     * Check user's answer
     * @param view
     */
    public void onClickAnswer(View view) {
        buttonClick = (Button) view;
        answer = new Integer(buttonClick.getText().toString());
        textViewAnswer.setVisibility(View.VISIBLE);
        if (answer.compareTo(randomValue.getResult()) == 0) {
            textViewAnswer.setText(R.string.correct_answer);
            textViewAnswer.setTextColor(Color.GREEN);
        } else {
            textViewAnswer.setText(R.string.wrong_answer);
            textViewAnswer.append(" ");
            textViewAnswer.append(randomValue.getResult().toString());
            textViewAnswer.setTextColor(Color.RED);
        }
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        buttonProceed.setVisibility(View.VISIBLE);

        // generate new expression
        randomValue.generateExpression();
        list = randomValue.getList();
    }

    /**
     * Action when button "proceed" was pressed
     * @param view
     */
    public void onClickProceed(View view){
        fillingActivity();
    }

    /**
     * data filling Activity
     */
    public void fillingActivity() {
        buttonProceed.setVisibility(View.INVISIBLE);
        textViewAnswer.setText(R.string.title_answer);
        textViewAnswer.setTextColor(Color.BLACK);
        textViewExpression.setText(randomValue.getExpression());
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer1.setText(String.valueOf(list.get(0)));
        answer2.setText(String.valueOf(list.get(1)));
        answer3.setText(String.valueOf(list.get(2)));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RANDOM_VALUE, randomValue);
    }
}

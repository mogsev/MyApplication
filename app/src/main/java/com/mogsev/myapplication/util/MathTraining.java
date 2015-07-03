package com.mogsev.myapplication.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mogsev.myapplication.R;

import java.util.ArrayList;

/**
 * Created by zhenya on 24.06.2015.
 */
public abstract class MathTraining extends Activity {
    public static final String RANDOM_VALUE = "RANDOM_VALUE";
    public static final String MATH_RESULT = "MATH_RESULT";
    public Integer answer;
    public ArrayList<Integer> list;
    public Button buttonClick;
    public Button buttonAnswer1;
    public Button buttonAnswer2;
    public Button buttonAnswer3;
    public Button buttonProceed;
    public TextView textViewAnswer;
    public TextView textViewExpression;
    public TextView textViewNumPositiveAnswer;
    public TextView textViewNumNegativeAnswer;
    public TextView textViewNumAnswer;
    public TextView textViewTotalAnswer;
    public TextView textViewNumLevel;

    public RandomValue randomValue;
    public MathResult mathResult;

    public void initElements() {
        // fragment_assignment content
        textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
        buttonAnswer1 = (Button) findViewById(R.id.answer1);
        buttonAnswer2 = (Button) findViewById(R.id.answer2);
        buttonAnswer3 = (Button) findViewById(R.id.answer3);
        buttonProceed = (Button) findViewById(R.id.buttonProceed);
        textViewExpression = (TextView) findViewById(R.id.textViewExpression);

        // fragment_bottom content
        textViewNumNegativeAnswer = (TextView) findViewById(R.id.textViewNumNegativeAnswer);
        textViewNumPositiveAnswer = (TextView) findViewById(R.id.textViewNumPositiveAnswer);
        textViewNumLevel = (TextView) findViewById(R.id.textViewNumLevel);
        textViewNumAnswer = (TextView) findViewById(R.id.textViewNumAnswer);
        textViewTotalAnswer = (TextView) findViewById(R.id.textViewTotalAnswer);
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
            textViewNumPositiveAnswer.setText(String.valueOf(mathResult.increaseNumPositiveAnswer()));
        } else {
            textViewAnswer.setText(R.string.wrong_answer);
            textViewAnswer.append(" ");
            textViewAnswer.append(randomValue.getResult().toString());
            textViewAnswer.setTextColor(Color.RED);
            textViewNumNegativeAnswer.setText(String.valueOf(mathResult.increaseNumNegativeAnswer()));
        }
        textViewNumAnswer.setText(String.valueOf(mathResult.increaseNumAnswer()));
        buttonAnswer1.setEnabled(false);
        buttonAnswer2.setEnabled(false);
        buttonAnswer3.setEnabled(false);
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
        mathResultCheck();

        // fragment_assignment content
        buttonProceed.setVisibility(View.INVISIBLE);
        textViewAnswer.setText(R.string.title_answer);
        textViewAnswer.setTextColor(Color.BLACK);
        textViewExpression.setText(randomValue.getExpression());
        buttonAnswer1.setEnabled(true);
        buttonAnswer2.setEnabled(true);
        buttonAnswer3.setEnabled(true);
        buttonAnswer1.setText(String.valueOf(list.get(0)));
        buttonAnswer2.setText(String.valueOf(list.get(1)));
        buttonAnswer3.setText(String.valueOf(list.get(2)));

        // fragment_bottom content
        textViewNumNegativeAnswer.setText(String.valueOf(mathResult.getNumNegativeAnswer()));
        textViewNumPositiveAnswer.setText(String.valueOf(mathResult.getNumPositiveAnswer()));
        textViewNumLevel.setText(String.valueOf(mathResult.getNumLevel()));
        textViewNumAnswer.setText(String.valueOf(mathResult.getNumAnswer()));
        textViewTotalAnswer.setText(String.valueOf(mathResult.getTotalAnswer()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RANDOM_VALUE, randomValue);
        outState.putSerializable(MATH_RESULT, mathResult);
    }

    private void mathResultCheck() {
        switch (mathResult.getOperation()) {
            case MathOperation.TABLE_MULTIPLICATION:
                if (mathResult.getNumAnswer() == 10) {
                    showResult();
                    mathResult.setNumAnswer(0);
                    mathResult.setNumNegativeAnswer(0);
                    mathResult.setNumPositiveAnswer(0);
                }
                break;
            case MathOperation.MULTIPLICATION:

                break;
            case MathOperation.DIVISION:

                break;
            case MathOperation.SUM:

                break;
            case MathOperation.SUBTRACTION:

                break;
        }
    }

    private void showResult() {
        final AlertDialog.Builder showResult = new AlertDialog.Builder(this);
        View linerLayout = getLayoutInflater().inflate(R.layout.dialog_result, null);
        showResult.setView(linerLayout);
        showResult.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        TextView textView9 = (TextView) linerLayout.findViewById(R.id.textView9);
        textView9.setText(String.valueOf(mathResult.getTotalAnswer()));
        showResult.create();
        showResult.show();
    }
}

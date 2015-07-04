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
    public ArrayList<Integer> list;
    public RandomValue randomValue;
    public MathResult mathResult;

    private Integer answer;
    private Button buttonClick;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonProceed;
    private TextView textViewAnswer;
    private TextView textViewExpression;
    private TextView textViewNumPositiveAnswer;
    private TextView textViewNumNegativeAnswer;
    private TextView textViewNumAnswer;
    private TextView textViewTotalQuestion;
    private TextView textViewNumLevel;

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
        textViewTotalQuestion = (TextView) findViewById(R.id.textViewTotalQuestion);
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
        textViewTotalQuestion.setText(String.valueOf(mathResult.getTotalQuestion()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RANDOM_VALUE, randomValue);
        outState.putSerializable(MATH_RESULT, mathResult);
    }

    private void mathResultCheck() {
        if (mathResult.getNumAnswer() == mathResult.getTotalQuestion()) {
            showResult();
        }
    }

    private void showResult() {
        final AlertDialog.Builder dialogResult = new AlertDialog.Builder(this);
        View linerLayout = getLayoutInflater().inflate(R.layout.dialog_result, null);
        dialogResult.setView(linerLayout);
        dialogResult.setTitle(R.string.title_result);
        dialogResult.setPositiveButton(R.string.dialog_save_result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                cleanOutResult();
                fillingActivity();
            }
        });
        dialogResult.setNegativeButton(R.string.dialog_continue_result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getApplicationContext(), R.string.toast_continue, Toast.LENGTH_LONG).show();
                mathResult.setTotalQuestion(mathResult.getTotalQuestion() + 10);
                textViewTotalQuestion.setText(String.valueOf(mathResult.getTotalQuestion()));
                dialogInterface.cancel();
            }
        });

        TextView dialogResultTotalQuestion = (TextView) linerLayout.findViewById(R.id.dialogResultTotalQuestion);
        TextView dialogResultPositiveAnswer = (TextView) linerLayout.findViewById(R.id.dialogResultPositiveAnswer);
        TextView dialogResultNegativeAnswer = (TextView) linerLayout.findViewById(R.id.dialogResultNegativeAnswer);
        dialogResultTotalQuestion.setText(R.string.dialog_total_number_question);
        dialogResultTotalQuestion.append(" ");
        dialogResultTotalQuestion.append(String.valueOf(mathResult.getTotalQuestion()));
        dialogResultPositiveAnswer.setText(R.string.dialog_number_positive_answer);
        dialogResultPositiveAnswer.append(" ");
        dialogResultPositiveAnswer.append(String.valueOf(mathResult.increaseNumPositiveAnswer()));
        dialogResultNegativeAnswer.setText(R.string.dialog_number_negative_answer);
        dialogResultNegativeAnswer.append(" ");
        dialogResultNegativeAnswer.append(String.valueOf(mathResult.getNumNegativeAnswer()));
        dialogResult.create();
        dialogResult.show();
    }

    private void cleanOutResult() {
        mathResult.setTotalQuestion(mathResult.getCountRandom());
        mathResult.setNumAnswer(0);
        mathResult.setNumNegativeAnswer(0);
        mathResult.setNumPositiveAnswer(0);
    }
}

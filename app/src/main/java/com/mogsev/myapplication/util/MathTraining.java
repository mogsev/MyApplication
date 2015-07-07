package com.mogsev.myapplication.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RANDOM_VALUE, randomValue);
        outState.putSerializable(MATH_RESULT, mathResult);
    }

    /**
     * Initialize view elements
     */
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
            buttonClick.setBackgroundResource(R.drawable.button_answer_positive);
        } else {
            textViewAnswer.setText(R.string.wrong_answer);
            textViewAnswer.append(" ");
            textViewAnswer.append(randomValue.getResult().toString());
            textViewAnswer.setTextColor(Color.RED);
            textViewNumNegativeAnswer.setText(String.valueOf(mathResult.increaseNumNegativeAnswer()));
            buttonClick.setBackgroundResource(R.drawable.button_answer_negative);
        }
        textViewNumAnswer.setText(String.valueOf(mathResult.increaseNumAnswer()));
        setEnabledButtonAnswers(false);
        buttonProceed.setVisibility(View.VISIBLE);
        mathResult.setCheckAnswer(true);
    }

    /**
     * Action when button "proceed" was pressed
     * @param view
     */
    public void onClickProceed(View view){
        mathResultCheck();
    }

    /**
     * data filling Activity
     */
    public void fillingActivity() {
        // fragment_assignment content
        buttonProceed.setVisibility(View.INVISIBLE);
        textViewAnswer.setText(R.string.title_answer);
        textViewAnswer.setTextColor(Color.BLACK);
        textViewExpression.setText(randomValue.getExpression());
        buttonAnswer1.setBackgroundResource(R.drawable.button_answer);
        buttonAnswer2.setBackgroundResource(R.drawable.button_answer);
        buttonAnswer3.setBackgroundResource(R.drawable.button_answer);
        buttonAnswer1.setText(String.valueOf(list.get(0)));
        buttonAnswer2.setText(String.valueOf(list.get(1)));
        buttonAnswer3.setText(String.valueOf(list.get(2)));
        setEnabledButtonAnswers(true);

        // fragment_bottom content
        textViewNumNegativeAnswer.setText(String.valueOf(mathResult.getNumNegativeAnswer()));
        textViewNumPositiveAnswer.setText(String.valueOf(mathResult.getNumPositiveAnswer()));
        textViewNumLevel.setText(String.valueOf(mathResult.getNumLevel()));
        textViewNumAnswer.setText(String.valueOf(mathResult.getNumAnswer()));
        textViewTotalQuestion.setText(String.valueOf(mathResult.getTotalQuestion()));

        if (mathResult.isCheckAnswer()) {
            showCheckView();
        }
    }

    /**
     * Check result of expression
     */
    private void mathResultCheck() {
        if (mathResult.getNumAnswer() >= mathResult.getTotalQuestion()) {
            if (mathResult.getNumNegativeAnswer() <= 1) {
                mathResult.increaseNumLevel();
            } else {
                mathResult.decreaseNumLevel();
            }
            showResult();
        } else {
            generateExpression();
        }
    }

    /**
     *
     */
    private void generateExpression() {
        // generate new expression
        randomValue.generateExpression(mathResult.getNumLevel());
        list = randomValue.getList();
        mathResult.setCheckAnswer(false);
        fillingActivity();
    }

    /**
     * Show dialog with result of expression
     */
    private void showResult() {
        final AlertDialog.Builder dialogResult = new AlertDialog.Builder(this);
        View linerLayout = getLayoutInflater().inflate(R.layout.dialog_result, null);
        dialogResult.setView(linerLayout);
        dialogResult.setTitle(R.string.title_result);
        dialogResult.setPositiveButton(R.string.dialog_save_result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mathResult.cleanOutResult();
                fillingActivity();
            }
        });
        dialogResult.setNegativeButton(R.string.dialog_continue_result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), R.string.toast_continue, Toast.LENGTH_LONG).show();
                mathResult.setTotalQuestion(mathResult.getTotalQuestion() + 10);
                textViewTotalQuestion.setText(String.valueOf(mathResult.getTotalQuestion()));
                dialogInterface.cancel();
                fillingActivity();
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
        dialogResultPositiveAnswer.append(String.valueOf(mathResult.getNumPositiveAnswer()));
        dialogResultNegativeAnswer.setText(R.string.dialog_number_negative_answer);
        dialogResultNegativeAnswer.append(" ");
        dialogResultNegativeAnswer.append(String.valueOf(mathResult.getNumNegativeAnswer()));
        dialogResult.create();
        dialogResult.show();
    }

    /**
     *
     */
    private void showCheckView() {
        textViewAnswer.setText(R.string.check_answer);
        textViewAnswer.setTextColor(Color.GREEN);
        buttonProceed.setVisibility(View.VISIBLE);
        setEnabledButtonAnswers(false);
    }

    /**
     *
     */
    private void setEnabledButtonAnswers(boolean bool) {
        buttonAnswer1.setEnabled(bool);
        buttonAnswer2.setEnabled(bool);
        buttonAnswer3.setEnabled(bool);
    }
}

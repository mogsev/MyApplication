package com.mogsev.myapplication.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mogsev.myapplication.R;

import java.util.ArrayList;

/**
 * Created by zhenya on 24.06.2015.
 */
public abstract class MathTraining extends AppCompatActivity {
    private static final String TAG = "MathTraning";

    public static final String RANDOM_VALUE = "RANDOM_VALUE";
    public static final String MATH_RESULT = "MATH_RESULT";
    public static final String MATH_RESULTS = "MATH_RESULTS";
    public static final String START_TIMER = "START_TIMER";
    public static final String MATH_MULTI = "MATH_MULTI";

    protected ArrayList<Integer> list;
    protected RandomValue randomValue;
    protected MathResult mathResult;
    protected MathMulti mathMulti;

    private Integer answer;
    private Button buttonClick;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private TextView textViewAnswer;
    private TextView textViewExpression;
    private TextView textViewNumPositiveAnswer;
    private TextView textViewNumNegativeAnswer;
    private TextView textViewNumAnswer;
    private TextView textViewTotalQuestion;
    private TextView textViewLevel;
    private View fragmentProceed;

    private AlertDialog dialogResults;

    private Handler handler;

    // SharedPreferences
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    // ProgressBar
    private ProgressBar progressBar;
    private TimerProgress timer;
    protected int startTimer;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RANDOM_VALUE, randomValue);
        outState.putSerializable(MATH_RESULT, mathResult);
        outState.putSerializable(MATH_MULTI, mathMulti);
        if (timer != null) {
            outState.putInt(START_TIMER, timer.getStartTime());
        }
        if (dialogResults != null && dialogResults.isShowing()) {
            dialogResults.dismiss();
            outState.putBoolean("dialogResults", true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_math_operation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_results:
                showResults();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        savePreferences(mathResult.getOperation());
    }

    /**
     * Initialize view elements
     */
    protected void initElements() {
        // fragment_assignment content
        textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
        buttonAnswer1 = (Button) findViewById(R.id.answer1);
        buttonAnswer2 = (Button) findViewById(R.id.answer2);
        buttonAnswer3 = (Button) findViewById(R.id.answer3);
        textViewExpression = (TextView) findViewById(R.id.textViewExpression);

        // fragment_proceed content
        fragmentProceed = (View) getFragmentManager().findFragmentById(R.id.fragment_proceed).getView();

        // fragment_bottom content
        textViewNumNegativeAnswer = (TextView) findViewById(R.id.textViewNumNegativeAnswer);
        textViewNumPositiveAnswer = (TextView) findViewById(R.id.textViewNumPositiveAnswer);
        textViewLevel = (TextView) findViewById(R.id.textViewNumLevel);
        textViewNumAnswer = (TextView) findViewById(R.id.textViewNumAnswer);
        textViewTotalQuestion = (TextView) findViewById(R.id.textViewTotalQuestion);

        // SharedPreferences
        sharedPreferences = getApplicationContext().getSharedPreferences("MATH_RESULTS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //progressBar
        progressBar = (ProgressBar) findViewById(R.id.progressBarTime);

        // Handler
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    setEnabledButtonAnswers(false);
                    fragmentProceed.setVisibility(View.VISIBLE);
                }
            }
        };

        mathMulti = new MathMulti();
    }

    /**
     * Check user's answer
     *
     * @param view
     */
    public void onClickAnswer(View view) {
        timer.timerOff();
        //timer = null;
        buttonClick = (Button) view;
        answer = new Integer(buttonClick.getText().toString());
        textViewAnswer.setVisibility(View.VISIBLE);
        textViewExpression.setText(randomValue.getFullExpression());
        if (answer.compareTo(randomValue.getResult()) == 0) {
            textViewAnswer.setText(R.string.correct_answer);
            textViewAnswer.setTextColor(Color.GREEN);
            textViewNumPositiveAnswer.setText(String.valueOf(mathResult.increaseNumPositiveAnswers()));
        } else {
            textViewAnswer.setText(R.string.wrong_answer);
            textViewAnswer.setTextColor(Color.RED);
            textViewNumNegativeAnswer.setText(String.valueOf(mathResult.increaseNumNegativeAnswers()));
        }
        textViewNumAnswer.setText(String.valueOf(mathResult.increaseNumAnswers()));
        setEnabledButtonAnswers(false);
        //buttonProceed.setVisibility(View.VISIBLE);
        mathResult.setCheckAnswer(true);
        fragmentProceed.setVisibility(View.VISIBLE);
    }

    /**
     * Action when button "proceed" was pressed
     *
     * @param view
     */
    public void onClickProceed(View view) {
        if (mathResult.getAnswers() >= mathResult.getQuestions()) {
            showResult();
        } else {
            generateExpression();
        }
    }

    /**
     * data filling Activity
     */
    protected void fillingActivity() {
        // fragment_assignment content
        textViewAnswer.setText(R.string.title_answer);
        textViewAnswer.setTextColor(Color.BLACK);
        textViewExpression.setText(randomValue.getExpression());
        buttonAnswer1.setText(String.valueOf(list.get(0)));
        buttonAnswer2.setText(String.valueOf(list.get(1)));
        buttonAnswer3.setText(String.valueOf(list.get(2)));
        setEnabledButtonAnswers(true);

        // fragment_proceed content
        fragmentProceed.setVisibility(View.INVISIBLE);

        // fragment_bottom content
        textViewNumNegativeAnswer.setText(String.valueOf(mathResult.getNegativeAnswers()));
        textViewNumPositiveAnswer.setText(String.valueOf(mathResult.getPositiveAnswers()));
        textViewLevel.setText(String.valueOf(mathResult.getLevel()));
        textViewNumAnswer.setText(String.valueOf(mathResult.getAnswers()));
        textViewTotalQuestion.setText(String.valueOf(mathResult.getQuestions()));

        // Check result view
        if (mathResult.isCheckAnswer()) {
            showCheckView();
        } else {
            timer = new TimerProgress(startTimer);
            timer.start();
        }
    }

    /**
     * Generate expression
     */
    private void generateExpression() {
        // generate new expression
        if (randomValue.getOperation() == MathOperation.MULTI) {
            randomValue.generateExpression(mathResult.getLevel(), mathMulti);
        } else {
            randomValue.generateExpression(mathResult.getLevel());
        }
        list = randomValue.getListAnswer();
        mathResult.setCheckAnswer(false);
        fillingActivity();
    }

    /**
     * Show dialog with result of expression
     */
    protected void showResult() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View linerLayout = getLayoutInflater().inflate(R.layout.dialog_result, null);
        dialogBuilder.setView(linerLayout);
        dialogBuilder.setTitle(R.string.title_result);
        dialogBuilder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                checkLevelUp();
                mathResult.resultHandler();
                savePreferences(mathResult.getOperation());
                mathResult.cleanOutResult();
                generateExpression();
                fillingActivity();
            }
        });
        /*
        dialogBuilder.setNegativeButton(R.string.dialog_continue_result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), R.string.toast_continue, Toast.LENGTH_LONG).show();
                mathResult.setQuestions(mathResult.getQuestions() + 10);
                textViewTotalQuestion.setText(String.valueOf(mathResult.getQuestions()));
                dialogInterface.cancel();
                checkLevelUp();
                fillingActivity();
            }
        });
        */

        TextView dialogResultTotalQuestion = (TextView) linerLayout.findViewById(R.id.dialogResultTotalQuestion);
        TextView dialogResultPositiveAnswer = (TextView) linerLayout.findViewById(R.id.dialogResultPositiveAnswer);
        TextView dialogResultNegativeAnswer = (TextView) linerLayout.findViewById(R.id.dialogResultNegativeAnswer);
        dialogResultTotalQuestion.setText(R.string.dialog_total_number_question);
        dialogResultTotalQuestion.append(" ");
        dialogResultTotalQuestion.append(String.valueOf(mathResult.getQuestions()));
        dialogResultPositiveAnswer.setText(R.string.dialog_number_positive_answer);
        dialogResultPositiveAnswer.append(" ");
        dialogResultPositiveAnswer.append(String.valueOf(mathResult.getPositiveAnswers()));
        dialogResultNegativeAnswer.setText(R.string.dialog_number_negative_answer);
        dialogResultNegativeAnswer.append(" ");
        dialogResultNegativeAnswer.append(String.valueOf(mathResult.getNegativeAnswers()));
        dialogResults = dialogBuilder.create();
        dialogResults.show();
    }

    /**
     *
     */
    private void showCheckView() {
        textViewAnswer.setText(R.string.check_answer);
        textViewAnswer.setTextColor(Color.GREEN);
        //buttonProceed.setVisibility(View.VISIBLE);
        fragmentProceed.setVisibility(View.VISIBLE);
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

    /**
     *
     */
    private void checkLevelUp() {
        if (mathResult.getNegativeAnswers() <= 1) {
            mathResult.increaseNumLevel();
            textViewLevel.setText(String.valueOf(mathResult.getLevel()));
            Toast.makeText(getApplicationContext(), R.string.toast_next_level, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param operation
     */
    protected void savePreferences(int operation) {
        switch (operation) {
            case MathOperation.SUM:
                editor.putInt(getString(R.string.sum_score), mathResult.getScore());
                editor.putInt(getString(R.string.sum_level), mathResult.getLevel());
                editor.putInt(getString(R.string.sum_total_questions), mathResult.getTotalQuestions());
                editor.putInt(getString(R.string.sum_total_positive_answers), mathResult.getTotalPositiveAnswers());
                editor.putInt(getString(R.string.sum_total_negative_answers), mathResult.getTotalNegativeAnswers());
                break;
            case MathOperation.SUBTRACTION:
                editor.putInt(getString(R.string.subtraction_score), mathResult.getScore());
                editor.putInt(getString(R.string.subtraction_level), mathResult.getLevel());
                editor.putInt(getString(R.string.subtraction_total_questions), mathResult.getTotalQuestions());
                editor.putInt(getString(R.string.subtraction_total_positive_answers), mathResult.getTotalPositiveAnswers());
                editor.putInt(getString(R.string.subtraction_total_negative_answers), mathResult.getTotalNegativeAnswers());
                break;
            case MathOperation.MULTIPLICATION:
                editor.putInt(getString(R.string.multiplication_score), mathResult.getScore());
                editor.putInt(getString(R.string.multiplication_level), mathResult.getLevel());
                editor.putInt(getString(R.string.multiplication_total_questions), mathResult.getTotalQuestions());
                editor.putInt(getString(R.string.multiplication_total_positive_answers), mathResult.getTotalPositiveAnswers());
                editor.putInt(getString(R.string.multiplication_total_negative_answers), mathResult.getTotalNegativeAnswers());
                break;
            case MathOperation.DIVISION:
                editor.putInt(getString(R.string.division_score), mathResult.getScore());
                editor.putInt(getString(R.string.division_level), mathResult.getLevel());
                editor.putInt(getString(R.string.division_total_questions), mathResult.getTotalQuestions());
                editor.putInt(getString(R.string.division_total_positive_answers), mathResult.getTotalPositiveAnswers());
                editor.putInt(getString(R.string.division_total_negative_answers), mathResult.getTotalNegativeAnswers());
                break;
            case MathOperation.TABLE_MULTIPLICATION:
                editor.putInt(getString(R.string.table_multiplication_score), mathResult.getScore());
                editor.putInt(getString(R.string.table_multiplication_level), mathResult.getLevel());
                editor.putInt(getString(R.string.table_multiplication_questions), mathResult.getTotalQuestions());
                editor.putInt(getString(R.string.table_multiplication_positive_answers), mathResult.getTotalPositiveAnswers());
                editor.putInt(getString(R.string.table_multiplication_negative_answers), mathResult.getTotalNegativeAnswers());
                break;
            case MathOperation.MULTI:
                editor.putInt(getString(R.string.multi_score), mathResult.getScore());
                editor.putInt(getString(R.string.multi_level), mathResult.getLevel());
                editor.putInt(getString(R.string.multi_questions), mathResult.getTotalQuestions());
                editor.putInt(getString(R.string.multi_positive_answers), mathResult.getTotalPositiveAnswers());
                editor.putInt(getString(R.string.multi_negative_answers), mathResult.getTotalNegativeAnswers());
                editor.putBoolean(getString(R.string.multi_sum), mathMulti.isSum());
                editor.putBoolean(getString(R.string.multi_subtraction), mathMulti.isSubtraction());
                editor.putBoolean(getString(R.string.multi_multiplication), mathMulti.isMultiplication());
                editor.putBoolean(getString(R.string.multi_division), mathMulti.isDivision());
                break;
        }
        editor.commit();
    }

    /**
     * @param operation
     */
    protected void loadPreferences(int operation) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(MATH_RESULTS, Context.MODE_PRIVATE);
        switch (operation) {
            case MathOperation.SUM:
                mathResult.setScore(sharedPreferences.getInt(getString(R.string.sum_score), 0));
                mathResult.setLevel(sharedPreferences.getInt(getString(R.string.sum_level), 1));
                mathResult.setTotalQuestions(sharedPreferences.getInt(getString(R.string.sum_total_questions), 0));
                mathResult.setTotalPositiveAnswers(sharedPreferences.getInt(getString(R.string.sum_total_positive_answers), 0));
                mathResult.setTotalNegativeAnswers(sharedPreferences.getInt(getString(R.string.sum_total_negative_answers), 0));
                break;
            case MathOperation.SUBTRACTION:
                mathResult.setScore(sharedPreferences.getInt(getString(R.string.subtraction_score), 0));
                mathResult.setLevel(sharedPreferences.getInt(getString(R.string.subtraction_level), 1));
                mathResult.setTotalQuestions(sharedPreferences.getInt(getString(R.string.subtraction_total_questions), 0));
                mathResult.setTotalPositiveAnswers(sharedPreferences.getInt(getString(R.string.subtraction_total_positive_answers), 0));
                mathResult.setTotalNegativeAnswers(sharedPreferences.getInt(getString(R.string.subtraction_total_negative_answers), 0));
                break;
            case MathOperation.MULTIPLICATION:
                mathResult.setScore(sharedPreferences.getInt(getString(R.string.multiplication_score), 0));
                mathResult.setLevel(sharedPreferences.getInt(getString(R.string.multiplication_level), 1));
                mathResult.setTotalQuestions(sharedPreferences.getInt(getString(R.string.multiplication_total_questions), 0));
                mathResult.setTotalPositiveAnswers(sharedPreferences.getInt(getString(R.string.multiplication_total_positive_answers), 0));
                mathResult.setTotalNegativeAnswers(sharedPreferences.getInt(getString(R.string.multiplication_total_negative_answers), 0));
                break;
            case MathOperation.DIVISION:
                mathResult.setScore(sharedPreferences.getInt(getString(R.string.division_score), 0));
                mathResult.setLevel(sharedPreferences.getInt(getString(R.string.division_level), 1));
                mathResult.setTotalQuestions(sharedPreferences.getInt(getString(R.string.division_total_questions), 0));
                mathResult.setTotalPositiveAnswers(sharedPreferences.getInt(getString(R.string.division_total_positive_answers), 0));
                mathResult.setTotalNegativeAnswers(sharedPreferences.getInt(getString(R.string.division_total_negative_answers), 0));
                break;
            case MathOperation.TABLE_MULTIPLICATION:
                mathResult.setScore(sharedPreferences.getInt(getString(R.string.table_multiplication_score), 0));
                mathResult.setLevel(sharedPreferences.getInt(getString(R.string.table_multiplication_level), 1));
                mathResult.setTotalQuestions(sharedPreferences.getInt(getString(R.string.table_multiplication_questions), 0));
                mathResult.setTotalPositiveAnswers(sharedPreferences.getInt(getString(R.string.table_multiplication_positive_answers), 0));
                mathResult.setTotalNegativeAnswers(sharedPreferences.getInt(getString(R.string.table_multiplication_negative_answers), 0));
                break;
            case MathOperation.MULTI:
                mathResult.setScore(sharedPreferences.getInt(getString(R.string.multi_score), 0));
                mathResult.setLevel(sharedPreferences.getInt(getString(R.string.multi_level), 1));
                mathResult.setTotalQuestions(sharedPreferences.getInt(getString(R.string.multi_questions), 0));
                mathResult.setTotalPositiveAnswers(sharedPreferences.getInt(getString(R.string.multi_positive_answers), 0));
                mathResult.setTotalNegativeAnswers(sharedPreferences.getInt(getString(R.string.multi_negative_answers), 0));
                mathMulti.setSum(sharedPreferences.getBoolean(getString(R.string.multi_sum), false));
                mathMulti.setSubtraction(sharedPreferences.getBoolean(getString(R.string.multi_subtraction), false));
                mathMulti.setMultiplication(sharedPreferences.getBoolean(getString(R.string.multi_multiplication), false));
                mathMulti.setDivision(sharedPreferences.getBoolean(getString(R.string.multi_division), false));
                break;
        }
    }

    /**
     *
     */
    private class TimerProgress {
        private boolean isTimer;
        private int time;
        private int startTime;
        private int endTime;
        private Thread timerThread;

        TimerProgress() {
            startTime = 0;
            isTimer = true;
            switch (mathResult.getLevel()) {
                case 2:
                    time = 900;
                    break;
                case 3:
                    time = 800;
                    break;
                case 4:
                    time = 700;
                    break;
                case 5:
                    time = 900;
                    break;
                case 7:
                    time = 900;
                    break;
                case 8:
                    time = 800;
                    break;
                case 10:
                    time = 900;
                    break;
                default:
                    time = 1000;
                    break;
            }
            endTime = time;
            progressBar.setMax(time);
        }

        TimerProgress(int startTime) {
            this();
            this.startTime = startTime;
        }

        public void timerOff() {
            isTimer = false;
        }

        public int getStartTime() {
            return startTime;
        }

        public void start() {
            timerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (startTime < endTime) {
                        startTime++;
                        progressBar.setProgress(startTime);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //if (!isTimer) {
                        //    break;
                        //}
                        if (mathResult.isCheckAnswer()) {
                            break;
                        }
                    }
                    close();
                }
            });
            timerThread.start();
        }

        private void close() {
            progressBar.setProgress(0);
            startTimer = 0;
            handler.sendEmptyMessage(0);
            timer = null;
        }
    }

    private void showResults() {
        final AlertDialog.Builder dialogResults = new AlertDialog.Builder(this);

        View view = this.getLayoutInflater().inflate(R.layout.dialog_results_operation, null);

        TextView score = (TextView) view.findViewById(R.id.score);
        TextView level = (TextView) view.findViewById(R.id.level);
        TextView questions = (TextView) view.findViewById(R.id.totalQuestions);
        TextView positive = (TextView) view.findViewById(R.id.totalPositiveAnswers);
        TextView negative = (TextView) view.findViewById(R.id.totalNegativeAnswers);

        score.setText(R.string.result_score);
        level.setText(R.string.result_level);
        questions.setText(R.string.result_questions);
        positive.setText(R.string.result_positive_answers);
        negative.setText(R.string.result_negative_answers);

        score.append(" " + String.valueOf(mathResult.getScore()));
        level.append(" " + String.valueOf(mathResult.getLevel()));
        questions.append(" " + String.valueOf(mathResult.getTotalQuestions()));
        positive.append(" " + String.valueOf(mathResult.getTotalPositiveAnswers()));
        negative.append(" " + String.valueOf(mathResult.getTotalNegativeAnswers()));

        dialogResults.setView(view);
        dialogResults.create();
        dialogResults.show();
    }
}

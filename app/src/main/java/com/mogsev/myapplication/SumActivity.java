package com.mogsev.myapplication;

import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.mogsev.util.RandomValue;
import java.util.ArrayList;


public class SumActivity extends ActionBarActivity {
    private static final String LIST_RANDOM = "LIST_RANDOM";
    private static final String LEVEL = "LEVEL";
    private static final String RESULT = "RESULT";
    private Integer answer, result;
    private int level;
    private ArrayList<Integer> list;
    Button buttonClick;
    Button answer1;
    Button answer2;
    Button answer3;
    Button buttonProceed;
    TextView textViewAnswer;
    TextView textViewExpression;
    RandomValue randomValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);

        //The application was lunched?
        if (savedInstanceState == null) {
            randomValue = new RandomValue(20, 0);
            list = randomValue.getList();
        } else {
            list = savedInstanceState.getIntegerArrayList(LIST_RANDOM);
            level = savedInstanceState.getInt(LEVEL);
            result = savedInstanceState.getInt(RESULT);
            randomValue = new RandomValue(level, 0);
        }

        //Initialize links for objects
        textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        buttonProceed = (Button) findViewById(R.id.buttonProceed);
        textViewExpression = (TextView) findViewById(R.id.textViewExpression);
        if (level == 0) {
            level = 10;
        }

        //filling Activity
        onSum();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_sum, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LEVEL, level);
        outState.putInt(RESULT, result);
        outState.putIntegerArrayList(LIST_RANDOM, list);
    }

    /**
     *
     * @param view
     */
    public void onClickAnswer(View view) {
        buttonClick = (Button) view;
        answer = new Integer(buttonClick.getText().toString());
        textViewAnswer.setVisibility(View.VISIBLE);
        if (answer.compareTo(result) == 0) {
            textViewAnswer.setText(R.string.correct_answer);
            textViewAnswer.setTextColor(Color.GREEN);
        } else {
            textViewAnswer.setText(R.string.wrong_answer);
            textViewAnswer.append(" ");
            textViewAnswer.append(result.toString());
            textViewAnswer.setTextColor(Color.RED);
        }
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        buttonProceed.setVisibility(View.VISIBLE);
        list = randomValue.getList();
        result = randomValue.getResult();
    }

    /**
     *
     * @param view
     */
    public void onClickProceed(View view){
        onSum();
    }

    /**
     *
     */
    public void onSum() {
        buttonProceed.setVisibility(View.INVISIBLE);
        textViewAnswer.setText(R.string.title_answer);
        textViewAnswer.setTextColor(Color.BLACK);

        //ArrayList<Integer> list = randomValue.getList();
        textViewExpression.setText(randomValue.getExpression());
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer1.setText(String.valueOf(list.get(0)));
        answer2.setText(String.valueOf(list.get(1)));
        answer3.setText(String.valueOf(list.get(2)));
    }
}

package com.mogsev.myapplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class SumActivity extends ActionBarActivity {
    private Integer number1, number2, result;
    private Random random;
    private Integer answer;
    Button buttonClick;
    Button answer1;
    Button answer2;
    Button answer3;
    Button buttonProceed;
    TextView textViewResult;
    TextView textViewExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);

        //Initialize links for objects
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        buttonProceed = (Button) findViewById(R.id.buttonProceed);
        textViewExpression = (TextView) findViewById(R.id.textViewExpression);
        random = new Random();

        //filling Activity
        onSum();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sum, menu);
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

    /**
     *
     * @param view
     */
    public void onClickAnswer(View view) {
        buttonClick = (Button) view;
        answer = new Integer(buttonClick.getText().toString());
        textViewResult.setVisibility(View.VISIBLE);
        if (answer.compareTo(result) == 0) {
            textViewResult.setText(R.string.correct_answer);
            textViewResult.setTextColor(Color.GREEN);
        } else {
            textViewResult.setText(R.string.wrong_answer);
            textViewResult.append(" ");
            textViewResult.append(result.toString());
            textViewResult.setTextColor(Color.RED);
        }
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        buttonProceed.setVisibility(View.VISIBLE);
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
     * @return
     */
    private int[] generateExpression() {
        number1 = random.nextInt(10);
        number2 = random.nextInt(10);
        result = number1 + number2;

        int[] box = {random.nextInt(10), random.nextInt(10), result};
        for (int i = 0; i < random.nextInt(10); i++) {
            int num = box[0];
            box[0] = box[1];
            box[1] = box[2];
            box[2] = num;
        }
        return box;
    }

    /**
     *
     */
    public void onSum() {
        buttonProceed.setVisibility(View.INVISIBLE);
        textViewResult.setVisibility(View.INVISIBLE);

        int[] box = generateExpression();
        textViewExpression.setText(number1 + " + " + number2);
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer1.setText(String.valueOf(box[0]));
        answer2.setText(String.valueOf(box[1]));
        answer3.setText(String.valueOf(box[2]));
    }
}

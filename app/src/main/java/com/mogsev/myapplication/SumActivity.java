package com.mogsev.myapplication;

import android.content.DialogInterface;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
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

    public void onClickAnswer(View view) {
        Button test = (Button) view;
        Integer press = new Integer(test.getText().toString());
        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewResult.setVisibility(View.VISIBLE);
        if (press.compareTo(result) == 0) {
            textViewResult.setText("Правильный ответ");
        } else {
            textViewResult.setText("Ошибка, правильный ответ: " + result);
        }
        Button answer1 = (Button) findViewById(R.id.answer1);
        answer1.setEnabled(false);
        Button answer2 = (Button) findViewById(R.id.answer2);
        answer2.setEnabled(false);
        Button answer3 = (Button) findViewById(R.id.answer3);
        answer3.setEnabled(false);
        Button button = (Button) findViewById(R.id.buttonProceed);
        button.setVisibility(View.VISIBLE);
    }

    public void onClickProceed(View view){
        onSum();
    }

    public void onSum() {
        Button button = (Button) findViewById(R.id.buttonProceed);
        button.setVisibility(View.INVISIBLE);
        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewResult.setVisibility(View.INVISIBLE);
        random = new Random();
        number1 = random.nextInt(10);
        number2 = random.nextInt(10);
        result = number1 + number2;

        TextView textViewExpression = (TextView) findViewById(R.id.textViewExpression);
        Button answer1 = (Button) findViewById(R.id.answer1);
        Button answer2 = (Button) findViewById(R.id.answer2);
        Button answer3 = (Button) findViewById(R.id.answer3);
        textViewExpression.setText(number1 + " + " + number2);

        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer1.setText(String.valueOf(number1));
        answer2.setText(String.valueOf(number2));
        answer3.setText(String.valueOf(result));
    }
}

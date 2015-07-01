package com.mogsev.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mogsev.util.MathOperation;
import com.mogsev.util.MathTraining;
import com.mogsev.util.RandomValue;


public class TableMultiplicationActivity extends MathTraining {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_multiplication);

        //The application was lunched?
        if (savedInstanceState == null) {
            randomValue = new RandomValue(10, MathOperation.TABLE_MULTIPLICATION);
            randomValue.generateExpression();
            list = randomValue.getList();
        } else {
            randomValue = (RandomValue) savedInstanceState.get(RANDOM_VALUE);
            list = randomValue.getList();
        }

        //Initialize links for objects
        initElements();

        //filling Activity
        fillingActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table_multiplication, menu);
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
}

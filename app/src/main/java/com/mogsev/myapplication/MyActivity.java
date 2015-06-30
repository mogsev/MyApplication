package com.mogsev.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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
     * Handle clicks on button
     * @param view
     */
    public void startActivityTraining(View view) {
        switch (view.getId()) {
            case R.id.button_sum:
                startActivity(new Intent(this, SumActivity.class));
                break;
            case R.id.button_subtraction:
                startActivity(new Intent(this, SubtractionActivity.class));
                break;
            case R.id.button_multiplication:
                startActivity(new Intent(this, MultiplicationActivity.class));
                break;
            case R.id.button_division:
                startActivity(new Intent(this, DivisionActivity.class));
                break;
            case R.id.button_table_multiplication:
                //startActivity(new Intent(this, ));
                break;
        }
    }
}

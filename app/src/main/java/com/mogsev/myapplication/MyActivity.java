package com.mogsev.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mogsev.myapplication.activity.ChoiceActivity;
import com.mogsev.myapplication.activity.DivisionActivity;
import com.mogsev.myapplication.activity.MultiplicationActivity;
import com.mogsev.myapplication.activity.ResultsActivity;
import com.mogsev.myapplication.activity.SubtractionActivity;
import com.mogsev.myapplication.activity.SumActivity;
import com.mogsev.myapplication.activity.TableMultiplicationActivity;

public class MyActivity extends AppCompatActivity {

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
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_results:
                startActivity(new Intent(this, ResultsActivity.class));
                break;
            case R.id.action_about:
                onActionMenuAbout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Create dialog about app
     */
    private void onActionMenuAbout() {
        AlertDialog.Builder actionAbout = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        actionAbout.setView(layoutInflater.inflate(R.layout.dialog_about, null))
                .create()
                .show();
    }

    /**
     * Handle clicks on button
     *
     * @param view
     */
    public void startActivity(View view) {
        switch (view.getId()) {
            case R.id.training_sum:
                startActivity(new Intent(this, SumActivity.class));
                break;
            case R.id.training_subtraction:
                startActivity(new Intent(this, SubtractionActivity.class));
                break;
            case R.id.training_multiplication:
                startActivity(new Intent(this, MultiplicationActivity.class));
                break;
            case R.id.training_division:
                startActivity(new Intent(this, DivisionActivity.class));
                break;
            case R.id.training_table_multiplication:
                startActivity(new Intent(this, TableMultiplicationActivity.class));
                break;
            case R.id.training_multi:
                startActivity(new Intent(this, ChoiceActivity.class));
                break;
            case R.id.button_results:
                startActivity(new Intent(this, ResultsActivity.class));
                break;
        }
    }
}

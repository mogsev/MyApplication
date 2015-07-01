package com.mogsev.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MyActivity extends ActionBarActivity {

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
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_about :
                onActionMenuAbout();
                break;
            case R.id.action_level :
                onActionMenuLevel();
                break;
        }
        /*
        if (id == R.id.action_settings) {

            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    /**
     * Create dialog of level menu
     */
    private void onActionMenuLevel() {

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
                startActivity(new Intent(this, TableMultiplicationActivity.class));
                break;
        }
    }
}

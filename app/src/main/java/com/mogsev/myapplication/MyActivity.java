package com.mogsev.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
                onActionAbout();
                break;
            case R.id.action_level :

                break;
        }
        /*
        if (id == R.id.action_settings) {

            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    private void onActionAbout() {
        AlertDialog.Builder actionAbout = new AlertDialog.Builder(getApplicationContext());
        actionAbout.setTitle(R.string.action_about)
                .setMessage(R.string.dialog_about_message);
        actionAbout.create();
        actionAbout.show();
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

package com.mogsev.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import com.mogsev.myapplication.R;

public class ChoiceActivity extends AppCompatActivity {
    private CheckBox checkSum;
    private CheckBox checkSubtraction;
    private CheckBox checkMultiplication;
    private CheckBox checkDivision;

    // SharedPreferences
    protected SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        checkSum = (CheckBox) this.findViewById(R.id.checkBoxSum);
        checkSubtraction = (CheckBox) this.findViewById(R.id.checkBoxSubtraction);
        checkMultiplication = (CheckBox) this.findViewById(R.id.checkBoxMultiplication);
        checkDivision = (CheckBox) this.findViewById(R.id.checkBoxDivision);
    }

    @Override
    protected void onPause() {
        super.onPause();
        editor.putBoolean(getString(R.string.multi_sum), checkSum.isChecked());
        editor.putBoolean(getString(R.string.multi_subtraction), checkSubtraction.isChecked());
        editor.putBoolean(getString(R.string.multi_multiplication), checkMultiplication.isChecked());
        editor.putBoolean(getString(R.string.multi_division), checkDivision.isChecked());
        editor.commit();
    }

    public void startActivity() {
        Intent intent = new Intent(this, MultiActivity.class);
        startActivity(intent);
    }
}

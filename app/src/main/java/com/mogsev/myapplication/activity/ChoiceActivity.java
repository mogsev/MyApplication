package com.mogsev.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.mogsev.myapplication.R;

public class ChoiceActivity extends AppCompatActivity {
    private CheckBox checkSum;
    private CheckBox checkSubtraction;
    private CheckBox checkMultiplication;
    private CheckBox checkDivision;

    // SharedPreferences
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        // SharedPreferences
        sharedPreferences = getApplicationContext().getSharedPreferences("MATH_RESULTS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        checkSum = (CheckBox) this.findViewById(R.id.checkBoxSum);
        checkSubtraction = (CheckBox) this.findViewById(R.id.checkBoxSubtraction);
        checkMultiplication = (CheckBox) this.findViewById(R.id.checkBoxMultiplication);
        checkDivision = (CheckBox) this.findViewById(R.id.checkBoxDivision);

        checkSum.setChecked(sharedPreferences.getBoolean(getString(R.string.multi_sum), false));
        checkSubtraction.setChecked(sharedPreferences.getBoolean(getString(R.string.multi_subtraction), false));
        checkMultiplication.setChecked(sharedPreferences.getBoolean(getString(R.string.multi_multiplication), false));
        checkDivision.setChecked(sharedPreferences.getBoolean(getString(R.string.multi_division), false));
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

    public void startActivity(View view) {
        if (view.getId() == R.id.btn_begin_multi) {
            if (!checkSum.isChecked() && !checkSubtraction.isChecked()
                    && !checkMultiplication.isChecked() && !checkDivision.isChecked()) {
                Toast.makeText(getApplicationContext(), R.string.toast_check_operation, Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MultiActivity.class);
                startActivity(intent);
            }
        }
    }

}

package com.mogsev.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mogsev.myapplication.R;
import com.mogsev.myapplication.util.MathOperation;

public class ResultsActivity extends AppCompatActivity {
    private ScrollView scrollView;
    private LayoutInflater layoutInflater;

    // SharedPreferences
    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        sharedPreferences = this.getSharedPreferences("MATH_RESULTS", Context.MODE_PRIVATE);

        scrollView = (ScrollView) this.findViewById(R.id.scrollViewResult);
        layoutInflater = this.getLayoutInflater();

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView((View) getViewResults(MathOperation.SUM));
        linearLayout.addView((View) getViewResults(MathOperation.SUBTRACTION));
        linearLayout.addView((View) getViewResults(MathOperation.MULTIPLICATION));
        linearLayout.addView((View) getViewResults(MathOperation.DIVISION));
        linearLayout.addView((View) getViewResults(MathOperation.TABLE_MULTIPLICATION));

        scrollView.addView(linearLayout);
    }

    private View getViewResults(int operation) {
        View view = (View) layoutInflater.inflate(R.layout.result, null);
        TextView name = (TextView) view.findViewById(R.id.textViewName);
        TextView score = (TextView) view.findViewById(R.id.textViewScore);
        TextView level = (TextView) view.findViewById(R.id.textViewLevel);
        TextView questions = (TextView) view.findViewById(R.id.textViewQuestions);
        TextView positive = (TextView) view.findViewById(R.id.textViewPositiveAnswers);
        TextView negative = (TextView) view.findViewById(R.id.textViewNegativeAnswers);

        score.setText(R.string.result_score);
        level.setText(R.string.result_level);
        questions.setText(R.string.result_questions);
        positive.setText(R.string.result_positive_answers);
        negative.setText(R.string.result_negative_answers);

        switch (operation) {
            case MathOperation.SUM:
                name.setText(R.string.title_sum);
                score.append(" " + sharedPreferences.getInt(getString(R.string.sum_score), 0));
                level.append(" " + sharedPreferences.getInt(getString(R.string.sum_level), 1));
                questions.append(" " + sharedPreferences.getInt(getString(R.string.sum_total_questions), 0));
                positive.append(" " + sharedPreferences.getInt(getString(R.string.sum_total_positive_answers), 0));
                negative.append(" " + sharedPreferences.getInt(getString(R.string.sum_total_negative_answers), 0));
                break;
            case MathOperation.SUBTRACTION:
                name.setText(R.string.title_subtraction);
                score.append(" " + sharedPreferences.getInt(getString(R.string.subtraction_score), 0));
                level.append(" " + sharedPreferences.getInt(getString(R.string.subtraction_level), 1));
                questions.append(" " + sharedPreferences.getInt(getString(R.string.subtraction_total_questions), 0));
                positive.append(" " + sharedPreferences.getInt(getString(R.string.subtraction_total_positive_answers), 0));
                negative.append(" " + sharedPreferences.getInt(getString(R.string.subtraction_total_negative_answers), 0));
                break;
            case MathOperation.MULTIPLICATION:
                name.setText(R.string.title_multiplication);
                score.append(" " + sharedPreferences.getInt(getString(R.string.multiplication_score), 0));
                level.append(" " + sharedPreferences.getInt(getString(R.string.multiplication_level), 1));
                questions.append(" " + sharedPreferences.getInt(getString(R.string.multiplication_total_questions), 0));
                positive.append(" " + sharedPreferences.getInt(getString(R.string.multiplication_total_positive_answers), 0));
                negative.append(" " + sharedPreferences.getInt(getString(R.string.multiplication_total_negative_answers), 0));
                break;
            case MathOperation.DIVISION:
                name.setText(R.string.title_division);
                score.append(" " + sharedPreferences.getInt(getString(R.string.division_score), 0));
                level.append(" " + sharedPreferences.getInt(getString(R.string.division_level), 1));
                questions.append(" " + sharedPreferences.getInt(getString(R.string.division_total_questions), 0));
                positive.append(" " + sharedPreferences.getInt(getString(R.string.division_total_positive_answers), 0));
                negative.append(" " + sharedPreferences.getInt(getString(R.string.division_total_negative_answers), 0));
                break;
            case MathOperation.TABLE_MULTIPLICATION:
                name.setText(R.string.title_table_multiplication);
                score.append(" " + sharedPreferences.getInt(getString(R.string.table_multiplication_score), 0));
                level.append(" " + sharedPreferences.getInt(getString(R.string.table_multiplication_level), 1));
                questions.append(" " + sharedPreferences.getInt(getString(R.string.table_multiplication_questions), 0));
                positive.append(" " + sharedPreferences.getInt(getString(R.string.table_multiplication_positive_answers), 0));
                negative.append(" " + sharedPreferences.getInt(getString(R.string.table_multiplication_negative_answers), 0));
                break;
            case MathOperation.MULTI:

                break;
        }
        return view;
    }
}

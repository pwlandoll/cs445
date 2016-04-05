package edu.jcu.plandoll16.homework7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

public class FlashCardActivity extends AppCompatActivity {
    private EditText[] problemEditTexts;
    private double time;
    private Integer[] answers;
    private LinearLayout[] problemLayouts;
    private TextView[] problemTextViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        problemEditTexts = new EditText[3];
        problemLayouts = new LinearLayout[3];
        problemTextViews = new TextView[3];

        problemEditTexts[0] = (EditText)findViewById(R.id.problem1EditText);
        problemEditTexts[1] = (EditText)findViewById(R.id.problem2EditText);
        problemEditTexts[2] = (EditText)findViewById(R.id.problem3EditText);

        // Connect to the linear layouts to set each group to visible for each problem
        problemLayouts[0] = (LinearLayout)findViewById(R.id.problem1Layout);
        problemLayouts[1] = (LinearLayout)findViewById(R.id.problem2Layout);
        problemLayouts[2] = (LinearLayout)findViewById(R.id.problem3Layout);

        problemTextViews[0] = (TextView)findViewById(R.id.problem1TextView);
        problemTextViews[1] = (TextView)findViewById(R.id.problem2TextView);
        problemTextViews[2] = (TextView)findViewById(R.id.problem3TextView);

        Random r = new Random();
        Integer a, b;
        answers = new Integer[3];
        for (int i = 0; i < 3; i++) {
            a = r.nextInt(10);
            b = r.nextInt(10);
            answers[i] = a * b;
            problemTextViews[i].setText(a.toString() + " * " + b.toString() + ": ");

        }

        Intent mIntent = this.getIntent();
        Bundle mBundle = mIntent.getExtras();
        int difficulty = mBundle.getInt("edu.jcu.plandoll16.Homework7.difficulty");
        time = Math.pow(2, difficulty);
    }

    public void startButtonClick(View view) {
        int score = 0;

        this.getIntent().putExtra("edu.jcu.plandoll16.Homework7.correct", score);
        setResult(RESULT_OK, this.getIntent());
        finish();
    }

}

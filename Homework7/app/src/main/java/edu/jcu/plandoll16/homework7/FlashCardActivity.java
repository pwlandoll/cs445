package edu.jcu.plandoll16.homework7;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class FlashCardActivity extends AppCompatActivity {
    private static Button startButton;
    private static EditText[] problemEditTexts;
    private int time;
    private static int problemCounter, score, colorRed, colorGreen;
    private static Integer[] answers;
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

        startButton = (Button) findViewById(R.id.startButton);
        colorGreen = getResources().getColor(R.color.green);
        colorRed = getResources().getColor(R.color.red);

        // Calculate the problems ahead of time, and only set the layouts to visible later
        Random r = new Random();
        Integer a, b;
        String problem;
        answers = new Integer[3];
        for (int i = 0; i < 3; i++) {
            a = r.nextInt(10);
            b = r.nextInt(10);
            answers[i] = a * b;
            problem = a.toString() + " * " + b.toString() + ": ";
            problemTextViews[i].setText(problem);
        }

        // Used to keep track of the current problem
        problemCounter = 0;
        // Keep track of the score
        score = 0;

        // Get the difficulty, and calculate the time between each problem
        Intent mIntent = this.getIntent();
        Bundle mBundle = mIntent.getExtras();
        int difficulty = mBundle.getInt("edu.jcu.plandoll16.Homework7.difficulty");
        switch (difficulty) {
            case 1: time = 2; break;
            case 2: time = 4; break;
            case 3: time = 8; break;
            default: time = 8; break;
        }
    }

    public void startButtonClick(View view) {
        if (problemCounter == 3) {
            returnScore();
            return;
        }
        startButton.setEnabled(false);
        problemLayouts[problemCounter].setVisibility(View.VISIBLE);
        // Code for auto-focusing on the EditText
        problemEditTexts[problemCounter].requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(problemEditTexts[problemCounter], InputMethodManager.SHOW_IMPLICIT);

        try {
            Intent timerIntent = new Intent(this, TimerBroadcastReceiver.class);
            PendingIntent timerPendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, timerIntent, 0);
            AlarmManager timerAlarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            timerAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000 * time), timerPendingIntent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void returnScore() {
        this.getIntent().putExtra("edu.jcu.plandoll16.Homework7.correct", score);
        setResult(RESULT_OK, this.getIntent());
        finish();
    }

    // This class listens for Broadcast that says Alarm happened
    public static class TimerBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            startButton.setEnabled(true);
            int answer = 0;
            try {
                answer = Integer.parseInt(problemEditTexts[problemCounter].getText().toString());
            } catch (Exception ex) {
            }
            if (answer == answers[problemCounter]) {
                problemEditTexts[problemCounter].setTextColor(colorGreen);
                score++;
            } else {
                problemEditTexts[problemCounter].setTextColor(colorRed);
            }
            problemCounter++;
        }
    }

}

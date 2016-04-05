package edu.jcu.plandoll16.homework7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int difficulty;
    public RadioGroup difficultyRadioGroup;
    public TextView percentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        difficultyRadioGroup = (RadioGroup)findViewById(R.id.difficultyRadioGroup);
        percentTextView = (TextView)findViewById(R.id.percentTextView);

        difficulty = -1;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            try {
                // Defaults to -3 so that the score displays as -1
                float correct = intent.getIntExtra("edu.jcu.plandoll16.Homework7.correct", -3) / 3;
                String percent = getResources().getString(R.string.percent) + ": " + correct;
                percentTextView.setText(percent);
            } catch (Exception ex) {
                Toast.makeText(this, "There was an error fetching the score", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void beginButtonClick(View view) {
        int selected = difficultyRadioGroup.getCheckedRadioButtonId();
        if (selected == R.id.easyRadioButton) {
            difficulty = 3;
        } else if (selected == R.id.mediumRadioButton) {
            difficulty = 2;
        } else if (selected == R.id.hardRadioButton) {
            difficulty = 1;
        }
        if (difficulty != -1) {
            Intent mIntent = new Intent(view.getContext(), FlashCardActivity.class);
            mIntent.putExtra("edu.jcu.plandoll16.Homework7.difficulty", difficulty);
            startActivityForResult(mIntent, 0);
        } else {
            Toast.makeText(this, "There was an error choosing difficulty", Toast.LENGTH_LONG).show();
        }
    }
}

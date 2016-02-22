package kirsch.jcu.edu.quiz3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText guessEditText;
    private TextView response;
    private int targetNumber, guess;
    private Random randomGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guessEditText = (EditText)findViewById(R.id.guessEditText);
        guessEditText.addTextChangedListener(newNumber);
        response = (TextView)findViewById(R.id.responseTextView);
        randomGenerator = new Random();
        targetNumber = randomGenerator.nextInt(99) + 1;
    }

    private TextWatcher newNumber = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                guess = Integer.parseInt(s.toString());
            } catch (NumberFormatException e) {
                // If there's nothing, don't trigger any changes
                guess = -1;
            }
            if (guess == 0) {
                // Choose new number, set to orange
                targetNumber = randomGenerator.nextInt(99) + 1;
                response.setBackgroundColor(getResources().getColor(R.color.orange));
            } else if (guess < targetNumber) {
                // Set to red
                response.setBackgroundColor(getResources().getColor(R.color.red));
            } else if (guess > targetNumber) {
                // Set to blue
                response.setBackgroundColor(getResources().getColor(R.color.blue));
            } else if (guess == targetNumber) {
                // Set to green
                response.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}

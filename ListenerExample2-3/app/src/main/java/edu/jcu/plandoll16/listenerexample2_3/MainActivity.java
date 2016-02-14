package edu.jcu.plandoll16.listenerexample2_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText input;     // Contains age of the cat, input from user
    private TextView output;    // Corresponding human age for the cat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.inputEditText);
        output = (TextView)findViewById(R.id.outputTextView);
        input.addTextChangedListener(ageTextListener);
    }

    // Add a listener
    private TextWatcher ageTextListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                double age = Double.parseDouble(input.getText().toString());
                age = ((int)(100*age+0.5))/100;
                Double convertedAge = convert(age);
                output.setText(convertedAge.toString());
            } catch (NumberFormatException nfe) {
                output.setText(getResources().getString(R.string.Error));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public Double convert(double catYears) {
        if (catYears <= 1) {
            return 15.0;
        } else if (catYears == 2) {
            return 10 + convert(catYears - 1);
        } else {
            return 4 + convert(catYears - 1);
        }
    }

}

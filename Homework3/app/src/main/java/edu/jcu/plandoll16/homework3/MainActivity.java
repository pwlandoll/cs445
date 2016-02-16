package edu.jcu.plandoll16.homework3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Integer heightMin, heightMax, widthMin, widthMax;
    private TextView heightMinText, heightMaxText, widthMinText, widthMaxText, colorBox;
    private RadioGroup buttonGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heightMin = 50;
        heightMax = 400;
        widthMin = 50;
        widthMax = 400;
        heightMinText = (TextView)findViewById(R.id.heightMinTextView);
        heightMaxText = (TextView)findViewById(R.id.heightMaxTextView);
        widthMinText = (TextView)findViewById(R.id.widthMinTextView);
        widthMaxText = (TextView)findViewById(R.id.widthMaxTextView);
        heightMinText.setText(heightMin.toString());
        heightMaxText.setText(heightMax.toString());
        widthMinText.setText(widthMin.toString());
        widthMaxText.setText(widthMax.toString());
        buttonGroup = (RadioGroup)findViewById(R.id.radioGroup);
    }
}

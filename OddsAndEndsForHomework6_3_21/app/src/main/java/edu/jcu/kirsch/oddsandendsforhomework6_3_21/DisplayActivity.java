package edu.jcu.kirsch.oddsandendsforhomework6_3_21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    private ArrayList<Integer> theNumbers;
    private LinearLayout display;
    private Intent thisIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        display = (LinearLayout)findViewById(R.id.numbersLinearLayout);
        thisIntent = this.getIntent();
        theNumbers = thisIntent.getIntegerArrayListExtra("MainNumbers");
        // Dynamically create TextViews for each of the numbers
        for (Integer number:theNumbers)
        {
            TextView theText = new TextView(getBaseContext());
            theText.setText(number.toString());
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(200,200);
            textParams.setMargins(10,10,10,10);
            theText.setBackgroundColor(getResources().getColor(R.color.orange));
            theText.setTextColor(getResources().getColor(R.color.black));
            theText.setLayoutParams(textParams);
            display.addView(theText);
        }
    }
}

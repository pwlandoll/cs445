package edu.jcu.plandoll16.oddsendsforhw6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Peter on 3/21/2016.
 */
public class DisplayActivity extends AppCompatActivity {
    private ArrayList<Integer> nums;
    private LinearLayout display;
    private Intent thisIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (LinearLayout)findViewById(R.id.numbersLinearLayout);
        thisIntent = this.getIntent();
        nums = thisIntent.getIntegerArrayListExtra("MainNumbers");
        int width = 200;
        int height = 200;
        int margin = 10;
        // Dynamically create the TextViews for each number
        for (Integer number : nums) {
            TextView theText = new TextView(getBaseContext());
            theText.setText(number.toString());
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(width, height);
            textParams.setMargins(margin, margin, margin, margin);
            theText.setBackgroundColor(getResources().getColor(R.color.orange));
            theText.setTextColor(getResources().getColor(R.color.black));
            theText.setLayoutParams(textParams);
            display.addView(theText);
        }
    }
}

package edu.jcu.plandoll16.linearlayout;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Use array for detecting button colors later
    private Button[] theButtons;
    private TextView redCount, greenCount, blueCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theButtons = new Button[5];
        theButtons[0] = (Button)findViewById(R.id.button0);
        theButtons[1] = (Button)findViewById(R.id.button1);
        theButtons[2] = (Button)findViewById(R.id.button2);
        theButtons[3] = (Button)findViewById(R.id.button3);
        theButtons[4] = (Button)findViewById(R.id.button4);
        redCount = (TextView)findViewById(R.id.redText);
        greenCount = (TextView)findViewById(R.id.greenText);
        blueCount = (TextView)findViewById(R.id.blueText);
        calculateTotals();
    }

    public void onButtonClick(View view) {
        Button thisButton = (Button)findViewById(view.getId());
        int pickColor = (int)(3*Math.random());
        if (pickColor == 0) {
            thisButton.setBackgroundColor(getResources().getColor(R.color.red));
        } else if (pickColor == 1) {
            thisButton.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            thisButton.setBackgroundColor(getResources().getColor(R.color.blue));
        }
        calculateTotals();
    }

    public void calculateTotals() {
        int reds = 0, greens = 0, blues = 0;
        for (int index = 0; index < theButtons.length; index++) {
            // Get color
            ColorDrawable buttonBackground = (ColorDrawable)theButtons[index].getBackground();
            if (buttonBackground.getColor() == getResources().getColor(R.color.red)) {
                reds++;
            } else if (buttonBackground.getColor() == getResources().getColor(R.color.green)) {
                greens++;
            } else if (buttonBackground.getColor() == getResources().getColor(R.color.blue)) {
                blues++;
            }
        }
        redCount.setText(getResources().getString(R.string.redMessage) + " " + reds);
        greenCount.setText(getResources().getString(R.string.greenMessage) + " " + greens);
        blueCount.setText(getResources().getString(R.string.blueMessage) + " " + blues);
    }
}

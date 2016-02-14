package edu.jcu.plandoll16.homework1;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Use a boolean value for alternating blue/red colors
    private Boolean bluesTurn = Boolean.TRUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view) {
        // Get the pressed button from the view and check its background color
        Button clickedButton = (Button)view;
        ColorDrawable buttonColor = (ColorDrawable)clickedButton.getBackground();
        // Default to start is bluesTurn = true, then alternates after each press
	    if (buttonColor.getColor() == getResources().getColor(R.color.grey)) {
            if (bluesTurn) {
                clickedButton.setBackgroundColor(getResources().getColor(R.color.blue));
            } else {
                clickedButton.setBackgroundColor(getResources().getColor(R.color.red));
            }
            bluesTurn = !bluesTurn;
        }
    }

}

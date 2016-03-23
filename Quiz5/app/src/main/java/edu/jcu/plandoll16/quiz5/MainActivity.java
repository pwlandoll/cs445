package edu.jcu.plandoll16.quiz5;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LinearLayout buttonLayout;
    private TextView topTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLayout = (LinearLayout)findViewById(R.id.buttonLayout);
        topTextView = (TextView)findViewById(R.id.topTextView);

        // Randomly generate between 2 and 6 buttons
        Random r = new Random();
        int numberOfButtons = r.nextInt(5) + 2;
        for (int i = 0; i < numberOfButtons; i++) {
            final Button aButton = new Button(this);
            int size = topTextView.getWidth();
            aButton.setHeight(size);
            aButton.setWidth(size);
            int colorNumber = r.nextInt(3);
            int color;
            if (colorNumber == 0) {
                color = getResources().getColor(R.color.teal);
            } else if (colorNumber == 1) {
                color = getResources().getColor(R.color.purple);
            } else {
                color = getResources().getColor(R.color.red);
            }
            aButton.setBackgroundColor(color);
            aButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Drawable drawable = aButton.getBackground();
                    ColorDrawable buttonColor = (ColorDrawable) drawable;
                    topTextView.setBackground(buttonColor);
                }
            });
            buttonLayout.addView(aButton);
        }
    }
}

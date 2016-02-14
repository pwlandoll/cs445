package edu.jcu.plandoll16.quiz1;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView leftMessage;
    private TextView rightMessage;
    private Button toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftMessage = (TextView)findViewById(R.id.leftMessageTextView);
        rightMessage = (TextView)findViewById(R.id.rightMessageTextView);
        leftMessage.setText(getResources().getString(R.string.firstMessage));
        rightMessage.setText(getResources().getString(R.string.secondMessage));
    }

    public void toggleButtonClick(View view) {
        toggleButton = (Button)findViewById(R.id.toggleButton);
        ColorDrawable buttonColor = (ColorDrawable)toggleButton.getBackground();
        if (buttonColor.getColor() == getResources().getColor(R.color.orange)) {
            // Change color to white, switch messages
            toggleButton.setBackgroundColor(getResources().getColor(R.color.white));
            leftMessage.setText(getString(R.string.secondMessage));
            rightMessage.setText(getString(R.string.firstMessage));
        } else {
            // Change color to orange, switch messages
            toggleButton.setBackgroundColor(getResources().getColor(R.color.orange));
            leftMessage.setText(getString(R.string.firstMessage));
            rightMessage.setText(getString(R.string.secondMessage));
        }
    }
}

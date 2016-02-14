package edu.jcu.plandoll16.simplelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout canvasLayout;
    private TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageText = (TextView)findViewById(R.id.messageText);
        String messageColorText = getResources().getString(R.string.messageText)
                + " " + getResources().getString(R.string.whiteText);
        canvasLayout = (RelativeLayout)findViewById(R.id.canvasLayout);
        //Attempt to set text in messageText corresponding to a certain color
        //  R.color.white will not evaluate to "white"
        messageText.setText(messageColorText);
    }

    public void setColor(View view) {
        String messageColorText = getResources().getString(R.string.messageText);
        if (view.getId() == R.id.blueButton) {
            canvasLayout.setBackgroundColor(getResources().getColor(R.color.blue));
            messageColorText += " " + getResources().getString(R.string.blueText);
            messageText.setText(messageColorText);
        } else if (view.getId() == R.id.redButton) {
            canvasLayout.setBackgroundColor(getResources().getColor(R.color.red));
            messageColorText += " " + getResources().getString(R.string.redText);
            messageText.setText(messageColorText);
        } else {
            canvasLayout.setBackgroundColor(getResources().getColor(R.color.white));
            messageColorText += " " + getResources().getString(R.string.whiteText);
            messageText.setText(messageColorText);
        }
    }
}

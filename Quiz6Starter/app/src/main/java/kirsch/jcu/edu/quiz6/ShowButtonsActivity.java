package kirsch.jcu.edu.quiz6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowButtonsActivity extends AppCompatActivity {
    //Instance Variables
    private int input;
    private TextView yesTextView, noTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_button);

        yesTextView = (TextView)findViewById(R.id.yesTextView);
        noTextView = (TextView)findViewById(R.id.noTextView);

        Intent mIntent = this.getIntent();
        input = mIntent.getIntExtra("edu.jcu.plandoll16.Quiz6.input", 200); // Defaults to 200 here too

        yesTextView.setHeight(input);
        yesTextView.setWidth(input);
        noTextView.setHeight(input);
        noTextView.setWidth(input);
    }

    public void onClick(View view) {
        String decision;
        try {
            decision = ((TextView)view).getText().toString();
        } catch (Exception ex) {
            decision = "ERROR";
        }
        this.getIntent().putExtra("edu.jcu.plandoll16.Quiz6.decision", decision);
        setResult(RESULT_OK, this.getIntent());
        finish();
    }
}

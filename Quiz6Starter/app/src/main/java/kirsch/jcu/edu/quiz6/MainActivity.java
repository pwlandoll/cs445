package kirsch.jcu.edu.quiz6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Instance Variables
    private EditText numberEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberEditText = (EditText)findViewById(R.id.numberEditText);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
    }

    public void displayNumber(View view) {
        int input;
        try { // Just in case?
            input = Integer.parseInt(numberEditText.getText().toString());
            input = Math.min(800, input);
            input = Math.max(200, input);
        } catch (Exception ex) {
            input = 200; // Default to 200 if anything goes wrong
        }
        Intent mIntent = new Intent(view.getContext(), ShowButtonsActivity.class);
        mIntent.putExtra("edu.jcu.plandoll16.Quiz6.input", input);
        startActivityForResult(mIntent, 100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String result = intent.getStringExtra("edu.jcu.plandoll16.Quiz6.decision");
            resultTextView.setText(result);
        }
    }
}

package kirsch.jcu.edu.lineswithwatchersandwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  {
    private MyLine theLine;
    private EditText x1, y1, x2, y2;
    private TextView slope, yIntercept, seekBarInput, resultY;
    private SeekBar xValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect instance vars with resources in layout
        theLine = new MyLine();
        x1 = (EditText)findViewById(R.id.x1EditText);
        y1 = (EditText)findViewById(R.id.y1EditText);
        x2 = (EditText)findViewById(R.id.x2EditText);
        y2 = (EditText)findViewById(R.id.y2EditText);
        seekBarInput = (TextView)findViewById(R.id.xInputTextView);
        slope = (TextView)findViewById(R.id.slopeTextView);
        yIntercept = (TextView)findViewById(R.id.interceptTextView);
        resultY = (TextView)findViewById(R.id.resultingYTextView);
        xValues = (SeekBar)findViewById(R.id.xSeekBar);

        // Put info about default line on the layout
        Double startX = theLine.getStartX();
        x1.setText(startX.toString());
        Double endX = theLine.getEndX();
        x2.setText(endX.toString());
        Double startY = theLine.getStartY();
        y1.setText(startY.toString());
        Double endY = theLine.getEndY();
        y2.setText(endY.toString());
        slope.setText(theLine.getMySlope().toString());
        yIntercept.setText(theLine.getMySlope().toString());

        // Set up listeners
        x1.addTextChangedListener(inputListener);
        x2.addTextChangedListener(inputListener);
        y1.addTextChangedListener(inputListener);
        y2.addTextChangedListener(inputListener);
        xValues.setOnSeekBarChangeListener(changeXListener);
    }


    private TextWatcher inputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                theLine.setStartX(Double.parseDouble(x1.getText().toString()));
                theLine.setStartY(Double.parseDouble(y1.getText().toString()));
                theLine.setEndX(Double.parseDouble(x2.getText().toString()));
                theLine.setEndY(Double.parseDouble(y2.getText().toString()));
            } catch (NumberFormatException e) {

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private SeekBar.OnSeekBarChangeListener changeXListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int xValue = progress - 100;
            Double yValue = theLine.getResult(xValue);
            seekBarInput.setText(xValue);
            if (yValue != null) {
                resultY.setText(yValue.toString());
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}

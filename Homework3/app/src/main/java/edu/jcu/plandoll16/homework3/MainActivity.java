package edu.jcu.plandoll16.homework3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Integer heightMin, heightMax, widthMin, widthMax;
    private TextView heightMinText, heightMaxText, widthMinText, widthMaxText, colorBox;
    private RadioGroup buttonGroup;
    private SeekBar widthBar, heightBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set the min and max values for the color box
        heightMin = 50;
        widthMin = 50;
        heightMax = 350;
        widthMax = 350;
        // Find the text views for the seek bars and set the text
        heightMinText = (TextView)findViewById(R.id.heightMinTextView);
        heightMaxText = (TextView)findViewById(R.id.heightMaxTextView);
        widthMinText = (TextView)findViewById(R.id.widthMinTextView);
        widthMaxText = (TextView)findViewById(R.id.widthMaxTextView);
        heightMinText.setText(heightMin.toString());
        heightMaxText.setText(heightMax.toString());
        widthMinText.setText(widthMin.toString());
        widthMaxText.setText(widthMax.toString());
        // Set the color box and set its width and height
        colorBox = (TextView)findViewById(R.id.colorBox);
        setColorBoxWidth(widthMin);
        setColorBoxHeight(heightMin);
        // Pull selected color from radio group and set color box background
        buttonGroup = (RadioGroup)findViewById(R.id.radioGroup);
        buttonGroup.setOnCheckedChangeListener(checkedListener);
        updateColors(buttonGroup.getCheckedRadioButtonId());
        // Set the seek bars, set progress to 0 (i.e. the min), connect the listeners
        widthBar = (SeekBar)findViewById(R.id.widthSeekBar);
        widthBar.setMax(widthMax);
        widthBar.setProgress(0);
        widthBar.setOnSeekBarChangeListener(widthListener);
        heightBar = (SeekBar)findViewById(R.id.heightSeekBar);
        heightBar.setMax(heightMax);
        heightBar.setProgress(0);
        heightBar.setOnSeekBarChangeListener(heightListener);
    }

    private void updateColors(int checked) {
        // Set the color of the color box to the color matching the button
        if (checked == R.id.redRadioButton) {
            colorBox.setBackgroundColor(getResources().getColor(R.color.red));
        } else if (checked == R.id.greenRadioButton) {
            colorBox.setBackgroundColor(getResources().getColor(R.color.green));
        } else if (checked == R.id.blueRadioButton) {
            colorBox.setBackgroundColor(getResources().getColor(R.color.blue));
        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5f);
    }

    private void setColorBoxWidth(int width) {
        colorBox.setWidth(dpToPx(width));
    }

    private void setColorBoxHeight(int height) {
        colorBox.setHeight(dpToPx(height));
    }

    private RadioGroup.OnCheckedChangeListener checkedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            updateColors(checkedId);
        }
    };

    private SeekBar.OnSeekBarChangeListener widthListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setColorBoxWidth(widthMin + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener heightListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setColorBoxHeight(heightMin + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}

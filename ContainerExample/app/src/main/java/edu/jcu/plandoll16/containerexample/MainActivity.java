package edu.jcu.plandoll16.containerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioGroup textSize;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textSize = (RadioGroup)findViewById(R.id.buttonGroup);
        message = (TextView)findViewById(R.id.textView);
        message.setTextSize(getResources().getDimension(R.dimen.smallText));
        textSize.setOnCheckedChangeListener(sizeListener);
    }

    private RadioGroup.OnCheckedChangeListener sizeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            float size;
            if (checkedId == R.id.smallButton) {
                size = getResources().getDimension(R.dimen.smallText);
            } else if (checkedId == R.id.mediumButton) {
                size = getResources().getDimension(R.dimen.mediumText);
            } else {
                size = getResources().getDimension(R.dimen.largeText);
            }
            message.setTextSize(size);
        }
    };
}

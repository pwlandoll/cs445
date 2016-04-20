package edu.jcu.plandoll16.homework8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText latEditText, lonEditText;
    private double lon, lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latEditText = (EditText)findViewById(R.id.latEditText);
        lonEditText = (EditText)findViewById(R.id.lonEditText);
    }

    public void startButtonClick(View view) {
        try {
            lat = Double.parseDouble(latEditText.getText().toString());
            lon = Double.parseDouble(lonEditText.getText().toString());
        } catch (Exception ex) {
            // Default location is JCU
            lat = 41.4901948;
            lon = -81.5315792;
        }
        Intent mIntent = new Intent(view.getContext(), MapsActivity.class);
        mIntent.putExtra("edu.jcu.plandoll16.Homework8.lat", lat);
        mIntent.putExtra("edu.jcu.plandoll16.Homework8.lon", lon);
        startActivity(mIntent);
    }
}

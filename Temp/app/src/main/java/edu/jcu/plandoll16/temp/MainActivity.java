package edu.jcu.plandoll16.temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Create intent and run
    public void onClick(View view) {
        Intent alternate = new Intent(view.getContext(), AlternateActivity.class);
        startActivityForResult(alternate, 0);
    }
}

package edu.jcu.plandoll16.homework6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TextDisplayActivity extends AppCompatActivity {
    private ArrayList<Integer> numberArrayList;
    private Integer listMin, listMax, listMean;
    private TextView minTextView, maxTextView, meanTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);

        Intent thisIntent = this.getIntent();
        Bundle thisBundle = thisIntent.getExtras();
        numberArrayList = thisBundle.getIntegerArrayList("edu.jcu.plandoll16.Homework6.numberArrayList");
        listMean = thisBundle.getInt("edu.jcu.plandoll16.Homework6.listMean");
        listMax = thisBundle.getInt("edu.jcu.plandoll16.Homework6.listMax");
        listMin = thisBundle.getInt("edu.jcu.plandoll16.Homework6.listMin");

        minTextView = (TextView)findViewById(R.id.minTextView);
        maxTextView = (TextView)findViewById(R.id.maxTextView);
        meanTextView = (TextView)findViewById(R.id.meanTextView);

        minTextView.setText(getResources().getString(R.string.minimum) + ": " + listMin.toString());
        maxTextView.setText(getResources().getString(R.string.maximum) + ": " + listMax.toString());
        meanTextView.setText(getResources().getString(R.string.mean) + ": " + listMean.toString());
    }

    public void returnButtonOnClick(View view) {
        this.getIntent().putExtra("edu.jcu.plandoll16.Homework6.selected", -2);
        setResult(RESULT_OK, this.getIntent());
        finish();
    }
}

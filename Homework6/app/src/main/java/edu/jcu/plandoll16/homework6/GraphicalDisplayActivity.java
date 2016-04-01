package edu.jcu.plandoll16.homework6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class GraphicalDisplayActivity extends AppCompatActivity {
    private ArrayList<Integer> numberArrayList;
    private Integer listMin, listMax, listMean;
    private LinearLayout buttonLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_display);

        // Set padding for buttons
        int padding = 10;

        buttonLinearLayout = (LinearLayout)findViewById(R.id.buttonLinearLayout);
        Intent thisIntent = this.getIntent();
        Bundle thisBundle = thisIntent.getExtras();
        numberArrayList = thisBundle.getIntegerArrayList("edu.jcu.plandoll16.Homework6.numberArrayList");
        calculateValues();

        for (int i = 0; i < 6; i++) {
            final int selected = i;
            Button addButton = new Button(this.getBaseContext());
            addButton.setBackgroundColor(getResources().getColor(R.color.buttonColor));
            addButton.setHeight(numberArrayList.get(i));
            addButton.setWidth(numberArrayList.get(i));
            addButton.setPadding(padding, 0, padding, 0);
            addButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("edu.jcu.plandoll16.Homework6.selected", selected);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            });
            buttonLinearLayout.addView(addButton);
        }
    }

    public void returnButtonOnClick(View view) {
        this.getIntent().putExtra("edu.jcu.plandoll16.Homework6.selected", -1);
        setResult(RESULT_OK, this.getIntent());
        finish();
    }

    private void calculateValues() {
        try {
            listMin = numberArrayList.get(0);
            listMax = numberArrayList.get(0);
            int sum = 0;
            int n;
            for (int i = 0; i < 6; i++) {
                n = numberArrayList.get(i);
                listMin = Math.min(listMin, n);
                listMax = Math.max(listMax, n);
                sum += n;
            }
            listMean = sum / 6;
        } catch (Exception ex) {
            listMax = 0;
            listMin = 0;
            listMean = 0;
        }
    }
}

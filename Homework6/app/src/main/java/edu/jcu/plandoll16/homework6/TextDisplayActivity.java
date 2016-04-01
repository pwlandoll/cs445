package edu.jcu.plandoll16.homework6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TextDisplayActivity extends AppCompatActivity {
    private ArrayList<Integer> numberArrayList;
    private ArrayAdapter<Integer> numberArrayListAdapter;
    private Integer listMin, listMax, listMean;
    private ListView textDisplayListView;
    private TextView minTextView, maxTextView, meanTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);

        Intent thisIntent = this.getIntent();
        Bundle thisBundle = thisIntent.getExtras();
        numberArrayList = thisBundle.getIntegerArrayList("edu.jcu.plandoll16.Homework6.numberArrayList");
        calculateValues();

        textDisplayListView = (ListView)findViewById(R.id.textDisplayListView);
        minTextView = (TextView)findViewById(R.id.minTextView);
        maxTextView = (TextView)findViewById(R.id.maxTextView);
        meanTextView = (TextView)findViewById(R.id.meanTextView);

        minTextView.setText(getResources().getString(R.string.minimum) + ": " + listMin.toString());
        maxTextView.setText(getResources().getString(R.string.maximum) + ": " + listMax.toString());
        meanTextView.setText(getResources().getString(R.string.mean) + ": " + listMean.toString());

        numberArrayListAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, numberArrayList);
        textDisplayListView.setAdapter(numberArrayListAdapter);
        textDisplayListView.setOnItemClickListener(textListViewListener);
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

    private ListView.OnItemClickListener textListViewListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("edu.jcu.plandoll16.Homework6.selected", position);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    };
}

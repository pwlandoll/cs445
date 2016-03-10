package edu.jcu.plandoll16.homework4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Instance variables corresponding to resources
    private EditText minEditText, maxEditText;
    private SeekBar numberSeekBar;
    private TextView barTextView;
    private ListView topListView, rightListView, leftListView;
    // Other instance variables
    private int min, max;
    private Integer minText, maxText, threshold;
    private ArrayList<Integer> topArray, rightArray, leftArray;
    private ArrayAdapter<Integer> topAdapter, rightAdapter, leftAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Associate instance variables with resources
        minEditText = (EditText)findViewById(R.id.minEditText);
        maxEditText = (EditText)findViewById(R.id.maxEditText);
        numberSeekBar = (SeekBar)findViewById(R.id.numberSeekBar);
        barTextView = (TextView)findViewById(R.id.barTextView);
        topListView = (ListView)findViewById(R.id.topListView);
        rightListView = (ListView)findViewById(R.id.rightListView);
        leftListView = (ListView)findViewById(R.id.leftListView);

        // Set defaults for min, max, threshold, numberSeekBar
        minEditText.setText(getResources().getString(R.string.defaultMin));
        maxEditText.setText(getResources().getString(R.string.defaultMax));
        threshold = -1; //allows for updateMinAndMax() to set the threshold based on defaults for min, max
        updateMinAndMax();

        // Create/set adapters for arrays
        topArray = new ArrayList<Integer>();
        rightArray = new ArrayList<Integer>();
        leftArray = new ArrayList<Integer>();
        topAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, topArray);
        rightAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, rightArray);
        leftAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, leftArray);
        topListView.setAdapter(topAdapter);
        rightListView.setAdapter(rightAdapter);
        leftListView.setAdapter(leftAdapter);

        topListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        topListView.setOnItemClickListener(topListViewListener);

        // Connect listeners
        minEditText.addTextChangedListener(editTextWatcher);
        maxEditText.addTextChangedListener(editTextWatcher);
        numberSeekBar.setOnSeekBarChangeListener(numberSeekBarListener);
    }

    public void generateNumbers(View view) {
        Random r = new Random();
        topAdapter.clear();
        leftAdapter.clear();
        rightAdapter.clear();
        for (int i = 0; i < 6; i++) {
            topAdapter.add(r.nextInt(max-min) + min);
        }
        topAdapter.notifyDataSetChanged();
        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();
    }

    private void updateMinAndMax() {
        try {
            minText = Integer.parseInt(minEditText.getText().toString());
            maxText = Integer.parseInt(maxEditText.getText().toString());
        } catch (Exception e) {
            // Set 0, 100 as default values
            minText = 0;
            maxText = 100;
        }
        if (minText <= maxText) {
            min = minText;
            max = maxText;
        } else {
            min = maxText;
            max = minText;
        }
        numberSeekBar.setMax(max - min);
        if (threshold > max || threshold < min) {
            threshold = (max-min) / 2;
        }
        numberSeekBar.setProgress(threshold);
        barTextView.setText(threshold.toString());
    }

    private void clearAll() {
        topAdapter.clear();
        leftAdapter.clear();
        rightAdapter.clear();
        topAdapter.notifyDataSetChanged();
        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();
    }

    private AdapterView.OnItemClickListener topListViewListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position >= 0) {
                Integer data = topArray.get(position);
                topAdapter.remove(data);
                topAdapter.notifyDataSetChanged();
                if (data <= threshold) {
                    leftAdapter.add(data);
                    leftAdapter.notifyDataSetChanged();
                } else if (data > threshold) {
                    rightAdapter.add(data);
                    rightAdapter.notifyDataSetChanged();
                }
            }
        }
    };

    private TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateMinAndMax();
            clearAll();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private SeekBar.OnSeekBarChangeListener numberSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // Update the threshold
            threshold = min + progress;
            barTextView.setText(threshold.toString());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // Clear the topArray and topListView
            clearAll();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}

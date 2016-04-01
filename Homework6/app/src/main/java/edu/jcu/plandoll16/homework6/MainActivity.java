package edu.jcu.plandoll16.homework6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<Integer> listAdapter;
    private ArrayList<Integer> numberArrayList;
    private EditText minEditText, maxEditText;
    private Integer min, max, minAllowed, maxAllowed, minText, maxText, listMin, listMax, listMean;
    private ListView numberListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minEditText = (EditText)findViewById(R.id.minEditText);
        maxEditText = (EditText)findViewById(R.id.maxEditText);
        numberListView = (ListView)findViewById(R.id.numberListView);

        // Hard-code min/max permitted values
        minAllowed = 50;
        maxAllowed = 450;

        // Set defaults for values passed to other activities
        listMin = 0;
        listMax = 0;
        listMean = 0;

        // Set default text values to min/max allowed
        minEditText.setText(minAllowed.toString());
        maxEditText.setText(maxAllowed.toString());
        updateMinAndMax();

        // Create/set adapter
        numberArrayList = new ArrayList<Integer>();
        listAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, numberArrayList);
        numberListView.setAdapter(listAdapter);

        // Connect EditText listener
        minEditText.addTextChangedListener(editTextWatcher);
        maxEditText.addTextChangedListener(editTextWatcher);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // TextDisplay
            Integer selected = intent.getIntExtra("edu.jcu.plandoll16.Homework6.selected", -1);
            Toast.makeText(this, selected.toString(), Toast.LENGTH_LONG).show();
        } else if (requestCode == 101 && resultCode == RESULT_OK) {
            // GraphicalDisplay
        }
    }

    public void textDisplayButtonClick(View view) {
        Intent textDisplayIntent = new Intent(view.getContext(), TextDisplayActivity.class);
        textDisplayIntent.putIntegerArrayListExtra("edu.jcu.plandoll16.Homework6.numberArrayList", numberArrayList);
        textDisplayIntent.putExtra("edu.jcu.plandoll16.Homework6.listMean", listMean);
        textDisplayIntent.putExtra("edu.jcu.plandoll16.Homework6.listMax", listMax);
        textDisplayIntent.putExtra("edu.jcu.plandoll16.Homework6.listMin", listMin);
        startActivityForResult(textDisplayIntent, 100);
    }

    private void calculateValues() {
        int sum = 0;
        int n = 0;
        listMax = numberArrayList.get(0);
        listMin = numberArrayList.get(0);
        for (int i = 0; i < 6; i++) {
            n = numberArrayList.get(0);
            listMax = Math.max(listMax, n);
            listMin = Math.min(listMin, n);
            sum += n;
        }
        listMean = sum / 6;
    }

    public void generateNumbers(View view) {
        Random r = new Random();
        listAdapter.clear();
        updateMinAndMax();
        // Ensure minimum and maximum aren't outside the limits
        if (min < minAllowed) {
            min = minAllowed;
            minEditText.setText(min.toString());
        } else if (min > maxAllowed) {
            min = maxAllowed;
            minEditText.setText(min.toString());
        }
        if (max < minAllowed) {
            max = minAllowed;
            maxEditText.setText(max.toString());
        } else if (max > maxAllowed) {
            max = maxAllowed;
            maxEditText.setText(max.toString());
        }
        updateMinAndMax();
        for (int i = 0; i < 6; i++) {
            listAdapter.add(r.nextInt(max-min) + min);
        }
        listAdapter.notifyDataSetChanged();
    }

    private void updateMinAndMax() {
        try {
            minText = Integer.parseInt(minEditText.getText().toString());
            maxText = Integer.parseInt(maxEditText.getText().toString());
        } catch (Exception e) {
            // Set to defaults
            minText = minAllowed;
            maxText = maxAllowed;
        }
        if (minText <= maxText) {
            min = minText;
            max = maxText;
        } else {
            min = maxText;
            max = minText;
        }
    }

    private TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateMinAndMax();
            listAdapter.clear();
            listAdapter.notifyDataSetChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}

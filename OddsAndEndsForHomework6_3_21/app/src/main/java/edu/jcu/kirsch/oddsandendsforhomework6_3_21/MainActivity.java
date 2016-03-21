package edu.jcu.kirsch.oddsandendsforhomework6_3_21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> numbers;
    private ArrayAdapter<Integer> numbersAdapter;
    private ListView numbersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbersList = (ListView)findViewById(R.id.numbersListView);
        numbers = new ArrayList<Integer>();
        numbersAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1,numbers);
        numbersList.setAdapter(numbersAdapter);
        for (int index=0;index<8;index++)
        {
            numbersAdapter.add(50 + 100*index);
        }
        numbersAdapter.notifyDataSetChanged();

        numbersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Create an Intent to send to the Display Activity.
                Intent displayIntent = new Intent(view.getContext(),DisplayActivity.class);
                displayIntent.putIntegerArrayListExtra("MainNumbers",numbers);
                startActivity(displayIntent);
            }
        });
    }
}

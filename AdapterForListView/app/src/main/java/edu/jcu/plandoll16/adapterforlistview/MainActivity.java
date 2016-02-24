package edu.jcu.plandoll16.adapterforlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private ListView myListView;
    //private Spinner mySpinner;
    private ListView leftList, rightList;
    private EditText inputText;
    //private ArrayList<String> toDoItems;
    private ArrayList<String> leftItems, rightItems;
    //private ArrayAdapter<String> myAdapter;
    private ArrayAdapter<String> leftAdapter, rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //myListView = (ListView)findViewById(R.id.toDoListView);
        //mySpinner = (Spinner)findViewById(R.id.spinner);
        leftList = (ListView)findViewById(R.id.leftToDo);
        rightList = (ListView)findViewById(R.id.rightToDo);
        inputText = (EditText)findViewById(R.id.promptEditText);
	    //toDoItems = new ArrayList<String>();
        //myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toDoItems);
        leftItems = new ArrayList<String>();
        rightItems = new ArrayList<String>();
        leftAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, leftItems);
        rightAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rightItems);
        //myListView.setAdapter(myAdapter);
        //mySpinner.setAdapter(myAdapter);
        leftList.setAdapter(leftAdapter);
        rightList.setAdapter(rightAdapter);
        inputText.setOnKeyListener(keyboardListener);

        leftList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        leftList.setOnItemClickListener(selectListener);
    }

    private AdapterView.OnItemClickListener selectListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position >= 0) {
                String data = leftList.getItemAtPosition(position).toString();
                leftAdapter.remove(data);
                leftAdapter.notifyDataSetChanged();
                rightAdapter.add(data);
                //rightAdapter.notifyDataSetChanged();
            }
        }
    };

    private View.OnKeyListener keyboardListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                leftAdapter.add(inputText.getText().toString());
                leftAdapter.notifyDataSetChanged();
                inputText.setText("");
                return true;
            }
            return false;
        }
    };
}

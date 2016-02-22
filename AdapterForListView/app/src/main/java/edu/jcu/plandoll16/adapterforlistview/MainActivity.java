package edu.jcu.plandoll16.adapterforlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView myListView;
    private EditText inputText;
    private ArrayList<String> myToDoItems;
    private ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView)findViewById(R.id.toDoListView);
        inputText = (EditText)findViewById(R.id.inputText);
	// TODO: Fix inputText in the layout
    }
}

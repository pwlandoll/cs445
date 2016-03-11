package edu.jcu.plandoll16.lifecycleexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> messages;
    private ArrayAdapter<String> myAdapter;
    private String createMessage, startMessage, resumeMessage, pauseMessage, stopMessage, restartMessage;
    private ListView messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        myAdapter.add(createMessage);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myAdapter.add(pauseMessage);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAdapter.add(resumeMessage);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.add(startMessage);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.add(stopMessage);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        myAdapter.add(restartMessage);
        myAdapter.notifyDataSetChanged();
    }

    private void initialize() {
        messageList = (ListView)findViewById(R.id.messageListView);
        messages = new ArrayList<String>();
        myAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1);
        messageList.setAdapter(myAdapter);
        createMessage = getResources().getString(R.string.create);
        startMessage = getResources().getString(R.string.start);
        resumeMessage = getResources().getString(R.string.resume);
        pauseMessage = getResources().getString(R.string.pause);
        stopMessage = getResources().getString(R.string.stop);
        restartMessage = getResources().getString(R.string.restart);
    }
}

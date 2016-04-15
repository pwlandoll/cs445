package edu.jcu.kirsch.databaseexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private CommentsDataSource datasource;
    private ListView commentsList;
    private ArrayAdapter<OneComment> adapter;
    private EditText index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource = new CommentsDataSource(this);
        datasource.open();
        commentsList = (ListView)findViewById(R.id.commentsListView);
        index = (EditText)findViewById(R.id.indexEditText);
        List<OneComment> values = datasource.getAllComments();
        adapter = new ArrayAdapter<OneComment>(this, android.R.layout.simple_list_item_1,values);
        commentsList.setAdapter(adapter);
    }

    public void onClick(View view){
        //ArrayAdapter<OneComment> adapter=(ArrayAdapter<OneComment>)commentsList.getAdapter();
        OneComment comment = null;
        Integer position = 0;
        switch(view.getId())
        {
            case R.id.addButton:

                break;
            case R.id.delete:

                break;
            }

        }

        @Override
        protected void onPause() {
            // TODO Auto-generated method stub
            datasource.close();
            super.onPause();
        }

        @Override
        protected void onResume() {
            // TODO Auto-generated method stub
            datasource.open();
            super.onResume();
        }

    }


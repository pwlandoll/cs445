package edu.jcu.kirsch.databaseexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
                String[] text = {"Nice", "Most Excellent", "It's good"};
                position = new Random().nextInt(3);
                comment = datasource.createComment(text[position]);
                adapter.add(comment);
                break;
            case R.id.delete:
                if (adapter.getCount() > 0){
                    try {
                        position = Integer.parseInt(index.getText().toString());
                        if (position >= 0 && position < adapter.getCount()) {
                            // Valid position in list
                            comment = (OneComment)adapter.getItem(position);
                            datasource.deleteComment(comment);
                            adapter.remove(comment);
                        } else {
                            Toast.makeText(getBaseContext(), "No such position on the list", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getBaseContext(), "Illegal Input: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {

                }
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


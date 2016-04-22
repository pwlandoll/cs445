package edu.jcu.kirsch.databaseexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecordsDataSource datasource;
    private ListView recordsList;
    private ArrayAdapter<OneRecord> adapter;
    private EditText index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Copy database from the assets folder to internal storage.
        String dir = "/data/data/" + getPackageName() + "/databases/";
        String path = dir + "blogger.db";
        File newFile = new File(path);
        if (!newFile.exists()) {
            File directory = new File(path);
            directory.mkdirs();
            try {
                copyDB(getBaseContext().getAssets().open("blogger.db"), new FileOutputStream(path));
            } catch (IOException ex) {
                Toast.makeText(getBaseContext(), "Can't copy blogger.db", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "blogger exists", Toast.LENGTH_LONG).show();
        }
        datasource = new RecordsDataSource(this);
        datasource.open();
        recordsList = (ListView) findViewById(R.id.recordsListView);
        index = (EditText) findViewById(R.id.indexEditText);
        List<OneRecord> values = datasource.getAllRecords();
        adapter = new ArrayAdapter<OneRecord>(this, android.R.layout.simple_list_item_1, values);
        recordsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        OneRecord record = null;
        Integer position = 0;
        switch (view.getId()) {
            case R.id.addButton:
                String[] name = {"Barb", "Tom", "Mary"};
                String[] domain = {"@gmail.com", "@jcu.edu", "@nasa.gov"};
                int nextName = new Random().nextInt(3);
                int nextDomain = new Random().nextInt(3);
                record = datasource.createRecord(name[nextName], name[nextName]+domain[nextDomain]);
                adapter.add(record);
                break;
            case R.id.deleteButton:
                if (adapter.getCount() > 0) {
                    try {
                        position = Integer.parseInt(index.getText().toString());
                        if (position >= 0 && position < adapter.getCount()) {
                            record = (OneRecord) adapter.getItem(position);
                            datasource.deleteRecord(record);
                            adapter.remove(record);
                        } else {
                            Toast.makeText(getBaseContext(), "No such position", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getBaseContext(), "Illegal Input ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Illegal Input ", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.updateButton:
                if (adapter.getCount() > 0) {
                    try {
                        position = Integer.parseInt(index.getText().toString());
                        if (position >= 0 && position < adapter.getCount()) {
                            record = (OneRecord) adapter.getItem(position);
                            String newName = record.getName() + " is updated ";
                            record.setName(newName);
                            String newEmail = record.getEmail() + " is updated ";
                            record.setEmail(newEmail);
                            datasource.updateRecord(record);
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), "No such position", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getBaseContext(), "Illegal Input " + ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Illegal Input - no records", Toast.LENGTH_LONG).show();
                }
                break;
        }
        adapter.notifyDataSetChanged();
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

    public void copyDB(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.close();
    }

}


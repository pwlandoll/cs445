package edu.jcu.kirsch.internalstorageexample;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String filename = "MySampleFile.txt";
    private String filepath = "MyDirectory";

    private File myInternalFile;
    private Button saveToInternalStorage, readFromInternalStorage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        myInternalFile = new File(directory , filename);

        saveToInternalStorage = (Button) findViewById(R.id.saveInternalStorage);
        saveToInternalStorage.setOnClickListener(this);

        readFromInternalStorage = (Button) findViewById(R.id.getInternalStorage);
        readFromInternalStorage.setOnClickListener(this);

    }

    public void onClick(View v) {
        EditText myInputText = (EditText)findViewById(R.id.myInputText);
        TextView responseText = (TextView)findViewById(R.id.responseText);
        if (v.getId() == R.id.saveInternalStorage) {
            try {
                FileOutputStream output = new FileOutputStream(myInternalFile);
                output.write(myInputText.getText().toString().getBytes());
                output.close();
                responseText.setText("MySampleFile.txt saved to internal storage");
                myInputText.setText("");
            } catch (IOException ex) {
                Log.i("File errors:", ex.getMessage());
            }
        } else {
            try {
                FileInputStream in = new FileInputStream(myInternalFile);
                DataInputStream input = new DataInputStream(in);
                BufferedReader read = new BufferedReader(new InputStreamReader(input));
                String line = "";
                String myData = "";
                while ((line = read.readLine()) != null) {
                    
                }
            } catch (IOException ex) {
                Log.i("File errors", ex.getMessage());
            }
        }
    }
}
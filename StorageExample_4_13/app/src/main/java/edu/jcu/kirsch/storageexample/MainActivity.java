package edu.jcu.kirsch.storageexample;

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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private String internalFilename = "myInternalFile.txt";
    private String internalFilePath = "MyDirectory";

    private File myInternalFile;
    private Button saveToInternalStorage, readFromInternalStorage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir(internalFilePath, Context.MODE_PRIVATE);
        myInternalFile = new File(directory , internalFilename);

        saveToInternalStorage = (Button) findViewById(R.id.saveInternalStorage);
        readFromInternalStorage = (Button) findViewById(R.id.getInternalStorage);
        isExternalStorageAvailable();

    }

    public boolean isExternalStorageAvailable() {
        boolean externalStorageAvailable = false;
        try {
            String externalState = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(externalState)) {
                externalStorageAvailable = true;
                Toast.makeText(getBaseContext(), "External storage is read/writeable", Toast.LENGTH_SHORT).show();
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalState)) {
                externalStorageAvailable = true;
                Toast.makeText(getBaseContext(), "External storage is readable", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "External storage is not available", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }


        return externalStorageAvailable;
    }

    public void onClick(View v) {
        EditText myInputText = (EditText)findViewById(R.id.myInputText);
        TextView responseText = (TextView)findViewById(R.id.responseText);
        if (v.getId() == R.id.saveInternalStorage) {
            try {
                FileOutputStream output = new FileOutputStream(myInternalFile);
                output.write(myInputText.getText().toString().getBytes());
                output.close();
                responseText.setText("myInternalFile.txt saved to internal storage");
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
                    myData = myData + line;
                }
                in.close();
                myInputText.setText(myData);
                responseText.setText("myInternalFile.txt data retrieved from internal storage");
            } catch (IOException ex) {
                Log.i("File errors", ex.getMessage());
            }
        }
    }
}
package edu.jcu.plandoll16.homework6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class TextDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display);

        Intent thisIntent = this.getIntent();
        Bundle thisBundle = thisIntent.getExtras();
        ArrayList<Integer> numbersArrayList = thisBundle.getIntegerArrayList("edu.jcu.plandoll16.SizeArray.option");
    }
}

package edu.jcu.kirsch.intentexample1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Marc on 2/26/2016.
 */
public class AlternateActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternate);
    }

    public void onClick(View view)	{
        Intent replyIntent = new Intent();
        setResult(RESULT_OK,replyIntent);
        finish();
    }
}


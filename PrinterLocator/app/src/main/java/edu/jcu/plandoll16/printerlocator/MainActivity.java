package edu.jcu.plandoll16.printerlocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Main Activity class.
 *
 * @author Peter Landoll <plandoll16@jcu.edu>
 * @version 1.0
 * @since 2016-4-12
 */
public class MainActivity extends AppCompatActivity {
    Button findNearestPrinterButton, listAllPrintersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findNearestPrinterButton = (Button)findViewById(R.id.findNearestPrintersButton);
        listAllPrintersButton = (Button)findViewById(R.id.listAllPrintersButton);
    }

    /**
     * Starts the ListAllPrintersActivity activity.
     *
     * @param view necessary for being an onClick method.
     */
    public void listAllPrinters(View view) {
        Intent mIntent = new Intent(view.getContext(), AllPrinterFilterActivity.class);
        startActivity(mIntent);
    }

    public void findNearestPrinter(View view) {
        Intent mIntent = new Intent(view.getContext(), FindNearestPrinterActivity.class);
        startActivity(mIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

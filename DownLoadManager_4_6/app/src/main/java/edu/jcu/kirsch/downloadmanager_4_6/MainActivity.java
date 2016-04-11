package edu.jcu.kirsch.downloadmanager_4_6;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private long enqueue;
    private DownloadManager dm;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the BroadcastReceiver
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Find out what the DownloadManager did, and respond accordingly
                String action = intent.getAction();
                // Make sure this was from a Download complete, if so, it's our request
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    // Do the query, put the results in a Cursor
                    Cursor cursor = dm.query(query);
                    if (cursor.moveToFirst()) {
                        // There is data
                        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)) {
                            // Get the data
                            ImageView view = (ImageView)findViewById(R.id.motorcycleImageView);
                            String uriString = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                            view.setImageURI(Uri.parse(uriString));
                        }
                    }
                }
            }
        };
        // Register the BroadcastReceiver
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void onClick(View view) {
        // Step 1: Create a DownloadManager
        dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        // Step 2: Create a request
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse("http://stuweb08.jcu.edu/kirsch/motorcycle.jpg"));
        enqueue = dm.enqueue(request);
    }

    public void showDownload(View view) {
        Intent mIntent = new Intent();
        mIntent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(mIntent);
    }
}


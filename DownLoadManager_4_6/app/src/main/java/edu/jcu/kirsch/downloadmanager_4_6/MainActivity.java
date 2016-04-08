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


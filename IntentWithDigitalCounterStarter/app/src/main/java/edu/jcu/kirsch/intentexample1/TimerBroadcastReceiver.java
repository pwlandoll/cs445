package edu.jcu.kirsch.intentexample1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Peter on 3/30/2016.
 */
public class TimerBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm notification", Toast.LENGTH_LONG).show();
    }
}

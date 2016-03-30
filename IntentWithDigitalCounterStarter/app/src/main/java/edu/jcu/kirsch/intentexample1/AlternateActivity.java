package edu.jcu.kirsch.intentexample1;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Marc on 2/26/2016.
 */
public class AlternateActivity extends Activity {
    private EditText timeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternate);
        timeEditText = (EditText)findViewById(R.id.setTimerEditText);
    }

    public void onClick(View view)	{
        Intent replyIntent = new Intent();
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    /* // This class listens for Broadcast that says Alarm happened
    public class TimerBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Alarm notification", Toast.LENGTH_LONG).show();
        }
    } */

    public void setTimerClick(View view) {
        try {
            int amountOfTime = Integer.parseInt(timeEditText.getText().toString());
            Intent timerIntent = new Intent(this, TimerBroadcastReceiver.class);
            PendingIntent timerPendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, timerIntent, 0);
            AlarmManager timerAlarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            timerAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (1000 * amountOfTime), timerPendingIntent);
            Toast.makeText(this, "Alarm is set for " + amountOfTime + " seconds from now", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}


package edu.jcu.kirsch.Quiz7;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private Integer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.numberTextView);

        count = 15;
        mTextView.setText(count.toString());
        Intent mIntent;
        PendingIntent mPendingIntent;
        AlarmManager mAlarmManager;

        /*
        while (count > 0) {
            try {
                mIntent = new Intent(this, MyReceiver.class);
                mPendingIntent = PendingIntent.getBroadcast(this, 0, mIntent, 0);
                mAlarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                mAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, mPendingIntent);
            } catch (Exception ex) {
                Log.i("error", ex.getMessage());
            }
        }
        */
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            count -= 3;
            if (count == 0) {
                mTextView.setText("The End");
            } else {
                mTextView.setText(count.toString());
            }
        }
    };

}

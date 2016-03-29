package edu.jcu.kirsch.digitalcounter_3_23;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Integer count;
    private TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        counterTextView = (TextView)findViewById(R.id.numberTextView);
        Thread counterThread = new Thread(increment);
        counterThread.start();
    }

    // Create a runnable
    private Runnable increment = new Runnable() {
        private static final int WAIT_TIME = 100;
        @Override
        public void run() {
            try {
                while (true) {
                    count++;
                    Thread.sleep(WAIT_TIME);
                    incrementHandler.sendEmptyMessage(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public android.os.Handler incrementHandler = new android.os.Handler() {
        public void handleMessage(Message message) {
            counterTextView.setText(count.toString());
        }
    };
}

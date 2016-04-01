package edu.jcu.kirsch.showlocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private TextView latitudeView,longitudeView;
    private LocationManager locationManager;
    private String provider;
    private Button colorButton;
    private boolean firstTime;

    private CountDownTimer countDownTimer;
    private final long startTime = 3000;
    private final long interval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitudeView = (TextView)findViewById(R.id.latitude);
        longitudeView = (TextView)findViewById(R.id.longitude);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        firstTime=true;
        // Actually get GPS locations: define criteria for selecting location provider, use defaults
        Criteria locationCriteria = new Criteria();
        provider = locationManager.getBestProvider(locationCriteria, false);
        try {
            Location location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(provider, 0, 1, this);
            colorButton = (Button)findViewById(R.id.display);
            if (location != null) {
                Log.d("Location", "Provider " + provider + " has been selected.");
                onLocationChanged(location);
            } else {
                Log.d("Location", "Provider has not been set");
                latitudeView.setText("Location not available");
                longitudeView.setText("Location not available");
            }
            colorButton.setBackgroundColor(getResources().getColor(R.color.red));
        } catch (SecurityException ex) {
            Toast.makeText(getBaseContext(), "Unable to get location at this time", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        try {
            locationManager.removeUpdates(this);
        } catch (SecurityException e) {
            Toast.makeText(getBaseContext(), "Unable to remove updates", Toast.LENGTH_LONG).show();
        }
    }
    @Override
	/*Requesting updates on startup*/
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        try
        {
            locationManager.requestLocationUpdates(provider, 0, 1, this);
        }
        catch (SecurityException e)
        {
            Toast.makeText(getBaseContext(), "Unable to update location", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "Disabled provider " + provider, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
        Toast.makeText(this,"Enabled new provider " + provider,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    public class MyCountDownTimer extends CountDownTimer
    {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onTick(long millisUntilFinished)
        {

        }

        public void onFinish()
        {
            colorButton.setBackgroundColor(getResources().getColor(R.color.red));
            countDownTimer = new MyCountDownTimer(startTime, interval);
            countDownTimer.start();
        }
    }
}


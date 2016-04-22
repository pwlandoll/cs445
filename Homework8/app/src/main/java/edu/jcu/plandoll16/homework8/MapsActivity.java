package edu.jcu.plandoll16.homework8;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private double firstDistance, lat, lon;
    private GoogleMap mMap;
    private final int AMOUNT = 15;
    private final int REFRESH = 10000;
    private LatLng fixedLocation;
    private LocationManager mLocationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double fixedLat = this.getIntent().getDoubleExtra("edu.jcu.plandoll16.Homework8.lat", 0);
        double fixedLon = this.getIntent().getDoubleExtra("edu.jcu.plandoll16.Homework8.lon", 0);
        fixedLocation = new LatLng(fixedLat, fixedLon);
        mMap.addMarker(new MarkerOptions().position(fixedLocation).title("Marker in given location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fixedLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(AMOUNT));
    }

    private double distance(LatLng l1, LatLng l2) {
        double latD = Math.pow((364605 * (l1.latitude - l2.latitude)), 2);
        double lonD = Math.pow((258683 * (l1.longitude - l2.longitude)), 2);
        return Math.sqrt(latD + lonD);
    }

    @Override
    public void onLocationChanged(Location location) {
        double userLat = location.getLatitude();
        double userLon = location.getLongitude();
        LatLng userLocation = new LatLng(userLat, userLon);
        if (firstDistance == 0) {
            firstDistance = distance(fixedLocation, userLocation);
        }
        float color = BitmapDescriptorFactory.HUE_RED;
        double currentDist = distance(fixedLocation, userLocation);
        if (currentDist < 50) {
            color = BitmapDescriptorFactory.HUE_BLUE;
        } else if (currentDist < 0.25 * firstDistance) {
            color = BitmapDescriptorFactory.HUE_VIOLET;
        } else if (currentDist < 0.5 * firstDistance) {
            color = BitmapDescriptorFactory.HUE_CYAN;
        } else if (currentDist < 0.75 * firstDistance) {
            color = BitmapDescriptorFactory.HUE_ROSE;
        }
        mMap.addMarker(new MarkerOptions()
                .position(userLocation)
                .title("Your Location")
                .icon(BitmapDescriptorFactory.defaultMarker(color)));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(AMOUNT));
    }
}

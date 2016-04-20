package edu.jcu.plandoll16.homework8;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private double lat, lon;
    private GoogleMap mMap;
    private final int AMOUNT = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double fixedLat = this.getIntent().getDoubleExtra("edu.jcu.plandoll16.Homework8.lat", 0);
        double fixedLon = this.getIntent().getDoubleExtra("edu.jcu.plandoll16.Homework8.lon", 0);
        LatLng fixedLocation = new LatLng(fixedLat, fixedLon);
        mMap.addMarker(new MarkerOptions().position(fixedLocation).title("Marker in given location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fixedLocation));
    }

    public void handleNewLocation(Location location) {

        mMap.animateCamera(CameraUpdateFactory.zoomTo(AMOUNT));
    }
}

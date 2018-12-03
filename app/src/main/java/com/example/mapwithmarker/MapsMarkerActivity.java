package com.example.mapwithmarker;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {
    public LatLng chosen_point;
    public double view_lat;
    public double view_lng;
    public ArrayList<Location> records_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        /*
        Intent view_intent = getIntent();
        view_intent.getDoubleExtra("lat", view_lat);
        view_lng = view_intent.getDoubleExtra("lng", view_lng);
        LatLng view = new LatLng(view_lat, view_lng);
        */
        Snackbar.make(this.findViewById(android.R.id.content)
                , "Tap to change location, hold to save.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng point = new LatLng(-33.852, 151.211);
        //LatLng point = view;
        googleMap.addMarker(new MarkerOptions().position(point)
                .title("Record Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        /*
        for (int i = 0; i < records_map.size(); i++){
            LatLng temp = new LatLng(records_map.get(i).getLatitude(), records_map.get(i).getLongitude());
            googleMap.addMarker(new MarkerOptions().position(temp));
        }*/

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(point));
                chosen_point = point;
            }
        });
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                /*Intent save_intent = new Intent(R.layout.activity_maps,homepage.class);
                save_intent.putExtra("lat", chosen_point.latitude); // double
                save_intent.putExtra("lng", chosen_point.longitude); // double
                setResult(RESULT_OK, save_intent);
                finish();*/
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

package com.assessment.intelliment.harkirat.androidassessmentapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.assessment.intelliment.harkirat.androidassessmentapp.Model.Location;
import com.assessment.intelliment.harkirat.androidassessmentapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback{

    private final String EXTRA_LOCATION_ID="locationID";
    private Location location;
    private double longitude;
    private double latitude;
    private LatLng marker;
    private String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent intent=this.getIntent();
    location= (Location)intent.getSerializableExtra("EXTRA_LOCATION_ID");
    latitude=location.getLatitude();
    longitude=location.getLongitude();
    locationName=intent.getStringExtra("EXTRA_LOCATION_NAME");
    marker=new LatLng(latitude,longitude);
        googleMap.addMarker(new MarkerOptions().position(marker).title(locationName));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        Marker title=googleMap.addMarker(new MarkerOptions().position(marker).title(locationName));
        title.showInfoWindow();

    }
}

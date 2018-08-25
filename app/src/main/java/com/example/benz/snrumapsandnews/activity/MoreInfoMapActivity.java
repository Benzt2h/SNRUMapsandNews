package com.example.benz.snrumapsandnews.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.benz.snrumapsandnews.R;
import com.example.benz.snrumapsandnews.dao.MapItemDao;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MoreInfoMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapItemDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        dao = getIntent().getParcelableExtra("dao");

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

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(Double.parseDouble(dao.getMapLatitude()), Double.parseDouble(dao.getMapLogitude()));
        mMap.addMarker(new MarkerOptions().position(latLng).title(dao.getMapName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
    }
}

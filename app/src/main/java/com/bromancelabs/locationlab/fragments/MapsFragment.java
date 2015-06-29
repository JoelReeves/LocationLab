package com.bromancelabs.locationlab.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bromancelabs.locationlab.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends LocationFragment {
    private GoogleMap map;
    private boolean isMapReady = false;
    private static final float MAP_ZOOM = 15;
    private static final String TAG = MapsFragment.class.getSimpleName();

    public MapsFragment() {}

    public static MapsFragment newInstance() {return new MapsFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();

        return view;
    }

    protected void getNewLocation(Location location) {
        Log.d(TAG, "Location: " + location.toString());
        isMapReady = true;

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng currentLocation = new LatLng(currentLatitude, currentLongitude);

        MarkerOptions options = new MarkerOptions()
                .position(currentLocation)
                .title("Current location");

        CameraPosition position = CameraPosition.builder()
                .target(currentLocation)
                .zoom(MAP_ZOOM)
                .build();

        map.addMarker(options);
        map.moveCamera(CameraUpdateFactory.newCameraPosition(position));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_maps, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mapNormal:
                if (isMapReady) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    return true;
                }
            case R.id.mapSatellite:
                if (isMapReady) {
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    return true;
                }
            case R.id.mapHybrid:
                if (isMapReady) {
                    map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

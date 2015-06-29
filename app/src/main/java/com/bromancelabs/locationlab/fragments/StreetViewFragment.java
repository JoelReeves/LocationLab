package com.bromancelabs.locationlab.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bromancelabs.locationlab.R;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class StreetViewFragment extends LocationFragment {
    private StreetViewPanorama panorama;
    private static final int PANORAMA_RADIUS = 200;
    private static final long CAMERA_ANIMATION_DURATION = 500;
    private static final String TAG = StreetViewFragment.class.getSimpleName();

    public StreetViewFragment() {}

    public static StreetViewFragment newInstance() {return new StreetViewFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streetview, container, false);

        panorama = ((SupportStreetViewPanoramaFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapStreetView)).getStreetViewPanorama();

        return view;
    }

    protected void getNewLocation(Location location) {
        Log.d(TAG, "Location: " + location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng currentLocation = new LatLng(currentLatitude, currentLongitude);

        panorama.setPosition(currentLocation, PANORAMA_RADIUS);

        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                .bearing(180)
                .build();

        panorama.animateTo(camera, CAMERA_ANIMATION_DURATION);
    }
}

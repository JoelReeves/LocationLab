package com.bromancelabs.locationlab.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bromancelabs.locationlab.R;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContinuousLocationFragment extends LocationFragment {
    @Bind(R.id.txtLocationHeader) TextView header;
    @Bind(R.id.txtLatitude) TextView txtLatitude;
    @Bind(R.id.txtLongitude) TextView txtLongitude;
    @Bind(R.id.txtUpdatedTime) TextView txtUpdatedTime;
    private static final long LOCATION_UPDATE_INTERVAL = 1000;
    private static final long LOCATION_FASTEST_UPDATE_INTERVAL = 5000;
    private static final String TAG = ContinuousLocationFragment.class.getSimpleName();

    public ContinuousLocationFragment() {}

    public static ContinuousLocationFragment newInstance() {return new ContinuousLocationFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_continuous, container, false);

        ButterKnife.bind(this, view);

        header.setText(getString(R.string.device_location) + " updated every " +
                (LOCATION_FASTEST_UPDATE_INTERVAL / LOCATION_UPDATE_INTERVAL) + " seconds");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onConnected(Bundle bundle) {

        // sets up a location request w/ high accuracy and update interval in milliseconds
        // also sets the fastest rate in milliseconds which the app can handle location updates
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(LOCATION_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(LOCATION_FASTEST_UPDATE_INTERVAL);

        // starts the request for location updates
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    @Override
    protected void getNewLocation(Location location) {
        Log.d(TAG, "Location: " + location.toString());

        // when location changes populate the textfields with: latitude, longitude, and updated time
        txtLatitude.setText("Latitude: " + String.valueOf(location.getLatitude()));
        txtLongitude.setText("Longitude: " + String.valueOf(location.getLongitude()));
        txtUpdatedTime.setText("Last Updated: " + DateFormat.getTimeInstance().format(new Date()));
    }
}

package com.bromancelabs.locationlab.fragments;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.bromancelabs.locationlab.util.PlayServicesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public abstract class LocationFragment extends BaseFragment implements LocationListener {
    protected static final long LOCATION_UPDATE_INTERVAL = 1000;
    protected static final long LOCATION_FASTEST_UPDATE_INTERVAL = 60000;
    protected static final String TAG = LocationFragment.class.getSimpleName();

    protected abstract void getNewLocation(Location location);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
    }

    @Override
    public void onStart() {
        super.onStart();
        // connect the client
        googleApiClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        // stops requesting location updates & disconnect the client if connected
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }

    @Override
    protected void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        // obtaining last known location
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        // if the last location is null, create a location request & start receiving location updates
        if (lastLocation == null) {
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(LOCATION_UPDATE_INTERVAL);
            locationRequest.setFastestInterval(LOCATION_FASTEST_UPDATE_INTERVAL);

            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        } else {
            // otherwise update the location
            getNewLocation(lastLocation);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // when location changes, get a new location
        getNewLocation(location);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "GoogleApiClient connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "GoogleApiClient connection failed: " + connectionResult.toString());
        PlayServicesUtil.displayGoogleErrorDialog(getActivity(), connectionResult.getErrorCode());
    }
}

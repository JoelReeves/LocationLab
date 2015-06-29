package com.bromancelabs.locationlab.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bromancelabs.locationlab.R;
import com.bromancelabs.locationlab.util.PlayServicesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class StreetViewFragment extends BaseFragment implements LocationListener {
    private StreetViewPanorama panorama;
    private static final int PANORAMA_RADIUS = 200;
    private static final long CAMERA_ANIMATION_DURATION = 500;
    private static final long LOCATION_UPDATE_INTERVAL = 1000;
    private static final long LOCATION_FASTEST_UPDATE_INTERVAL = 60000;
    private static final String TAG = StreetViewFragment.class.getSimpleName();
    public StreetViewFragment() {}

    public static StreetViewFragment newInstance() {return new StreetViewFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streetview, container, false);

        buildGoogleApiClient();

        panorama = ((SupportStreetViewPanoramaFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapStreetView)).getStreetViewPanorama();

        return view;
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
            getNewLocation(lastLocation);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // when location changes, get a new location
        getNewLocation(location);

    }

    /**
     *
     * @param location
     *          sets the map to this location
     */
    private void getNewLocation(Location location) {
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

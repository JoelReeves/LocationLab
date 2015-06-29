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
import com.bromancelabs.locationlab.util.PlayServicesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends BaseFragment implements LocationListener {
    private GoogleMap map;
    private boolean isMapReady = false;
    private static final float MAP_ZOOM = 15;
    private static final long LOCATION_UPDATE_INTERVAL = 1000;
    private static final long LOCATION_FASTEST_UPDATE_INTERVAL = 60000;
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

        buildGoogleApiClient();

        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();

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
    protected synchronized void buildGoogleApiClient() {
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
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "GoogleApiClient connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "GoogleApiClient connection failed: " + connectionResult.toString());
        PlayServicesUtil.displayGoogleErrorDialog(getActivity(), connectionResult.getErrorCode());
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

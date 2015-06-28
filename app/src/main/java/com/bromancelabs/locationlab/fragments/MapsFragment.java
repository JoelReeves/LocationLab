package com.bromancelabs.locationlab.fragments;

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
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends BaseFragment implements OnMapReadyCallback {
    private GoogleMap map;
    private boolean isMapReady = false;
    private double latitute = 39.9522222;
    private double longitude = -75.1641667;
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

        //buildGoogleApiClient();

        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // connect the client
        //googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        // disconnect the client if connected
        /*if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }*/
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
        /*
        // obtaining last known location and populating results into the textfields
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (lastLocation != null) {
            // set location to the last location if not null
            latitute = lastLocation.getLatitude();
            longitude = lastLocation.getLongitude();
        } else {
            // otherwise sets location to Philadelphia's Lat/Lng
            latitute = 39.9522222;
            longitude = -75.1641667;
        }*/
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
    public void onMapReady(GoogleMap googleMap) {
        isMapReady = true;
        map = googleMap;

        LatLng philly = new LatLng(latitute, longitude);
        CameraPosition position = CameraPosition.builder().target(philly).zoom(MAP_ZOOM).build();
        map.moveCamera(CameraUpdateFactory.newCameraPosition(position));

        map.addMarker(new MarkerOptions()
                .title("Philadelphia")
                .snippet("The City of Brotherly Love")
                .position(philly));
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

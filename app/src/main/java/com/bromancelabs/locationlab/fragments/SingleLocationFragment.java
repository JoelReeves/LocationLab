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
import com.bromancelabs.locationlab.util.PlayServicesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import butterknife.ButterKnife;
import butterknife.FindView;

public class SingleLocationFragment extends BaseFragment {
    @FindView(R.id.txtLatitude) TextView txtLatitude;
    @FindView(R.id.txtLongitude) TextView txtLongitude;
    private static final String TAG = SingleLocationFragment.class.getSimpleName();

    public SingleLocationFragment() {}

    public static SingleLocationFragment newInstance() {return new SingleLocationFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_single, container, false);

        ButterKnife.bind(this, view);

        buildGoogleApiClient();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // connect the client
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        // disconnect the client if connected
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    // creates instance of Google API client using Location Services
    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        // obtaining last known location and populating results into the textfields
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (lastLocation != null) {
            txtLatitude.setText("Latitude: " + String.valueOf(lastLocation.getLatitude()));
            txtLongitude.setText("Longitude: " + String.valueOf(lastLocation.getLongitude()));
        } else {
            txtLatitude.setText("Latitude: unknown");
            txtLongitude.setText("Longitude: unknown");
        }
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

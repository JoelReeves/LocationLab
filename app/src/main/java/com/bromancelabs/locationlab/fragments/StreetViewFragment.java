package com.bromancelabs.locationlab.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bromancelabs.locationlab.R;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class StreetViewFragment extends Fragment implements OnStreetViewPanoramaReadyCallback {
    private static final String TAG = StreetViewFragment.class.getSimpleName();
    private double latitute = 36.0579667;
    private double longitude = -112.1430996;
    private static final long CAMERA_ANIMATION_DURATION = 1000;
    public StreetViewFragment() {}

    public static StreetViewFragment newInstance() {return new StreetViewFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streetview, container, false);

        SupportStreetViewPanoramaFragment fragment = (SupportStreetViewPanoramaFragment)
                getChildFragmentManager().findFragmentById(R.id.mapStreetView);
        fragment.getStreetViewPanoramaAsync(this);

        return view;
    }

    /*@Override
    protected void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "GoogleApiClient connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "GoogleApiClient connection failed: " + connectionResult.toString());
        PlayServicesUtil.displayGoogleErrorDialog(getActivity(), connectionResult.getErrorCode());
    }*/

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        LatLng position = new LatLng(latitute, longitude);
        streetViewPanorama.setPosition(position);

        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                .bearing(180)
                .build();

        streetViewPanorama.animateTo(camera, CAMERA_ANIMATION_DURATION);
    }
}

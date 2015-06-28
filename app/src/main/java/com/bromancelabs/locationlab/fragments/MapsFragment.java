package com.bromancelabs.locationlab.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bromancelabs.locationlab.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap map;
    private boolean isMapReady = false;
    private static final double LATITUDE = 40.7484;
    private static final double LONGITUDE = -739857;

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

        //SupportMapFragment fragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        //fragment.getMapAsync(this);
        //map = fragment.getMap();

        return view;
    }

    /*@Override
    protected void buildGoogleApiClient() {

    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        isMapReady = true;
        map = googleMap;
        LatLng location = new LatLng(LATITUDE, LONGITUDE);
        CameraPosition target = CameraPosition.builder().target(location).zoom(14).build();
        map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
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

package com.bromancelabs.locationlab.fragments;

import android.support.v4.app.Fragment;

import com.google.android.gms.common.api.GoogleApiClient;

public abstract class BaseFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    protected GoogleApiClient googleApiClient;

    protected abstract void buildGoogleApiClient();
}

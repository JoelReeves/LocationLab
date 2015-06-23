package com.bromancelabs.locationlab.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bromancelabs.locationlab.R;

import butterknife.ButterKnife;
import butterknife.FindView;

public class SingleLocationFragment extends Fragment {
    @FindView(R.id.txtLatitude) TextView txtLatitude;
    @FindView(R.id.txtLongitude) TextView txtLongitude;

    public SingleLocationFragment() {}

    public static SingleLocationFragment newInstance() {return new SingleLocationFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_single, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

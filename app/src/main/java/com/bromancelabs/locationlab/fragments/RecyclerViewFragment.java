package com.bromancelabs.locationlab.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bromancelabs.locationlab.R;
import com.bromancelabs.locationlab.models.RecyclerItem;
import com.bromancelabs.locationlab.views.DividerItemDecoration;
import com.bromancelabs.locationlab.views.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.FindView;

public class RecyclerViewFragment extends Fragment {
    @FindView(R.id.recyclerView) RecyclerView recyclerView;
    private ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();

    public RecyclerViewFragment() {}

    public static RecyclerViewFragment newInstance() {return new RecyclerViewFragment();}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        ButterKnife.bind(this, view);

        // use a LinearLayoutManager for the RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(linearLayoutManager);

        // allows for optimizations if all item views are of the same size:
        recyclerView.setHasFixedSize(true);

        initializeItems();

        RecyclerAdapter adapter = new RecyclerAdapter(recyclerItems);

        // allows RecyclerView to use item decorations
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

        // attaches adapter to the RecyclerView
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initializeItems() {
        // retrieves activity names and descriptions from XML
        String[] activityNames = getResources().getStringArray(R.array.activity_names);
        String[] activityDescriptions = getResources().getStringArray(R.array.activity_descriptions);
        final int size = activityNames.length;
        RecyclerItem[] items = new RecyclerItem[size];

        // adding both String array elements into the RecyclerItem array
        for (int i = 0; i < size; i++) {
            items[i] = new RecyclerItem(activityNames[i], activityDescriptions[i]);
        }

        // adding RecyclerItem elements into the recyclerItems ArrayList
        Collections.addAll(recyclerItems, items);
    }
}

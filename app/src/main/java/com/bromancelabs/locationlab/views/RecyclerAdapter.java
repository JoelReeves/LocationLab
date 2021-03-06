package com.bromancelabs.locationlab.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bromancelabs.locationlab.R;
import com.bromancelabs.locationlab.activities.ContinuousLocationActivity;
import com.bromancelabs.locationlab.activities.MapsActivity;
import com.bromancelabs.locationlab.activities.SingleLocationActivity;
import com.bromancelabs.locationlab.activities.StreetViewActivity;
import com.bromancelabs.locationlab.models.RecyclerItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private ArrayList<RecyclerItem> recyclerItems;

    public RecyclerAdapter(ArrayList<RecyclerItem> recyclerItems) {
        if (recyclerItems == null || recyclerItems.size() == 0) {
            throw new IllegalArgumentException("Parameter cannot be null or empty");
        }

        this.recyclerItems = recyclerItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_row_item, viewGroup, false);

        final ViewHolder holder = new ViewHolder(v);

        holder.activityName.setTag(holder);
        holder.activityDescription.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // sets Activity name & description
        viewHolder.activityName.setText(getItem(position).getName());
        viewHolder.activityDescription.setText(getItem(position).getDescription());
    }

    // returns size of the RecyclerView
    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }

    // returns the RecyclerItem at the specified position
    private RecyclerItem getItem(int position) {
        return recyclerItems.get(position);
    }

    // Create the ViewHolder class to keep references to your views
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.txtActivityName) TextView activityName;
        @Bind(R.id.txtActivityDescription) TextView activityDescription;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            activityName.setOnClickListener(this);
            activityDescription.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ViewHolder holder = (ViewHolder) v.getTag();

            final Context context = v.getContext();

            /*if (v.getId() == holder.activityName.getId() || v.getId() == holder.activityDescription.getId()) {
            switch (holder.getLayoutPosition()) {
                case 0:
                    intent = new Intent(context, LocationServicesActivity.class);
                    context.startActivity(intent);
                    break;
            }
            }*/

            if (v instanceof TextView) {
                switch (holder.getLayoutPosition()) {
                    case 0:
                        context.startActivity(new Intent(context, ContinuousLocationActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, SingleLocationActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, MapsActivity.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, StreetViewActivity.class));
                        break;
                }
            }
        }
    }
}

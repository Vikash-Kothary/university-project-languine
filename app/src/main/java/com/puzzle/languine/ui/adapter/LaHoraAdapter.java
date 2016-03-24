package com.puzzle.languine.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.puzzle.languine.datamodel.Hour;
import com.puzzle.languine.R;

import java.util.ArrayList;

/**
 * Created by fauco on 23/03/2016.
 */
public class LaHoraAdapter extends RecyclerView.Adapter<LaHoraAdapter.LessonViewHolder> {
    private ArrayList<Hour> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView hourThumbnail;
        TextView hourTime;

        public LessonViewHolder(View itemView) {
            super(itemView);
            hourThumbnail = (ImageView) itemView.findViewById(R.id.hour_thumbnail);
            hourTime = (TextView)itemView.findViewById(R.id.hour_time);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public LaHoraAdapter(ArrayList<Hour> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LaHoraAdapter.LessonViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_hour, parent, false);
        // set the view's size, margins, paddings and layout parameters

        LessonViewHolder vh = new LessonViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.hourThumbnail.setImageResource(mDataset.get(position).getImageID());
        holder.hourTime.setText(mDataset.get(position).getTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}

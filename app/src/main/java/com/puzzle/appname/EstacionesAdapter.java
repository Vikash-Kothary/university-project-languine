package com.puzzle.appname;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by williamhawken on 12/02/2016.
 */
public class EstacionesAdapter extends RecyclerView.Adapter<EstacionesAdapter.LessonViewHolder> {
    private ArrayList<Estacion> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView seasonThumbnail;
        TextView seasonTitle;
        Button month1, month2, month3;

        public LessonViewHolder(View itemView) {
            super(itemView);
            seasonThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            seasonTitle = (TextView)itemView.findViewById(R.id.season_title);
            month1 = (Button) itemView.findViewById(R.id.month1);
            month2 = (Button)itemView.findViewById(R.id.month2);
            month3 = (Button)itemView.findViewById(R.id.month3);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EstacionesAdapter(ArrayList<Estacion> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EstacionesAdapter.LessonViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_season, parent, false);
        // set the view's size, margins, paddings and layout parameters

        LessonViewHolder vh = new LessonViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.seasonThumbnail.setImageResource(mDataset.get(position).getImageID());
        holder.seasonTitle.setText(mDataset.get(position).getName());
        holder.month1.setText(mDataset.get(position).getMonth1());
        holder.month2.setText(mDataset.get(position).getMonth2());
        holder.month3.setText(mDataset.get(position).getMonth3());
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
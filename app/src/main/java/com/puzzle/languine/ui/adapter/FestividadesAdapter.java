package com.puzzle.languine.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.datamodel.Festividad;

import java.util.ArrayList;

/**
 * Created by Namz on 23/03/2016.
 */
public class FestividadesAdapter extends RecyclerView.Adapter<FestividadesAdapter.LessonViewHolder>
{
    private ArrayList<Festividad> mDataset;

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView festividadThumbnail;
        TextView date;
        TextView spanishName;
        TextView englishName;

        public LessonViewHolder(View itemView) {
            super(itemView);
            festividadThumbnail = (ImageView) itemView.findViewById(R.id.festividad_thumbnail);
            date = (TextView) itemView.findViewById(R.id.date);
            spanishName = (TextView) itemView.findViewById(R.id.spanish_name);
            englishName = (TextView) itemView.findViewById(R.id.english_name);

        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FestividadesAdapter(ArrayList<Festividad> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FestividadesAdapter.LessonViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_festividad, parent, false);
        // set the view's size, margins, paddings and layout parameters

        LessonViewHolder vh = new LessonViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.festividadThumbnail.setImageResource(mDataset.get(position).getImageID());
        holder.date.setText(mDataset.get(position).date());
        holder.spanishName.setText(mDataset.get(position).getSpanishName());
        holder.englishName.setText(mDataset.get(position).getEnglishName());
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
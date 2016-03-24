package com.puzzle.appname;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by williamhawken on 12/02/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.LessonViewHolder> {
    private ArrayList<Lesson> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView lessonThumbnail;
        TextView lessonTitle;
        TextView lessonProgress;

        public LessonViewHolder(View itemView) {
            super(itemView);

            lessonThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            lessonTitle = (TextView)itemView.findViewById(R.id.module_title);
            lessonProgress = (TextView)itemView.findViewById(R.id.module_description);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Lesson> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.LessonViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_lesson, parent, false);
        // set the view's size, margins, paddings and layout parameters

        LessonViewHolder vh = new LessonViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.lessonThumbnail.setImageResource(mDataset.get(position).getImageID());
        holder.lessonTitle.setText(mDataset.get(position).getLessonName());
        holder.lessonProgress.setText(mDataset.get(position).getProgress() + "%");

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
package com.puzzle.appname;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fauco on 26/02/2016.
 */
public class GlossaryAdapter extends RecyclerView.Adapter<GlossaryAdapter.TranslationHolder>
{
    private ArrayList<Translation> dataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class TranslationHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView firstWord;
        TextView secondWord;

        public TranslationHolder(View itemView)
        {
            super(itemView);
            firstWord = (TextView)itemView.findViewById(R.id.trans1);
            secondWord = (TextView) itemView.findViewById(R.id.trans2);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GlossaryAdapter(ArrayList<Translation> dataset)
    {
        this.dataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GlossaryAdapter.TranslationHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_translation, parent, false);
        // set the view's size, margins, paddings and layout parameters

        TranslationHolder th = new TranslationHolder(v);
        return th;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TranslationHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Translation translation = dataset.get(position);

        String spanishWord = translation.getSpanishWord();
        String englishWord = translation.getEnglishWord();

        holder.firstWord.setText(spanishWord);
        holder.secondWord.setText(englishWord);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

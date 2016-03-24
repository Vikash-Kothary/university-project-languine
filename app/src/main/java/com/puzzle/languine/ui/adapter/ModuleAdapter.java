package com.puzzle.languine.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.datamodel.ModuleData;

/**
 * Created by Vikash Kothary on 15-Mar-16.
 */
public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
    private ModuleData moduleData;
    private OnItemClickListener mListener;

    public ModuleAdapter(ModuleData moduleData, OnItemClickListener listener) {
        this.moduleData = moduleData;
        this.mListener = listener;
    }

    @Override
    public ModuleAdapter.ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.module_select_card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ModuleViewHolder vh = new ModuleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder holder, final int position) {
        holder.imageView_picture.setImageResource(moduleData.get(position).getImageID());
        holder.textView_moduleTitle.setText(moduleData.get(position).getModuleName());
        holder.textView_moduleDescription.setText(moduleData.get(position).getProgress() + "%");
        holder.button_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(v, position);
            }
        });
        holder.button_introVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, position);

            }
        });
        holder.button_revisionVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, position);
            }
        });
        holder.button_exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return moduleData.size();
    }

    public void onButtonClick(View v, int position) {

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ModuleViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView_picture;
        public TextView textView_moduleTitle;
        public TextView textView_moduleDescription;
        public Button button_resume;
        public Button button_introVideo;
        public Button button_revisionVideos;
        public Button button_exercises;

        public ModuleViewHolder(View itemView) {
            super(itemView);
            imageView_picture = (ImageView) itemView.findViewById(R.id.image_view_module_picture);
            textView_moduleTitle = (TextView) itemView.findViewById(R.id.text_view_module_title);
            textView_moduleDescription = (TextView) itemView.findViewById(R.id.text_view_module_description);
            button_resume = (Button) itemView.findViewById(R.id.button_resume);
            button_introVideo = (Button) itemView.findViewById(R.id.button_intro_video);
            button_revisionVideos = (Button) itemView.findViewById(R.id.button_revision_videos);
            button_exercises = (Button) itemView.findViewById(R.id.button_exercises);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}

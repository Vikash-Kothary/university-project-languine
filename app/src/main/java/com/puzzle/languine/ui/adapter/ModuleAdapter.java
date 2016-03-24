package com.puzzle.languine.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.datamodel.ModuleData;
import com.puzzle.languine.ui.MaterialActivity;

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
    public void setDrawableSize(Bitmap bitmap, ImageView view) {
// Get current dimensions AND the desired bounding box
        int width = 0;
        width = bitmap.getWidth();

        int height = bitmap.getHeight();
        int bounding = dpToPx(250);

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth(); // re-use
        height = scaledBitmap.getHeight(); // re-use
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);

        // Apply the scaled bitmap
        view.setImageDrawable(result);

        // Now change ImageView's dimensions to match the scaled image
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private int dpToPx(int dp) {
        float density = MaterialActivity.density;
        return Math.round((float) dp * density);
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder holder, final int position) {
        if (moduleData.get(position) != null) {
            setDrawableSize(moduleData.get(position).getImageBitmap(), holder.imageView_picture);

            holder.textView_moduleTitle.setText(moduleData.get(position).getModuleName());
            holder.textView_moduleDescription.setText(String.format("%d%%", moduleData.get(position).getProgress()));
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

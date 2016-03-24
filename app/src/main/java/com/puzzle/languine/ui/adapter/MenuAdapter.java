package com.puzzle.languine.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puzzle.languine.R;

import java.util.ArrayList;

/**
 * Created by Vikash Kothary on 15-Mar-16.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private ArrayList<String> menuData;

    public MenuAdapter(ArrayList<String> moduleData) {
        this.menuData = moduleData;
    }

    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.module_select_card_view_simple, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, final int position) {
        holder.textView_moduleTitle.setText(menuData.get(position));
    }

    @Override
    public int getItemCount() {
        return menuData.size();
    }

    public void onButtonClick(View v, int position) {

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView_moduleTitle;

        public MenuViewHolder(View itemView) {
            super(itemView);
            textView_moduleTitle = (TextView) itemView.findViewById(R.id.text_view_module_title);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}

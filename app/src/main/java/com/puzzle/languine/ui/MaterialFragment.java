package com.puzzle.languine.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.puzzle.languine.R;

/**
 * Created by Vikash Kothary on 15-Mar-16.
 */
public class MaterialFragment extends Fragment {

    public View rootView = null;

    public MaterialFragment() {
    }

    public Toolbar getToolbar() {
        return ((MaterialActivity) getActivity()).getToolbar();
    }

    protected void setupRecyclerView(RecyclerView.Adapter adapter, MaterialRecyclerView.OnItemClickListener listener) {
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);
            // use a linear layout manager
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            // specify an adapter
            recyclerView.setAdapter(adapter);
//            recyclerView.addOnItemTouchListener(this);
        }
    }

    @Override
    public Context getContext() {
        if(rootView!=null){
            return rootView.getContext();
        }
        return getActivity().getApplicationContext();
    }

    public View findViewById(int id) {
        View view = rootView.findViewById(id);
        if(view instanceof TextView){
            ((TextView) view).setTextColor(Color.WHITE);
        }
        return view;
    }

    protected void finish() {
        getActivity().finish();
    }

    protected void changeFragment(MaterialFragment framgent) {
        ((MaterialActivity) getActivity()).changeFragment(framgent);
    }

    protected AssetManager getAssets(){
        return getActivity().getAssets();
    }

    protected SharedPreferences getPrefs(){
        return ((MaterialActivity)getActivity()).getPrefs();
    }
}

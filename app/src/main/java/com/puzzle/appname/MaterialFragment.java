package com.puzzle.appname;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.puzzle.appname.R;

/**
 * Created by Vikash Kothary on 15-Mar-16.
 */
public class MaterialFragment extends Fragment {

    public View rootView = null;

    public MaterialFragment() {
    }

    public Toolbar getToolbar(){
        return ((MaterialActivity)getActivity()).getToolbar();
    }

    /*protected void setupRecyclerView(RecyclerView.Adapter adapter) {
        if (rootView != null) {
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
//                recyclerView.addOnItemTouchListener(this);
            }
        }
    }*/

    @Override
    public Context getContext() {
        if (rootView != null) {
            return rootView.getContext();
        }
        return getActivity().getApplicationContext();
    }

    protected View findViewById(int id) {
        return rootView.findViewById(id);
    }

    protected void finish() {
        getActivity().finish();
    }

    protected void changeFragment(MaterialFragment framgent) {
        ((MaterialActivity)getActivity()).changeFragment(framgent);
    }
}

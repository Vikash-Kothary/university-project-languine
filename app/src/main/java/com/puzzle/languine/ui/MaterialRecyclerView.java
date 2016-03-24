package com.puzzle.languine.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.puzzle.languine.R;

/**
 * Created by Vikash Kothary on 15-Mar-16.
 */
public class MaterialRecyclerView implements RecyclerView.OnItemTouchListener {
    GestureDetector mGestureDetector;
    private OnItemClickListener mListener;

    public MaterialRecyclerView(MaterialActivity activity, RecyclerView.Adapter adapter) {
        mListener = (OnItemClickListener) activity;
        mGestureDetector = new GestureDetector(activity, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);
            // use a linear layout manager
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(mLayoutManager);
            // specify an adapter
            recyclerView.setAdapter(adapter);
            recyclerView.addOnItemTouchListener(this);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
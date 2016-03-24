package com.puzzle.languine.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.ui.MaterialRecyclerView;
import com.puzzle.languine.ui.adapter.MenuAdapter;
import com.puzzle.languine.utils.IntentConts;

import java.util.ArrayList;

public class ExerciseMenuActivity extends MaterialActivity implements MaterialRecyclerView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);

        setupToolbar();
        ArrayList<String> exerciseList = new ArrayList<>();
        exerciseList.add("Flo");
        MenuAdapter adapter = new MenuAdapter(exerciseList);
//        setupRecyclerView(adapter, this);
    }

    @Override
    public void onItemClick(View view, int position) {
        System.out.println("It works!");
    }
}

package com.puzzle.languine.ui.activity;

import android.content.Intent;
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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        setupToolbar();

        ArrayList<String> exerciseList = new ArrayList<>();
        String moduleName = getIntent().getStringExtra(IntentConts.MODULE_NAME);

        String exerciseNames = getResources().getString(R.string.lesson_names);
        String[] exerciseNamesPerUnit = exerciseNames.split(": ");
        String[] quizNames = null;

        for(int i = 0; i < exerciseNamesPerUnit.length; ++i)
        {
            if(exerciseNamesPerUnit[i].startsWith(moduleName))
            {
                quizNames = exerciseNamesPerUnit[i].split(", ");
            }
        }

        for(int i = 3; i < quizNames.length; ++i)
        {
            exerciseList.add(quizNames[i].split(";")[0]);
        }

        MenuAdapter adapter = new MenuAdapter(exerciseList);
        new MaterialRecyclerView(this, adapter);
    }

    @Override
    public void onItemClick(View view, int position)
    {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }
}

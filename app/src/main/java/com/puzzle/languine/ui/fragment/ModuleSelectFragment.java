package com.puzzle.languine.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puzzle.languine.ui.activity.VideoActivity;
import com.puzzle.languine.R;
import com.puzzle.languine.ui.activity.RevisionVideosActivity;
import com.puzzle.languine.datamodel.ModuleData;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.ui.MaterialRecyclerView;
import com.puzzle.languine.ui.activity.ExerciseMenuActivity;
import com.puzzle.languine.ui.adapter.ModuleAdapter;
import com.puzzle.languine.utils.IntentConts;


public class ModuleSelectFragment extends MaterialFragment implements ModuleAdapter.OnItemClickListener, MaterialRecyclerView.OnItemClickListener {
    private ModuleData moduleData;

    public ModuleSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);

        moduleData = new ModuleData();
        ModuleAdapter moduleAdapter = new ModuleAdapter(moduleData, this);
        setupRecyclerView(moduleAdapter, this);

        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=null;
        switch (view.getId())
        {
            case R.id.intro_button:
                System.out.println("Intro");
                intent = new Intent(getContext(), VideoActivity.class);
                break;
            case R.id.revision_button:
                intent = new Intent(getContext(), RevisionVideosActivity.class);

                break;
            case R.id.exercises_button:
                intent = new Intent(getContext(), ExerciseMenuActivity.class);
                break;
            default: // if card clicked
                break;
        }
        if(intent!=null){
            intent.putExtra(IntentConts.MODULE_NAME, moduleData.getModule(position).getModuleName());
            int lessonNumber = 9-position;
            intent.putExtra(IntentConts.LESSON_NUMBER, ""+lessonNumber);
            Log.e("NULL","FIRST PASS: " + intent.getStringExtra(IntentConts.LESSON_NUMBER));
            startActivity(intent);
        }
    }
}

package com.puzzle.languine.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puzzle.languine.R;
import com.puzzle.languine.TODO.Data;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.ui.MaterialRecyclerView;
import com.puzzle.languine.ui.adapter.MenuAdapter;
import com.puzzle.languine.utils.IntentConts;

import java.util.ArrayList;

public class ExerciseMenuActivity extends MaterialActivity implements MaterialRecyclerView.OnItemClickListener
{
    private ArrayList<String> exerciseList;
    private ArrayList<Integer> imagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        setupToolbar();

        exerciseList = new ArrayList<>();
        imagesList = new ArrayList<>();
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
            String[] exerciseDetails = quizNames[i].split(";");
            exerciseList.add(exerciseDetails[0]);
            imagesList.add(getResources().getIdentifier(exerciseDetails[1],"drawable",getPackageName()));
        }

        MenuAdapter adapter = new MenuAdapter(exerciseList, imagesList);
        new MaterialRecyclerView(this, adapter);
    }

    @Override
    public void onItemClick(View view, int position)
    {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra(IntentConts.EXERCISE_NAME, exerciseList.get(position));
        intent.putExtra(IntentConts.LESSON_NUMBER,getIntent().getStringExtra(IntentConts.LESSON_NUMBER));
        intent.putExtra(IntentConts.QUIZ_TYPE,getQuizType(exerciseList.get(position),getIntent().getStringExtra(IntentConts.LESSON_NUMBER)));
        startActivity(intent);
    }

    private String getQuizType(String exerciseName, String lessonNumber)
    {
        Exercise unitExercise = Data.getExercise(lessonNumber,exerciseName,this);
        return unitExercise.getType();
    }
}

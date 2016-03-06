package com.puzzle.appname;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class Exercises extends AppCompatActivity {

    public static final String EXERCISE_TITLE = "TITLE";
    public static final String EXERCISE_NAMES = "NAMES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle((getIntent().getStringExtra(GetStarted.LESSON_TITLE)));
        setSupportActionBar(toolbar);
        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        //use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        ArrayList<Lesson> myDataset = new ArrayList<>();
        myDataset.add(new Lesson(R.mipmap.ic_launcher, "Revision Videos",100));
        myDataset.add(new Lesson(R.mipmap.ic_launcher, "Exercises", 100));


        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);

        //get the string of all exercises for all lessons
        String allExerciseNames = getResources().getString(R.string.lesson_names);
        //split that string for each lesson
        String[] exercisesPerLesson = allExerciseNames.split(": ");

        String titlePlaceHolder = "";
        String namesPlaceHolder = "";

        for(int i = 0; i < exercisesPerLesson.length; ++i)
        {
            //if the name of the lesson matches the lesson the user selected
            if(exercisesPerLesson[i].startsWith(toolbar.getTitle().toString()))
            {
                //split that lesson's string into the exercise names
                namesPlaceHolder = exercisesPerLesson[i];
                String[] buttonTitles = namesPlaceHolder.split(", ");
                //get the name of the page title for next activity
                titlePlaceHolder = buttonTitles[2];
            }
        }

        final String exercisePageTitle = titlePlaceHolder;
        final String exerciseNames = namesPlaceHolder;

        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), Experiment.class);
                        intent.putExtra(EXERCISE_TITLE, exercisePageTitle);
                        intent.putExtra(EXERCISE_NAMES, exerciseNames);
                        startActivity(intent);
                    }
                })
        );
    }

}

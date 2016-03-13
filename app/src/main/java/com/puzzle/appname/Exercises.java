package com.puzzle.appname;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Exercises extends AppCompatActivity {

    /* Constants */
    public static final String EXERCISE_TITLE = "EXERCISE_TITLE";
    public static final String EXERCISE_NAMES = "EXERCISE_NAMES";
    public static final String EXERCISE_VIDEO_TITLE = "EXERCISE_VIDEO_TITLE";
    public static final String EXERCISE_VIDEO_NAMES = "EXERCISE_VIDEO_NAMES";
    public static final String LESSON_NUMBER = "LESSON_NUMBER";

    /* Global Variables */
    private String lessonNumber;
    ArrayList<Integer> exerciseImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle((getIntent().getStringExtra(GetStarted.LESSON_TITLE)));
        lessonNumber = (getIntent().getStringExtra(GetStarted.LESSON_NUMBER));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        //use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);


        exerciseImages = new ArrayList<Integer>(
                Arrays.asList(R.drawable.at_the_train1, R.drawable.at_the_hotel2,
                        R.drawable.at_the_tourist_office3, R.drawable.at_the_park4,
                        R.drawable.at_the_restaurant5, R.drawable.at_the_museum6,
                        R.drawable.at_the_centre_of_madrid7, R.drawable.at_the_shopping_centre8,
                        R.drawable.at_the_cafe9)
        );

        // specify an adapter (see also next example)
        ArrayList<Lesson> myDataset = new ArrayList<>();
        myDataset.add(new Lesson(R.drawable.revision_videos, "Revision Videos", 100));
        myDataset.add(new Lesson(exerciseImages.get(Integer.parseInt(lessonNumber) - 1), "Exercises", 100));

        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);

        //get the string of all exercises for all lessons
        String allExerciseNames = getResources().getString(R.string.lesson_names);
        //split that string for each lesson
        String[] exercisesPerLesson = allExerciseNames.split(": ");

        //get the string for all the revision video names
        String allVideoTopics = getResources().getString(R.string.video_names);
        //split that string for each video topic
        String[] videosPerLesson = allVideoTopics.split(": ");

        String titlePlaceHolder = "";
        String namesPlaceHolder = "";

        String videoPlaceHolder = "";
        String videoNamesPlaceHolder = "";


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

        for(int i = 0; i < videosPerLesson.length; ++i){
            // if the name of the lesson matches the lesson the user selected
            if (videosPerLesson[i].startsWith(toolbar.getTitle().toString())){
                videoNamesPlaceHolder = videosPerLesson[i];
                String[] videoButtonTitles = videoNamesPlaceHolder.split(", ");

                videoPlaceHolder = videoButtonTitles[1];
            }
        }

        final String exercisePageTitle = titlePlaceHolder;
        final String exerciseNames = namesPlaceHolder;

        final String videoPageTitle = videoPlaceHolder;
        final String videoNames = videoNamesPlaceHolder;

        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position)
                    {
                        if(position == 0)
                        {
                            Intent intent = new Intent(getBaseContext(), RevisionVideos.class);
                            intent.putExtra(EXERCISE_VIDEO_TITLE, videoPageTitle);
                            intent.putExtra(EXERCISE_VIDEO_NAMES, videoNames);
                            intent.putExtra(LESSON_NUMBER, lessonNumber);
                            intent.putExtra("FirstVideo",getIntent().getIntExtra("FirstVideo",-1));
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(getBaseContext(), Experiment.class);
                            intent.putExtra(EXERCISE_TITLE, exercisePageTitle);
                            intent.putExtra(EXERCISE_NAMES, exerciseNames);
                            intent.putExtra(LESSON_NUMBER, lessonNumber);
                            startActivity(intent);
                        }
                    }
                })
        );
    }

}

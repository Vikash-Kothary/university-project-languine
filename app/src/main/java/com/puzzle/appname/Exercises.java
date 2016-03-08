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
<<<<<<< HEAD
    public static final String EXERCISE_VIDEO_TITLE = "TITLE";
    public static final String EXERCISE_VIDEO_NAMES = "NAMES";



=======
    public static final String LESSON_NUMBER = "NUMBER";
    private String lessonNumber;
>>>>>>> refs/remotes/origin/populating-data

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
<<<<<<< HEAD
                    public void onItemClick(View view, int position)
                    {
                        if(position == 0)
                        {
                            Intent intent = new Intent(getBaseContext(), RevisionVideos.class);
                            intent.putExtra(EXERCISE_VIDEO_TITLE, videoPageTitle);
                            intent.putExtra(EXERCISE_VIDEO_NAMES, videoNames);
                            intent.putExtra("FirstVideo",getIntent().getIntExtra("FirstVideo",-1));
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(getBaseContext(), Experiment.class);
                            intent.putExtra(EXERCISE_TITLE, exercisePageTitle);
                            intent.putExtra(EXERCISE_NAMES, exerciseNames);
                            startActivity(intent);
                        }
=======
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), Experiment.class);
                        intent.putExtra(EXERCISE_TITLE, exercisePageTitle);
                        intent.putExtra(EXERCISE_NAMES, exerciseNames);
                        intent.putExtra(LESSON_NUMBER, lessonNumber);
                        startActivity(intent);
>>>>>>> refs/remotes/origin/populating-data
                    }
                })
        );
    }

}

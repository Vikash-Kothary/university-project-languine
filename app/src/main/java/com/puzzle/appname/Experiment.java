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

import java.util.ArrayList;

public class Experiment extends AppCompatActivity {

    public static final String QUIZ_TITLE = "TITLE";
    public static final String UNIT_NUMBER = "UNIT";
    public static final String LESSON_NUMBER = "NUMBER";
    private String lessonNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(Exercises.EXERCISE_TITLE));
        lessonNumber = (getIntent().getStringExtra(Exercises.LESSON_NUMBER));
        setSupportActionBar(toolbar);

        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        //use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String exerciseNames = getIntent().getStringExtra(Exercises.EXERCISE_NAMES);
        final String[] exerciseNamesArray = exerciseNames.split(", ");

        ArrayList<Lesson> myDataset = new ArrayList<>();
        for(int i = 3; i < exerciseNamesArray.length; ++i)
        {
            myDataset.add(new Lesson(R.mipmap.ic_launcher,exerciseNamesArray[i],0));
        }
        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);

        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), QuizIntroActivity.class);
                        intent.putExtra(QUIZ_TITLE, exerciseNamesArray[position+3]);
                        intent.putExtra(UNIT_NUMBER, exerciseNamesArray[1]);
                        intent.putExtra(LESSON_NUMBER, lessonNumber);
                        Log.e("NUMBERS", exerciseNamesArray[1] + " " + lessonNumber);
                        startActivity(intent);
                    }
                })
        );
    }

}

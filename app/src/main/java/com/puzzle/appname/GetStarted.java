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

public class GetStarted extends AppCompatActivity {


//    VideoFragment video = new VideoFragment();

    public static final String LESSON_TITLE = "TITLE";
    public static final String LESSON_NUMBER = "NUMBER";
    private String lessonNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(LessonSelectActivity.LESSON_TITLE));
        lessonNumber = getIntent().getStringExtra(LessonSelectActivity.LESSON_NUMBER);
        System.out.println("LESSON NUMBER IS: " + lessonNumber);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        ArrayList<Lesson> myDataset = new ArrayList<>();
        myDataset.add(new Lesson(R.mipmap.ic_launcher, "Get Started", 100));

        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);


        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), Exercises.class);
                        intent.putExtra(LESSON_TITLE, toolbar.getTitle());
                        intent.putExtra(LESSON_NUMBER, lessonNumber);
                        startActivity(intent);
                    }
                })
        );

        View frag = findViewById(R.id.fragment3);
        frag.setMinimumHeight(200);
        VideoFragment fragment = (VideoFragment)getSupportFragmentManager().findFragmentById(R.id.fragment3);
        fragment.runVideo(R.raw.ttt);
    }

}

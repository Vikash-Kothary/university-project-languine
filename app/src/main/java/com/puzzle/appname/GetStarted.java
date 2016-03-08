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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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
<<<<<<< HEAD
                        intent.putExtra("FirstVideo",getIntent().getIntExtra("WhichVideo",-1));
=======
                        intent.putExtra(LESSON_NUMBER, lessonNumber);
>>>>>>> refs/remotes/origin/populating-data
                        startActivity(intent);
                    }
                })
        );

        View frag = findViewById(R.id.fragment3);
        frag.setMinimumHeight(200);
        VideoFragment fragment = (VideoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        switch (getIntent().getIntExtra("WhichVideo", 0)+1) {
            case 1:
                fragment.runVideo(R.raw.u01);
                break;
            case 2:
                fragment.runVideo(R.raw.u02);
                break;
            case 3:
                fragment.runVideo(R.raw.u03);
                break;
            case 4:
                fragment.runVideo(R.raw.u04);
                break;
            case 5:
                fragment.runVideo(R.raw.u05);
                break;
            case 6:
                fragment.runVideo(R.raw.u06);
                break;
            case 7:
                fragment.runVideo(R.raw.u07);
                break;
            case 8:
                fragment.runVideo(R.raw.u08);
                break;
            case 9:
                fragment.runVideo(R.raw.u09);
                break;
            default:
                fragment.runVideo(R.raw.ttt);
        }
    }

}

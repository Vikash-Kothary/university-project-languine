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
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class GetStarted extends AppCompatActivity {


//    VideoFragment video = new VideoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(LessonSelectActivity.LESSON_TITLE));
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



        ArrayList<Lessons> myDataset = new ArrayList<>();
        myDataset.add(new Lessons(R.mipmap.ic_launcher, "Get Started", 59));
        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);


        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), Exercises.class);

                        startActivity(intent);
                    }
                })
        );





        VideoView intro = (VideoView) findViewById(R.id.introVid);

        intro.setVideoPath("android.resource://" +this.getPackageName() + "/" + R.raw.ttt);
        intro.setMinimumWidth(720);
        intro.start();

        MediaController controller = new MediaController(this);
        controller.setAnchorView(intro);
        controller.setMediaPlayer(intro);

        intro.setMediaController(controller);
    }

}

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

public class RevisionVideos extends AppCompatActivity {

    private static final String TOPIC_TITLE = "TOPIC_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(Exercises.EXERCISE_TITLE));
        setSupportActionBar(toolbar);



        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        //use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String videoNames = getIntent().getStringExtra(Exercises.EXERCISE_VIDEO_NAMES);
        String[] videoNamesArray = videoNames.split(", ");

        final ArrayList<Lesson> myDataset = new ArrayList<>();
        for(int i = 2; i < videoNamesArray.length; ++i)
        {
            myDataset.add(new Lesson(R.mipmap.ic_launcher,videoNamesArray[i],59));
        }
        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);
        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), RevisionVideoView.class);
                        intent.putExtra(TOPIC_TITLE, myDataset.get(position).getLessonName());
                        intent.putExtra("WhichVideo", position);
                        intent.putExtra("FirstVideo",getIntent().getIntExtra("FirstVideo",-1));
                        startActivity(intent);
                    }
                })
        );
    }

}

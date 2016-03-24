package com.puzzle.languine;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.puzzle.languine.ui.fragment.VideoFragment;

public class RevisionVideoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_video_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        View frag = findViewById(R.id.fragment4);
        frag.setMinimumHeight(200);
        VideoFragment fragment = (VideoFragment)getSupportFragmentManager().findFragmentById(R.id.fragment4);

       switch (getIntent().getIntExtra("WhichVideo", 0)+1){
           case 1: fragment.runVideo(R.raw.u0101grapronombres);
               break;
           case 2: fragment.runVideo(R.raw.u0102grallamarse);
               break;
           case 3: fragment.runVideo(R.raw.u0103graserestar);
               break;
           case 4: fragment.runVideo(R.raw.u0104vocabmdetransporte);
               break;
           default:fragment.runVideo(R.raw.ttt);
               break;
       }



    }

}

package com.puzzle.appname;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AudioQuiz extends AppCompatActivity {

    //Sounds sound;

    private boolean playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_audio_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void playSound(View view)
    {
        try
        {
            Sounds.mySound.setDataSource("/Users/WSH/Google Drive/ProjectRun/Puzzle/app/src/main/res/raw/background.wav");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Log.e("SOUND", "Sound is playing? " + Sounds.mySound.isPlaying());
        if(!Sounds.mySound.isPlaying())
        {
            Sounds.mySound.start();
            playing = true;
            Log.e("SOUND", "Sound is playing? " + playing);
        }
    }

    public void pauseSound(View view)
    {
        //sound = new Sounds(this);
        Sounds.mySound.pause();
    }

}

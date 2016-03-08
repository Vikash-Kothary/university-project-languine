package com.puzzle.appname;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AudioQuiz extends AppCompatActivity {

    Sounds sounds;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sounds = new Sounds(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_audio_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        initiateBar();
    }

    public void playSound(View view)
    {
        if(!sounds.mySound.isPlaying())
        {
            sounds.mySound.start();
        }
    }

    public void pauseSound(View view)
    {
        sounds.mySound.pause();
    }

    public void initiateBar()
    {
        seekBar.setMax(sounds.mySound.getDuration());
        while(sounds.mySound.isPlaying())
        {
            seekBar.setProgress(sounds.mySound.getCurrentPosition());
        }
    }

}

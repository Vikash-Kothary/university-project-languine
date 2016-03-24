package com.puzzle.languine;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.puzzle.languine.ui.activity.ResourcesActivity;

public class Numeros7 extends AppCompatActivity {


    private int[] number_id = {R.id.onedec, R.id.twodec, R.id.threedec, R.id.fourdec, R.id.fivedec, R.id.sixdec, R.id.sevendec,
            R.id.eightdec, R.id.ninedec};

    private int[] spa_audio = {R.raw.n01_o_s, R.raw.n02_o_s, R.raw.n03_o_s, R.raw.n04_o_s, R.raw.n05_o_s,R.raw.n06_o_s,
            R.raw.n07_o_s, R.raw.n08_o_s, R.raw.n09_o_s, R.raw.n10_o_s};

    private int[] mex_audio = {R.raw.n01_o_m, R.raw.n02_o_m, R.raw.n03_o_m, R.raw.n04_o_m, R.raw.n05_o_m,R.raw.n06_o_m,
            R.raw.n07_o_m, R.raw.n08_o_m, R.raw.n09_o_m, R.raw.n10_o_m};

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros7);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);


        for ( int i = 0; i < number_id.length; ++i) {
            ImageButton imageButton = (ImageButton) findViewById(number_id[i]);
            final int num = i;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Numeros.spanish)
                    {
                        mediaPlayer = MediaPlayer.create(Numeros7.this, spa_audio[num]);
                    }
                    else
                    {
                        mediaPlayer = MediaPlayer.create(Numeros7.this, mex_audio[num]);
                    }
                    mediaPlayer.start();
                }
            });
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros6.class);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ResourcesActivity.class);
                startActivity(intent);
            }
        });
    }
}

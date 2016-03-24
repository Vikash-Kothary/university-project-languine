package com.puzzle.languine;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.puzzle.languine.R;

public class Numeros3 extends AppCompatActivity {

    private int[] number_id = {R.id.twohundredtwo, R.id.threehundredthree, R.id.fourhundredfour,R.id.fivehundredfive, R.id.sixhundredsix,
            R.id.sevenhundredseven, R.id.eighthundredright, R.id.ninehundrednine, R.id.onethousand};

    private int[] spa_audio = {R.raw.n202m,R.raw.n303m,R.raw.n404_s, R.raw.n505_s, R.raw.n606_s, R.raw.n707_s, R.raw.n808_s,
            R.raw.n909_s, R.raw.n1000_s};

    private int[] mex_audio = {R.raw.n202m,R.raw.n303m,R.raw.n404m, R.raw.n505m, R.raw.n606m, R.raw.n707m, R.raw.n808m,
            R.raw.n909_m, R.raw.n1000m};

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for ( int i = 0; i < number_id.length; ++i) {
            ImageButton imageButton = (ImageButton) findViewById(number_id[i]);
            final int num = i;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Numeros.spanish)
                    {
                        mediaPlayer = MediaPlayer.create(Numeros3.this, spa_audio[num]);
                    }
                    else
                    {
                        mediaPlayer = MediaPlayer.create(Numeros3.this, mex_audio[num]);
                    }
                    mediaPlayer.start();
                }
            });
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros2.class);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros4.class);
                startActivity(intent);
            }
        });
    }

}

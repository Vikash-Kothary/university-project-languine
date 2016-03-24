package com.puzzle.languine;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.puzzle.languine.ui.MaterialActivity;

public class Numeros6 extends MaterialActivity {


    private int[] number_id = {R.id.million, R.id.twomillion, R.id.fivehundredmillion, R.id.onethousandmillion, R.id.onethousandtwohundredmillion,
    R.id.fivethousandmillion,R.id.onebillion, R.id.thousandbillion, R.id.onetrillion, R.id.quadtrillion};

    private int[] spa_audio = {R.raw.n1m_s, R.raw.n2m_s,R.raw.n500m_s,R.raw.n1000m_s, R.raw.n1200m_s,R.raw.n5000m_s,
            R.raw.n1b_s, R.raw.n1000b_s, R.raw.n1t_s,R.raw.ttt };

    private int[] mex_audio = {R.raw.n1mm, R.raw.n2mm,R.raw.n500mm,R.raw.n1000mm, R.raw.n1200mm,R.raw.n5000mm,
            R.raw.n1mm, R.raw.n1000bm, R.raw.n1tm,R.raw.ttt };

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros6);
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
                        mediaPlayer = MediaPlayer.create(Numeros6.this, spa_audio[num]);
                    }
                    else
                    {
                        mediaPlayer = MediaPlayer.create(Numeros6.this, mex_audio[num]);
                    }
                    mediaPlayer.start();
                }
            });
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros5.class);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros7.class);
                startActivity(intent);
            }
        });
    }
}

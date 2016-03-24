package com.puzzle.appname;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.puzzle.appname.R;

public class Numeros2 extends AppCompatActivity {

    private int[] number_id = {R.id.forty,R.id.fifty, R.id.sixty, R.id.seventy, R.id.eighty,R.id.ninety, R.id.onehundred};

    private int[] spa_audio = {R.raw.n40_s, R.raw.n50_s, R.raw.n60_s, R.raw.n70_s, R.raw.n80_s, R.raw.n90_s, R.raw.n100_s };

    private int[] mex_audio = {R.raw.n40m, R.raw.n50m, R.raw.n60m, R.raw.n70m, R.raw.n80m, R.raw.n90m, R.raw.n100m };




    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros2);
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
                        mediaPlayer = MediaPlayer.create(Numeros2.this, spa_audio[num]);
                    }
                    else
                    {
                        mediaPlayer = MediaPlayer.create(Numeros2.this, mex_audio[num]);
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
                Intent intent = new Intent(getBaseContext(), Numeros.class);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros3.class);
                startActivity(intent);
            }
        });
    }
}

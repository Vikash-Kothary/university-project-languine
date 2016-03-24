package com.puzzle.languine;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;

public class Numeros4 extends MaterialActivity {

    private int[] number_id = {R.id.onethousand,R.id.twothousandtwo, R.id.threethousandthreethree, R.id.fourzerozerofour,
            R.id.fivezerofivefive, R.id.sixsixsixsix, R.id.sevensevenzeroseven, R.id.eightzeroeighteight, R.id.ninenineninenine,
            R.id.tenthousand};

    private int[] spa_audio = {R.raw.n1000_s, R.raw.n2002_s, R.raw.n3333_s, R.raw.n4404_s, R.raw.n5055_s, R.raw.n6666_s,
            R.raw.n7707_s, R.raw.n8088_s, R.raw.n9999_s, R.raw.n10000_s};

    private int[] mex_audio = {R.raw.n1000m, R.raw.n2002m, R.raw.n3333m, R.raw.n4404m, R.raw.n5055m, R.raw.n6666m,
            R.raw.n7707m, R.raw.n8088m, R.raw.n9999m, R.raw.n10000m};

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros4);
        setupToolbar();
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
                        mediaPlayer = MediaPlayer.create(Numeros4.this, spa_audio[num]);
                    }
                    else
                    {
                        mediaPlayer = MediaPlayer.create(Numeros4.this, mex_audio[num]);
                    }
                    mediaPlayer.start();
                }
            });
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros3.class);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros5.class);
                startActivity(intent);
            }
        });
    }
}

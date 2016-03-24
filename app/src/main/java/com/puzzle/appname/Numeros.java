package com.puzzle.appname;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.puzzle.appname.R;

public class Numeros extends AppCompatActivity {

    private int[] number_id = {R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine,
            R.id.ten, R.id.eleven, R.id.twelve, R.id.thirteen, R.id.fourteen, R.id.fifteen, R.id.sixteen, R.id.seventeen,
            R.id.eighteen, R.id.nineteen, R.id.twenty, R.id.twentyone, R.id.twentytwo, R.id.twentythree, R.id.twentyfour,
            R.id.twentyfive, R.id.twentysix, R.id.twentyseven, R.id.twentyeight, R.id.twentynine, R.id.thirty, R.id.thirtyone};

    private int[] audio = {R.raw.n01, R.raw.n02, R.raw.n03, R.raw.n04, R.raw.n05, R.raw.n06, R.raw.n07, R.raw.n08, R.raw.n09,
            R.raw.n10, R.raw.n11, R.raw.n12, R.raw.n13, R.raw.n14, R.raw.n15, R.raw.n16, R.raw.n17, R.raw.n18,R.raw.n19, R.raw.n20, R.raw.n21,
            R.raw.n22, R.raw.n23, R.raw.n24, R.raw.n25, R.raw.n26, R.raw.n27, R.raw.n28, R.raw.n29, R.raw.n30, R.raw.n31};
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);
        for ( int i = 0; i < number_id.length; ++i) {
            ImageButton imageButton = (ImageButton) findViewById(number_id[i]);
            final int num = i;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(Numeros.this, audio[num]);
                    mediaPlayer.start();
                }
            });
        }
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros2.class);
                startActivity(intent);
            }
        });
    }
}

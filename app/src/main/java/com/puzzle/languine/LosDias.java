package com.puzzle.languine;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LosDias extends AppCompatActivity {


    MediaPlayer mediaPlayer;

//    private int[] day_id = {};
//
//    private int[] audio = {R.raw.n01, R.raw.n02, R.raw.n03, R.raw.n04, R.raw.n05, R.raw.n06, R.raw.n07, R.raw.n08, R.raw.n09,
//            R.raw.n10, R.raw.n11, R.raw.n12, R.raw.n13, R.raw.n14, R.raw.n15, R.raw.n16, R.raw.n17, R.raw.n18,R.raw.n19, R.raw.n20, R.raw.n21,
//            R.raw.n22, R.raw.n23, R.raw.n24, R.raw.n25, R.raw.n26, R.raw.n27, R.raw.n28, R.raw.n29, R.raw.n30, R.raw.n31};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_los_dias);

//        for ( int i = 0; i < day_id.length; ++i) {
//            ImageButton imageButton = (ImageButton) findViewById(day_id[i]);
//            final int num = i;
//            imageButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mediaPlayer = MediaPlayer.create(LosDias.this, audio[num]);
//                    mediaPlayer.start();
//                }
//            });
//        }

    }

}

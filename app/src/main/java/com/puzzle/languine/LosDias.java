package com.puzzle.languine;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.puzzle.languine.ui.MaterialActivity;

public class LosDias extends MaterialActivity {

    MediaPlayer mediaPlayer;

    private int[] day_id = {R.id.monBtn, R.id.tueBtn, R.id.wedBtn, R.id.thuBtn, R.id.friBtn, R.id.satBtn, R.id.sunBtn};

    private int[] audio = {R.raw.d01_slunes, R.raw.d02_smartes, R.raw.d03_smiercoles, R.raw.d04_sjueves, R.raw.d05_sviernes,
            R.raw.d06_ssabado, R.raw.d07_sdomingo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_los_dias);
        for (int i = 0; i < day_id.length; ++i) {
            ImageButton imageButton = (ImageButton) findViewById(day_id[i]);

            final int num = i;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(LosDias.this, audio[num]);
                    mediaPlayer.start();
                }
            });
        }
        addFragment(new com.puzzle.languine.RecordingToolFragment());
    }

}

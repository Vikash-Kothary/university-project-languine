package com.puzzle.languine;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.puzzle.languine.ui.MaterialActivity;

public class LosDias extends MaterialActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_los_dias);
        setupToolbar();

        /*for ( int i = 0; i < number_id.length; ++i) {
            ImageButton imageButton = (ImageButton) findViewById(number_id[i]);
            final int num = i;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(LosDias.this, audio[num]);
                    mediaPlayer.start();
                }
            });
        }*/
    }
}

package com.puzzle.appname;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.puzzle.appname.R;

public class Alphabeto extends AppCompatActivity {

    public static boolean spanish = true;

    private int[] alpha_id = {R.id.a, R.id.b, R.id.c, R.id.ch, R.id.d, R.id.e, R.id.f, R.id.g, R.id.h, R.id.i, R.id.j, R.id.k, R.id.ll,
            R.id.m, R.id.n, R.id.ene, R.id.o, R.id.p, R.id.q, R.id.r, R.id.s, R.id.t, R.id.u, R.id.v, R.id.w, R.id.x, R.id.y, R.id.z};

    private int[] spa_audio = {R.raw.as, R.raw.bs, R.raw.cs, R.raw.chs, R.raw.ds, R.raw.es, R.raw.fs, R.raw.gs, R.raw.hs,
            R.raw.is, R.raw.js, R.raw.ks, R.raw.lls, R.raw.ms, R.raw.ns, R.raw.n2s, R.raw.os, R.raw.ps, R.raw.qs, R.raw.rs, R.raw.ss,
            R.raw.ts, R.raw.us, R.raw.vs, R.raw.ws, R.raw.xs, R.raw.ys, R.raw.zs};

    private int[] mex_audio = {R.raw.a, R.raw.b, R.raw.c, R.raw.ch, R.raw.d, R.raw.e, R.raw.f, R.raw.g, R.raw.h,
            R.raw.i, R.raw.j, R.raw.k, R.raw.ll, R.raw.m, R.raw.n, R.raw.n, R.raw.o, R.raw.p, R.raw.q, R.raw.r, R.raw.s,
            R.raw.t, R.raw.u, R.raw.v, R.raw.w, R.raw.x, R.raw.y, R.raw.z};





    RelativeLayout layout1;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabeto);

        String language = getIntent().getStringExtra(SpaMex.LANGUAGE);
        if (language.equals("Mexican")) {
            spanish = false;
        } else {
            spanish = true;
        }

        for (int i = 0; i < alpha_id.length; ++i) {
            ImageButton imageButton = (ImageButton) findViewById(alpha_id[i]);
            final int num = i;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Alphabeto.spanish) {
                        System.out.println(spa_audio[num]);
                        System.out.println(mex_audio[num]);
                        mediaPlayer = MediaPlayer.create(Alphabeto.this, spa_audio[num]);
                    } else {
                        mediaPlayer = MediaPlayer.create(Alphabeto.this, mex_audio[num]);
                    }
                    if(mediaPlayer!=null) mediaPlayer.start();
                }
            });
        }
    }
}

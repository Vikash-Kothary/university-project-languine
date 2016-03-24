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

    private int[] alpha_id = {R.id.a,R.id.b,R.id.c,R.id.ch,R.id.d,R.id.e,R.id.f,R.id.g,R.id.h,R.id.i,R.id.j,R.id.k,R.id.ll,
            R.id.m,R.id.n,R.id.ene,R.id.o,R.id.p,R.id.q,R.id.r,R.id.s,R.id.t,R.id.u,R.id.v,R.id.w,R.id.x,R.id.y,R.id.z};

    private int[] spa_audio = {R.raw.a_s,R.raw.b_s, R.raw.c_s,R.raw.ch_s,R.raw.d_s,R.raw.e_s,R.raw.f_s,R.raw.g_s,R.raw.h_s,
            R.raw.i_s,R.raw.j_s,R.raw.k_s,R.raw.ll_s,R.raw.m_s,R.raw.n_s,R.raw.n_s,R.raw.o_s,R.raw.p_s,R.raw.q_s,R.raw.r_s,R.raw.s_s,
            R.raw.t_s,R.raw.u_s,R.raw.v_s,R.raw.w_s,R.raw.x_s,R.raw.y_s,R.raw.z_s};

    private int[] mex_audio = {R.raw.a_m,R.raw.b_m, R.raw.c_m,R.raw.ch_m,R.raw.d_m,R.raw.e_m,R.raw.f_m,R.raw.g_m,R.raw.h_m,
            R.raw.i_m,R.raw.j_m,R.raw.k_m,R.raw.ll_m,R.raw.m_m,R.raw.n_m,R.raw.n_m,R.raw.o_m,R.raw.p_m,R.raw.q_m,R.raw.r_m,R.raw.s_m,
            R.raw.t_m,R.raw.u_m,R.raw.v_m,R.raw.w_m,R.raw.x_m,R.raw.y_m,R.raw.z_m};

    RelativeLayout layout1;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabeto);
        for (final int id :alpha_id
             ) {
            ImageButton im = (ImageButton) findViewById(id);
            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(Alphabeto.this, mex_audio[id]);
                    mediaPlayer.start();
                }
            });

        }

    }
}

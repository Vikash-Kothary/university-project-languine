package com.puzzle.appname;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Numeros5 extends AppCompatActivity {


    private int[] number_id = {R.id.eleventhousand, R.id.fifteenthousand, R.id.eighteenthousand, R.id.twentytwothousand,
            R.id.twentyeightthousand, R.id.thirtyseventhousand, R.id.eightyfivethousand, R.id.hundredthousand,
            R.id.onezeroeightthousand, R.id.onehundredsixtythousand, R.id.fivesevenfivethousand, R.id.nineeightsixthousand};

    private int[] spa_audio = {R.raw.n11k_s,R.raw.n15k_s, R.raw.n18k_s, R.raw.n22k_s, R.raw.n28k_s,R.raw.n37k_s,R.raw.n85k_s,
            R.raw.n100k_s, R.raw.n108k_s, R.raw.n160k_s, R.raw.n575k_s, R.raw.n986k_s};

    private int[] mex_audio = {R.raw.n11km,R.raw.n15km, R.raw.n18km, R.raw.n22km, R.raw.n28km,R.raw.n37km,R.raw.n85km,
            R.raw.n100km, R.raw.n108km, R.raw.n160km, R.raw.n575km, R.raw.n986km};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros5);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros4.class);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Numeros6.class);
                startActivity(intent);
            }
        });
    }
}

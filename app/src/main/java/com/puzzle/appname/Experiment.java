package com.puzzle.appname;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class Experiment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(Exercises.EXERCISE_TITLE));
        setSupportActionBar(toolbar);

        /*RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        //cardList.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);



        ArrayList<Lessons> myDataset = new ArrayList<>();



        myDataset.add(new Lessons(R.mipmap.ic_launcher, "Revision Videos", 59));
        myDataset.add(new Lessons(R.mipmap.ic_launcher, "Exercises", 79));
        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);*/
    }

}

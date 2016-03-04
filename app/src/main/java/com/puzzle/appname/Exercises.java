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

public class Exercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        //use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        ArrayList<Lesson> myDataset = new ArrayList<>();
        myDataset.add(new Lesson(R.mipmap.ic_launcher, "Revision Videos",100));
        myDataset.add(new Lesson(R.mipmap.ic_launcher, "Exercises", 100));


        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);
    }

}

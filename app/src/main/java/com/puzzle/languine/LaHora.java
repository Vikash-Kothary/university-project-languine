package com.puzzle.languine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.puzzle.languine.datamodel.Hour;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.adapter.LaHoraAdapter;

import java.util.ArrayList;

public class LaHora extends MaterialActivity
{
    private int[] images = {R.drawable.clock1,R.drawable.clock2,R.drawable.clock3,R.drawable.clock4,R.drawable.clock5,R.drawable.clock6};
    private String[] times = {"Son la doce en punto","Son las doce y cinco","Son las doce y cuarto","Son las doce y media","Es la una menos cuarto","Es la una menos diez"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_la_hora);
        setupToolbar();

        setupRecyclerView();
    }

    private void setupRecyclerView()
    {
        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        // use a linear layout manager
        cardList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // specify an adapter (see also next example)
        ArrayList<Hour> myDataset = new ArrayList<Hour>();
        for (int i = 0; i < times.length; ++i)
        {
            myDataset.add(new Hour(images[i], times[i]));
        }

        LaHoraAdapter laHoraAdapter = new LaHoraAdapter(myDataset);
        cardList.setAdapter(laHoraAdapter);
    }
}

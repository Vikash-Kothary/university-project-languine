package com.puzzle.appname;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class Estaciones extends AppCompatActivity
{
    private String[] seasons = {"Primavera","Verano","Otoño","Invierno"};
    private String[] months = {"marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre","enero","febrero"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estaciones);

        setupRecyclerView();
    }

    private void setupRecyclerView()
    {
        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<Estacion> myDataset = new ArrayList<Estacion>();
        for (int i = 0; i < seasons.length; ++i) {
            myDataset.add(new Estacion(seasons[i],months[i*3],months[i*3+1],months[i*3+2]));
        }

        EstacionesAdapter estacionesAdapter = new EstacionesAdapter(myDataset);
        cardList.setAdapter(estacionesAdapter);
        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), GetStarted.class);
                        startActivity(intent);
                    }
                })
        );
    }
}

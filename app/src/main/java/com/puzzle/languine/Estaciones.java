package com.puzzle.languine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.puzzle.languine.datamodel.Estacion;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.adapter.EstacionesAdapter;

import java.util.ArrayList;

/**
 * Class which represents the activity for the seasons part of the Resources page.
 */
public class Estaciones extends MaterialActivity
{
    private int[] images = {R.drawable.primavera,R.drawable.verana,R.drawable.otono,R.drawable.invierno};
    private String[] seasons = {"Primavera","Verano","Otoño","Invierno"};
    private String[] months = {"marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre","enero","febrero"};

    @Override
    /**
     * Creates an instance of the Estaciones object.
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estaciones);
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
        ArrayList<Estacion> myDataset = new ArrayList<Estacion>();
        for (int i = 0; i < seasons.length; ++i)
        {
            myDataset.add(new Estacion(images[i],seasons[i],months[i*3],months[i*3+1],months[i*3+2]));
        }

        EstacionesAdapter estacionesAdapter = new EstacionesAdapter(myDataset);
        cardList.setAdapter(estacionesAdapter);
    }
}

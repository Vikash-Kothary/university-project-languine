package com.puzzle.appname;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Festividades extends AppCompatActivity {

    private int[] span_images = {R.drawable.fest1,R.drawable.fest2,R.drawable.fest3, R.drawable.fest4, R.drawable.fest5, R.drawable.fest6,R.drawable.fest7, R.drawable.fest8, R.drawable.fest9, R.drawable.fest10};
    //private int[] mex_images = {R.drawable.fest1,R.drawable.fest2};

    private String[] dates = {"Uno de enero","Seis de enero", "Marzo o abril", "Uno de mayo", "25 de julio", "12 de octubre", "Uno de noviembre", "Seis de diciembre", "Ocho de diciembre", "25 de diciembre"};
    private String[] spanishNames = {"Año Nuevo"," Día de Reyes", "Semanta santa", "Día del Trabajo", "Día de Santiago", "Día de la Hispanidad", "Día de Todos los Santos", "Día de la Constitucíon Española", "Inmaculada Conceptíon", "Día de la Navidad"};
    private String[] englishNames = {"New Year's Day","Epiphany", "Easter(March or April)", "Labour Day", "St James' Day", "Columbus Day(National Day)", "All Saints' Day", "Constitution Day", "Immaculate Conception", "Christmas Day"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festividades_spa);

        setupRecyclerView();
    }

    private void setupRecyclerView()
    {
        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        // use a linear layout manager
        cardList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // specify an adapter (see also next example)
        ArrayList<Festividad> myDataset = new ArrayList<Festividad>();
        for (int i = 0; i < dates.length; ++i)
        {
            myDataset.add(new Festividad(span_images[i], dates[i], spanishNames[i], englishNames[i]));
        }

        FestividadesAdapter festividadesAdapter = new FestividadesAdapter(myDataset);
        cardList.setAdapter(festividadesAdapter);
    }
}

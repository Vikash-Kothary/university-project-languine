package com.puzzle.appname;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.puzzle.appname.GetStarted;
import com.puzzle.appname.Lesson;
import com.puzzle.appname.MyAdapter;
import com.puzzle.appname.R;
import com.puzzle.appname.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;

public class Resources extends AppCompatActivity {

    ArrayList<Integer> ResourceImages;
    ArrayList<String> ResourceNames;
    public static final String RESOURCE_TITLE = "resource-title";
    public static final String RESOURCE_NUMBER = "resource-number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);



        ResourceImages = new ArrayList<Integer>(
                Arrays.asList(R.drawable.alphabeto)
        );
        ResourceNames = new ArrayList<>(
                Arrays.asList("El Alphabeto", "Los Numeros", "Los Dias", "El Calendario", "Festivadades", "Estaciones y Meses", "La hora")
        );

        //Toolbar toolbar = setupToolbar();
        setupRecyclerView();

    }

    private Toolbar setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        return toolbar;
    }

    private void setupRecyclerView() {
        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<Lesson> myDataset = new ArrayList<Lesson>();
        for (int i = 0; i < ResourceNames.size(); ++i) {
            myDataset.add(new Lesson(R.mipmap.ic_launcher, i + 1 + ". " + ResourceNames.get(i), 0));
        }

        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);
        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position)
                    {
                        if(position == 0 || position == 1 || position == 4)
                        {
                            Intent intent = new Intent(getBaseContext(), SpaMex.class);
                            //intent.putExtra(RESOURCE_TITLE, ResourceNames.get(position));
                            intent.putExtra(RESOURCE_NUMBER, ""+position);
                            //intent.putExtra(LESSON_NUMBER, (position + 1) + "");
                            startActivity(intent);
                        }
                        else if(position == 2)
                        {
                            Intent intent = new Intent(getBaseContext(), LosDias.class);
                            startActivity(intent);
                        }
                        else if(position == 3)
                        {
                            Intent intent = new Intent(getBaseContext(), ElCalendario.class);
                            startActivity(intent);
                        }
                        else if(position == 5)
                        {
                            Intent intent = new Intent(getBaseContext(), Estaciones.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getBaseContext(), LaHora.class);
                            startActivity(intent);
                        }
                    }
                })
        );

    }

}

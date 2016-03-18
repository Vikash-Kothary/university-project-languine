package com.puzzle.appname;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toolbar;

import com.puzzle.appname.ui.activity.Alphabeto;
import com.puzzle.appname.ui.activity.LessonSelectActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class SpaMex extends AppCompatActivity {

    ArrayList<Integer> SpaMaxImages;
    ArrayList<String> SpaMaxNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa_mex);

        SpaMaxNames = new ArrayList<>(
                Arrays.asList("Spanish", "Mexican")
        );

        //Toolbar toolbar = setupToolbar();
        setupRecyclerView();

    }


    private void setupRecyclerView() {
        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<Lesson> myDataset = new ArrayList<Lesson>();
        for (int i = 0; i < SpaMaxNames.size(); ++i) {
            myDataset.add(new Lesson(R.mipmap.ic_launcher, i + 1 + ". " + SpaMaxNames.get(i), 0));
        }

        final int resourceNumber = Integer.parseInt(getIntent().getStringExtra(Resources.RESOURCE_NUMBER));

        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);
        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (resourceNumber == 0) {
                            Intent intent = new Intent(getBaseContext(), Alphabeto.class);
                            startActivity(intent);
                        } else if(resourceNumber == 1) {
                            Intent intent = new Intent(getBaseContext(), Numeros.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(getBaseContext(), Festividades.class);
                            startActivity(intent);
                        }


                    }
                })
        );

    }
}

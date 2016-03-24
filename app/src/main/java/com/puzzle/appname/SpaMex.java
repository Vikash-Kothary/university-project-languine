package com.puzzle.appname;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class SpaMex extends AppCompatActivity {

    public static final String LANGUAGE = "Language";

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
        RecyclerView cardList = (RecyclerView) findViewById(R.id.languages_1);
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
                    public void onItemClick(View view, int position)
                    {
                        Intent intent = new Intent(getBaseContext(), Festividades.class);
                        if(position == 0)
                        {
                            intent.putExtra(LANGUAGE,"Spanish");
                        }
                        else
                        {
                            intent.putExtra(LANGUAGE,"Mexican");
                        }
                        startActivity(intent);
                    }
                })
        );
    }
}

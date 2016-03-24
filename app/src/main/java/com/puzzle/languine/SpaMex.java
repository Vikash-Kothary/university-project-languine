package com.puzzle.languine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.MaterialRecyclerView;
import com.puzzle.languine.ui.adapter.MenuAdapter;
import com.puzzle.languine.utils.IntentConts;

import java.util.ArrayList;
import java.util.Arrays;

public class SpaMex extends MaterialActivity implements MaterialRecyclerView.OnItemClickListener {
    ArrayList<Integer> spaMexImages;
    ArrayList<String> spaMexNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa_mex);

        spaMexNames = new ArrayList<>(
                Arrays.asList("Spanish", "Mexican")
        );

        setupToolbar();
        MenuAdapter menuAdapter = new MenuAdapter(spaMexNames);
        new MaterialRecyclerView(this,menuAdapter);
    }

    @Override
    public void onItemClick(View view, int position)
    {
        Intent intent = null;
        switch ((getIntent().getIntExtra(IntentConts.RESOURCE_NUM, 0)))
        {
            case 0:
                intent = new Intent(getBaseContext(), Alphabeto.class);
                break;
            case 1:
                intent = new Intent(getBaseContext(), Numeros.class);
                break;
            case 4:
                intent = new Intent(getBaseContext(), Festividades.class);
                break;
        }
        intent.putExtra(IntentConts.LANGUAGE, spaMexNames.get(position));
        startActivity(intent);
    }
}

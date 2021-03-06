package com.puzzle.languine.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.puzzle.languine.ElCalendario;
import com.puzzle.languine.Estaciones;
import com.puzzle.languine.LaHora;
import com.puzzle.languine.LosDias;
import com.puzzle.languine.R;
import com.puzzle.languine.SpaMex;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.MaterialRecyclerView;
import com.puzzle.languine.ui.adapter.MenuAdapter;
import com.puzzle.languine.utils.IntentConts;

import java.util.ArrayList;
import java.util.Arrays;

public class ResourcesActivity extends MaterialActivity implements MaterialRecyclerView.OnItemClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        setupToolbar();

        ArrayList<Integer> resourceImages = new ArrayList<Integer>(
                Arrays.asList(R.drawable.el_alphabeto, R.drawable.los_numeros, R.drawable.los_dias, R.drawable.el_calendario, R.drawable.festivadades, R.drawable.estaciones_y_meses, R.drawable.la_hora)
        );
        ArrayList<String> resourceNames = new ArrayList<>(
                Arrays.asList("El Alphabeto", "Los Numeros", "Los Dias", "El Calendario", "Festivadades", "Estaciones y Meses", "La hora")
        );

        MenuAdapter adapter = new MenuAdapter(resourceNames, resourceImages);
        new MaterialRecyclerView(this, adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = null;
        if (position == 0 || position == 1 || position == 4) {
            intent = new Intent(ResourcesActivity.this, SpaMex.class);
            intent.putExtra(IntentConts.RESOURCE_NUM, position);
            //intent.putExtra(RESOURCE_TITLE, ResourceNames.get(position));
        } else if (position == 2) {
            intent = new Intent(ResourcesActivity.this, LosDias.class);
        } else if (position == 3) {
            intent = new Intent(ResourcesActivity.this, ElCalendario.class);
        } else if (position == 5) {
            intent = new Intent(ResourcesActivity.this, Estaciones.class);
        } else {
            intent = new Intent(ResourcesActivity.this, LaHora.class);
        }
        startActivity(intent);
    }

}

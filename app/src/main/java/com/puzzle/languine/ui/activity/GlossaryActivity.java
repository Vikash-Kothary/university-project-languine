package com.puzzle.languine.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.MaterialRecyclerView;
import com.puzzle.languine.ui.adapter.GlossaryAdapter;
import com.puzzle.languine.GlossaryData;
import com.puzzle.languine.R;
import com.puzzle.languine.Translation;

import java.util.ArrayList;

public class GlossaryActivity extends MaterialActivity implements MaterialRecyclerView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_glossary);

        setupToolbar();
//        setupTabLayout({"EN -> S"});

        GlossaryData gc = new GlossaryData();
        String glossaryText = getResources().getString(R.string.glossary_text);
        ArrayList<Translation> translations = gc.getTranslations(glossaryText);// specify an adapter (see also next example)
        GlossaryAdapter adapter = new GlossaryAdapter(translations);
//        setupRecyclerView(adapter, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.glossary_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}

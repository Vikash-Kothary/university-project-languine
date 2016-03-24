package com.puzzle.languine.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.MaterialRecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puzzle.languine.ui.adapter.GlossaryAdapter;
import com.puzzle.languine.GlossaryData;
import com.puzzle.languine.R;
import com.puzzle.languine.Translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GlossaryActivity extends MaterialActivity implements MaterialRecyclerView.OnItemClickListener {

    private ArrayList<String> alphabet = new ArrayList<>();
    private HashMap<String, Integer> sections = new HashMap<String, Integer>();
    private GestureDetector mGestureDetector;
    private int sideIndexHeight;
    private static float sideIndexX;
    private static float sideIndexY;

    class AlphabetListGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            sideIndexX = sideIndexX - distanceX;
            sideIndexY = sideIndexY - distanceY;

            if (sideIndexX >= 0 && sideIndexY >= 0) {
                displayListItem();
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);

        setupToolbar();

        GlossaryData gc = new GlossaryData();
        String glossaryText = getResources().getString(R.string.glossary_text);
        ArrayList<Translation> translations = gc.getTranslations(glossaryText);// specify an adapter (see also next example)
        GlossaryAdapter adapter = new GlossaryAdapter(translations);

        mGestureDetector = new GestureDetector(this, new AlphabetListGestureListener());
        setUpRecyclerView();
    }

    public void setUpRecyclerView() {
        RecyclerView translationList = (RecyclerView) findViewById(R.id.translation_list);
        translationList.setHasFixedSize(false);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        translationList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        GlossaryData gc = new GlossaryData();
        String glossaryText = getResources().getString(R.string.glossary_text);
        ArrayList<Translation> translations = gc.getTranslations(glossaryText);

        createAlphabet(translations);

        GlossaryAdapter gAdapter = new GlossaryAdapter(translations);
        translationList.setAdapter(gAdapter);

        populateAlphabet();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        } else {
            return false;
        }
    }

    public void displayListItem(){
        RecyclerView translationList = (RecyclerView) findViewById(R.id.translation_list);

        LinearLayout sideIndex = (LinearLayout) findViewById(R.id.alphabetView);

        sideIndexHeight = sideIndex.getHeight();
        // compute number of pixels for every side index item
        double pixelPerIndexItem = (double) sideIndexHeight / alphabet.size();

        // compute the item index for given event position belongs to
        int itemPosition = (int) (sideIndexY / pixelPerIndexItem);

        // get the item (we can do it since we know item index)
        if (itemPosition < alphabet.size()) {
            String indexItem = alphabet.get(itemPosition);
            int subitemPosition = sections.get(indexItem) - 3;

            LinearLayoutManager layoutManager = (LinearLayoutManager) translationList.getLayoutManager();
            layoutManager.scrollToPositionWithOffset(subitemPosition, 0);
        }
    }

    private void createAlphabet(ArrayList<Translation> translations) {
        String letter = "";
        String previousLetter = letter;
        int start = 0;
        for (Translation t: translations) {
            start++;
            String word = t.getSpanishWord();
            if (word.length() > 0) {
                letter = word.substring(0, 1).toUpperCase();
                if(!alphabet.contains(letter)) {
                    alphabet.add(letter);
                    if (!letter.equals(previousLetter)) {
                        sections.put(letter, start);
                    }
                }
            }
            Collections.sort(alphabet);
        }
    }

    private void populateAlphabet() {
        LinearLayout alphabetList = (LinearLayout) findViewById(R.id.alphabetView);


        TextView tmpTV;
        for (String letter: alphabet) {
            tmpTV = new TextView(this);
            tmpTV.setTextColor(Color.WHITE);
            tmpTV.setText(letter);
            tmpTV.setGravity(Gravity.CENTER);

            tmpTV.setTextSize(15);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            tmpTV.setLayoutParams(params);

            alphabetList.addView(tmpTV);
        }

        alphabetList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // now you know coordinates of touch
                sideIndexX = event.getX();
                sideIndexY = event.getY();

                // and can display a proper item it country list
                displayListItem();

                return false;
            }
        });
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

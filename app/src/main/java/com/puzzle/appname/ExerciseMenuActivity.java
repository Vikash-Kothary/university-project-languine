package com.puzzle.appname;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ExerciseMenuActivity extends AppCompatActivity {
    String[] menu = {"Observe", "Reflect", "Experiment"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra(LessonSelectActivity.LESSON_TITLE));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createButtons();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createButtons() {
        LinearLayout rl = (LinearLayout) findViewById(R.id.content_exercise_menu);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < menu.length; i++) {
            Button b = new Button(this);
            b.setLayoutParams(params);
            b.setText(menu[i]);
            rl.addView(b);
        }
    }

}

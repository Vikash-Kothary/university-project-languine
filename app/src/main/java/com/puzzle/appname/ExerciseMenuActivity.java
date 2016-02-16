package com.puzzle.appname;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;

public class ExerciseMenuActivity extends AppCompatActivity {
    String[] menu = {"Observe", "Reflect", "Experiment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createButtons();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createButtons(){
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.content_exercise_menu);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


        int prevButID = 0;
        for (int i=0; i<menu.length; i++){
            if(i == 0) { params.addRule(RelativeLayout.ALIGN_PARENT_TOP);}
            else { params.addRule(RelativeLayout.BELOW,prevButID); }

            Button b = new Button(this);
            b.setId(i+5);
            b.setLayoutParams(params);
            b.setText(menu[i]);
            rl.addView(b);
            prevButID = b.getId();
        }
    }

}

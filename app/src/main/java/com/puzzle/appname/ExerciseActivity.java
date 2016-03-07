package com.puzzle.appname;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch(getIntent().getIntExtra(LessonSelectActivity.QUESTION_TYPE, -1)){
            case -1:
                break;
            case QuestionType.TEXT:
                setContentView(R.layout.activity_exercise);
                break;
            case QuestionType.PICTURE:
                setContentView(R.layout.fragment_picture_question);
                break;

        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}

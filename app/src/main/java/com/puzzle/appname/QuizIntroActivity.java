package com.puzzle.appname;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.puzzle.appname.Backend.Data;
import com.puzzle.appname.Backend.UnitExercise;

public class QuizIntroActivity extends AppCompatActivity {

    public static final String QUIZ_TYPE = "QUIZ_TYPE";
    public static final String QUIZ_TITLE = "QUIZ_TITLE";
    public static final String LESSON_NUMBER = "NUMBER";
    private String lessonNumber;

    private String quizType;
    private String quizTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_intro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        TextView spaInstructionView = (TextView) findViewById(R.id.description_esp);
        TextView engInstructionView = (TextView) findViewById(R.id.description_en);

        String unitNumber = getIntent().getStringExtra(Experiment.UNIT_NUMBER);
        quizTitle = getIntent().getStringExtra(Experiment.QUIZ_TITLE);
        lessonNumber = (getIntent().getStringExtra(Experiment.LESSON_NUMBER));
        toolbar.setTitle(quizTitle);

        UnitExercise exercise = Data.getExercise(lessonNumber, quizTitle, this);

        spaInstructionView.setText(exercise.getSpanishDescription());
        engInstructionView.setText(exercise.getEnglishDescription());

        quizType = exercise.getType();

        /*setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
<<<<<<< HEAD
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
=======

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
>>>>>>> refs/remotes/origin/xmlEdit
    }

    public void nextButtonClicked(View view){
        Intent i = new Intent(this, ExerciseActivity.class);
        i.putExtra(QUIZ_TYPE, quizType);
        i.putExtra(QUIZ_TITLE, quizTitle);
        i.putExtra(LESSON_NUMBER, lessonNumber);
        startActivity(i);
    }

}

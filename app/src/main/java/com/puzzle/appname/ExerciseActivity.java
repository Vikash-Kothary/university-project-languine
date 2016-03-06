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
        chooseFragment();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        /*Something weird is going on with the toolbar, when trying to use the fragment, 'toolbar' is
        somehow null and the activity therefore crashes...*/
        //toolbar.setTitle(getIntent().getStringExtra(QuizIntroActivity.QUIZ_TITLE));
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*switch(getIntent().getIntExtra(LessonSelectActivity.QUESTION_TYPE, -1)){
            case -1:
                break;
            case QuestionType.TEXT:
                setContentView(R.layout.activity_exercise);
                break;
            case QuestionType.PICTURE:
                setContentView(R.layout.fragment_picture_question);
                break;

        }*/
    }

    private void chooseFragment()
    {
        String quizType = getIntent().getStringExtra(QuizIntroActivity.QUIZ_TYPE);

        switch (quizType)
        {
            case "single":
                setContentView(R.layout.fragment_text_question);
                break;
            case "multiple":
                setContentView(R.layout.activity_content_audio_quiz);
                break;
            case "pictures":
                setContentView(R.layout.fragment_picture_question);
                break;
            case "audio":
                //start ??? New fragment just like textQuestionFragment with an audio as well
                break;
            default:
                //nothing
                break;
        }
    }

}

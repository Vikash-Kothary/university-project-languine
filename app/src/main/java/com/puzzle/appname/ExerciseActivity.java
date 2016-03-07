package com.puzzle.appname;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puzzle.appname.Backend.Data;
import com.puzzle.appname.Backend.UnitExercise;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {
    private String lessonNumber, exerciseName;
    private UnitExercise unitExercise;
    private int questionCounter;
    private String quizType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exerciseName = getIntent().getStringExtra(QuizIntroActivity.QUIZ_TITLE);
        System.out.println("EXERCISE NAME: " + exerciseName);
        lessonNumber = (getIntent().getStringExtra(QuizIntroActivity.LESSON_NUMBER));
        //toolbar.setTitle(exerciseName);

        unitExercise = Data.getExercise(lessonNumber, exerciseName, this.getApplicationContext());

        chooseFragment();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        questionCounter = 1;
        //TODO increase questionCounter when the user clicks next (goes to the next question)

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

    /**
     * Writes the questions and possible answers
     * into the fragment. Can be used every time
     * the user goes to the next question.
     */
    private void populateFragment(String quizType){
        //TODO for now it just works for single choice text questions
        if(questionCounter <= unitExercise.getQuestionsNumber())
        {
            if(quizType.equals("single"))
            {
                TextView questionText = (TextView) findViewById(R.id.question);
                questionText.setText(unitExercise.getQuestion(questionCounter-1).getQuestionText());
                RadioGroup possibleAnswers = (RadioGroup) findViewById(R.id.possible_answers);

                if(questionCounter == 1) {
                    possibleAnswers.removeAllViews();
                }

                ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();

                for(String answer: answers) {
                    RadioButton button = new RadioButton(this);
                    button.setText(answer);
                    possibleAnswers.addView(button);
                }
            }
            else if(quizType.equals("multiple"))
            {
                //set the audio file to be played to the correct audio file
                
                RelativeLayout possibleAnswers = (RelativeLayout) findViewById(R.id.multiple_possible_answers);

                if(questionCounter == 0) {
                    possibleAnswers.removeAllViews();
                }

                ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();

                for(String answer: answers) {
                    CheckBox box = new CheckBox(this);
                    box.setText(answer);
                    possibleAnswers.addView(box);
                }
            }
            else if(quizType.equals("pictures"))
            {
                TextView questionText = (TextView) findViewById(R.id.picture_question);
                questionText.setText(unitExercise.getQuestion(questionCounter-1).getQuestionText());
                ImageView[] images = {
                        (ImageView) findViewById(R.id.picture1),
                        (ImageView) findViewById(R.id.picture2),
                        (ImageView) findViewById(R.id.picture3),
                        (ImageView) findViewById(R.id.picture4),
                        (ImageView) findViewById(R.id.picture5),
                        (ImageView) findViewById(R.id.picture6),};

                ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();

                for(int i = 0; i < answers.size(); ++i)
                {
                    AssetManager assetManager = getAssets();
                    InputStream istr = null;
                    try
                    {
                        istr = assetManager.open("Spanish/pictures-for-exercises/"+answers.get(i)+".png");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(istr);
                    images[i].setImageBitmap(bitmap);
                }
            }
        }

    }

    private void chooseFragment()
    {
        String quizType = getIntent().getStringExtra(QuizIntroActivity.QUIZ_TYPE);

        switch (quizType) {
            case "single":
                setContentView(R.layout.fragment_text_question);
                questionCounter = 1;
                populateFragment("single");
                quizType="single";
                break;
            case "multiple":
                setContentView(R.layout.activity_content_audio_quiz);
                questionCounter = 0;
                populateFragment("multiple");
                quizType="multiple";
                break;
            case "pictures":
                setContentView(R.layout.fragment_picture_question);
                questionCounter = 1;
                populateFragment("pictures");
                quizType="pictures";
                break;
            case "audio":
                //start ??? New fragment just like textQuestionFragment with an audio as well
                questionCounter = 1;
                populateFragment("audio");
                quizType="audio";
                break;
            default:
                //nothing
                break;
        }
    }
}

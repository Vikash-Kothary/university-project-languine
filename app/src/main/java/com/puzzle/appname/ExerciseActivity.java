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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.puzzle.appname.Backend.Data;
import com.puzzle.appname.Backend.UnitExercise;
import com.puzzle.appname.Backend.UnitQuestion;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        exerciseName = getIntent().getStringExtra(QuizIntroActivity.QUIZ_TITLE);
        System.out.println("EXERCISE NAME: " + exerciseName);
        lessonNumber = (getIntent().getStringExtra(QuizIntroActivity.LESSON_NUMBER));
        toolbar.setTitle(exerciseName);
        unitExercise = Data.getExercise(lessonNumber, exerciseName, this.getApplicationContext());

        chooseFragment();
        questionCounter = 0;
        //TODO increase questionCounter when the user clicks next (goes to the next question)

        /*Something weird is going on with the toolbar, when trying to use the fragment, 'toolbar' is
        somehow null and the activity therefore crashes...*/
        //toolbar.setTitle(getIntent().getStringExtra(QuizIntroActivity.QUIZ_TITLE));
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Writes the questions and possible answers
     * into the fragment. Can be used every time
     * the user goes to the next question.
     */
    private void populateFragment(final String quizType){
        if(questionCounter < unitExercise.getQuestionsNumber())
        {
            Button nextButton = (Button) findViewById(R.id.next_question);
            if(nextButton.getText().equals("NEXT"));
                nextButton.setText("NEXT");

            if(quizType.equals("single"))
            {
                TextView questionText = (TextView) findViewById(R.id.question);
                questionText.setText(unitExercise.getQuestion(questionCounter).getQuestionText());
                RadioGroup possibleAnswers = (RadioGroup) findViewById(R.id.possible_answers);

                if(questionCounter == 0) {
                    possibleAnswers.removeAllViews();
                }

                ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();

                for(String answer: answers) {
                    RadioButton button = new RadioButton(this);
                    button.setText(answer);
                    possibleAnswers.addView(button);
                }
            }
            if(quizType.equals("audio"))
            {
                TextView questionTextView = (TextView) findViewById(R.id.question);
                String questionLine = (unitExercise.getQuestion(questionCounter).getQuestionText());
                String[] questionArr = questionLine.split(",");
                if(questionArr.length > 1){ //set question text to be the second thing after the comma
                    questionTextView.setText(questionArr[1]);
                }
                RadioGroup possibleAnswers = (RadioGroup) findViewById(R.id.radio_possible_answers);

                if(questionCounter == 0) {
                    possibleAnswers.removeAllViews();
                }

                ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();

                for(String answer: answers) {
                    RadioButton button = new RadioButton(this);
                    button.setText(answer);
                    possibleAnswers.addView(button);
                }
                SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
                seekBar.setVisibility(View.GONE);
            }
            else if(quizType.equals("multiple"))
            {
                //set the audio file to be played to the correct audio file

                LinearLayout possibleAnswers = (LinearLayout) findViewById(R.id.possible_answers);

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
                questionText.setText(unitExercise.getQuestion(questionCounter).getQuestionText());
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
        if(questionCounter == unitExercise.getQuestionsNumber()-1)
        {
            Button nextButton = (Button) findViewById(R.id.next_question);
            nextButton.setText("FINISH");
        }
    }

    public void nextQuestionButtonClicked(View view)
    {
        Button nextButton = (Button) findViewById(R.id.next_question);

        if(quizType.equals("single"))
        {
            RadioGroup possibleAnswers = (RadioGroup) findViewById(R.id.possible_answers);
            String resultMessage = "";
            if(possibleAnswers.getCheckedRadioButtonId() == -1)
            {
                resultMessage = "Please select an answer.";
            }
            else
            {
                int id= possibleAnswers.getCheckedRadioButtonId();
                View radioButton = possibleAnswers.findViewById(id);
                int radioId = possibleAnswers.indexOfChild(radioButton);
                RadioButton selectedButton = (RadioButton) possibleAnswers.getChildAt(radioId);
                String selectedAnswer = (String) selectedButton.getText();
                UnitQuestion currentQuestion = unitExercise.getQuestion(questionCounter);

                if(currentQuestion.checkAnswer(selectedAnswer))
                {
                    resultMessage = "This is the correct answer!";
                }
                else
                {
                    resultMessage = "This is the wrong answer";
                }

                Toast toast = Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT);
                toast.show();

                if(nextButton.getText().equals("NEXT"))
                {
                    LinearLayout possibleAnswersContainer = (LinearLayout) findViewById(R.id.possible_answers);
                    possibleAnswersContainer.removeAllViews();
                    ++questionCounter;
                    populateFragment(quizType);
                }
                else
                {
                    Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                    startActivity(intent);
                }
            }
            Toast toast = Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void previousQuestionButtonClicked(View view)
    {
        LinearLayout possibleAnswers = (LinearLayout) findViewById(R.id.possible_answers);
        possibleAnswers.removeAllViews();
        --questionCounter;
        populateFragment(quizType);
    }

    private void chooseFragment()
    {
        String quizType = getIntent().getStringExtra(QuizIntroActivity.QUIZ_TYPE);
        //FrameLayout frameLayout = (FrameLayout) findViewById(R.id.question_container);

        switch (quizType) {
            case "single":
                setContentView(R.layout.fragment_text_question);
                questionCounter = 0;
                populateFragment("single");
                this.quizType="single";
                break;
            case "multiple":
                setContentView(R.layout.activity_content_audio_quiz);
                questionCounter = 0;
                populateFragment("multiple");
                this.quizType="multiple";
                break;
            case "pictures":
                setContentView(R.layout.fragment_picture_question);
                questionCounter = 0;
                populateFragment("pictures");
                this.quizType="pictures";
                break;
            case "audio":
                setContentView(R.layout.activity_content_audio_quiz);
                questionCounter = 0;
                populateFragment("audio");
                this.quizType="audio";
                break;
            default:
                //nothing
                break;
        }
    }
}

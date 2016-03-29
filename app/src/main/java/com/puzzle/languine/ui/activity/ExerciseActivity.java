package com.puzzle.languine.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.puzzle.languine.R;
import com.puzzle.languine.TODO.Data;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.datamodel.Question;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.fragment.AudioQuizFragment;
import com.puzzle.languine.ui.fragment.PictureQuestionFragment;
import com.puzzle.languine.ui.fragment.QuizIntroFragment;
import com.puzzle.languine.ui.fragment.TextQuestionFragment;
import com.puzzle.languine.utils.IntentConts;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ExerciseActivity extends MaterialActivity implements DialogInterface.OnClickListener
{
    private String lessonNumber, exerciseName, quizType, selectedAnswer, moduleName;
    public static Exercise unitExercise = null;
    public static Question currentQuestion;
    public static int questionCounter;
    public static boolean answered = false, isRightAnswer = false;
    private ImageView selectedImage;
    private ImageView[] images = new ImageView[6];
    private ArrayList<CheckBox> checkBoxes;
    private RadioGroup possibleAnswers;
    private ImageButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise);
        setupToolbar();

        exerciseName = getIntent().getStringExtra(IntentConts.EXERCISE_NAME);
        moduleName = getIntent().getStringExtra(IntentConts.MODULE_NAME);
        lessonNumber = getIntent().getStringExtra(IntentConts.LESSON_NUMBER);
        quizType = getIntent().getStringExtra(IntentConts.QUIZ_TYPE);

        addFragment(new QuizIntroFragment());
        questionCounter = 0;
    }

    /**
     * Writes the questions and possible answers
     * into the fragment. Can be used every time
     * the user goes to the next question.
     */
    /*private void populateFragment(final String quizType) {
        if (questionCounter < unitExercise.getQuestionsNumber()) {
            previousButton.setVisibility(View.VISIBLE);
            nextButton = (ImageButton) findViewById(R.id.next_question);

            switch (quizType) {
                case "single":
                    createTextQuiz();
                    break;
                case "audio":
                    createTextAudioQuiz();
                    break;
                case "multiple":
                    createAudioQuiz();
                    break;
                case "pictures":
                    createPicturesQuiz();
                    break;
            }
        }
    }*/

    public void nextQuestionButtonClicked(View view)
    {
        ImageButton previousButton = (ImageButton) findViewById(R.id.previous_question);
        nextButton = (ImageButton) findViewById(R.id.next_question);

        unitExercise = Data.getExercise(lessonNumber,exerciseName,this);
        currentQuestion = unitExercise.getQuestion(questionCounter);

        if (questionCounter == unitExercise.getQuestionsNumber() - 1) {
            nextButton = (ImageButton) findViewById(R.id.next_question);
            nextButton.setImageResource(R.drawable.finish);
        }
        if (questionCounter == 0) {
            previousButton.setVisibility(View.INVISIBLE);
            chooseFragment();
        }
        else
        {
            if (quizType.equals("single")) {
                possibleAnswers = (RadioGroup) findViewById(R.id.possible_answers);


            } else if (quizType.equals("pictures")) {
                if (selectedImage != null) {
                    getPicturesQuizSelection();
                }
            } else if (quizType.equals("multiple")) {
                if (answerIsSelected()) {
                    getAudioQuizSelection();
                }
            }
        }
        String resultMessage = "";
        if (unitExercise.getSelectedAnswers().size() <= questionCounter) {
            resultMessage = writeToast();
            Toast toast = Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

        if (!resultMessage.equals("Please select an answer."))
        {
            if (unitExercise.getSelectedAnswers().size() <= questionCounter)
            {
                unitExercise.addPairOfAnswers(currentQuestion.getCorrectAnswers().get(0), selectedAnswer);
            }
            removeSelections();
            setUpButtonListener();
        }
    }

    public void previousQuestionButtonClicked(View view) {
        if (!quizType.equals("pictures")) {
            LinearLayout possibleAnswers = (LinearLayout) findViewById(R.id.possible_answers);
            possibleAnswers.removeAllViews();
        }
        --questionCounter;
        currentQuestion = unitExercise.getQuestion(questionCounter);
        chooseFragment();
    }

    private void chooseFragment()
    {
        quizType = getIntent().getStringExtra(IntentConts.QUIZ_TYPE);

        switch (quizType)
        {
            case "single":
                changeFragment(new TextQuestionFragment());
                this.quizType = "single";
                break;
            case "multiple":
                changeFragment(new AudioQuizFragment());
                this.quizType = "multiple";
                break;
            case "pictures":
                changeFragment(new PictureQuestionFragment());
                this.quizType = "pictures";
                break;
            case "audio":
                changeFragment(new AudioQuizFragment());
                this.quizType = "audio";
                break;
            default:
                //nothing
                break;
        }
    }

    private boolean answerIsSelected() {
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                return true;
            }
        }
        return false;
    }

    private void createTextQuiz() {
        TextView questionText = (TextView) findViewById(R.id.question);
        questionText.setText(unitExercise.getQuestion(questionCounter).getQuestionText());
        RadioGroup possibleAnswers = (RadioGroup) findViewById(R.id.possible_answers);

        if (questionCounter == 0) {
            possibleAnswers.removeAllViews();
        }

        ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();

        for (String answer : answers) {
            RadioButton button = new RadioButton(this);
            button.setText(answer);
            possibleAnswers.addView(button);
            if (unitExercise.getSelectedAnswers().size() > questionCounter && !unitExercise.getSelectedAnswers().isEmpty()) {
                button.setEnabled(false);
                //if the answer the user inputted matches that button's text
                if (unitExercise.getSelectedAnswers().get(currentQuestion.getCorrectAnswers().get(0)).equals(answer)) {
                    button.setChecked(true);
                }
            }
        }
    }

    private void createTextAudioQuiz() {
        TextView questionTextView = (TextView) findViewById(R.id.question);
        String questionLine = (unitExercise.getQuestion(questionCounter).getQuestionText());
        String[] questionArr = questionLine.split(",");
        if (questionArr.length > 1) { //set question text to be the second thing after the comma
            questionTextView.setText(questionArr[1]);
        }
        RadioGroup possibleAnswers = (RadioGroup) findViewById(R.id.radio_possible_answers);

        if (questionCounter == 0) {
            possibleAnswers.removeAllViews();
        }

        ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();

        for (String answer : answers) {
            RadioButton button = new RadioButton(this);
            button.setText(answer);
            possibleAnswers.addView(button);
        }
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setVisibility(View.GONE);
    }

    private void createAudioQuiz() {
        //set the audio file to be played to the correct audio file

        LinearLayout possibleAnswers = (LinearLayout) findViewById(R.id.possible_answers);

        if (questionCounter == 0) {
            possibleAnswers.removeAllViews();
        }

        ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();
        checkBoxes = new ArrayList<>();

        for (String answer : answers) {
            CheckBox box = new CheckBox(this);
            box.setText(answer);
            possibleAnswers.addView(box);
            checkBoxes.add(box);
        }
    }

    private void createPicturesQuiz() {
        TextView questionText = (TextView) findViewById(R.id.picture_question);
        questionText.setText(unitExercise.getQuestion(questionCounter).getQuestionText());
        images[0] = (ImageView) findViewById(R.id.picture1);
        images[1] = (ImageView) findViewById(R.id.picture2);
        images[2] = (ImageView) findViewById(R.id.picture3);
        images[3] = (ImageView) findViewById(R.id.picture4);
        images[4] = (ImageView) findViewById(R.id.picture5);
        images[5] = (ImageView) findViewById(R.id.picture6);

        ArrayList<String> answers = unitExercise.getQuestion(questionCounter).getPossibleAnswers();

        for (int i = 0; i < answers.size(); ++i) {
            AssetManager assetManager = getAssets();
            InputStream istr = null;
            try {
                istr = assetManager.open("Spanish/pictures-for-exercises/" + answers.get(i) + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(istr);
            images[i].setImageBitmap(bitmap);

            if (unitExercise.getSelectedAnswers().size() > questionCounter && !unitExercise.getSelectedAnswers().isEmpty()) {
                images[i].clearColorFilter();
                if (unitExercise.getSelectedAnswers().get(currentQuestion.getCorrectAnswers().get(0)).equals(answers.get(i))) {
                    images[i].setColorFilter(Color.argb(70, 31, 190, 214));
                }
                images[i].setEnabled(false);
            } else {
                images[i].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (selectedImage == null || !selectedImage.equals(v)) {
                            for (ImageView image : images) {
                                image.clearColorFilter();
                            }
                            selectedImage = (ImageView) v;
                            ((ImageView) v).setColorFilter(Color.argb(70, 31, 190, 214));
                        }
                    }
                });
            }
        }
    }

    private void getPicturesQuizSelection() {
        for (int i = 0; i < images.length; ++i) {
            if (selectedImage.equals(images[i])) {
                selectedAnswer = currentQuestion.getPossibleAnswers().get(i);
            }
        }
    }

    private void getAudioQuizSelection() {
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                selectedAnswer += checkBox.getText().toString() + ",";
            }
        }
        selectedAnswer = selectedAnswer.substring(0, selectedAnswer.length() - 1);
    }

    private String writeToast() {
        String resultMessage = "";

        if (!answered)
        {
            resultMessage = "Please select an answer.";
        } else if (isRightAnswer) {
            resultMessage = "This is the right answer!";
        } else {
            resultMessage = "This is the wrong answer.";
        }

        return resultMessage;
    }

    private void removeSelections() {
        switch (quizType) {
            case "single":
                possibleAnswers.clearCheck();
                break;
            case "pictures":
                if (selectedImage != null) {
                    selectedImage.clearColorFilter();
                }
                selectedImage = null;
                break;
            case "multiple":
                possibleAnswers.removeAllViews();
                break;
        }
        selectedAnswer = "";
    }

    private void setUpButtonListener() {
        if (questionCounter < unitExercise.getQuestionsNumber() - 1)
        {
            if (!quizType.equals("pictures"))
            {
                LinearLayout possibleAnswersContainer = (LinearLayout) findViewById(R.id.possible_answers);
                possibleAnswersContainer.removeAllViews();
            }
            ++questionCounter;
            currentQuestion = unitExercise.getQuestion(questionCounter);
            chooseFragment();
        } else {
            Intent intent = new Intent(getBaseContext(), ResultActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Are you sure you want to quit the quiz? All your progress will be lost.")
                .setTitle("Rage quit?");

        // Add the buttons
        builder.setPositiveButton("YES", this);
        builder.setNegativeButton("NO", null);

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String[] getResultStrings() {
        String resultMessage = "";
        String resultTitle = "";

        if ((quizType.equals("single") && possibleAnswers.getCheckedRadioButtonId() == -1)
                || (quizType.equals("pictures") && selectedImage == null)
                || (quizType.equals("multiple") && answerIsSelected())) {
            resultMessage = "Please select an answer.";
        } else if (currentQuestion.checkAnswer(selectedAnswer)) {
            resultMessage = "That's right! You selected the correct response.";
            resultTitle = "Correct";
            unitExercise.setScore(unitExercise.getScore() + 10);
        } else {
            resultMessage = "You did not select the correct response.";
            resultTitle = "Incorrect";
        }

        String[] resultStrings = {resultMessage, resultTitle};

        return resultStrings;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onBackPressed();
    }
}

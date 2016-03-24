package com.puzzle.languine.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.puzzle.languine.ui.fragment.TextQuestionFragment;
import com.puzzle.languine.utils.IntentConts;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ExerciseActivity extends MaterialActivity implements DialogInterface.OnClickListener {
    private String lessonNumber, exerciseName, quizType, selectedAnswer;
    private Exercise unitExercise;
    private Question currentQuestion;
    private int questionCounter;
    private ImageView selectedImage;
    private ImageView[] images = new ImageView[6];
    private ArrayList<CheckBox> checkBoxes;
    private RadioGroup possibleAnswers;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exerciseName = getIntent().getStringExtra(IntentConts.QUIZ_TITLE);
        lessonNumber = (getIntent().getStringExtra(IntentConts.LESSON_NUMBER));
        unitExercise = Data.getExercise(lessonNumber, exerciseName, this.getApplicationContext());

        setContentView(R.layout.activity_exercise);
        setupToolbar();

        chooseFragment();
        questionCounter = 0;
    }

    /**
     * Writes the questions and possible answers
     * into the fragment. Can be used every time
     * the user goes to the next question.
     */
    private void populateFragment(final String quizType) {
        Button previousButton = (Button) findViewById(R.id.previous_question);
        if (questionCounter < unitExercise.getQuestionsNumber()) {
            previousButton.setVisibility(View.VISIBLE);
            nextButton = (Button) findViewById(R.id.next_question);
            if (nextButton.getText().equals("NEXT")) ;
            nextButton.setText("NEXT");

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
        if (questionCounter == unitExercise.getQuestionsNumber() - 1) {
            nextButton = (Button) findViewById(R.id.next_question);
            nextButton.setText("FINISH");
        }
        if (questionCounter == 0) {
            previousButton.setVisibility(View.INVISIBLE);
        }
    }

    public void nextQuestionButtonClicked(View view) {
        nextButton = (Button) findViewById(R.id.next_question);
        currentQuestion = unitExercise.getQuestion(questionCounter);

        if (quizType.equals("single")) {
            possibleAnswers = (RadioGroup) findViewById(R.id.possible_answers);

            if (possibleAnswers.getCheckedRadioButtonId() > -1) {
                getTextQuizSelection();
            }
        } else if (quizType.equals("pictures")) {
            if (selectedImage != null) {
                getPicturesQuizSelection();
            }
        } else if (quizType.equals("multiple")) {
            if (answerIsSelected()) {
                getAudioQuizSelection();
            }
        }

        String resultMessage = "";
        if (unitExercise.getSelectedAnswers().size() <= questionCounter) {
            resultMessage = writeToast();
            Toast toast = Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

        if (!resultMessage.equals("Please select an answer.")) {
            if (unitExercise.getSelectedAnswers().size() <= questionCounter) {
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
        populateFragment(quizType);
    }

    private void chooseFragment() {
        String quizType = getIntent().getStringExtra(IntentConts.QUIZ_TYPE);

        switch (quizType) {
            case "single":
                //setContentView(R.layout.fragment_text_question);
                addFragment(new TextQuestionFragment());
                questionCounter = 0;
                populateFragment("single");
                this.quizType = "single";
                break;
            case "multiple":
                //getSupportFragmentManager().beginTransaction().add(R.id.fragment, AudioQuizFragment.newInstance(R.raw.background)).commit();
                //setContentView(R.layout.fragment_audio_quiz);
                addFragment(new AudioQuizFragment());
                questionCounter = 0;
                populateFragment("multiple");
                this.quizType = "multiple";
                break;
            case "pictures":
                //setContentView(R.layout.fragment_picture_question);
                addFragment(new PictureQuestionFragment());
                questionCounter = 0;
                populateFragment("pictures");
                this.quizType = "pictures";
                break;
            case "audio":
                //setContentView(R.layout.fragment_audio_quiz);
                addFragment(new AudioQuizFragment());
                questionCounter = 0;
                populateFragment("audio");
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

    private void getTextQuizSelection() {
        int id = possibleAnswers.getCheckedRadioButtonId();
        View radioButton = possibleAnswers.findViewById(id);
        int radioId = possibleAnswers.indexOfChild(radioButton);
        RadioButton selectedButton = (RadioButton) possibleAnswers.getChildAt(radioId);
        selectedAnswer = (String) selectedButton.getText();
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

        if ((quizType.equals("single") && possibleAnswers.getCheckedRadioButtonId() == -1)
                || (quizType.equals("pictures") && selectedImage == null)
                || (quizType.equals("multiple") && answerIsSelected())) {
            resultMessage = "Please select an answer.";
        } else if (currentQuestion.checkAnswer(selectedAnswer)) {
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
        if (nextButton.getText().equals("NEXT")) {
            if (!quizType.equals("pictures")) {
                LinearLayout possibleAnswersContainer = (LinearLayout) findViewById(R.id.possible_answers);
                possibleAnswersContainer.removeAllViews();
            }
            ++questionCounter;
            currentQuestion = unitExercise.getQuestion(questionCounter);
            populateFragment(quizType);
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

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onBackPressed();
    }
}
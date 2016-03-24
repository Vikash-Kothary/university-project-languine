package com.puzzle.languine.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
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

import com.puzzle.languine.R;
import com.puzzle.languine.TODO.Data;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.datamodel.Question;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.fragment.AudioQuizFragment;
import com.puzzle.languine.ui.fragment.PictureQuestionFragment;
import com.puzzle.languine.ui.fragment.TextQuestionFragment;
import com.puzzle.languine.utils.IntentConts;

import java.util.ArrayList;

public class ExerciseActivityOld extends MaterialActivity
        implements DialogInterface.OnClickListener, TextQuestionFragment.ExerciseData {
    private String lessonNumber, exerciseName, quizType, selectedAnswer;
    private Exercise unitExercise;
    private Question currentQuestion;
    private int questionCounter;
    private ImageView selectedImage;
    private ArrayList<CheckBox> checkBoxes;
    private RadioGroup possibleAnswers;
    private Button nextButton;

    public static final String SCORE = "SCORE";
    public static final String TOTAL_POSS_SCORE = "TOTAL-SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        setupToolbar();
        //getIntent().getStringExtra(IntentConts.);

        chooseFragment();

        Exercise unitExercise = Data.getExercise(lessonNumber, exerciseName, this.getApplicationContext());

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
            if (!quizType.equals("multiple")) {
                previousButton.setVisibility(View.VISIBLE);
            }
            nextButton = (Button) findViewById(R.id.next_question);

            switch (quizType) {
                case "single":
                    //createTextQuiz();
                    break;
                case "audio":
                    createTextAudioQuiz();
                    break;
                case "multiple":
                    createAudioQuiz();
                    break;
                case "pictures":
                    //createPicturesQuiz();
                    break;
            }
        }
        if (questionCounter == unitExercise.getQuestionsNumber() - 1) {
            nextButton = (Button) findViewById(R.id.next_question);
            nextButton.setText("FINISH");
        }
        if (questionCounter == 0 && !quizType.equals("multiple")) {
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
                //getPicturesQuizSelection();
            }
        } else if (quizType.equals("multiple")) {
            if (answerIsSelected()) {
                getAudioQuizSelection();
            }
        }

        String[] resultMessage = getResultStrings();
        if (unitExercise.getSelectedAnswers().size() <= questionCounter) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(resultMessage[0]).setTitle(resultMessage[1]);

            builder.setPositiveButton("CONTINUE", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        }

        if (!resultMessage[0].equals("Please select an answer.")) {
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
        quizType = getIntent().getStringExtra(IntentConts.QUIZ_TYPE);

        switch (quizType) {
            case "single":
                //setContentView(R.layout.fragment_text_question);
                addFragment(TextQuestionFragment.newInstance(questionCounter));
                questionCounter = 0;
                //populateFragment("single");
                this.quizType = "single";
                break;
            case "multiple":
                //getSupportFragmentManager().beginTransaction().add(R.id.fragment, AudioQuizFragment.newInstance(R.raw.background)).commit();
                //setContentView(R.layout.fragment_audio_quiz);
                addFragment(AudioQuizFragment.newInstance(0, questionCounter));
                questionCounter = 0;
                //populateFragment("multiple");
                this.quizType = "multiple";
                break;
            case "pictures":
                //setContentView(R.layout.fragment_picture_question);
                addFragment(PictureQuestionFragment.newInstance(questionCounter));
                questionCounter = 0;
                //populateFragment("pictures");
                this.quizType = "pictures";
                break;
            case "audio":
                setContentView(R.layout.fragment_audio_quiz);
                questionCounter = 0;
                //populateFragment("audio");
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
    }

    private void getTextQuizSelection() {
        int id = possibleAnswers.getCheckedRadioButtonId();
        View radioButton = possibleAnswers.findViewById(id);
        int radioId = possibleAnswers.indexOfChild(radioButton);
        RadioButton selectedButton = (RadioButton) possibleAnswers.getChildAt(radioId);
        selectedAnswer = (String) selectedButton.getText();
    }

    /*private void getPicturesQuizSelection() {
        for (int i = 0; i < images.length; ++i) {
            if (selectedImage.equals(images[i])) {
                selectedAnswer = currentQuestion.getPossibleAnswers().get(i);
            }
        }
    }*/

    private void getAudioQuizSelection() {
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                selectedAnswer += checkBox.getText().toString() + ",";
            }
        }
        selectedAnswer = selectedAnswer.substring(0, selectedAnswer.length() - 1);
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
            //intent.putExtra(IntentConst.REVIEW, unitExercise.getStringForReview());
            intent.putExtra(SCORE, "" + unitExercise.getScore());
            intent.putExtra(TOTAL_POSS_SCORE, "" + unitExercise.getTotalPossibleScore());
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to quit the quiz? All your progress will be lost.")
                .setTitle("Rage quit?");

        builder.setPositiveButton("YES", this);
        builder.setNegativeButton("NO", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onBackPressed();
    }

    @Override
    public Question getQuestion() {
        return unitExercise.getQuestion(questionCounter);
    }

    @Override
    public Exercise getExercise() {
        return unitExercise;
    }

    @Override
    public Question getCurrentQuestion() {
        return currentQuestion;
    }
}

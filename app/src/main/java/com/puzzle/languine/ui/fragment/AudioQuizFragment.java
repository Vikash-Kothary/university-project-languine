package com.puzzle.languine.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.TODO.Data;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.datamodel.Question;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.ui.activity.ResultActivity;
import com.puzzle.languine.utils.IntentConts;

import java.util.ArrayList;

public class AudioQuizFragment extends MaterialFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private String lessonNumber, exerciseName, quizType, selectedAnswer, moduleName;
    private Exercise unitExercise = null;
    private Question currentQuestion;
    private int questionCounter = 0;
    private ImageButton nextButton, soundButton;
    private RadioGroup possibleAnswers;
    private ArrayList<CheckBox> checkBoxes;
    private boolean soundIsPlaying = false;

    public AudioQuizFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_audio_quiz, container, false);

        exerciseName = getActivity().getIntent().getStringExtra(IntentConts.EXERCISE_NAME);
        moduleName = getActivity().getIntent().getStringExtra(IntentConts.MODULE_NAME);
        lessonNumber = getActivity().getIntent().getStringExtra(IntentConts.LESSON_NUMBER);

        unitExercise = Data.getExercise(lessonNumber, exerciseName, getContext());
        currentQuestion = unitExercise.getQuestion(questionCounter);
        nextButton = (ImageButton) findViewById(R.id.next_question);
        soundButton = (ImageButton) findViewById(R.id.play_button);

        createAudioQuiz();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestion = unitExercise.getQuestion(questionCounter);

                if (possibleAnswers.getCheckedRadioButtonId() > -1) {
                    getSelection();
                }

                String[] resultMessage = getResultStrings();
                if (unitExercise.getSelectedAnswers().size() <= questionCounter) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setMessage(resultMessage[0]).setTitle(resultMessage[1]);

                    if (questionCounter < unitExercise.getQuestionsNumber() - 1) {
                        builder.setPositiveButton("CONTINUE", null);
                    } else {
                        builder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), ResultActivity.class);
                                intent.putExtra(IntentConts.QUIZ_SCORE,""+unitExercise.getScore());
                                intent.putExtra(IntentConts.MAX_SCORE,""+unitExercise.getTotalPossibleScore());
                                startActivity(intent);
                            }
                        });
                    }

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                if (!resultMessage[0].equals("Please select an answer.")) {
                    if (unitExercise.getSelectedAnswers().size() <= questionCounter) {
                        unitExercise.addPairOfAnswers(currentQuestion.getCorrectAnswers().get(0), selectedAnswer);
                    }
                    possibleAnswers.removeAllViews();
                    if (questionCounter < unitExercise.getQuestionsNumber() - 1) {
                        LinearLayout possibleAnswersContainer = (LinearLayout) findViewById(R.id.possible_answers);
                        possibleAnswersContainer.removeAllViews();
                        ++questionCounter;
                        currentQuestion = unitExercise.getQuestion(questionCounter);
                        createAudioQuiz();
                    }
                }
            }
        });

        soundButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!soundIsPlaying)
                {
                    SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
                    //int spid = sp.load(Numeros.this, audio[num], 1);
                    //sp.play(spid,1,1,1,0,1);
                }
            }
        });

        return rootView;
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
            CheckBox box = new CheckBox(getContext());
            box.setText(answer);
            possibleAnswers.addView(box);
            checkBoxes.add(box);
        }
        if (questionCounter == unitExercise.getQuestionsNumber() - 1) {
            nextButton = (ImageButton) findViewById(R.id.next_question);
            nextButton.setImageResource(R.drawable.finish);
        }
    }

    private void getSelection() {
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

        if (possibleAnswers.getCheckedRadioButtonId() == -1)
        {
            resultMessage = "Please select an answer.";
        }
        else if (currentQuestion.checkAnswer(selectedAnswer))
        {
            resultMessage = "That's right! You selected the correct response.";
            resultTitle = "Correct";
            unitExercise.setScore(unitExercise.getScore() + 10);
        }
        else
        {
            resultMessage = "You did not select the correct response.";
            resultTitle = "Incorrect";
        }

        String[] resultStrings = {resultMessage, resultTitle};

        return resultStrings;
    }
}

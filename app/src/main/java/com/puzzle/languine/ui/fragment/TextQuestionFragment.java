package com.puzzle.languine.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.puzzle.languine.R;
import com.puzzle.languine.TODO.Data;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.datamodel.Question;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.ui.activity.ExerciseActivity;
import com.puzzle.languine.ui.activity.ResultActivity;
import com.puzzle.languine.utils.IntentConts;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TextQuestionFragment extends MaterialFragment
{
    private String lessonNumber, exerciseName, quizType, selectedAnswer, moduleName;
    private Exercise unitExercise = null;
    private Question currentQuestion;
    private int questionCounter = 0;
    private ImageButton nextButton, previousButton;
    private RadioGroup possibleAnswers;

    public TextQuestionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_text_question, container, false);

        exerciseName = getActivity().getIntent().getStringExtra(IntentConts.EXERCISE_NAME);
        moduleName = getActivity().getIntent().getStringExtra(IntentConts.MODULE_NAME);
        lessonNumber = getActivity().getIntent().getStringExtra(IntentConts.LESSON_NUMBER);

        unitExercise = Data.getExercise(lessonNumber, exerciseName, getContext());
        currentQuestion = unitExercise.getQuestion(questionCounter);
        previousButton = (ImageButton) findViewById(R.id.previous_question);
        nextButton = (ImageButton) findViewById(R.id.next_question);

        createTextQuiz();

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

                    if(questionCounter < unitExercise.getQuestionsNumber() - 1) {
                        builder.setPositiveButton("CONTINUE", null);
                    }
                    else
                    {
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
                    possibleAnswers.clearCheck();
                    if (questionCounter < unitExercise.getQuestionsNumber() - 1) {
                        LinearLayout possibleAnswersContainer = (LinearLayout) findViewById(R.id.possible_answers);
                        possibleAnswersContainer.removeAllViews();
                        ++questionCounter;
                        currentQuestion = unitExercise.getQuestion(questionCounter);
                        createTextQuiz();
                    }
                }
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout possibleAnswers = (LinearLayout) findViewById(R.id.possible_answers);
                possibleAnswers.removeAllViews();
                --questionCounter;
                currentQuestion = unitExercise.getQuestion(questionCounter);
                createTextQuiz();
            }
        });

        return rootView;
    }

    private void createTextQuiz()
    {
        TextView questionText = (TextView) findViewById(R.id.question);
        questionText.setText(currentQuestion.getQuestionText());
        possibleAnswers = (RadioGroup) findViewById(R.id.possible_answers);

        ArrayList<String> answers = currentQuestion.getPossibleAnswers();

        for (String answer : answers)
        {
            RadioButton button = new RadioButton(getContext());
            button.setText(answer);
            possibleAnswers.addView(button);
            if (unitExercise.getSelectedAnswers().size() > questionCounter && !unitExercise.getSelectedAnswers().isEmpty())
            {
                button.setEnabled(false);
                //if the answer the user inputted matches that button's text
                if (unitExercise.getSelectedAnswers().get(currentQuestion.getCorrectAnswers().get(0)).equals(answer)) {
                    button.setChecked(true);
                }
            }
        }
        if (questionCounter == 0) {
            previousButton.setVisibility(View.INVISIBLE);
        }
        else {
            previousButton.setVisibility(View.VISIBLE);
        }
        if (questionCounter == unitExercise.getQuestionsNumber() - 1) {
            nextButton = (ImageButton) findViewById(R.id.next_question);
            nextButton.setImageResource(R.drawable.finish);
        }
    }

    public void getSelection() {
        int id = possibleAnswers.getCheckedRadioButtonId();
        View radioButton = possibleAnswers.findViewById(id);
        int radioId = possibleAnswers.indexOfChild(radioButton);
        RadioButton selectedButton = (RadioButton) possibleAnswers.getChildAt(radioId);
        selectedAnswer = (String) selectedButton.getText();
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

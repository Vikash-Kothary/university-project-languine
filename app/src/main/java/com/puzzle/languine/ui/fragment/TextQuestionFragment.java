package com.puzzle.languine.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.datamodel.Question;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.utils.IntentConts;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TextQuestionFragment extends MaterialFragment
{
    private ExerciseData mExercise;

    public static TextQuestionFragment newInstance(int questionCounter) {
        TextQuestionFragment fragment = new TextQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(IntentConts.QUESTION_COUNTER, questionCounter);
        fragment.setArguments(args);
        return fragment;
    }

    public TextQuestionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_text_question, container, false);

        createTextQuiz();

        return rootView;
    }

    private void createTextQuiz() {
        TextView questionText = (TextView) findViewById(R.id.question);
        questionText.setText(mExercise.getQuestion().getQuestionText());
        RadioGroup possibleAnswers = (RadioGroup) findViewById(R.id.possible_answers);

        ArrayList<String> answers = mExercise.getQuestion().getPossibleAnswers();

        for (String answer : answers) {
            RadioButton button = new RadioButton(getContext());
            button.setText(answer);
            possibleAnswers.addView(button);
            if (mExercise.getExercise().getSelectedAnswers().size() > getArguments().getInt(IntentConts.QUESTION_COUNTER) &&
                    !mExercise.getExercise().getSelectedAnswers().isEmpty()) {
                button.setEnabled(false);
                //if the answer the user inputted matches that button's text
                if (mExercise.getExercise().getSelectedAnswers().get(mExercise.getCurrentQuestion().getCorrectAnswers().get(0)).equals(answer)) {
                    button.setChecked(true);
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ExerciseData) {
            mExercise = (ExerciseData) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mExercise = null;
    }

    public interface ExerciseData {
        Question getQuestion();
        Exercise getExercise();
        Question getCurrentQuestion();
    }
}

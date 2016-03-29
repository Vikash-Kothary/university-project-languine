package com.puzzle.languine.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.TODO.Data;
import com.puzzle.languine.datamodel.Exercise;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.utils.IntentConts;


public class QuizIntroFragment extends MaterialFragment
{
    //private OnNextButtonListener mListener;

    public QuizIntroFragment()
    {
        // Required empty public constructor
    }

    /*public static QuizIntroFragment newInstance(String moduleName, String exerciseName, String moduleNumber)
    {
        QuizIntroFragment fragment = new QuizIntroFragment();
        Bundle args = new Bundle();
        Log.e("NULL","Argument: " + moduleName);
        args.putString(IntentConts.MODULE_NAME, moduleName);
        args.putString(IntentConts.EXERCISE_NAME, exerciseName);
        args.putString(IntentConts.LESSON_NUMBER, moduleNumber);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_quiz_intro, container, false);

        String moduleNumber = getActivity().getIntent().getStringExtra(IntentConts.LESSON_NUMBER);
        String moduleName = getActivity().getIntent().getStringExtra(IntentConts.MODULE_NAME);
        String exerciseName = getActivity().getIntent().getStringExtra(IntentConts.EXERCISE_NAME);

        TextView spanishDesc = (TextView) findViewById(R.id.description_esp);
        TextView englishDesc = (TextView) findViewById(R.id.description_en);

        Exercise unitExercise = Data.getExercise(moduleNumber,exerciseName,getActivity());

        spanishDesc.setText(unitExercise.getSpanishDescription());
        englishDesc.setText(unitExercise.getEnglishDescription());

        ImageButton nextButton = (ImageButton) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String quizType = getActivity().getIntent().getStringExtra(IntentConts.QUIZ_TYPE);
                switch(quizType)
                {
                    case "single":
                        changeFragment(new TextQuestionFragment());
                        break;
                    case "pictures":
                        changeFragment(new PictureQuestionFragment());
                        break;
                    case "multiple":
                        changeFragment(new AudioQuizFragment());
                        break;
                    case "audio":
                        changeFragment(new AudioQuizFragment());
                        break;
                }
            }
        });

        return rootView;
    }

    /*@Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onNextButton();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNextButtonListener) {
            mListener = (OnNextButtonListener) context;
        } /*else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnNextButtonListener {
        void onNextButton();
    }*/
}

package com.puzzle.languine.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.utils.IntentConts;


public class QuizIntroFragment extends MaterialFragment implements View.OnClickListener {

    private OnNextButtonListener mListener;

    public QuizIntroFragment() {
        // Required empty public constructor
    }

    public static QuizIntroFragment newInstance(String moduleName, String exerciseName) {
        QuizIntroFragment fragment = new QuizIntroFragment();
        Bundle args = new Bundle();
        args.putString(IntentConts.MODULE_NAME, moduleName);
        args.putString(IntentConts.EXERCISE_NAME, exerciseName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_quiz_intro, container, false);

        String moduleName = getArguments().getString(IntentConts.MODULE_NAME);
        String exerciseName = getArguments().getString(IntentConts.EXERCISE_NAME);

        TextView spanishDesc = (TextView) findViewById(R.id.description_esp);
        TextView englishDesc = (TextView) findViewById(R.id.description_en);

        // TODO: display descriptions
        spanishDesc.setText("");
        englishDesc.setText("");

        Button button_next = (Button) findViewById(R.id.next_button);
        button_next.setOnClickListener(this);

        return rootView;
    }

    @Override
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
        } else {
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
    }
}

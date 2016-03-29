package com.puzzle.languine.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.ui.activity.ExerciseActivity;
import com.puzzle.languine.utils.IntentConts;

public class ResultExerciseFragment extends MaterialFragment {

    public ResultExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_result_exercise, container, false);
        computeScores();

        return rootView;
    }

    private void computeScores() {
        TextView scoreText = (TextView) findViewById(R.id.score_tv);
        TextView passingScoreText = (TextView) findViewById(R.id.passing_tv);
        TextView resultText = (TextView) findViewById(R.id.result_text);

        int score = Integer.parseInt(getActivity().getIntent().getStringExtra(IntentConts.QUIZ_SCORE));
        int totalPossibleScore = Integer.parseInt(getActivity().getIntent().getStringExtra(IntentConts.MAX_SCORE));
        double passingScore = totalPossibleScore * 0.8;

        scoreText.setText("Your Score: " + score);
        passingScoreText.setText("Passing Score: " + passingScore);

        if (score >= passingScore) {
            resultText.setText("You passed!");
        }
    }
}

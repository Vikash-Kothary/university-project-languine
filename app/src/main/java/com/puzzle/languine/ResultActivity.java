package com.puzzle.languine;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.puzzle.languine.ui.activity.ExerciseActivity;
import com.puzzle.languine.ui.fragment.ReviewExerciseFragment;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Result");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.fragment_result_exercise);
//        computeScores();
    }

    public void reviewButtonClicked(View v) {
        //TODO: check if the fragment to ReviewExerciseFragment works properly
        Fragment reviewFragment = new ReviewExerciseFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        //final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //check if the device is landscape or portrait
        Configuration configuration = getResources().getConfiguration();
        //Configuration configuration = getActivity().getResources().getConfiguration();
        int ori = configuration.orientation;

        fragmentTransaction.replace(R.id.fragment, reviewFragment);

        if (ori == configuration.ORIENTATION_PORTRAIT) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

//    private void computeScores() {
//        TextView scoreText = (TextView) findViewById(R.id.score_tv);
//        TextView passingScoreText = (TextView) findViewById(R.id.passing_tv);
//        TextView resultText = (TextView) findViewById(R.id.result_text);
//
//        int score = Integer.parseInt(getIntent().getStringExtra(ExerciseActivity.SCORE));
//        int totalPossibleScore = Integer.parseInt(getIntent().getStringExtra(ExerciseActivity.TOTAL_POSS_SCORE));
//        double passingScore = totalPossibleScore * 0.8;
//
//        scoreText.setText("Your Score: " + score);
//        passingScoreText.setText("Passing Score: " + passingScore);
//
//        if (score >= passingScore) {
//            resultText.setText("You passed!");
//        }
//    }
}

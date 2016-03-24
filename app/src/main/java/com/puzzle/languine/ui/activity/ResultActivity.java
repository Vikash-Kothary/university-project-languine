package com.puzzle.languine.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.utils.IntentConts;

public class ResultActivity extends MaterialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        setupToolbar();

        setContentView(R.layout.fragment_result_exercise);
//        computeScores();
    }

    public void reviewButtonClicked(View v) {
        Intent i = new Intent(this, ReviewActivity.class);
        i.putExtra(IntentConts.REVIEW, getIntent().getStringExtra(IntentConts.REVIEW));
        startActivity(i);
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

package com.puzzle.languine.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.fragment.ResultExerciseFragment;
import com.puzzle.languine.utils.IntentConts;

public class ResultActivity extends MaterialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        setupToolbar();

        addFragment(new ResultExerciseFragment());
//        computeScores();
    }

    public void reviewButtonClicked(View v) {
        Intent i = new Intent(this, ReviewActivity.class);
        i.putExtra(IntentConts.REVIEW, getIntent().getStringExtra(IntentConts.REVIEW));
        startActivity(i);
    }
}

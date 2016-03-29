package com.puzzle.languine.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;

public class CreditsActivity extends MaterialActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits_activity);

        setupToolbar();
        setupFab(this);

        findViewById(R.id.textView);

    }

    @Override
    public void onClick(View view) {
        Snackbar.make(view, "Are you sure you want to donate?", Snackbar.LENGTH_SHORT).setAction("Donate", null).show();
    }
}

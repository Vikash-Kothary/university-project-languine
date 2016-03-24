package com.puzzle.languine.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puzzle.languine.R;


public class ReviewActivity extends AppCompatActivity {
    private String reviewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Review");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reviewText = getIntent().getStringExtra("REVIEW");

        addContent();
    }

    private void addContent() {
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        FrameLayout layout = (FrameLayout) findViewById(R.id.layout);
        textView.setText(reviewText);
        layout.addView(textView);
    }

}
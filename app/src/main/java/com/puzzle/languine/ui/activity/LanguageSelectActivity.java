package com.puzzle.languine.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageButton;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.utils.IntentConts;
import com.puzzle.languine.utils.PrefsConst;

public class LanguageSelectActivity extends MaterialActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_select_activity);

        setupToolbar();

        @SuppressLint("WrongViewCast") AppCompatImageView spanishFlag = (AppCompatImageView) findViewById(R.id.spanish_image);
        spanishFlag.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.spanish_image:
                getPrefs().edit().putString(PrefsConst.LANGUAGE, "Spanish").commit();
                finish();
                break;
        }
    }
}

package com.puzzle.languine.ui.activity;

import android.os.Bundle;

import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.fragment.LoginFragment;
import com.puzzle.languine.ui.fragment.RegisterFragment;
import com.puzzle.languine.utils.IntentConts;

public class LoginActivity extends MaterialActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        setupToolbar();
        if(getIntent().getExtras()!=null){
            addFragment(new RegisterFragment());
        } else {
            addFragment(new LoginFragment());
        }
    }
}
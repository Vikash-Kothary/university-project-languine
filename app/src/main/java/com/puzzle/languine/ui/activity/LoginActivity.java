package com.puzzle.languine.ui.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.puzzle.languine.R;
import com.puzzle.languine.datamodel.ContentPull;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.fragment.LoginFragment;
import com.puzzle.languine.ui.fragment.RegisterFragment;

public class LoginActivity extends MaterialActivity {
    private static ContentPull contentPull = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        if (isNetworkAvailable()) {
            if (contentPull == null) {
                contentPull = new ContentPull();
            }
        }
        setupToolbar();
        if (getIntent().getExtras() != null) {
            addFragment(new RegisterFragment());
        } else {
            addFragment(new LoginFragment());
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
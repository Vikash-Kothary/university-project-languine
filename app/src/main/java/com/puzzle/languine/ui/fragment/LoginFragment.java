package com.puzzle.languine.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.MaterialFragment;
import com.puzzle.languine.ui.activity.LanguageSelectActivity;
import com.puzzle.languine.ui.activity.LoginActivity;
import com.puzzle.languine.ui.activity.MainActivity;
import com.puzzle.languine.utils.IntentConts;
import com.puzzle.languine.utils.PrefsConst;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends MaterialFragment implements View.OnClickListener {

    private EditText editText_username;
    private EditText editText_password;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_fragment, container, false);

        ((MaterialActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        editText_username = (EditText) findViewById(R.id.edit_text_username);
        editText_password = (EditText) findViewById(R.id.edit_text_password);
        Button button_login = (Button) findViewById(R.id.button_sign_in);
        Button button_register = (Button) findViewById(R.id.button_register);
        Button button_continue = (Button) findViewById(R.id.button_continue);

        button_login.setOnClickListener(this);
        button_register.setOnClickListener(this);
        button_continue.setOnClickListener(this);

        return rootView;
    }


    public void signInUser() {
        // Retrieve the text entered from the EditText
        String username = editText_username.getText().toString();
        String password = editText_password.getText().toString();

        // Send data to Parse.com for verification
        ParseUser.logInInBackground(username, password,
                new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // If user exist and authenticated
                            Toast.makeText(getContext(),
                                    "Successfully Logged in",
                                    Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(
                                    getContext(),
                                    "No such user exist, please signup",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_in:
                signInUser();
                break;
            case R.id.button_register:
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.putExtra(IntentConts.FRAGMENT, IntentConts.REGISTER);
                startActivity(intent);
                break;
            case R.id.button_continue:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(PrefsConst.CONTINUE, true);
                editor.commit();
                startActivity(new Intent(getContext(), MainActivity.class));
                askForLannguage();
                finish();
                break;
        }
    }

    private void askForLannguage() {
        if (getPrefs().getString(PrefsConst.LANGUAGE,"").equals("")) { // if no language has been selected
            startActivity(new Intent(getContext(), LanguageSelectActivity.class));
        }
    }
}

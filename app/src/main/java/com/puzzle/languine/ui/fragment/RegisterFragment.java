package com.puzzle.languine.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends MaterialFragment implements View.OnClickListener {

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_regisiter_fragment, container, false);

        getToolbar().setTitle("Register");

        ImageButton button_register = (ImageButton) findViewById(R.id.button_register);
        button_register.setOnClickListener(this);

        // TODO: check password and confirm_password are the same
        // TODO: Regisiter parse code doesn't work
        // TODO: register xml

        return rootView;
    }

    private void registerUser() {
        EditText editText_name = (EditText) findViewById(R.id.edit_text_name);
        EditText editText_email = (EditText) findViewById(R.id.edit_text_email);
        EditText editText_password = (EditText) findViewById(R.id.edit_text_password);
        EditText editText_confirm_password = (EditText) findViewById(R.id.edit_text_confirm_password);
        // Retrieve the text entered from the EditText
        String nametxt = editText_name.getText().toString();
        String usernametxt = editText_email.getText().toString();
        String passwordtxt = editText_password.getText().toString();
        String confirmPasswordtxt = editText_confirm_password.getText().toString();

        // Force user to fill up the form
        if (usernametxt.equals("") || passwordtxt.equals("") || passwordtxt.equals("")) {
            Toast.makeText(getContext(),
                    "Please complete the sign up form.",
                    Toast.LENGTH_LONG).show();
        } else if (!passwordtxt.equals(confirmPasswordtxt)) {
            Toast.makeText(getContext(),
                    "Passwords don't match.",
                    Toast.LENGTH_LONG).show();
        } else {
            // Save new user data into Parse.com Data Storage
            ParseUser user = new ParseUser();
            user.put("name", nametxt);
            user.setUsername(usernametxt);
            user.setPassword(passwordtxt);
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // Show a simple Toast message upon successful registration
                        Toast.makeText(getContext(),
                                "Successfully Signed up, please log in.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(),
                                "Sign up Error", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                registerUser();
                break;
            // TODO: add option to go back to sign in
        }
    }
}

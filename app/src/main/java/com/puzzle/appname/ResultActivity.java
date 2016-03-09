package com.puzzle.appname;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Result");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        if(ori == configuration.ORIENTATION_PORTRAIT){
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}

package com.puzzle.appname.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.facebook.appevents.AppEventsLogger;
//import com.puzzle.appname.AudioQuiz;
import com.puzzle.appname.ExerciseActivity;
import com.puzzle.appname.ExerciseMenuActivity;
import com.puzzle.appname.Exercises;
import com.puzzle.appname.GetStarted;
import com.puzzle.appname.Lesson;
import com.puzzle.appname.LoginActivity;
import com.puzzle.appname.LoginSignUpActivity;
import com.puzzle.appname.MyAdapter;
import com.puzzle.appname.QuizIntroActivity;
import com.puzzle.appname.R;
import com.puzzle.appname.RecyclerItemClickListener;
import com.puzzle.appname.Resources;
import com.puzzle.appname.SettingsActivity;
import com.puzzle.appname.VideoActivity;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;
import com.puzzle.appname.VideoFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class LessonSelectActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /* Constants */
    public static final String LESSON_TITLE = "TITLE";
    public static final String LESSON_NUMBER = "NUMBER";

    ArrayList<Integer> lessonImages;
    ArrayList<String> lessonNames;

//    private ViewPager viewPager;
//    private SwipeViewAdapter swipeViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_select);

        // Determine whether the current user is an anonymous user
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            // If user is anonymous, send the user to LoginSignupActivity.class
            Intent intent = new Intent(this,
                    LoginSignUpActivity.class);
            startActivity(intent);
            finish();
        }
//        else {
//            // If current user is NOT anonymous user
//            // Get current user data from Parse.com
//            ParseUser currentUser = ParseUser.getCurrentUser();
//            if (currentUser != null) {
//                // Send logged in users to Welcome.class
//                Intent intent = new Intent(this, Welcome.class);
//                startActivity(intent);
//                finish();
//            } else {
//                // Send user to LoginSignupActivity.class
//                Intent intent = new Intent(this,
//                        LoginSignupActivity.class);
//                startActivity(intent);
//                finish();
//            }
        //}






        lessonImages = new ArrayList<Integer>(
                Arrays.asList(R.drawable.greetings, R.drawable.checkingin,
                        R.drawable.sightseeing, R.drawable.directions, R.drawable.eating, R.drawable.likes, R.drawable.planning,
                        R.drawable.shopping, R.drawable.dating)
        );
        lessonNames = new ArrayList<>(
                Arrays.asList("Greetings", "Checking in", "Sightseeing", "Directions", "Eating", "Likes", "Planning", "Shopping", "Dating")
        );

        Toolbar toolbar = setupToolbar();
        setupNavigationDrawer(toolbar);
//        viewPager = (ViewPager) findViewById(R.id.pager);
//        swipeViewAdapter = new SwipeViewAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(swipeViewAdapter);
        setupRecyclerView();

//        Intent j = new Intent(this, LanguageSelectActivity.class);
//        startActivity(j);
//        Intent i = new Intent(this, LoginActivity.class);
//        startActivity(i);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
    private Toolbar setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        return toolbar;
    }

    private void setupNavigationDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupRecyclerView() {
        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<Lesson> myDataset = new ArrayList<Lesson>();
        for (int i = 0; i < lessonNames.size(); ++i) {
            myDataset.add(new Lesson(lessonImages.get(i), i + 1 + ". " + lessonNames.get(i), 0));
//            BlankFragment fragment = new BlankFragment();
//            fragment.setLessonImageID(lessonImages.get(i));
//            fragment.setLessonName(i + 1 + ". " + lessonNames.get(i));
//            fragment.setProgress(0);
//            fragment.setPosition(i);
//            swipeViewAdapter.addFragment(fragment);
//            swipeViewAdapter.notifyDataSetChanged();
        }

        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);
        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), GetStarted.class);
                        intent.putExtra(LESSON_TITLE, lessonNames.get(position));
                        intent.putExtra("WhichVideo", position);
                        intent.putExtra(LESSON_NUMBER, (position + 1) + "");
                        startActivity(intent);
                    }
                })
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.lesson_select, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.openDrawer(GravityCompat.START);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent i = null;
        switch (item.getItemId()) {
            case R.id.nav_login:
                i = new Intent(this, LoginSignUpActivity.class);
                break;
            case R.id.nav_lock_screen:
                i = new Intent(this, LoginActivity.class);
                break;
            case R.id.nav_credits:
                i = new Intent(this, CreditsActivity.class);
                break;
            case R.id.nav_exersice_menus:
                i = new Intent(this, ExerciseMenuActivity.class);
                break;
            case R.id.nav_audio_quiz:
                i = new Intent(this, ExerciseActivity.class);
                break;
            case R.id.nav_getStarted:
                i = new Intent(this, GetStarted.class);
                break;
            case R.id.nav_exercises:
                i = new Intent(this, Exercises.class);
                break;
            case R.id.nav_glossary:
                i = new Intent(this, GlossaryActivity.class);
                break;
            case R.id.nav_text_questions:
                i = new Intent(this, ExerciseActivity.class);
                break;
            case R.id.nav_picture_questions:
                i = new Intent(this, ExerciseActivity.class);
            case R.id.nav_quiz_intro:
                i = new Intent(this, QuizIntroActivity.class);
                break;
            case R.id.nav_language_select:
                i = new Intent(this, LanguageSelectActivity.class);
                break;
            case R.id.nav_Video:
                i = new Intent(this, VideoActivity.class);
                break;
            case R.id.nav_resources:
                i = new Intent(this, Resources.class);
                break;
            case R.id.nav_settings:
                i = new Intent(this, SettingsActivity.class);
                break;
            default:
                i = null;
        }
        if (i != null) {
            startActivity(i);
            i = null;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

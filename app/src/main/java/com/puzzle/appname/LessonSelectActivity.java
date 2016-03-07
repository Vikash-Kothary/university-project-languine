package com.puzzle.appname;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class LessonSelectActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String QUESTION_TYPE = "QuestionType";
    public static final String LESSON_TITLE = "TITLE";
    ArrayList<Integer> lessonImages = new ArrayList<Integer>(Arrays.asList(R.drawable.greetings, R.drawable.checkingin,
            R.drawable.sightseeing, R.drawable.directions, R.drawable.eating, R.drawable.likes, R.drawable.planning,
            R.drawable.shopping,R.drawable.dating ));
    ArrayList<String> lessonNames = new ArrayList<String>(
            Arrays.asList("Greetings","Checking in","Sightseeing","Directions","Eating","Likes","Planning","Shopping","Dating")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setupRecyclerView();

    }

    public void setupRecyclerView(){
        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        cardList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        ArrayList<Lesson> myDataset  = new ArrayList<Lesson>();
        for(int i = 0; i < lessonNames.size(); ++i)
        {
            myDataset.add(new Lesson(lessonImages.get(i), i+1 + ". " + lessonNames.get(i),0));
        }

        MyAdapter mAdapter = new MyAdapter(myDataset);
        cardList.setAdapter(mAdapter);
        cardList.addOnItemTouchListener(
            new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener()
            {
                @Override
                public void onItemClick(View view, int position)
                {
                    Intent intent = new Intent(getBaseContext(), GetStarted.class);
                    intent.putExtra(LESSON_TITLE, lessonNames.get(position));
                    intent.putExtra("WhichVideo",position);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lesson_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i=null;
        switch (item.getItemId()){
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
                i = new Intent(this, AudioQuiz.class);
                break;
            case R.id.nav_getStarted:
                i= new Intent(this, GetStarted.class);
                break;
            case R.id.nav_exercises:
                i = new Intent(this, Exercises.class);
                break;
            case R.id.nav_glossary:
                i = new Intent(this, Glossary.class);
                break;
            case R.id.nav_text_questions:
                i = new Intent(this, ExerciseActivity.class);
                break;
            case R.id.nav_picture_questions:
                i = new Intent(this, ExerciseActivity.class);
                i.putExtra(QUESTION_TYPE, QuestionType.PICTURE);
            case R.id.nav_quiz_intro:
                i = new Intent(this, QuizIntroActivity.class);
                break;
            case R.id.nav_language_select:
                i = new Intent(this, LanguageSelectActivity.class);
                break;
            case R.id.nav_Video:
                i = new Intent(this, VideoActivity.class);
                break;
        }
        if(i!=null){
            startActivity(i);
            i=null;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.puzzle.languine.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;
import com.puzzle.languine.R;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.utils.PrefsConst;

public class MainActivity extends MaterialActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askToLogin();
        setContentView(R.layout.main_activity);

        setupToolbar();
        setupNavigationalDrawer(null);

        //TODO: display name and email on nav drawer

    }


    private void askToLogin() {
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) { // if not logged in
            if (!getPrefs().getBoolean(PrefsConst.CONTINUE, false)) { // and not continuing without logging in
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent); // send to login screen
            }
        }
    }




    @Override
    protected void setupNavigationalDrawer(NavigationView.OnNavigationItemSelectedListener listener) {
        super.setupNavigationalDrawer(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // if continuing without logging in, change log out button into sign in button
        boolean displaySignIn = prefs.getBoolean(PrefsConst.CONTINUE, false);
        Menu navFooterMenu = getNavigationFooter().getMenu();

        MenuItem navSignIn = navFooterMenu.findItem(R.id.nav_sign_in);
        navSignIn.setVisible(displaySignIn);

        MenuItem navLogOut = navFooterMenu.findItem(R.id.nav_log_out);
        navLogOut.setVisible(!displaySignIn);

        getNavigationFooter().invalidate();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_resources:
                intent = new Intent(this, ResourcesActivity.class);
                break;
            case R.id.nav_glossary:
                intent = new Intent(this, GlossaryActivity.class);
                break;
            case R.id.nav_credits:
                intent = new Intent(this, CreditsActivity.class);
                break;
            case R.id.nav_sign_in:
                intent = new Intent(this, LoginActivity.class);
                break;
            case R.id.nav_log_out:
                ParseUser.logOut();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(PrefsConst.CONTINUE, true);
                editor.commit();
                setupNavigationalDrawer(null);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        switch (item.getItemId()){
//            case R.id.action_settings:
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

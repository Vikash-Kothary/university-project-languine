package com.puzzle.languine.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.puzzle.languine.R;

/**
 * Created by Vikash Kothary on 14-Mar-16.
 */
public class MaterialActivity extends AppCompatActivity {
    public static float density;
    private Toolbar toolbar = null;
    private NavigationView navigationViewFooter = null;
    private SharedPreferences prefs=null;

    protected SharedPreferences getPrefs(){
        if(prefs==null){
            return prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }
        return prefs;
    }

    protected void setupToolbar() {
        density =getApplicationContext().getResources().getDisplayMetrics().density;;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            // Show menu icon
            final ActionBar ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
        }
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        viewGroup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected void setupNavigationalDrawer(NavigationView.OnNavigationItemSelectedListener listener) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && toolbar != null) {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(listener);

            navigationViewFooter = (NavigationView) findViewById(R.id.nav_view_footer);
            navigationViewFooter.setNavigationItemSelectedListener(listener);
        }
    }

    protected NavigationView getNavigationFooter() {
        return navigationViewFooter;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
        super.onBackPressed();
    }

    protected void setupFab(View.OnClickListener listener) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(listener);
    }

    protected void addFragment(MaterialFragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    protected void changeFragment(MaterialFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }

//    protected void setupRecyclerView(RecyclerView.Adapter adapter) {
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        if (recyclerView != null) {
//            // use this setting to improve performance if you know that changes
//            // in content do not change the layout size of the RecyclerView
//            recyclerView.setHasFixedSize(true);
//            // use a linear layout manager
//            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//            recyclerView.setLayoutManager(mLayoutManager);
//            // specify an adapter
//            recyclerView.setAdapter(adapter);
////                recyclerView.addOnItemTouchListener(this);
//        }
//    }

//    protected void setupRecyclerView(RecyclerView.Adapter adapter, Context context){
//        new MaterialRecyclerView(getCurrentFocus(), adapter, context);
//    }

    protected void setupTabLayout(String[] tabNames){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if(tabLayout!=null){
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            for(String tabName : tabNames){
                tabLayout.addTab(tabLayout.newTab().setText(tabName));
            }
        }
    }


}

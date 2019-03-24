package com.vcalazas.formasgeomtricas.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.vcalazas.formasgeomtricas.R;
import com.vcalazas.formasgeomtricas.controllers.MyFragmentManager;
import com.vcalazas.formasgeomtricas.controllers.NavBar;
import com.vcalazas.formasgeomtricas.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private NavBar navBar;
    private MyFragmentManager myFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        MainActivity.this.myFragmentManager = new MyFragmentManager(MainActivity.this, MainActivity.this,
                ( (LinearLayout) findViewById(R.id.MainActMainLayout) ));

        MainActivity.this.navBar = new NavBar(MainActivity.this, drawer, MainActivity.this.myFragmentManager, MainActivity.this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this.navBar);



        MainActivity.this.myFragmentManager.navigator(Constants.NameOfFragments.HOME, null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            MainActivity.this.myFragmentManager.navigator(Constants.NameOfFragments.HOME, null);
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean n = navBar.onOptionsItemSelected(item, (LinearLayout) findViewById(R.id.MainActMainLayout) );

        return super.onOptionsItemSelected(item);
    }

}

package com.kadam.yogesh.g_gis.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import com.kadam.yogesh.g_gis.Helpers.GPSTracker;
import com.kadam.yogesh.g_gis.R;

import org.apache.cordova.Config;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CordovaInterface {
    //region DECLARATION
    public static final String mainUrl = "file:///android_asset/www/aoi.html";
    CordovaWebView cordovaWebView;
    FloatingActionMenu menu_fame;
    FloatingActionButton location_button, zoom_in_button, zoom_out_button, reset_north_button;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Config.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //region INITIALIZATION
        final GPSTracker gps = new GPSTracker(this);
        menu_fame = (FloatingActionMenu) findViewById(R.id.fam_menu);
        cordovaWebView = (CordovaWebView) findViewById(R.id.webview);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        location_button = (FloatingActionButton) findViewById(R.id.button_location);
        zoom_in_button = (FloatingActionButton) findViewById(R.id.button_zoom_in);
        zoom_out_button = (FloatingActionButton) findViewById(R.id.button_zoom_out);
        reset_north_button = (FloatingActionButton) findViewById(R.id.button_reset_north);
        //endregion


        //region BUTTON CLICKE LISTNER
        menu_fame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        location_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                cordovaWebView.loadUrl("javascript:zoomtoGPS(" + latitude + " ," + longitude + ")");
            }
        });
        zoom_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cordovaWebView.loadUrl("javascript:zoomIn()");
            }
        });

        zoom_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cordovaWebView.loadUrl("javascript:zoomOut()");
            }
        });
        reset_north_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cordovaWebView.loadUrl("javascript:resetNorth()");
            }
        });
        //endregion

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        cordovaWebView.loadUrl(mainUrl, 5000);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void startActivityForResult(CordovaPlugin cordovaPlugin, Intent intent, int i) {

    }

    @Override
    public void setActivityResultCallback(CordovaPlugin cordovaPlugin) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Object onMessage(String s, Object o) {
        return null;
    }

    @Override
    public ExecutorService getThreadPool() {
        return null;
    }

}

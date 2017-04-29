package com.kadam.yogesh.g_gis.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.widget.PopupMenu;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import com.jrummyapps.android.colorpicker.ColorPickerDialog;
import com.jrummyapps.android.colorpicker.ColorPickerDialogListener;
import com.kadam.yogesh.g_gis.Helpers.GPSTracker;
import com.kadam.yogesh.g_gis.R;

import org.apache.cordova.Config;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener,
        NavigationView.OnNavigationItemSelectedListener, CordovaInterface {
    //region DECLARATION
    public static final String mainUrl = "file:///android_asset/www/aoi.html";
    CordovaWebView cordovaWebView;
    FloatingActionMenu menu_fame;
    FloatingActionButton location_button, zoom_in_button, zoom_out_button, reset_north_button;
    int Map_selection;
    //endregion
    private static final int DIALOG_ID = 0;
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
                final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                if (ConnectionDetector.isInternetConnection(getApplicationContext())) {
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();
                } else {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    cordovaWebView.loadUrl("javascript:zoomtoGPS(" + latitude + " ," + longitude + ")");
                }
//                    }

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
        switch (id) {
            case R.id.baseMap:

                View view = getActivity().findViewById(R.id.baseMap);
                final PopupMenu map_selection_menu = new PopupMenu(MainActivity.this, view);
                map_selection_menu.getMenuInflater().inflate(R.menu.menu_map_type, map_selection_menu.getMenu());
                map_selection_menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_hybrid_map:
                                cordovaWebView.loadUrl("javascript:setMapLayerVisibility(0)");
                                break;
                            case R.id.menu_standard_map:
                                cordovaWebView.loadUrl("javascript:setMapLayerVisibility(1)");
                                break;
                            case R.id.menu_terrain_map:
                                cordovaWebView.loadUrl("javascript:setMapLayerVisibility(2)");
                                break;
                        }
                        return true;
                    }
                });
                map_selection_menu.show();
                return true;
            case R.id.About:
                ColorPickerDialog.newBuilder()
                        .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                        .setAllowCustom(true)
                        .setDialogTitle(R.string.layer_color)
                        .setDialogId(DIALOG_ID)
                        .setShowColorShades(true)

                        .setColor(Color.BLACK)
                        .setShowAlphaSlider(true)
                        .show(this);
                return true;
        }

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

    //region CHECK GPS IS AVAILABLE OR NOT
    private void buildAlertMessageNoGps() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onColorSelected(int dialogId, @ColorInt int color) {
        switch (dialogId) {
            case DIALOG_ID:
                Toast.makeText(MainActivity.this, "Color Selected IS  : " + Integer.toHexString(color), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }

    //endregion
}

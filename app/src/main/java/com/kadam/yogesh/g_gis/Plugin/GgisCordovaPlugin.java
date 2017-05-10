package com.kadam.yogesh.g_gis.Plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by 27957388 on 04/28/17.
 */

public class GgisCordovaPlugin extends CordovaPlugin {
    private static final String TAG = "GgisCordova";
    // private final ArbiterProject arbiterProject;
    public static final String mainUrl = "file:///android_asset/www/main.html";
    public static final String aoiUrl = "file:///android_asset/www/aoi.html";


    @Override
    public boolean execute(String action, JSONArray args,
                           final CallbackContext callbackContext) throws JSONException {
        return true;
    }
}


package com.kadam.yogesh.g_gis.Map;

import android.util.Log;

import com.kadam.yogesh.g_gis.AppFinishedLoading.AppFinishedLoading;
import com.kadam.yogesh.g_gis.AppFinishedLoading.AppFinishedLoadingJob;

import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Yogesh on 01-05-2017.
 */

public class Map {
    private Map(){}
    private static Map map = null;
    public interface MapChangeListener {
        public MapChangeHelper getMapChangeHelper();
    }
    public interface CordovaMap {
        public CordovaWebView getWebView();
    }

    public static Map getMap(){
        if(map == null){
            map = new Map();
        }

        return map;
    }
}

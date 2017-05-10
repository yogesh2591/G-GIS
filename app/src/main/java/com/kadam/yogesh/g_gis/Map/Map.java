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
    private Map() {
    }

    private static Map map = null;

    public interface MapChangeListener {
        public MapChangeHelper getMapChangeHelper();
    }

    public interface CordovaMap {
        public CordovaWebView getWebView();
    }

    public static Map getMap() {
        if (map == null) {
            map = new Map();
        }

        return map;
    }

    public void zoomToLocation(final CordovaWebView webView, final double latitude, final double longitude) {
//        AppFinishedLoading.getInstance().onAppFinishedLoading(new AppFinishedLoadingJob() {
//            @Override
//            public void run() {

        String url = "javascript:zoomtoGPS(" + latitude + " ," + longitude + ")";
        webView.loadUrl(url);
        Log.w("Map", "Map.zoomToExtent: " + url);
//            }
//        });
    }

    public void zoomIn(final CordovaWebView webView) {
//        AppFinishedLoading.getInstance().onAppFinishedLoading(new AppFinishedLoadingJob() {
//            @Override
//            public void run() {

        webView.loadUrl("javascript:zoomIn()");
//            }
//        });

    }

    public void zoomOut(final CordovaWebView webView) {
//        AppFinishedLoading.getInstance().onAppFinishedLoading(new AppFinishedLoadingJob() {
//            @Override
//            public void run() {

        webView.loadUrl("javascript:zoomOut()");
//            }
//        });

    }

    public void resetToNorth(final CordovaWebView webView) {
//        AppFinishedLoading.getInstance().onAppFinishedLoading(new AppFinishedLoadingJob() {
//            @Override
//            public void run() {

        webView.loadUrl("javascript:resetNorth()");
//            }
//        });

    }

    public void setMapLayerVisibility(final CordovaWebView webView, final int layer_code) {
        webView.loadUrl("javascript:setMapLayerVisibility(" + layer_code + ")");

    }

}

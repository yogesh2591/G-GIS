package com.kadam.yogesh.g_gis.BaseClasses;

/**
 * Created by 27957388 on 16/05/2017.
 */

public class Layer {
    private int layerId;
    private String featureType;
    private String srs;
    private String workspace;

    // Only for the adapter, for displaying
    private String serverName;
    private String serverUrl;
    private String serverOrigin;

    private String title;
    private String boundingBox;
    private String color = null;

    private int serverId;
    private int layerOrder;

    private int syncId;

    // Recycled for whether the layer is checked in the AddLayers List
    // and for the layers visibility
    private boolean checked;

    private boolean readOnly;

    public Layer(int layerId, String featureType, String workspace, int serverId, String serverName, String serverUrl,
                 String title, String boundingBox, String color, int layerOrder, boolean checked, String readOnly) {
        this.layerId = layerId;
        this.featureType = featureType;
        this.serverName = serverName;
        this.title = title;
        this.boundingBox = boundingBox;
        this.color = color;
        this.srs = null;
        this.serverId = serverId;
        this.serverUrl = serverUrl;
        this.workspace = workspace;
        this.layerOrder = layerOrder;
        this.syncId = -1;
        this.readOnly = Boolean.parseBoolean(readOnly);

        setChecked(checked);
    }

    public Layer(int layerId, String featureType, String workspace, int serverId, String serverName, String serverUrl,
                 String title, String srs, String boundingBox, String color, int layerOrder, boolean checked, String readOnly) {
        this(layerId, featureType, workspace, serverId, serverName, serverUrl,
                title, boundingBox, color, layerOrder, checked, readOnly);

        this.srs = srs;
    }

    // For cloning
    public Layer(Layer item) {
        this.layerId = item.getLayerId();
        this.featureType = item.getFeatureType();
        this.serverName = item.getServerName();
        this.title = item.getTitle();
        this.boundingBox = item.getBoundingBox();
        this.color = item.getColor();
        this.checked = item.isChecked();
        this.srs = item.getSrs();
        this.serverId = item.getServerId();
        this.serverUrl = item.getServerUrl();
        this.workspace = item.getWorkspace();
        this.layerOrder = item.getLayerOrder();
        this.syncId = item.getSyncId();
        this.readOnly = item.isReadOnly();
        this.serverOrigin = item.getServerOrigin();
    }


    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public int getLayerId() {
        return layerId;
    }

    public void setLayerId(int layerId) {
        this.layerId = layerId;
    }

    public int getLayerOrder() {
        return layerOrder;
    }

    public void setLayerOrder(int layerOrder) {
        this.layerOrder = layerOrder;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerOrigin() {
        return serverOrigin;
    }

    public void setServerOrigin(String serverOrigin) {
        this.serverOrigin = serverOrigin;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getSrs() {
        return srs;
    }

    public void setSrs(String srs) {
        this.srs = srs;
    }

    public int getSyncId() {
        return syncId;
    }

    public void setSyncId(int syncId) {
        this.syncId = syncId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }
}

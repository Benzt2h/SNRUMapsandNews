package com.example.benz.snrumapsandnews.dao;

import com.google.gson.annotations.SerializedName;

public class MapItemDao {
    @SerializedName("map_number") private int mapNumber;
    @SerializedName("map_name") private String mapName;
    @SerializedName("map_description") private String mapDescription;
    @SerializedName("map_img") private String mapImg;
    @SerializedName("map_latitude") private String mapLatitude;
    @SerializedName("map_logitude") private String mapLogitude;

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapDescription() {
        return mapDescription;
    }

    public void setMapDescription(String mapDescription) {
        this.mapDescription = mapDescription;
    }

    public String getMapImg() {
        return mapImg;
    }

    public void setMapImg(String mapImg) {
        this.mapImg = mapImg;
    }

    public String getMapLatitude() {
        return mapLatitude;
    }

    public void setMapLatitude(String mapLatitude) {
        this.mapLatitude = mapLatitude;
    }

    public String getMapLogitude() {
        return mapLogitude;
    }

    public void setMapLogitude(String mapLogitude) {
        this.mapLogitude = mapLogitude;
    }
}

package com.example.benz.snrumapsandnews.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapItemDao implements Parcelable {
    @SerializedName("map_number") private int mapNumber;
    @SerializedName("map_name") private String mapName;
    @SerializedName("map_description") private String mapDescription;
    @SerializedName("map_img") private String mapImg;
    @SerializedName("map_latitude") private String mapLatitude;
    @SerializedName("map_logitude") private String mapLogitude;

    public MapItemDao(){

    }

    protected MapItemDao(Parcel in) {
        mapNumber = in.readInt();
        mapName = in.readString();
        mapDescription = in.readString();
        mapImg = in.readString();
        mapLatitude = in.readString();
        mapLogitude = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mapNumber);
        dest.writeString(mapName);
        dest.writeString(mapDescription);
        dest.writeString(mapImg);
        dest.writeString(mapLatitude);
        dest.writeString(mapLogitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MapItemDao> CREATOR = new Creator<MapItemDao>() {
        @Override
        public MapItemDao createFromParcel(Parcel in) {
            return new MapItemDao(in);
        }

        @Override
        public MapItemDao[] newArray(int size) {
            return new MapItemDao[size];
        }
    };

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

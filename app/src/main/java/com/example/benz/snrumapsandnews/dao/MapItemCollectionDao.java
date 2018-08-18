package com.example.benz.snrumapsandnews.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapItemCollectionDao implements Parcelable {

   @SerializedName("data") private List<MapItemDao> data;

   public MapItemCollectionDao(){

   }

    protected MapItemCollectionDao(Parcel in) {
        data = in.createTypedArrayList(MapItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MapItemCollectionDao> CREATOR = new Creator<MapItemCollectionDao>() {
        @Override
        public MapItemCollectionDao createFromParcel(Parcel in) {
            return new MapItemCollectionDao(in);
        }

        @Override
        public MapItemCollectionDao[] newArray(int size) {
            return new MapItemCollectionDao[size];
        }
    };

    public List<MapItemDao> getData() {
        return data;
    }

    public void setData(List<MapItemDao> data) {
        this.data = data;
    }
}

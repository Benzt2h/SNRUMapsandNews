package com.example.benz.snrumapsandnews.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsItemCollectionDao implements Parcelable {

   @SerializedName("data") private List<NewsItemDao> data;

   public NewsItemCollectionDao(){

   }

    protected NewsItemCollectionDao(Parcel in) {
        data = in.createTypedArrayList(NewsItemDao.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsItemCollectionDao> CREATOR = new Creator<NewsItemCollectionDao>() {
        @Override
        public NewsItemCollectionDao createFromParcel(Parcel in) {
            return new NewsItemCollectionDao(in);
        }

        @Override
        public NewsItemCollectionDao[] newArray(int size) {
            return new NewsItemCollectionDao[size];
        }
    };

    public List<NewsItemDao> getData() {
        return data;
    }

    public void setData(List<NewsItemDao> data) {
        this.data = data;
    }
}

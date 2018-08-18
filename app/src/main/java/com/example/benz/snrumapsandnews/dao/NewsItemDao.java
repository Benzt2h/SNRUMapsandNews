package com.example.benz.snrumapsandnews.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsItemDao implements Parcelable {
    @SerializedName("news_number") private int newsNumber;
    @SerializedName("news_header") private String newsHeader;
    @SerializedName("news_description") private String newsDescription;
    @SerializedName("news_img") private String newsImg;

    public NewsItemDao(){

    }

    protected NewsItemDao(Parcel in) {
        newsNumber = in.readInt();
        newsHeader = in.readString();
        newsDescription = in.readString();
        newsImg = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(newsNumber);
        dest.writeString(newsHeader);
        dest.writeString(newsDescription);
        dest.writeString(newsImg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsItemDao> CREATOR = new Creator<NewsItemDao>() {
        @Override
        public NewsItemDao createFromParcel(Parcel in) {
            return new NewsItemDao(in);
        }

        @Override
        public NewsItemDao[] newArray(int size) {
            return new NewsItemDao[size];
        }
    };

    public int getNewsNumber() {
        return newsNumber;
    }

    public void setNewsNumber(int newsNumber) {
        this.newsNumber = newsNumber;
    }

    public String getNewsHeader() {
        return newsHeader;
    }

    public void setNewsHeader(String newsHeader) {
        this.newsHeader = newsHeader;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }
}

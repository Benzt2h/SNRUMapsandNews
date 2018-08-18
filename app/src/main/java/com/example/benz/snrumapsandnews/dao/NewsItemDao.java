package com.example.benz.snrumapsandnews.dao;

import com.google.gson.annotations.SerializedName;

public class NewsItemDao {
    @SerializedName("news_number") private int newsNumber;
    @SerializedName("news_header") private String newsHeader;
    @SerializedName("news_description") private String newsDescription;
    @SerializedName("news_img") private String newsImg;

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

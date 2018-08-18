package com.example.benz.snrumapsandnews.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsItemCollectionDao {

   @SerializedName("data") private List<NewsItemDao> data;

    public List<NewsItemDao> getData() {
        return data;
    }

    public void setData(List<NewsItemDao> data) {
        this.data = data;
    }
}

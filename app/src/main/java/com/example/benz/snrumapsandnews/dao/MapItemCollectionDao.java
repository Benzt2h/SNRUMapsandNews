package com.example.benz.snrumapsandnews.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapItemCollectionDao {


    @SerializedName("0") private List<MapItemDao> data;

    public List<MapItemDao> getData() {
        return data;
    }

    public void setData(List<MapItemDao> data) {
        this.data = data;
    }
}

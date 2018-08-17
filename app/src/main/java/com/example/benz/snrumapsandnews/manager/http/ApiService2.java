package com.example.benz.snrumapsandnews.manager.http;

import com.example.benz.snrumapsandnews.dao.MapItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.MapItemDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService2 {

    @POST("map_api.php")
    //Call<List<MapItemDao>> loadMapList();
    Call<MapItemCollectionDao> loadMapList();

}

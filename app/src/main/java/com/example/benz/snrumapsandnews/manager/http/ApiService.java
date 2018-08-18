package com.example.benz.snrumapsandnews.manager.http;

import com.example.benz.snrumapsandnews.dao.NewsItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.PhotoItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiService {

    @POST("news_api.php")
    Call<NewsItemCollectionDao> loadNewsList();
}

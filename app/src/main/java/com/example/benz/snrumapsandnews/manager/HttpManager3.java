package com.example.benz.snrumapsandnews.manager;

import android.content.Context;

import com.example.benz.snrumapsandnews.manager.http.ApiService;
import com.example.benz.snrumapsandnews.manager.http.ApiService2;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager3 {

    private static HttpManager3 instance;

    public static HttpManager3 getInstance() {
        if (instance == null)
            instance = new HttpManager3();
        return instance;
    }

    private Context mContext;
    private ApiService service;

    private HttpManager3() {
        mContext = Contextor.getInstance().getContext();

        //TODO:edit link api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://lmtznetwork.info/backend-snru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);
    }
    public  ApiService getService(){
        return service;
    }
}

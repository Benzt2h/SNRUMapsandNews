package com.example.benz.snrumapsandnews.manager;

import android.content.Context;

import com.example.benz.snrumapsandnews.manager.http.ApiService2;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager2 {

    private static HttpManager2 instance;

    public static HttpManager2 getInstance() {
        if (instance == null)
            instance = new HttpManager2();
        return instance;
    }

    private Context mContext;
    private ApiService2 service2;

    private HttpManager2() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.33/backend-snru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service2 = retrofit.create(ApiService2.class);
    }
    public  ApiService2 getService2(){
        return service2;
    }
}

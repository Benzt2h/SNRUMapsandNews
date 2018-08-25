package com.example.benz.snrumapsandnews.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.benz.snrumapsandnews.dao.MapItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.NewsItemCollectionDao;
import com.google.gson.Gson;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

public class NewsListManager {

 /*   private static NewsListManager instance;

    public static NewsListManager getInstance() {
        if (instance == null)
            instance = new NewsListManager();
        return instance;
    }*/

    private Context mContext;
    private NewsItemCollectionDao dao;

    public NewsListManager() {
        mContext = Contextor.getInstance().getContext();
        loadCache();
    }

    public NewsItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(NewsItemCollectionDao dao) {
        this.dao = dao;
        saveCache();
    }

    public Bundle onSaveInstanceState(){
        Bundle bundle =new Bundle();
        bundle.putParcelable("dao",dao);
        return bundle;
    }

    public void onRestoreInstanceState(Bundle saveInstanceState){
        dao = saveInstanceState.getParcelable("dao");
    }

    private void saveCache() {
        NewsItemCollectionDao cacheDao = new NewsItemCollectionDao();
        if (dao != null && dao.getData() != null)
            cacheDao.setData(dao.getData().subList(0, Math.min(20, dao.getData().size())));
        String json = new Gson().toJson(cacheDao);

        SharedPreferences prefs = mContext.getSharedPreferences("news", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("json",json);
        editor.apply();
    }

    private void loadCache() {
        //TODO:loadCach
        SharedPreferences prefs = mContext.getSharedPreferences("news", Context.MODE_PRIVATE);
        String json = prefs.getString("json",null);
        if (json==null)
            return;
        dao = new Gson().fromJson(json,NewsItemCollectionDao.class);
    }
}

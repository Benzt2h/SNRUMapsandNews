package com.example.benz.snrumapsandnews.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.example.benz.snrumapsandnews.dao.MapItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.MapItemDao;
import com.example.benz.snrumapsandnews.dao.PhotoItemCollectionDao;
import com.google.gson.Gson;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

public class MapListManager {

 /*   private static MapListManager instance;

    public static MapListManager getInstance() {
        if (instance == null)
            instance = new MapListManager();
        return instance;
    }
*/
    private Context mContext;
    private MapItemCollectionDao dao;

    public MapListManager() {
        mContext = Contextor.getInstance().getContext();
        loadCache();
    }

    public MapItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(MapItemCollectionDao dao) {
        this.dao = dao;
        saveCache();
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("dao", dao);
        return bundle;
    }

    public void onRestoreInstanceState(Bundle saveInstanceState) {
        dao = saveInstanceState.getParcelable("dao");
    }

    private void saveCache() {
        MapItemCollectionDao cacheDao = new MapItemCollectionDao();
        if (dao != null && dao.getData() != null)
            cacheDao.setData(dao.getData().subList(0, Math.min(20, dao.getData().size())));
        String json = new Gson().toJson(cacheDao);

        SharedPreferences prefs = mContext.getSharedPreferences("map", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("json",json);
        editor.apply();
    }

    private void loadCache() {
        //TODO:loadCach
        SharedPreferences prefs = mContext.getSharedPreferences("map", Context.MODE_PRIVATE);
        String json = prefs.getString("json",null);
        if (json==null)
            return;
        dao = new Gson().fromJson(json,MapItemCollectionDao.class);
    }
}

package com.example.benz.snrumapsandnews.manager;

import android.content.Context;

import com.example.benz.snrumapsandnews.dao.MapItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.MapItemDao;
import com.example.benz.snrumapsandnews.dao.PhotoItemCollectionDao;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

public class MapListManager {

    private static MapListManager instance;

    public static MapListManager getInstance() {
        if (instance == null)
            instance = new MapListManager();
        return instance;
    }

    private Context mContext;
    private MapItemCollectionDao dao;

    private MapListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public MapItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(MapItemCollectionDao dao) {
        this.dao = dao;
    }
}

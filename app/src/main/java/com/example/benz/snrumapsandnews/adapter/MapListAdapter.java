package com.example.benz.snrumapsandnews.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.benz.snrumapsandnews.dao.MapItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.MapItemDao;
import com.example.benz.snrumapsandnews.manager.MapListManager;
import com.example.benz.snrumapsandnews.manager.PhotoListManager;
import com.example.benz.snrumapsandnews.view.PhotoListitem;

public class MapListAdapter extends BaseAdapter {

    MapItemCollectionDao dao;

    public void setDao(MapItemCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        //TODO size Map fix here
        if(dao == null)
            return 0;
        if (dao.getData() == null)
            return 0;
        return dao.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            PhotoListitem item;
            if (convertView != null) {
                item = (PhotoListitem) convertView;
            } else
                item = new PhotoListitem(parent.getContext());

        MapItemDao dao = (MapItemDao) getItem(position);
        item.setNameText(dao.getMapName());
        item.setDescriptionText(dao.getMapDescription());
        item.setImageUrl(dao.getMapImg());

            return item;
    }
}

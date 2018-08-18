package com.example.benz.snrumapsandnews.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.benz.snrumapsandnews.dao.MapItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.MapItemDao;
import com.example.benz.snrumapsandnews.dao.NewsItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.NewsItemDao;
import com.example.benz.snrumapsandnews.view.PhotoListitem;

public class NewsListAdapter extends BaseAdapter {

    NewsItemCollectionDao dao;

    public void setDao(NewsItemCollectionDao dao) {
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

        NewsItemDao dao = (NewsItemDao) getItem(position);
        item.setNameText(dao.getNewsHeader());
        item.setDescriptionText(dao.getNewsDescription());
        item.setImageUrl(dao.getNewsImg());

            return item;
    }
}

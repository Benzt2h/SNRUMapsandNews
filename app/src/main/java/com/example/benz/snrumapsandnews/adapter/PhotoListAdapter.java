package com.example.benz.snrumapsandnews.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.benz.snrumapsandnews.manager.PhotoListManager;
import com.example.benz.snrumapsandnews.view.PhotoListitem;

public class PhotoListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        //TODO size Map fix here
        if(PhotoListManager.getInstance().getDao() == null)
            return 0;
        if (PhotoListManager.getInstance().getDao().getData() == null)
            return 0;
        return PhotoListManager.getInstance().getDao().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            return item;
    }
}

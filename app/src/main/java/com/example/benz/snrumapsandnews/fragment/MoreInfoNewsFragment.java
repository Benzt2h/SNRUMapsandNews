package com.example.benz.snrumapsandnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.benz.snrumapsandnews.R;
import com.example.benz.snrumapsandnews.dao.NewsItemDao;

import org.w3c.dom.Text;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MoreInfoNewsFragment extends Fragment {

    NewsItemDao dao;
    ImageView ivImg;
    TextView tvName,tvDescription;

    public MoreInfoNewsFragment() {
        super();
    }

    public static MoreInfoNewsFragment newInstance(NewsItemDao dao) {
        MoreInfoNewsFragment fragment = new MoreInfoNewsFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao",dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        dao =getArguments().getParcelable("dao");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more_info_news, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        ivImg = (ImageView)rootView.findViewById(R.id.ivImg);
        tvName = (TextView)rootView.findViewById(R.id.tvName);
        tvDescription = (TextView)rootView.findViewById(R.id.tvDescription);

        RequestOptions requestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL).placeholder(R.drawable.mock);

        Glide.with(MoreInfoNewsFragment.this)
                .setDefaultRequestOptions(requestOptions)
                .load(dao.getNewsImg())
                .into(ivImg);
        tvName.setText(dao.getNewsHeader());
        tvDescription.setText(dao.getNewsDescription());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

}

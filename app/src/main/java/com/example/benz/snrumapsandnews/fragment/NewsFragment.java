package com.example.benz.snrumapsandnews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.benz.snrumapsandnews.R;
import com.example.benz.snrumapsandnews.activity.MoreInfoMapActivity;
import com.example.benz.snrumapsandnews.activity.MoreInfoNewsActivity;
import com.example.benz.snrumapsandnews.adapter.MapListAdapter;
import com.example.benz.snrumapsandnews.adapter.NewsListAdapter;
import com.example.benz.snrumapsandnews.dao.MapItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.NewsItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.NewsItemDao;
import com.example.benz.snrumapsandnews.manager.HttpManager2;
import com.example.benz.snrumapsandnews.manager.HttpManager3;
import com.example.benz.snrumapsandnews.manager.NewsListManager;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFragment extends Fragment {

    public interface FragmentNewsListener{
        void onNewsItemClick(NewsItemDao dao);
    }
    ListView listView;
    NewsListAdapter listAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    NewsListManager newsListManager;

    public NewsFragment() {
        super();
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if(savedInstanceState!=null){
            onRestoreInstanceState(savedInstanceState);
        }
    }

    private void init(Bundle savedInstanceState) {
        newsListManager = new NewsListManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView,savedInstanceState);
        return rootView;
    }

    private void initInstances(View rootView,Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new NewsListAdapter();
        listAdapter.setDao(newsListManager.getDao());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listViewItemClickListener);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(pullToRefreshListener);
        listView.setOnScrollListener(listviewScorollListener);

        reloadData();
    }

    private void reloadData() {
        Call<NewsItemCollectionDao> call = HttpManager3.getInstance().getService().loadNewsList();
        call.enqueue(new Callback<NewsItemCollectionDao>() {
            @Override
            public void onResponse(Call<NewsItemCollectionDao> call, Response<NewsItemCollectionDao> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    NewsItemCollectionDao dao = response.body();
                    newsListManager.setDao(dao);
                    listAdapter.setDao(dao);
                    listAdapter.notifyDataSetChanged();
                    Toast.makeText(Contextor.getInstance().getContext(), "อัพเดทข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsItemCollectionDao> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Contextor.getInstance().getContext(), "ไม่สามารถอัพเดทข้อมูลได้", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
        outState.putBundle("newsListManager", newsListManager.onSaveInstanceState());
    }

    private void onRestoreInstanceState(Bundle saveInstanceState){
        newsListManager.onRestoreInstanceState(saveInstanceState.getBundle("newsListManager"));
    }
    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    final SwipeRefreshLayout.OnRefreshListener pullToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            reloadData();
        }
    };

    final AbsListView.OnScrollListener listviewScorollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            swipeRefreshLayout.setEnabled(firstVisibleItem == 0);
        }
    };

    final AdapterView.OnItemClickListener listViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            NewsItemDao dao = newsListManager.getDao().getData().get(position);
            FragmentNewsListener listener = (FragmentNewsListener) getActivity();
            listener.onNewsItemClick(dao);
        }
    };
}

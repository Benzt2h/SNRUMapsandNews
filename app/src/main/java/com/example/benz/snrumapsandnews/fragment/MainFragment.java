package com.example.benz.snrumapsandnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.benz.snrumapsandnews.R;
import com.example.benz.snrumapsandnews.activity.MainActivity;
import com.example.benz.snrumapsandnews.adapter.MapListAdapter;
import com.example.benz.snrumapsandnews.dao.MapItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.MapItemDao;
import com.example.benz.snrumapsandnews.manager.HttpManager2;
import com.example.benz.snrumapsandnews.manager.MapListManager;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {

    public interface FragmentMapListener {
        void onMapItemClick(MapItemDao dao);
    }

    ListView listView;
    MapListAdapter listAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    MapListManager mapListManager;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    private void init(Bundle savedInstanceState) {
        mapListManager = new MapListManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new MapListAdapter();
        listAdapter.setDao(mapListManager.getDao());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(listViewItemClickListener);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(pullToRefreshListener);
        listView.setOnScrollListener(listviewScorollListener);

        reloadData();
    }

    private void reloadData() {
        Call<MapItemCollectionDao> call = HttpManager2.getInstance().getService2().loadMapList();
        call.enqueue(new Callback<MapItemCollectionDao>() {
            @Override
            public void onResponse(Call<MapItemCollectionDao> call, Response<MapItemCollectionDao> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    MapItemCollectionDao dao = response.body();
                    mapListManager.setDao(dao);
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
            public void onFailure(Call<MapItemCollectionDao> call, Throwable t) {
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
        outState.putBundle("mapListManager", mapListManager.onSaveInstanceState());
    }

    private void onRestoreInstanceState(Bundle saveInstanceState) {
        mapListManager.onRestoreInstanceState(saveInstanceState.getBundle("mapListManager"));
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

            MapItemDao dao = mapListManager.getDao().getData().get(position);
            FragmentMapListener listener = (FragmentMapListener) getActivity();
            listener.onMapItemClick(dao);
        }
    };
}

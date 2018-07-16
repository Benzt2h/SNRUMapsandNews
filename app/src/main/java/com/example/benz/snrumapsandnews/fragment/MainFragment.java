package com.example.benz.snrumapsandnews.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benz.snrumapsandnews.R;
import com.example.benz.snrumapsandnews.adapter.PhotoListAdapter;
import com.example.benz.snrumapsandnews.dao.MapItemCollectionDao;
import com.example.benz.snrumapsandnews.dao.MapItemDao;
import com.example.benz.snrumapsandnews.dao.PhotoItemCollectionDao;
import com.example.benz.snrumapsandnews.manager.HttpManager;
import com.example.benz.snrumapsandnews.manager.HttpManager2;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {

    ListView listView;
    PhotoListAdapter listAdapter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new PhotoListAdapter();
        listView.setAdapter(listAdapter);

        Call<List<MapItemDao>> call = HttpManager2.getInstance().getService2().loadMapList();
        call.enqueue(new Callback<List<MapItemDao>>() {
            @Override
            public void onResponse(Call<List<MapItemDao>> call, Response<List<MapItemDao>> response) {
                if(response.isSuccessful()){
                    List<MapItemDao> dao = response.body();
                    Toast.makeText(Contextor.getInstance().getContext(),dao.get(0).getMapName(),Toast.LENGTH_LONG).show();
                }else{
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MapItemDao>> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });

       /* Call<PhotoItemCollectionDao> call = HttpManager.getInstance().getService().loadPhotoList();
        call.enqueue(new Callback<PhotoItemCollectionDao>() {
            @Override
            public void onResponse(Call<PhotoItemCollectionDao> call, Response<PhotoItemCollectionDao> response) {
                if(response.isSuccessful()){
                    PhotoItemCollectionDao dao = response.body();
                    Toast.makeText(getActivity(),dao.getData().get(0).getCaption(),Toast.LENGTH_LONG).show();
                }else{
                    try {
                        Toast.makeText(getActivity(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PhotoItemCollectionDao> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });*/
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
}

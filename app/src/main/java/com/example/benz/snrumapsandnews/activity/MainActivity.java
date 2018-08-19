package com.example.benz.snrumapsandnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.benz.snrumapsandnews.R;
import com.example.benz.snrumapsandnews.dao.MapItemDao;
import com.example.benz.snrumapsandnews.dao.NewsItemDao;
import com.example.benz.snrumapsandnews.fragment.MainFragment;
import com.example.benz.snrumapsandnews.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity implements NewsFragment.FragmentNewsListener,MainFragment.FragmentMapListener  {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contentContainer, MainFragment.newInstance())
                            .commit();
                    return true;
                case R.id.navigation_news:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contentContainer, NewsFragment.newInstance())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onMapItemClick(MapItemDao dao) {
        //TODO: handle click
        Intent intent = new Intent(MainActivity.this,MoreInfoMapActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNewsItemClick(NewsItemDao dao) {
        Intent intent = new Intent(MainActivity.this,MoreInfoNewsActivity.class);
        startActivity(intent);
    }
}

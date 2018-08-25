package com.example.benz.snrumapsandnews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.benz.snrumapsandnews.R;
import com.example.benz.snrumapsandnews.dao.NewsItemDao;
import com.example.benz.snrumapsandnews.fragment.MoreInfoNewsFragment;

public class MoreInfoNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_news);

        NewsItemDao dao = getIntent().getParcelableExtra("dao");

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().add(R.id.contentContainer, MoreInfoNewsFragment.newInstance(dao))
                    .commit();
        }
    }
}

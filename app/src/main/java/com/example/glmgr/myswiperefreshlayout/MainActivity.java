package com.example.glmgr.myswiperefreshlayout;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * 项目里面使用到ButterKnife插件，
 * 原来的ButterKnife.inject(this)已经废弃了，
 * 改用为ButterKnife.bind(this);
* */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeContainer;
    @BindView(R.id.list_item)
    ListView mListView;

    private String[] strings = {"Android", "Angular", "Bootstrap", "C++", "C#", "java", "IOS", "SQL", "Swift", "JDBC", "jQuery", "javascript", "PHP", "JSON", "Python", "Scala", "Razor", "RSS", "Eclipse", "Oracle", "Web services"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSwipeContainer.setOnRefreshListener(this);

        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));
        mSwipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeContainer.setRefreshing(false);
            }
        }, 5000);
    }
}

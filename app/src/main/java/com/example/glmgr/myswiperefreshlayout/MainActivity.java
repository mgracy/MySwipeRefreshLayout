package com.example.glmgr.myswiperefreshlayout;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目里面使用到ButterKnife插件，
 * 原来的ButterKnife.inject(this)已经废弃了，
 * 改用为ButterKnife.bind(this);
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeContainer;
    @BindView(R.id.list_item)
    ListView mListView;
    public static final String TAG = "MainActivity";
    private String[] strings = {"Android", "Bootstrap", "C#", "Java", "Swift", "jQuery", "PHP", "JSON", "Python", "Scala"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("RecyclerView");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "RecyclerView":
                Log.d(TAG, "onOptionsItemSelected");
                Intent intent = new Intent(MainActivity.this, MyRecyclerView.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}

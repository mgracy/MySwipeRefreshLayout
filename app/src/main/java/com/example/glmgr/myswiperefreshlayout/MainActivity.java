package com.example.glmgr.myswiperefreshlayout;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
//        //隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=MainActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_webview:
                openNewActivity(WebViewActivity.class);
                break;
            case R.id.mnu_recyclerview:
                openNewActivity(MyRecyclerView.class);
                break;
            case R.id.mnu_fragment:
                openNewActivity(MyFragmentActivity.class);
                break;
            case R.id.menu_audioRes:
                openNewActivity(AudioClass.class);
        }
        return true;
    }

    public void openNewActivity(Class clazz){
        Log.i(" -NEW- ","start " + clazz.getName() + " activity ");
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }
}

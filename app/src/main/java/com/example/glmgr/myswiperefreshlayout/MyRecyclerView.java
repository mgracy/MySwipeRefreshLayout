package com.example.glmgr.myswiperefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glmgr on 2016/5/23.
 */
public class MyRecyclerView extends Activity {
    @BindView(R.id.myRecyclerView)
    RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MyRecyclerAdapter mRecyclerAdapter;
    public static final String TAG = "MyRecyclerView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecycleview);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate");

        initData();

        mRecyclerView.setHasFixedSize(true);
//        LinearLayoutManager 现行管理器，支持横向、纵向。
//        GridLayoutManager 网格布局管理器
//        StaggeredGridLayoutManager 瀑布就式布局管理器

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerAdapter = new MyRecyclerAdapter(MyRecyclerView.this, mDatas);


        mRecyclerView.setAdapter(mRecyclerAdapter);

        mRecyclerView.addItemDecoration(new DividerGirdItemDecoration(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MyRecyclerView.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MyRecyclerView.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                mRecyclerAdapter.notifyItemRemoved(position);
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
}

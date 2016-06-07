package com.example.glmgr.myswiperefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by glmgr on 2016/5/31.
 */
public class MyFragmentActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.myfragment);
    }
}

package com.example.glmgr.myswiperefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glmgracy on 16/5/21.
 */
public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.wv_srl)
    SwipeRefreshLayout mWv_srl;
    @BindView(R.id.webView)
    WebView mWebView;

    public static final String TAG = "WebViewActivity";
    public static final String URL = "http://www.stormzhang.com/timeline";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        ButterKnife.bind(this);
        initData();
    }

    protected void initData(){
        mWv_srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl(URL);
                    }
                }, 5000);

            }
        });
        mWv_srl.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        mWebView.loadUrl(URL);
        // Add Javascript support
        mWebView.getSettings().setJavaScriptEnabled(true);

        //load the url content in current browser, not open the new browser(ex. Chrome, UC Browser)
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }
        });

//        mWebView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if(newProgress == 100){
//                    //TODO
//                }else{
//                    //TODO
//                }
//            }
//        });
    }

//    //override the return key to return the activity like stack.
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
//            mWebView.goBack();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}

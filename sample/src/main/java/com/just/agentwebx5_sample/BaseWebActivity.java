package com.just.agentwebx5_sample;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentwebX5.AgentWebX5;
import com.just.agentwebX5.LogUtils;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import static com.just.agentwebX5.ChromeClientCallbackManager.ReceivedTitleCallback;


/**
 * Created by cenxiaozhong on 2017/5/26.
 */

public class BaseWebActivity extends AppCompatActivity {



    protected AgentWebX5 mAgentWebX5;
    private LinearLayout mLinearLayout;
    private Toolbar mToolbar;
    private TextView mTitleTextView;
    private AlertDialog mAlertDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);


        mLinearLayout = (LinearLayout) this.findViewById(R.id.container);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        mTitleTextView = (TextView) this.findViewById(R.id.toolbar_title);
        this.setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });



        long p = System.currentTimeMillis();

        mAgentWebX5 = AgentWebX5.with(this)//
                .setAgentWebParent(mLinearLayout,new LinearLayout.LayoutParams(-1,-1) )//
                .useDefaultIndicator()//
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mCallback)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecutityType(AgentWebX5.SecurityType.strict)
                .setWebLayout(new WebLayout(this))
                .createAgentWeb()//
                .ready()
                .go(getUrl());

        mAgentWebX5.getLoader().loadUrl(getUrl());


        long n = System.currentTimeMillis();
        Log.i("Info", "init used time:" + (n - p));







    }

    private WebViewClient mWebViewClient=new WebViewClient(){
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
           //do you  work
            Log.i("Info","BaseWebActivity onPageStarted");
        }
    };
    private com.tencent.smtt.sdk.WebChromeClient mWebChromeClient=new com.tencent.smtt.sdk.WebChromeClient(){

        @Override
        public void onProgressChanged(com.tencent.smtt.sdk.WebView webView, int i) {
            super.onProgressChanged(webView, i);

        }
    };
    public String getUrl(){

        return "https://m.jd.com/";
    }

    private ReceivedTitleCallback mCallback = new ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (mTitleTextView != null)
                mTitleTextView.setText(title);
        }
    };


    private void showDialog() {

        if (mAlertDialog == null)
            mAlertDialog = new AlertDialog.Builder(this)
                    .setMessage("您确定要关闭该页面吗?")
                    .setNegativeButton("再逛逛", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null)
                                mAlertDialog.dismiss();
                        }
                    })//
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (mAlertDialog != null)
                                mAlertDialog.dismiss();

                            BaseWebActivity.this.finish();
                        }
                    }).create();
        mAlertDialog.show();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWebX5.handleKeyEvent(keyCode, event)) {
            return true;
        }
        
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWebX5.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWebX5.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        LogUtils.i("Info", "result:" + requestCode + " result:" + resultCode);
        mAgentWebX5.uploadFileResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWebX5.destroy();
        mAgentWebX5.getWebLifeCycle().onDestroy();
    }
}

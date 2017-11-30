package com.just.agentwebx5_sample;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;



/**
 * Created by cenxiaozhong on 2017/5/23.
 */

public class CommonActivity extends AppCompatActivity {


    private FrameLayout mFrameLayout;
    public static final String TYPE_KEY = "type_key";
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        mFrameLayout = (FrameLayout) this.findViewById(R.id.container_framelayout);


        int key = getIntent().getIntExtra(TYPE_KEY, -1);
        mFragmentManager = this.getSupportFragmentManager();
        openFragment(key);
    }


    private AgentWebX5Fragment mAgentWebX5Fragment;

    private void openFragment(int key) {

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Bundle mBundle = null;


        switch (key) {

            /*Fragment 使用AgenWebt*/
            case 0:
                ft.add(R.id.container_framelayout, mAgentWebX5Fragment = AgentWebX5Fragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "http://www.vip.com");
                break;
            case 1:
                ft.add(R.id.container_framelayout, mAgentWebX5Fragment = AgentWebX5Fragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "https://h5.m.jd.com/active/download/download.html?channel=jd-msy1");
                break;
            case 2:
                ft.add(R.id.container_framelayout, mAgentWebX5Fragment = AgentWebX5Fragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "file:///android_asset/upload_file/uploadfile.html");
                break;
            case 3:
                ft.add(R.id.container_framelayout, mAgentWebX5Fragment = AgentWebX5Fragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "file:///android_asset/upload_file/jsuploadfile.html");
                break;
            case 4:
                ft.add(R.id.container_framelayout, mAgentWebX5Fragment = JsAgentWebFragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "file:///android_asset/js_interaction/hello.html");
                break;

            case 5:
                ft.add(R.id.container_framelayout, mAgentWebX5Fragment = AgentWebX5Fragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "http://m.youku.com/video/id_XODEzMjU1MTI4.html");
                break;
            case 6:
                ft.add(R.id.container_framelayout, mAgentWebX5Fragment = CustomIndicatorFragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "http://www.taobao.com");
                break;
            case 7:
                ft.add(R.id.container_framelayout, mAgentWebX5Fragment = CustomSettingsFragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "http://www.wandoujia.com/apps");
                break;

            case 8:
                ft.add(R.id.container_framelayout, mAgentWebX5Fragment = AgentWebX5Fragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "file:///android_asset/sms/sms.html");
                break;
            /* 自定义 WebView */
            case 9:
                /*ft.add(R.id.container_framelayout, mAgentWebX5Fragment = CustomWebViewFragment.getInstance(mBundle = new Bundle()), AgentWebX5Fragment.class.getName());
                mBundle.putString(AgentWebX5Fragment.URL_KEY, "");*/
                break;

        }
        ft.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mAgentWebX5Fragment.onActivityResult(requestCode, resultCode, data);
        Log.i("Info", "activity result");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        AgentWebX5Fragment mAgentWebX5Fragment = this.mAgentWebX5Fragment;
        if (mAgentWebX5Fragment != null) {
            FragmentKeyDown mFragmentKeyDown = (FragmentKeyDown) mAgentWebX5Fragment;
            if (mFragmentKeyDown.onFragmentKeyDown(keyCode, event))
                return true;
            else
                return super.onKeyDown(keyCode, event);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

package com.just.agentwebx5_sample;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.just.agentwebX5.AgentWeb;
import com.just.agentwebX5.WebDefaultSettingsManager;

/**
 * Created by cenxiaozhong on 2017/5/26.
 */

public class CustomIndicatorFragment extends AgentWebFragment {
    public static CustomIndicatorFragment getInstance(Bundle bundle){
        CustomIndicatorFragment mCustomIndicatorFragment=new CustomIndicatorFragment();
        if(bundle!=null)
            mCustomIndicatorFragment.setArguments(bundle);
        return mCustomIndicatorFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        CommonIndicator mCommonIndicator=new CommonIndicator(this.getActivity());
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(-2,-2);
        lp.gravity= Gravity.CENTER;
        ProgressBar mProgressBar=new ProgressBar(this.getActivity());
        mProgressBar.setBackground(this.getResources().getDrawable(R.drawable.indicator_shape));
        mCommonIndicator.addView(mProgressBar,lp);

        this.mAgentWeb = AgentWeb.with( this)//
                .setAgentWebParent((ViewGroup) view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .setCustomIndicator(mCommonIndicator)
                .setWebSettings(WebDefaultSettingsManager.getInstance())//
                .setWebViewClient(mWebViewClient)
                .setReceivedTitleCallback(mCallback)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()//
                .go(getUrl());


        initView(view);
    }
}

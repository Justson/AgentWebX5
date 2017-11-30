package com.just.agentwebx5_sample;

import android.os.Bundle;

/**
 * Created by cenxiaozhong on 2017/5/26.
 */

public class CustomSettingsFragment extends AgentWebX5Fragment {

    public static AgentWebX5Fragment getInstance(Bundle bundle) {

        CustomSettingsFragment mCustomSettingsFragment = new CustomSettingsFragment();
        if (bundle != null)
            mCustomSettingsFragment.setArguments(bundle);

        return mCustomSettingsFragment;

    }


    @Override
    public com.just.agentwebX5.WebSettings getSettings() {

        return new CustomSettings();
    }
}

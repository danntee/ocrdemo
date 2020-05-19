package com.smsf.allroundscan;

import android.os.Bundle;

import android.view.Window;


import com.umeng.analytics.MobclickAgent;

import androidx.fragment.app.FragmentActivity;

/**
 * @Author: Mr
 * @CreateDate: 2019/12/26 11:22
 */


public abstract class BaseActivity extends FragmentActivity {

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutId());
        //初始化控件
        initViews(savedInstanceState);
        //初始化数据
        initData();
        //初始化事件
        initListener();
    }


    public abstract int getLayoutId();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void initListener();


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}

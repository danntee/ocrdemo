package com.smsf.allroundscan;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "d0ffc1dba8ca3920f8c919acd9bb72c7");
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        UMConfigure.setLogEnabled(true);
        MobclickAgent.setCatchUncaughtExceptions(true);
        UMConfigure.setEncryptEnabled(true);

    }

}

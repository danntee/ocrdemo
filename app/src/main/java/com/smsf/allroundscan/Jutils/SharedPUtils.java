package com.smsf.allroundscan.Jutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Description: 本地数据库缓存
 * @Author: Mr
 * @CreateDate: 2020/1/6 11:04
 */

public class SharedPUtils {

    // BD名称
    public final static String APP_INFOR = "SFProject";


    /**
     * 保存APP引导页状态
     */
    public static void setAppGuide(Context context, boolean isLoad) {
        SharedPreferences sp = context.getSharedPreferences(APP_INFOR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("appIsLoad",isLoad);
        editor.commit();
    }

    /***
     * 获取当前APP引导页状态
     * */
    public static boolean getAppGuide(Context context) {
        SharedPreferences sp = context.getSharedPreferences(APP_INFOR, Context.MODE_PRIVATE);
        if (sp != null) {
            boolean isLoad= sp.getBoolean("appIsLoad", false);
            return isLoad;
        }
        return false;
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     *  是否显示保存反馈dialog
     * */
    public static void putInt(Context context, String key, Integer value) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }


    public static int getInt(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return sp.getInt(key, -1);
    }

}

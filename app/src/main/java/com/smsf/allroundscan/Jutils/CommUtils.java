package com.smsf.allroundscan.Jutils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

import java.util.List;


/**
 * @Description: 反馈工具类
 * @Author: Mr
 * @CreateDate: 2020/3/9 13:45
 */
public class CommUtils {

    private static String KEY_IS_SHOW_PRIVACYDIALOG = "KEY_IS_SHOW_PRIVACYDIALOG";
    private static String title_privacy = "用户协议";
    private static String title_user_agreement = "隐私政策";

    /**
     * 跳转隐私政策弹窗
     *
     * @param context
     * @param onClick
     */
    public static void startPrivacyDialog(final Context context, final OnClick onClick) {
        if (SharedPUtils.getInt(context, KEY_IS_SHOW_PRIVACYDIALOG) < 0) {
            CommonPrivacyPolicyDialog privacyPolicyDialog = new CommonPrivacyPolicyDialog(context, new CommonPrivacyPolicyDialog.OnClick() {
                @Override
                public void onLeftClick() {
                    onClick.onLeftClick();
                }

                @Override
                public void onRight() {
                    SharedPUtils.putInt(context, KEY_IS_SHOW_PRIVACYDIALOG, 1);
                    onClick.onRight();
                }
            });
            privacyPolicyDialog.show();
        }
    }

    /**
     * 跳转隐私政策内容界面
     *
     * @param context
     * @param title
     */
    public static void startPrivacyActivity(final Context context, String title) {
        Intent intent = new Intent();
        intent.setClass(context, CommonWebViewActivity.class);
        if (title.contains("用户协议")) {
            intent.putExtra("source", "user_agreement.htm");
        } else {
            intent.putExtra("source", "privacy_agreement.htm");
        }
        context.startActivity(intent);
    }

    /**
     * 跳转qq客服
     *
     * @param context
     */
    public static void startQQCustomerService(Context context) {
        if (isQQClientAvailable(context, "com.tencent.mobileqq")) {
            //跳转客服QQ界面
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + "3172938578";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            // 跳转前先判断Uri是否存在，如果打开一个不存在的Uri，App可能会崩溃
            if (isValidIntent(context, intent)) {
                context.startActivity(intent);
            }
        } else {
            Toast.makeText(context, "检查到您手机没有安装QQ客户端，请安装后使用该功能", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 判断 用户是否安装客户端
     */
    public static boolean isQQClientAvailable(Context context, String pageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equalsIgnoreCase(pageName) || pn.equalsIgnoreCase(pageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断 Uri是否有效
     */
    public static boolean isValidIntent(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return !activities.isEmpty();
    }


    /**
     * 隐私政策弹窗点击事件
     */
    public interface OnClick {
        void onLeftClick();
        void onRight();
    }


}

package com.smsf.allroundscan.Jutils;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.smsf.allroundscan.BaseActivity;
import com.smsf.allroundscan.R;


/**
 * @Description: 用户协议页面
 * @Author: Mr
 * @CreateDate: 2020/3/9 14:31
 */

public class CommonWebViewActivity extends BaseActivity {
    private WebView webview_main;
    private ImageView iv_back;

    @Override
    public int getLayoutId() {
        return R.layout.common_activity_web_view;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        iv_back = findViewById(R.id.iv_back);
        webview_main = findViewById(R.id.webview_main);
    }

    @Override
    protected void initData() {
        String url = getIntent().getExtras().getString("url");
        webview_main.loadUrl(url);
        WebSettings settings=webview_main.getSettings();
        settings.setTextSize(WebSettings.TextSize.LARGER);
    }

    @Override
    protected void initListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

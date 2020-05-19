package com.smsf.allroundscan.Jutils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smsf.allroundscan.R;

import androidx.annotation.NonNull;


/**
 * 隐私反馈弹窗
 * */
public class CommonPrivacyPolicyDialog extends Dialog {

    private Context mContext;
    OnClick onClick;

    public CommonPrivacyPolicyDialog(@NonNull Context context, OnClick onClick) {
        super(context);
        this.mContext = context;
        this.onClick = onClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = View.inflate(mContext, R.layout.common_dialog_privacy_policy, null);
        setContentView(contentView);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        Button btLeft = contentView.findViewById(R.id.bt_left);
        Button btRight = contentView.findViewById(R.id.bt_right);
        TextView content = contentView.findViewById(R.id.tv_privacy_content);
        String ttt = "《用户协议》";
        String sss = "《隐私政策》";
        SpannableString spannableString = new SpannableString(ttt);
        SpannableString spannableString1 = new SpannableString(sss);
        ClickableSpan clickableSpan = new MyClickableSpan(ttt, mContext);
        ClickableSpan clickableSpan2 = new MyClickableSpan(sss, mContext);
        spannableString.setSpan(clickableSpan, 0, ttt.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString1.setSpan(clickableSpan2, 0, sss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        content.setText("欢迎使用" + AppUtils.getAppName(mContext)+"!" + AppUtils.getAppName(mContext) +
                "非常重视您的隐私和个人信息保护。在您使用" + AppUtils.getAppName(mContext) +"前，请认真阅读");
        content.append(spannableString);
        content.append("及");
        content.append(spannableString1);
        content.append(", 您同意并接受全部条款后方可开始使用" + AppUtils.getAppName(mContext) + "。");
        content.setMovementMethod(LinkMovementMethod.getInstance());
        btLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onLeftClick();
                dismiss();
            }
        });

        btRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onRight();
                dismiss();
            }
        });
    }

    public class MyClickableSpan extends ClickableSpan {

        String str;
        Context context;
        public MyClickableSpan(String str, Context context){
            super();
            this.str = str;
            this.context = context;
        }


        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(mContext.getResources().getColor(R.color.common_color_privacy));
        }


        @Override
        public void onClick(View widget) {
            Intent intent = new Intent();
            intent.setClass(context, CommonWebViewActivity.class);
            if(str.contains("用户协议")){
                intent.putExtra("url",Conts.URL_API_AGREEMENT);
            }else{
                intent.putExtra("url", Conts.URL_API_PRIVACY_AGREEMENT);
            }
            context.startActivity(intent);
        }
    }

    public interface OnClick{
        void onLeftClick();
        void onRight();
    }
}

package com.smsf.allroundscan.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.smsf.allroundscan.R;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * @Description: 显示文字识别结果
 * @Author: liys
 * @CreateDate: 2020/4/27 13:53
 */

public class fragmenttext extends Fragment {
    TextView tx;
    ImageButton share,cppyb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_frag_words, null);
        tx=view.findViewById(R.id.textView2);
        Bundle b=getArguments();
        tx.setText(b.getString("ocr"));
        share=view.findViewById(R.id.button9);
        cppyb=view.findViewById(R.id.button10);
        cppyb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String copydat=tx.getText().toString();
                copy(Objects.requireNonNull(getActivity()),copydat);
Toast.makeText(getContext(),"拷贝完成！", Integer.parseInt("3")).show();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=getArguments();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,b.getString("ocr"));
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

        return view;
    }
    public static void copy(Context context, String s) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）
        ClipData clipData = ClipData.newPlainText(null, s);        // 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
    }

}

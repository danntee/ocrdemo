package com.smsf.allroundscan.ocrActivites;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.smsf.allroundscan.fragments.fragmenttext;
import com.smsf.allroundscan.R;
import com.smsf.allroundscan.fragments.fragmentimage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @Description: ocr扫描完成后，识别结果展示页
 * @Author: liys
 * @CreateDate: 2020/4/27 14:07
 */

public class FresultActivity extends FragmentActivity {
    FrameLayout mFrameLayout;
    FragmentManager mSupportFragmentManager;
    fragmenttext frag;
    fragmentimage imgfrag;
    FragmentTransaction mTransaction;
    Button b1,b2;
    private List<Fragment> mFragments = new ArrayList<>();
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recresult);
        mFrameLayout=findViewById(R.id.frameLayout);
        b1=findViewById(R.id.button_word);
        b2=findViewById(R.id.button_img);
       initView();
    }




    private void initView() {
        mSupportFragmentManager = getSupportFragmentManager();
        mTransaction = mSupportFragmentManager.beginTransaction();
        //设置默认选中首页
        frag=new fragmenttext();
        Bundle b=new Bundle();
        b.putString("ocr",getIntent().getStringExtra("ocrResult"));
        frag.setArguments(b);
        mFragments.add(frag);
        hideOthersFragment(frag, true);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frag == null) {
                    frag = new fragmenttext();
                    mFragments.add(frag);
                    hideOthersFragment(frag, true);

                } else {

                    hideOthersFragment(frag, false);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgfrag == null) {
                    imgfrag = new fragmentimage();
                    mFragments.add(imgfrag);
                    Bundle b=new Bundle();
                    b.putString("ocrimg",getIntent().getStringExtra("ocrimg"));
                    imgfrag.setArguments(b);
                    hideOthersFragment(imgfrag, true);
                } else {
                    hideOthersFragment(imgfrag, false);
                }
            }
        });

    }   ;



    private void hideOthersFragment(Fragment showFragment, boolean add) {
        mSupportFragmentManager = getSupportFragmentManager();
        mTransaction = mSupportFragmentManager.beginTransaction();
        if (add) {
            mTransaction.add(R.id.frameLayout, showFragment); }
        for (Fragment fragment : mFragments) {
            if (showFragment.equals(fragment)) {
                mTransaction.show(fragment);
            } else {
                mTransaction.hide(fragment);
            }
        }
        mTransaction.commit();
    }

}

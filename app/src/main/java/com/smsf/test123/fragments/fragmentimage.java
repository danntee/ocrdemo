package com.smsf.test123.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smsf.test123.R;
import com.smsf.test123.Utils.FileUtil;

import java.io.File;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Description: java类作用描述
 * @Author: liys
 * @CreateDate: 2020/4/27 14:39
 */

public class fragmentimage extends Fragment {
    ImageView imgview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_frag_image, null);
        imgview=view.findViewById(R.id.img);
        Bundle b=getArguments();
        File f = null;
        if(b.getString("ocrimg")==null||b.getString("ocrimg").equals("")) {

            try {
                f = new File(FileUtil.getSaveFile(getActivity().getApplication()).getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
             f = null;
            f = new File(b.getString("ocrimg"));
        }
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        imgview.setImageBitmap(bitmap);
        return view;
    }
}

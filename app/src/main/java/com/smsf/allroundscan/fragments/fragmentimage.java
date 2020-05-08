package com.smsf.allroundscan.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.smsf.allroundscan.Utils.FileUtil;
import com.smsf.allroundscan.R;

import java.io.File;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

/**
 * @Description: java类作用描述
 * @Author: liys
 * @CreateDate: 2020/4/27 14:39
 */

public class fragmentimage extends Fragment {
    ImageView imgview;
    Button export;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_frag_image, null);
        imgview=view.findViewById(R.id.img);
        Bundle b=getArguments();
        export=view.findViewById(R.id.button11);
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
        final File finalF = f;
        final Uri[] uri = {null};
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT >= 24) {
                    uri[0] = FileProvider.getUriForFile(getContext(),"com.smsf.allroundscan.fileprovider", finalF);
                } else {
                   uri[0] = Uri.fromFile(finalF);
                }
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, uri[0]);
                intent.setType("image/*");

                startActivity(intent);
            }
        });
        return view;

    }
}

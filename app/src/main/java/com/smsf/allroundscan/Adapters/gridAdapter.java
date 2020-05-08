package com.smsf.allroundscan.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.smsf.allroundscan.history.historyActivity;
import com.smsf.allroundscan.R;
import com.smsf.allroundscan.Utils.GridItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: liys
 * @CreateDate: 2020/4/28 10:04
 */

public class gridAdapter extends BaseAdapter {
    List<GridItem> data = new ArrayList<>();

    public void setData(List<GridItem> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int count=0;
        final View menuItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.griditem, null);
        ImageView menu_img = menuItem.findViewById(R.id.menu_img);
        final RadioButton button=menuItem.findViewById(R.id.button_check);
        final RadioGroup grp=menuItem.findViewById(R.id.rgp);
        final RadioButton button1=menuItem.findViewById(R.id.radioButton);
        final File f=new File(data.get(position).getRes_id());
        final String time = new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date(f.lastModified()));

        final TextView menu_text = menuItem.findViewById(R.id.menu_text);
        if(data.get(position).isShowedit())
            button.setVisibility(View.VISIBLE);
        else
            button.setVisibility(View.INVISIBLE);
//        grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                switch (checkedId){
//
//                    case R.id.button_check:
//                        if(i%2==0)
//                            button.setChecked(false);
//                        break;
//                    case R.id.radioButton:
//                        Log.d("c","bbb");
//                        break;
//
//                }
//            }
//        });
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("check",time);
//                if(button1.isChecked()) {
//
//                    button.setChecked(true);
//                }
//            }
//        });
//
        button.setOnClickListener(new View.OnClickListener() {
            int i=0;

            @Override
            public void onClick(View v) {

                Log.d("check", String.valueOf(i));
                if(i%2==0) {

                    button.setChecked(true);
                    historyActivity.deleList.add(f.getAbsolutePath());
                    button1.setChecked(false);
                    i+=1;
                }
                else {
                    button.setChecked(false);
                    historyActivity.deleList.remove(f.getAbsolutePath());
                    button1.setChecked(true);
                    i+=1;
                }
            }
        });
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        menu_img.setImageBitmap(bitmap);
        menu_text.setText("新文档"+time);
        return menuItem;
    }
}

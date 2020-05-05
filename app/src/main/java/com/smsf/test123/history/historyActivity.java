package com.smsf.test123.history;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.GeneralBasicParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.Word;
import com.baidu.ocr.sdk.model.WordSimple;
import com.smsf.test123.Adapters.gridAdapter;
import com.smsf.test123.MainActivity;
import com.smsf.test123.R;
import com.smsf.test123.Utils.GridItem;
import com.smsf.test123.ocrActivites.FresultActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class historyActivity extends AppCompatActivity {
    Button edit,dele;
    boolean show=false;
    GridView gridView;
    gridAdapter adapter;
    public static ArrayList<String> deleList;
    ArrayList<GridItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myhsitorygrid);
        gridView = findViewById(R.id.menu_grid);
        edit=findViewById(R.id.button5);
        dele=findViewById(R.id.button6);
        deleList=new ArrayList<>();
        if(show)
            dele.setVisibility(View.VISIBLE);
        else
            dele.setVisibility(View.INVISIBLE);
        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String i:deleList)
                {
                    Log.d("ddd",i);
                    File f=new File(i);
                    f.delete();
                    getFilesAllName(getApplication().getFilesDir().toString());
;                }
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"WrongConstant", "ShowToast"})
            @Override
            public void onClick(View v) {
                Toast.makeText(historyActivity.this,"aaaaa",3);
                Log.d("sssss","xcxcxc");
                show=!show;
                if(show)
                    dele.setVisibility(View.VISIBLE);
                else
                    dele.setVisibility(View.INVISIBLE);
                getFilesAllName(getApplication().getFilesDir().toString());
            }
        });
        adapter = new gridAdapter();
        list=new ArrayList<>();
        getFilesAllName(getApplication().getFilesDir().toString());
    }
    public List<String> getFilesAllName(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){
            Log.e("error","空目录");return null;}
        List<String> s = new ArrayList<>();
        list.clear();
        for(int i = 0; i<files.length; i++){
            if(!files[i].getName().equals("pic.jpg"))
            s.add(files[i].getAbsolutePath());
            Log.d("ccc",files[i].getAbsolutePath());

            GridItem item=new GridItem("aaa",files[i].getAbsolutePath());
            item.setShowedit(show);
            list.add(item);
            adapter.setData(list);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("sss",list.get(position).getRes_id());
                    // 通用文字识别参数设置
                    GeneralBasicParams param = new GeneralBasicParams();
                    param.setDetectDirection(true);
                    param.setImageFile(new File(list.get(position).getRes_id()));
                    String img=null;
                    img=list.get(position).getRes_id();
                    // 调用通用文字识别服务
                    final String finalImg = img;
                    OCR.getInstance(getApplication()).recognizeGeneralBasic(param, new OnResultListener<GeneralResult>() {
                        StringBuilder sb=new StringBuilder();

                        @Override
                        public void onResult(GeneralResult result) {
                            // 调用成功，返回GeneralResult对象
                            for (WordSimple wordSimple : result.getWordList()) {
                                // wordSimple不包含位置信息
                                String word = wordSimple.getWords();

                                sb.append(word);


                            }
                            Log.d("dss",sb.toString());
                            Intent intent=new Intent(historyActivity.this, FresultActivity.class);
                            intent.putExtra("ocrResult",sb.toString());
                            intent.putExtra("ocrimg", finalImg);
                            startActivity(intent);
                        }
                        @Override
                        public void onError(OCRError error) {
                            // 调用失败，返回OCRError对象
                        }
                    });





                }
            });

        }
        return s;
    }

}

package com.smsf.test123;//package com.smsf.test123;/*

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smsf.test123.Services.AuthService;
import com.smsf.test123.Utils.Exceldata;
import com.smsf.test123.Utils.FileUtil;
import com.smsf.test123.Utils.Result_data;
import com.smsf.test123.Utils.TestExecleBean;
import com.smsf.test123.Utils.jsonFormOcrRequest;
import com.smsf.test123.Utils.jsonFormOcrResult;
import com.smsf.test123.Utils.jsonResult;
import com.smsf.test123.history.historyActivity;
import com.smsf.test123.ocrActivites.FresultActivity;
import com.smsf.test123.ocrActivites.IDCardActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

// * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
// */
//
//import com.baidu.ocr.sdk.OCR;
//import com.baidu.ocr.sdk.OnResultListener;
//import com.baidu.ocr.sdk.exception.OCRError;
//import com.baidu.ocr.sdk.model.AccessToken;
//import com.baidu.ocr.ui.camera.CameraActivity;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_GENERAL = 105;
    private static final int REQUEST_CODE_GENERAL_BASIC = 106;
    private static final int REQUEST_CODE_ACCURATE_BASIC = 107;
    private static final int REQUEST_CODE_ACCURATE = 108;
    private static final int REQUEST_CODE_GENERAL_ENHANCED = 109;
    private static final int REQUEST_CODE_GENERAL_WEBIMAGE = 110;
    private static final int REQUEST_CODE_BANKCARD = 111;
    private static final int REQUEST_CODE_VEHICLE_LICENSE = 120;
    private static final int REQUEST_CODE_DRIVING_LICENSE = 121;
    private static final int REQUEST_CODE_LICENSE_PLATE = 122;
    private static final int REQUEST_CODE_BUSINESS_LICENSE = 123;
    private static final int REQUEST_CODE_RECEIPT = 124;

    private static final int REQUEST_CODE_PASSPORT = 125;
    private static final int REQUEST_CODE_NUMBERS = 126;
    private static final int REQUEST_CODE_QRCODE = 127;
    private static final int REQUEST_CODE_BUSINESSCARD = 128;
    private static final int REQUEST_CODE_HANDWRITING = 129;
    private static final int REQUEST_CODE_LOTTERY = 130;
    private static final int REQUEST_CODE_VATINVOICE = 131;
    private static final int REQUEST_CODE_CUSTOM = 132;


    private boolean hasGotToken = false;
    static String request_id=null;
    private AlertDialog.Builder alertDialog;
    private ProgressDialog pd1 = null;
    String tabletoken=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        alertDialog = new AlertDialog.Builder(this);

        // 通用文字识别
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkTokenStatus()) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                try {
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                            FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                startActivityForResult(intent, REQUEST_CODE_GENERAL_BASIC);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(MainActivity.this, historyActivity.class);
                startActivity(t);
            }
        });
        // 身份证识别
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkTokenStatus()) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, IDCardActivity.class);
                startActivity(intent);
            }
        });
        // 表格识别
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkTokenStatus()) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this,CameraActivity.class);
                try {
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                    FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent,4);
            }
        });
        // 请选择您的初始化方式
        // initAccessToken();
        initAccessTokenWithAkSk();
    }

    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
        }
        return hasGotToken;
    }

    /**
     * 以license文件方式初始化
     */
    private void initAccessToken() {
        OCR.getInstance(this).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("licence方式获取token失败", error.getMessage());
            }
        }, getApplicationContext());
    }

    /**
     * 用明文ak，sk初始化
     */
    private void initAccessTokenWithAkSk() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("AK，SK方式获取token失败", error.getMessage());
            }
        }, getApplicationContext(), "0IOszaEX9vdl6FeGnLMIgFRB", "7mwqRyl9t2dqfUFSRz3eyprqDt3IozFi");
    }

    /**
     * 自定义license的文件路径和文件名称，以license文件方式初始化
     */
    private void initAccessTokenLicenseFile() {
        OCR.getInstance(this).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("自定义文件路径licence方式获取token失败", error.getMessage());
            }
        }, "aip.license", getApplicationContext());
    }


    private void alertText(final String title, final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("确定", null)
                        .show();
            }
        });
    }

    private void infoPopText(final String result) {
        alertText("", result);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initAccessToken();
        } else {
            Toast.makeText(getApplicationContext(), "需要android.permission.READ_PHONE_STATE", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        // 表格识别
        if (requestCode ==4 && resultCode == Activity.RESULT_OK) {
            //                RecognizeService.recAccurate(this, FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
//                        new RecognizeService.ServiceListener() {
//                            @Override
//                            public void onResult(String result) {
//                                infoPopText(result);
//                            }
//                        });
            new Thread(networkTask_request).start();
            pd1 = new ProgressDialog(MainActivity.this);
            pd1.show();




        }
        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            final Gson gson = new Gson() ;
            try {
                RecognizeService.recGeneralBasic(this, FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                        new RecognizeService.ServiceListener() {
                            @Override
                            public void onResult(String result) {

                                jsonResult j =  gson.fromJson(result,jsonResult.class);
                                String res="";
                                List<jsonResult.WordsResultBean> strings=j.getWords_result();
                                for (jsonResult.WordsResultBean i :strings)
                                    res+=i.getWords();
                                Log.d("123",res);
                                Intent intent=new Intent(MainActivity.this,FresultActivity.class);
                                intent.putExtra("ocrResult",res);
                                try {
                                    intent.putExtra("ocrPic",
                                            FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                                    File file= new File(FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                                    Calendar now = new GregorianCalendar();
                                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                                    String fileName = simpleDate.format(now.getTime());
                                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                                    File filec = new File(file.getAbsolutePath() + fileName + ".jpg");
                                    FileOutputStream out = new FileOutputStream(filec);
                                    try {
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                                        out.flush();
                                        out.close();
                                    }
                                    catch (NullPointerException e)
                                    {
                                        ;
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                startActivity(intent);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    Runnable networkTask_request = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data = new Bundle();
            String s= null;
            final Gson gson = new Gson() ;
            try {

                s = FormOcrRequest.formOcrRequest(AuthService.getAuth(), FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath());
                jsonFormOcrRequest j =  gson.fromJson(s,jsonFormOcrRequest.class);
                tabletoken=AuthService.getAuth();
                data.putString("value",j.getResult().get(0).getRequest_id());
                data.putString("TOKEN",tabletoken);
            } catch (IOException e) {
                e.printStackTrace();
            }

            msg.setData(data);
            handler_request.sendMessage(msg);

        }
    };
    Runnable networkTask_get_result = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data=new Bundle();
            String s= null;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s=FormOcrResult.formOcrResult(tabletoken,request_id);
//                s = FormOcrRequest.formOcrRequest(AuthService.getAuth(), FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath());
            data.putString("Resvalue", s);
            data.putString("TOKEN",tabletoken);
            msg.setData(data);
            handler_result.sendMessage(msg);

        }
    };

    @SuppressLint("HandlerLeak")
    Handler handler_request = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            String token=data.getString("TOKEN");
            request_id=data.getString("value");
            Log.i("mylog", "请求结果为-->" + val+"\n"+token);
            new Thread(networkTask_get_result).start();
        }
    };
    @SuppressLint("HandlerLeak")
    Handler handler_result = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("Resvalue");
            Result_data bean = JSON.parseObject(val, Result_data.class);
            Log.d("stat",String.valueOf(bean.getRecstat()));
            if (bean.getRecstat() == 3) {

                Exceldata edata = JSON.parseObject(bean.getData(), Exceldata.class);
                Log.d("ssi", String.valueOf(edata.getForm_num()));
                String res="";
                for (Exceldata.FormsBean b : edata.getForms()) {
                    for(int i=0;i<b.getBody().size();i++) {
                        Log.d("resu", b.getBody().get(i).getWord());
                        res += b.getBody().get(i).getWord();
                        res+=" ";
                    }

                }
                pd1.hide();
                Intent intent=new Intent(MainActivity.this,FresultActivity.class);
                intent.putExtra("ocrResult",res);
                try {
                    intent.putExtra("ocrPic",
                            FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                    File file= new File(FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                    Calendar now = new GregorianCalendar();
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                    String fileName = simpleDate.format(now.getTime());
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    File filec = new File(file.getAbsolutePath() + fileName + ".jpg");
                    FileOutputStream out = new FileOutputStream(filec);
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        out.flush();
                        out.close();
                    }
                    catch (NullPointerException e)
                    {
                        ;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
            else {
                new Thread(networkTask_get_result).start();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放内存资源
        OCR.getInstance(this).release();
    }
}







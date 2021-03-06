/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.smsf.allroundscan.ocrActivites;

import java.io.File;
import java.io.IOException;


import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.smsf.allroundscan.Utils.FileUtil;
import com.smsf.allroundscan.R;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class IDCardActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_IMAGE_FRONT = 201;
    private static final int REQUEST_CODE_PICK_IMAGE_BACK = 202;
    private static final int REQUEST_CODE_CAMERA = 102;

    private TextView infoTextView;
    private AlertDialog.Builder alertDialog;
   ProgressDialog pd;
    private boolean checkGalleryPermission() {
        int ret = ActivityCompat.checkSelfPermission(IDCardActivity.this, Manifest.permission
                .READ_EXTERNAL_STORAGE);
        if (ret != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(IDCardActivity.this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    1000);
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcard);
        alertDialog = new AlertDialog.Builder(this);
        infoTextView = (TextView) findViewById(R.id.info_text_view);

        //  初始化本地质量控制模型,释放代码在onDestory中
        //  调用身份证扫描必须加上 intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true); 关闭自动初始化和释放本地模型
//        CameraNativeHelper.init(this, OCR.getInstance(this).getLicense(),
//                new CameraNativeHelper.CameraNativeInitCallback() {
//            @Override
//            public void onError(int errorCode, Throwable e) {
//                String msg;
//                switch (errorCode) {
//                    case CameraView.NATIVE_SOLOAD_FAIL:
//                        msg = "加载so失败，请确保apk中存在ui部分的so";
//                        break;
//                    case CameraView.NATIVE_AUTH_FAIL:
//                        msg = "授权本地质量控制token获取失败";
//                        break;
//                    case CameraView.NATIVE_INIT_FAIL:
//                        msg = "本地质量控制";
//                        break;
//                    default:
//                        msg = String.valueOf(errorCode);
//                }
//                infoTextView.setText("本地质量控制初始化错误，错误原因： " + msg);
//            }
//        });
        
        findViewById(R.id.gallery_button_front).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkGalleryPermission()) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE_FRONT);
                }
            }
        });

        findViewById(R.id.gallery_button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkGalleryPermission()) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE_BACK);
                }
            }
        });

        // 身份证正面拍照
        findViewById(R.id.id_card_front_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IDCardActivity.this, CameraActivity.class);
                try {
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                            FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                pd=new ProgressDialog(IDCardActivity.this);
                pd.show();
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

//        // 身份证正面扫描
//        findViewById(R.id.id_card_front_button_native).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(IDCardActivity.this, CameraActivity.class);
//                try {
//                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
//                            FileUtil.getSaveFile(getApplication()).getAbsolutePath());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,
//                        true);
//                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
//                // 请手动使用CameraNativeHelper初始化和释放模型
//                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
//                intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,
//                        true);
//                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
//            }
//        });

        // 身份证反面拍照
        findViewById(R.id.id_card_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IDCardActivity.this, CameraActivity.class);
                try {
                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                            FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                pd=new ProgressDialog(IDCardActivity.this);
                pd.show();
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        // 身份证反面扫描
//        findViewById(R.id.id_card_back_button_native).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(IDCardActivity.this, CameraActivity.class);
//                try {
//                    intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
//                            FileUtil.getSaveFile(getApplication()).getAbsolutePath());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,
//                        true);
//                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
//                // 请手动使用CameraNativeHelper初始化和释放模型
//                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
//                intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,
//                        true);
//                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
//            }
//        });
 }

    private void recIDCard(String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(10);

        OCR.getInstance(this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                pd.hide();
                if(result.getIdCardSide().equals("front")){
                Log.d("ddddr",result.getName().getWords());
                if (result != null) {
                    Intent intent = new Intent(IDCardActivity.this, FresultActivity.class);
                    String resu = "";

                    try {
                        resu += result.getName().getWords();
                        resu += "\n";
                    } catch (NullPointerException e) {
                        ;
                    }
                    try {
                        resu += result.getGender().getWords();
                        resu += "\n";
                    } catch (NullPointerException e) {
                        ;
                    }
                    try {
                        resu += result.getBirthday().getWords();
                        resu += "\n";
                    } catch (NullPointerException e) {
                        ;
                    }
                    try {
                        resu += result.getEthnic().getWords();
                        resu += "\n";
                    } catch (NullPointerException e) {
                        ;
                    }
                    try {
                        resu += result.getIdNumber().getWords();
                        resu += "\n";
                    } catch (NullPointerException e) {
                        ;
                    }
                    try {
                        resu += result.getAddress().getWords();
                        resu += "\n";
                    } catch (NullPointerException e) {
                        ;
                    }
                    intent.putExtra("ocrResult", resu);

                    startActivity(intent);
                }

                } else {
                    String resu="";
                    try {
                        resu += result.getIssueAuthority();
                        resu += "\n";
                    } catch (NullPointerException e) {
                        ;
                    }
                    try {
                        resu += result.getSignDate();
                        resu += "\n";
                    } catch (NullPointerException e) {
                        ;
                    }
                    try {
                        resu += result.getExpiryDate();
                        resu += "\n";
                    } catch (NullPointerException e) {
                        ;
                    }
                    Log.d("sxsxs", String.valueOf(result.getIssueAuthority()));
                    Intent intent = new Intent(IDCardActivity.this, FresultActivity.class);
                    intent.putExtra("ocrResult", resu);

                    startActivity(intent);
                }
            }

            @Override
            public void onError(OCRError error) {
                if(error.getErrorCode()==283504)
                    alertText("","你的网络当前状态不佳，请稍后再试！");
                else
                alertText("", error.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE_FRONT && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
        }

        if (requestCode == REQUEST_CODE_PICK_IMAGE_BACK && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String filePath = getRealPathFromURI(uri);
            recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
        }

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = null;
                try {
                    filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                    }
                }
            }
        }
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

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        // 释放本地质量控制模型
        CameraNativeHelper.release();
        super.onDestroy();
    }
}

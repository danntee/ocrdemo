/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.smsf.allroundscan.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

public class FileUtil {
    public static File getSaveFile(Context context) throws IOException {
        File file = new File(context.getFilesDir(), "pic.jpg");
        if(!file.exists())
        {
            if (Build.VERSION.SDK_INT <= 24) {
                FileOutputStream i = context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);
                i.flush();
                i.close();

            }
            file.setReadable(true);
            file.setWritable(true);
        }


        Log.d("aaa",context.getFilesDir().getAbsolutePath());
        return file;
    }
}

package com.example.gengchunjiang.mzorder_soft.activity.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by aezdd on 2016/8/21.
 */
public class OtherUtils {
    private static final String SD_PATH = "/sdcard/dskqxt/pic/";
    private static final String IN_PATH = "/dskqxt/pic/";
    public static String TAG = "UTIL";

    public static Bitmap getBitmap(String imageUri) {
        // 显示网络上的图片
        Bitmap bitmap = null;
        try {
            URL myFileUrl = new URL(imageUri);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public static String getCourrentTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("MM月dd日");
        String today = sf.format(c.getTime());
        return today;
    }

    public static String getOrderCode() {
        Date date = new Date();
        String str1 = "";
        for (int i = 0; i < 4; i++) {
            str1 += (int) (Math.random() * 10);
        }
        String str2 = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        String code = str2 + str1;
        return code;
    }


    /**
     * 设置上传图片图片名
     */
    public static String setUploadImgName() {
        Date date = new Date();
        String str1 = "";
        for (int i = 0; i < 4; i++) {
            str1 += (int) (Math.random() * 10);
        }
        String str2 = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        String imgName = "user" + str2 + str1;
        return imgName;
    }

    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath = "";
        File filePic = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;
        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }

        try {
            filePic = new File(savePath + setUploadImgName() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            Log.e("filePic", filePic.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }
}

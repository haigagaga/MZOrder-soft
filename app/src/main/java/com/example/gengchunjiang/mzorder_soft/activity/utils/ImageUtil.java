package com.example.gengchunjiang.mzorder_soft.activity.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gengchunjiang on 2017/3/28.
 */

public class ImageUtil {


    public static Bitmap getImg(String url)

    {
        URL imgurl = null;
        Bitmap bitmap = null;
        try
        {
            imgurl = new URL(url);

        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            HttpURLConnection conn = (HttpURLConnection)imgurl.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
            conn.disconnect();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;

    }
}

package com.example.gengchunjiang.mzorder_soft.activity.utils;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by gengchunjiang on 2017/3/28.
 */

public class GetPostUtil {

    private static final String TAG = "GetPostUtil";

    /**
     * get请求
     *
     * @param url
     * @param params
     * @return
     */

    public static String sendGet(String url, String params) throws InterruptedException {
        Thread.sleep(2000);
        String result = "";
        BufferedReader in = null;  //输入流，用来读取url响应
        try {
            String urlName = url + "?" + params;
            Log.d(TAG,urlName);
            URL realurl = new URL(urlName);
            URLConnection conn = realurl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");

            conn.connect();
            //获得响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            //遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "----->" + map.get(key));
            }
            //定义BufferedReader输入流来读取URL响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result +=  line;
                Log.d(TAG,""+result);
            }
        } catch (Exception e) {
            Log.d(TAG, "发送GET请求异常");
            e.printStackTrace();
        }
        //使用final关闭流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String  sendPost(String url, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");

            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数
            out.print(params);
            //输出流的缓冲
            out.flush();
            //定义BufferedReader输入流来读取URL响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            Log.d(TAG, "发送POST请求异常");
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}

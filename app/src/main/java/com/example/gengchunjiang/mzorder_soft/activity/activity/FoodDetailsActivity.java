package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.adapter.EvAdapter;
import com.example.gengchunjiang.mzorder_soft.activity.adapter.PlaceAdapter;
import com.example.gengchunjiang.mzorder_soft.activity.entity.EvEntity;
import com.example.gengchunjiang.mzorder_soft.activity.entity.FoodEntity;
import com.example.gengchunjiang.mzorder_soft.activity.entity.PlaceEntity;
import com.example.gengchunjiang.mzorder_soft.activity.utils.URLUtils;
import com.example.gengchunjiang.mzorder_soft.activity.widget.MyListView;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengchunjiang on 2017/3/30.
 */
//未注册
public class FoodDetailsActivity extends Activity{

    OkHttpClient okHttpClient = new OkHttpClient();

    private LinearLayout ant_movie_detail_user_evaluate_layout;
    private EditText ant_movie_detail_user_evaluate_edit_text;
    private Button bt_evalue;

    private ImageView iv_food;
    private TextView tv_foodName,tv_foodType,tv_price,tv_foodplace,tv_foodInfo;
    private String food_id ="";
    private String user_id = "";

    private List<EvEntity> evlist = new ArrayList<>();
    private MyListView lv_ev;
    private EvAdapter adapter;

    private SharedPreferences sp = null;
private String ifLogin ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food_info);

        sp = getSharedPreferences("user_msg", Context.MODE_PRIVATE);
         ifLogin = sp.getString("userId", "error");
        if (ifLogin.equals("error")) {
            //尚未登陆
//            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
        } else {
            //已登录
            user_id = sp.getString("userId","error");
        }

        Intent intent = getIntent();
        food_id = intent.getStringExtra("food_id");
        // 取到食物的id 然后去查
        doGet(food_id);
        getEv(food_id);
        initView();
    }

    private void getEv(String food_id) {
        Request.Builder builder = new Request.Builder();
        final Request request = builder.get().url(URLUtils.HTTPURL + "EvalueServlet"+"?id=" +
                food_id).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String res1 = response.body().string();
                String str = URLDecoder.decode(res1);
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(str);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Gson gosn = new Gson();
                        EvEntity e = gosn.fromJson(jsonArray.getJSONObject(i).toString(), EvEntity.class);
                        evlist.add(e);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lv_ev = (MyListView)findViewById(R.id.lv_ev);
                            adapter = new EvAdapter(FoodDetailsActivity.this, evlist);
                            lv_ev.setAdapter(adapter);
                        }
                    });
                }
            }
        });

    }

    private void initView() {
        ant_movie_detail_user_evaluate_layout = (LinearLayout)findViewById(R.id.ant_movie_detail_user_evaluate_layout);
        ant_movie_detail_user_evaluate_edit_text = (EditText)findViewById(R.id.ant_movie_detail_user_evaluate_edit_text);
        iv_food = (ImageView)findViewById(R.id.iv_food);
        tv_foodName = (TextView)findViewById(R.id.tv_foodName);
        tv_foodType = (TextView)findViewById(R.id.tv_foodType);
        tv_price = (TextView)findViewById(R.id.tv_price);
        tv_foodplace = (TextView)findViewById(R.id.tv_foodplace);
        tv_foodInfo = (TextView)findViewById(R.id.tv_foodInfo);

    }


    private void doGet(String id) {
        Request.Builder builder = new Request.Builder();
        final Request request = builder.get().url(URLUtils.HTTPURL + "ImageAndInfoServlet"+"?id=" +
                id).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(FoodDetailsActivity.this, "请求连接失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            @Override
            public void onResponse(Response response) throws IOException {
                final String res = response.body().string();
                String str = URLDecoder.decode(res);
                Log.e("___食物详情_____",""+str);
//                System.out.println("___食物详情_____" + str);
                Gson gson = new Gson();
                final FoodEntity foodEntity = gson.fromJson(str,FoodEntity.class);
                final String foodName = foodEntity.getFoodName();
                final String foodType = foodEntity.getTypeName();
                final double price = foodEntity.getFoodPrice();
                final String foodplace = foodEntity.getPlaceName();
                final String foodInfo = foodEntity.getFoodInfo();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BitmapUtils bitmapUtils = new BitmapUtils(getApplicationContext());
                        // 加载网络图片
                        bitmapUtils.display(iv_food, URLUtils.IMAGEURL
                                + foodEntity.getFoodPic());
                        tv_foodName.setText(foodName+"");
                        tv_foodType.setText(foodType+"");
                        tv_price.setText("$"+price);
                        tv_foodplace.setText(foodplace+"");
                        tv_foodInfo.setText(foodInfo+"");
                    }
                });
            }
        });

    }

    public void evalue(View v) {
        if (ifLogin.equals("error")) {
            //尚未登陆
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(this,LoginActivity.class);
            startActivity(intent1);
        } else {
            ant_movie_detail_user_evaluate_layout.setVisibility(View.VISIBLE);
        }

    }

    public void evaluateFood(View v) throws UnsupportedEncodingException {


            evlist.removeAll(evlist);
            ant_movie_detail_user_evaluate_layout.setVisibility(View.GONE);
            final String text = ant_movie_detail_user_evaluate_edit_text.getText().toString();
//        FormEncodingBuilder requestBodyBuilder = new FormEncodingBuilder();
//        RequestBody requestBody = requestBodyBuilder.add("evalue", rp).build();
            /**
             * post提交出现乱码。不能使用FormEncodingBuilder。okhttp3.0以后FormBody.Builder代替。
             */
            RequestBody body = RequestBody.
                    create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),
                            "evalue=" + text + "&foodId=" + food_id + "&userId=" + user_id);
            Request.Builder builder = new Request.Builder();
            Request request = builder.
                    url(URLUtils.HTTPURL + "EvalueServlet").
                    post(body)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    final String res1 = response.body().string();
                    String str = URLDecoder.decode(res1);
                    Log.e("查看评论", str);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(str);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Gson gosn = new Gson();
                            EvEntity e = gosn.fromJson(jsonArray.getJSONObject(i).toString(), EvEntity.class);
                            evlist.add(e);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FoodDetailsActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
                                lv_ev = (MyListView) findViewById(R.id.lv_ev);
                                adapter = new EvAdapter(FoodDetailsActivity.this, evlist);
                                lv_ev.setAdapter(adapter);
                                ant_movie_detail_user_evaluate_edit_text.setText("");
                            }
                        });

                    }
                }
            });

    }

    public void back(View view){
        this.finish();
    }
}

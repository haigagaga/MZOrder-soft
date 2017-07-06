package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.utils.URLUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MyBalanceActivity extends AppCompatActivity implements View.OnClickListener{

    OkHttpClient okHttpClient = new OkHttpClient();
    private RelativeLayout rl_chongzhi,rl_tixian;
    private SharedPreferences sp = null;
    private TextView tv_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_balance);

        initView();
    }


    private void initView() {
//        rl_chongzhi = (RelativeLayout)findViewById(R.id.rl_chongzhi);
//        rl_chongzhi.setOnClickListener(this);
//        rl_tixian = (RelativeLayout)findViewById(R.id.rl_tixian);
//        rl_tixian.setOnClickListener(this);
        sp = getSharedPreferences("user_msg", MODE_PRIVATE);
        String userId = sp.getString("userId","-1");
        get(userId);
//        String balance = sp.getString("balance", "wrong");
        tv_balance = (TextView) findViewById(R.id.tv_balance);

    }

    public void get(String userId){
        Request.Builder builder = new Request.Builder();
        final Request request = builder.get().url(URLUtils.HTTPURL + "LookBlanceServlet"+
                "?userId="+userId).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String res = response.body().string();
                Log.e("balance",res+"");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_balance.setText(res + "");
                    }
                });
            }
        });

    }



    public void back(View view){
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }
}

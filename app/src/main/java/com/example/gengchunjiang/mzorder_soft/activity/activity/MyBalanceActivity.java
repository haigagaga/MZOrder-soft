package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.gengchunjiang.mzorder_soft.R;

public class MyBalanceActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout rl_chongzhi,rl_tixian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_balance);

        initView();
    }


    private void initView() {
        rl_chongzhi = (RelativeLayout)findViewById(R.id.rl_chongzhi);
        rl_chongzhi.setOnClickListener(this);
        rl_tixian = (RelativeLayout)findViewById(R.id.rl_tixian);
        rl_tixian.setOnClickListener(this);
    }


    public void back(View view){
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_chongzhi:
                Toast.makeText(this, "提现成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_tixian:
                Toast.makeText(this, "充值成功", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}

package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;

public class ChangeAdressActivity extends AppCompatActivity {

    private TextView tv_add_adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_adress);

        initView();
    }

    private void initView() {
        tv_add_adress = (TextView) findViewById(R.id.tv_add_adress);
        Drawable drawable1 = getResources().getDrawable(R.drawable.ic_add);
        drawable1.setBounds(0, 0, 40, 40);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        tv_add_adress.setCompoundDrawables(null, null, drawable1, null);//只放左边

    }





}

package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.fragment.ShouyeFragment;

public class NoFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_error_hint);
    }


    public void rechoose(View view){
        this.finish();
    }
}

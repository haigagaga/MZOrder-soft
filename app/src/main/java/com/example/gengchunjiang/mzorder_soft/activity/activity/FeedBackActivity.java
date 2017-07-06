package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;
import com.lidroid.xutils.HttpUtils;
import com.squareup.okhttp.OkHttpClient;

import org.w3c.dom.Text;

public class FeedBackActivity extends AppCompatActivity {

    OkHttpClient okHttpClient = new OkHttpClient();

    private TextView txt_count;
    private EditText edit_content, edit_contact_way;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feed_back);

        initView();

    }

    private void initView() {
        txt_count = (TextView) findViewById(R.id.txt_count);
        edit_content = (EditText) findViewById(R.id.edit_content);
        edit_contact_way = (EditText) findViewById(R.id.edit_contact_way);
    }

    public void commit_feedback(View view) {
//        okHttpClient
    }
    public void back(View view){
        finish();
    }
}

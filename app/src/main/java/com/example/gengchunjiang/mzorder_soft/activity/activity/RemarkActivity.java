package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;

public class RemarkActivity extends AppCompatActivity {

    private TextView edit_remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_remark);

        initView();

    }


    private void initView() {
        edit_remark = (TextView) findViewById(R.id.edit_remark);
    }





    public void commit_remark(View view){
        String str_remark = edit_remark.getText().toString();
        Intent intent1 = new Intent(RemarkActivity.this,SettlementActivity.class);
        intent1.putExtra("remark",str_remark+"");
        setResult(RESULT_OK,intent1);
        finish();
    }

}

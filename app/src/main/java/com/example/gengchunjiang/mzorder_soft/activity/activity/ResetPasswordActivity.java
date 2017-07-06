package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gengchunjiang.mzorder_soft.R;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText edit_oldpsd,edit_newpsd,edit_repeatpsd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reset_password);
        
        initView();

    }

    private void initView() {
//        edit_oldpsd = (EditText)findViewById(R.id.edit_oldpsd);
//        edit_newpsd =(EditText) findViewById(R.id.edit_newpsd);
//        edit_repeatpsd = (EditText)findViewById(R.id.edit_repeatpsd);
    }


    public void commit(View view){
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
    }

    public void reset(View view){
        edit_oldpsd.setText("");
        edit_newpsd.setText("");
        edit_repeatpsd.setText("");
    }

    public void back(View view){
        finish();
    }


}

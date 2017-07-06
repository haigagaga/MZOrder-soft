package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gengchunjiang.mzorder_soft.R;

import com.example.gengchunjiang.mzorder_soft.activity.utils.GetPostUtil;
import com.example.gengchunjiang.mzorder_soft.activity.utils.URLUtils;

import java.lang.ref.WeakReference;

public class RegisterActivity extends AppCompatActivity {

    public static final String BASE_URL = URLUtils.HTTPURL+"login.do";
    private EditText ed_username, ed_password,edit_re_password;
    private static ProgressDialog dialog;
    private MyHandler handler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("用户注册");

        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        //初始化控件。
        this.init();
    }

    public void init() {
        ed_username = (EditText) findViewById(R.id.edit_username);
        ed_password = (EditText) findViewById(R.id.edit_password);
        edit_re_password = (EditText)findViewById(R.id.edit_re_password);
    }

    public void register(View view) {
        if (ed_password.getText().toString() != edit_re_password.getText().toString()) {
            Toast.makeText(this, "请保持两次输入的密码相同", Toast.LENGTH_SHORT).show();
        }
        else {


            if (dialog == null) {
                dialog = new ProgressDialog(RegisterActivity.this);
            }
            dialog.setTitle("请等待");
            dialog.setMessage("注册中...");
            dialog.setCancelable(false);
            dialog.show();
            final String username = ed_username.getText().toString();
            final String password = ed_password.getText().toString();
            //子线程
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String endurl = "username=" + username + "&password=" + password;
                        //url?name1=value1&name2=value2
                        String register_code = GetPostUtil.sendGet(BASE_URL, endurl);
                        Log.d("asd", "" + register_code);
                        if (register_code.equals("1")) {
                            handler.sendEmptyMessage(1);
                        } else {
                            handler.sendEmptyMessage(2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }


    //注意handler内存泄露问题
    private class MyHandler extends Handler {

        private final WeakReference<Activity> mActivity;

        //把activity弄里面？？？？
        private MyHandler(RegisterActivity Activity) {
            mActivity = new WeakReference<Activity>(Activity);
            //要使用RegisterActivity中的方法时可以用
            //(（RegisterActivity）mActivity.get()).fangfa
        }

        @Override
        public void handleMessage(Message msg) {
            if (dialog != null) {
                dialog.dismiss();
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Toast.makeText(RegisterActivity.this, "注册失败，请检查网络设置", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

    }

    public void back(View view){
        finish();
    }

}

package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gengchunjiang.mzorder_soft.R;

import com.example.gengchunjiang.mzorder_soft.activity.utils.GetPostUtil;

import java.lang.ref.WeakReference;


public class LoginActivity extends AppCompatActivity {


    private MyHandler handler = new MyHandler(this);
    EditText et_username, et_password;
    private static ProgressDialog dialog;
    public static final String LOGIN_URL = "http://172.18.15.164:8080/MZOrder_Server/LoginServlet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MZOrder-用户登录");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        init();

    }

    public void init() {
        et_username = (EditText) findViewById(R.id.edit_username);
        et_password = (EditText) findViewById(R.id.edit_password);
    }

    public void login(View v) throws Exception {
        if (dialog == null) {
            dialog = new ProgressDialog(LoginActivity.this);
        }
        dialog.setTitle("请等待");
        dialog.setMessage("登录中...");
        dialog.setCancelable(false);
        dialog.show();
        final String username = et_username.getText().toString();
        final String password = et_password.getText().toString();
        //子线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String endurl = "username=" + username + "&password=" + password;
                    //url?name1=value1&name2=value2
                    String login_code = GetPostUtil.sendGet(LOGIN_URL, endurl);
//                    Log.d("asd",""+login_code);
                    if (login_code.equals("1")) {
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

    // 将用户信息保存到配置文件
//    private void saveUserMsg(String msg){
//        // 用户编号
//        String id = "";
//        // 用户名称
//        String name = "";
//        // 获得信息数组
//        String[] msgs = msg.split(";");
//        int idx = msgs[0].indexOf("=");
//        id = msgs[0].substring(idx+1);
//        idx = msgs[1].indexOf("=");
//        name = msgs[1].substring(idx+1);
//        // 共享信息
//        SharedPreferences pre = getSharedPreferences("user_msg", MODE_WORLD_WRITEABLE);
//        SharedPreferences.Editor editor = pre.edit();
//        editor.putString("id", id);
//        editor.putString("name", name);
//        editor.commit();
//    }

    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void register(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    //注意handler内存泄露问题
    private class MyHandler extends Handler {

        private final WeakReference<Activity> mActivity;

        //把activity弄里面？？？？
        private MyHandler(LoginActivity Activity) {
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
                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Toast.makeText(LoginActivity.this, "登录失败，请检查用户名或密码", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

    }

}

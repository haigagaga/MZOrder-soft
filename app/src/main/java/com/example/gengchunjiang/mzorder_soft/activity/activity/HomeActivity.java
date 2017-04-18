package com.example.gengchunjiang.mzorder_soft.activity.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.adapter.WelAdapter;
import com.example.gengchunjiang.mzorder_soft.activity.fragment.GerenzhongxinFragment;
import com.example.gengchunjiang.mzorder_soft.activity.fragment.ShoppingCartFragment;
import com.example.gengchunjiang.mzorder_soft.activity.fragment.ShouyeFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    //底部菜单3个LinearLayout
    private LinearLayout ll_shouye,ll_gouwuche,ll_gerenzhongxin;
    //底部菜单3个ImageView
    private ImageView iv_shouye,iv_gouwuche,iv_gerenzhongxin;
    //底部菜单3个菜单标题
    private TextView tv_shouye,tv_gouwuche,tv_gerenzhongxin;
    //4个Fragment
    private Fragment woeleFragment,sousouFragment,gerenzhongxinFragment;
    //adapter
    private WelAdapter welAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *
         *         当程序进入后台返回时 可能会出现fragment不对应相应的activity
         *
         */
        if(savedInstanceState!= null)
        {
            String FRAGMENTS_TAG = "Android:support:fragments";
            savedInstanceState.remove(FRAGMENTS_TAG);

        }
//        setTitle("MZOrder-用户登录");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_home);

        //初始化控件
        initView();
        //初始化底部按钮事件
        initEvent();
        //初始化并设置当前Fragment
        initFragment(0);



    }

    private void initView(){
        ll_shouye = (LinearLayout)findViewById(R.id.ll_shouye);
        ll_gouwuche = (LinearLayout)findViewById(R.id.ll_gouwuche);
        ll_gerenzhongxin = (LinearLayout)findViewById(R.id.ll_gerenzhongxin);
        iv_shouye = (ImageView)findViewById(R.id.iv_shouye);
        iv_gouwuche = (ImageView)findViewById(R.id.iv_gouwuche);
        iv_gerenzhongxin = (ImageView)findViewById(R.id.iv_gerenzhongxin);
        tv_shouye = (TextView)findViewById(R.id.tv_shouye);
        tv_gouwuche = (TextView)findViewById(R.id.tv_gouwuche);
        tv_gerenzhongxin = (TextView)findViewById(R.id.tv_gerenzhongxin);

    }

    private void initEvent(){
        //设置监听
        ll_shouye.setOnClickListener(this);
        ll_gerenzhongxin.setOnClickListener(this);
        ll_gouwuche.setOnClickListener(this);
    }

    private void initFragment(int index){
        //Fragment管理 v4
        FragmentManager  fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏Fragment
        hideFragment(ft);
        switch (index) {
            case 0:
                if (woeleFragment == null) {
                    woeleFragment = new ShouyeFragment();
                    ft.add(R.id.fl_content, woeleFragment);
//                    Toast.makeText(this, "显示的主布局 case0", Toast.LENGTH_SHORT).show();
                } else {
                    ft.show(woeleFragment);
                }
                break;
            case 1:
                if (sousouFragment == null) {
                    sousouFragment = new ShoppingCartFragment();
                    ft.add(R.id.fl_content, sousouFragment);
                } else {
                    ft.show(sousouFragment);
                }
                break;
            case 2:
                if (gerenzhongxinFragment == null) {
                    gerenzhongxinFragment = new GerenzhongxinFragment();
                    ft.add(R.id.fl_content, gerenzhongxinFragment);
                } else {
                    ft.show(gerenzhongxinFragment);
                }
                break;
            default:
                break;
        }
        //提交事务
        ft.commit();
    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if (woeleFragment != null) {
            fragmentTransaction.hide(woeleFragment);
        }
        if (sousouFragment != null) {
            fragmentTransaction.hide(sousouFragment);
        }
        if (gerenzhongxinFragment != null) {
            fragmentTransaction.hide(gerenzhongxinFragment);
        }
    }


    @Override
    public void onClick(View v) {
        //在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        //ImageView和TextView置为绿色，页面跳转
        switch (v.getId()){
            case R.id.ll_shouye:
//                iv_shouye.setImageResource(R.drawable.ic_action_emo_basic);
                tv_shouye.setTextColor(0xff1B940A);
                initFragment(0);
//                Toast.makeText(this, "显示的 case0", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_gouwuche:
//                iv_gouwuche.setImageResource(R.drawable.ic_action_emo_angry);
                tv_gouwuche.setTextColor(0xff1B940A);
                initFragment(1);
//                Toast.makeText(this, "显示的 case1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_gerenzhongxin:
//                iv_gerenzhongxin.setImageResource(R.drawable.ic_action_emo_cool);
                tv_gerenzhongxin.setTextColor(0xff1B940A);
                initFragment(2);

//                Toast.makeText(this, "显示的 case2", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void restartBotton(){
        //ImageView置为灰色
        iv_gerenzhongxin.setImageResource(R.drawable.ic_action_user);
        iv_shouye.setImageResource(R.drawable.ic_action_home);
        iv_gouwuche.setImageResource(R.drawable.ic_action_cart);
        //textView置为黑白
        tv_gerenzhongxin.setTextColor(0xff000000);
        tv_shouye.setTextColor(0xff000000);
        tv_gouwuche.setTextColor(0xff000000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){

        }
        return super.onOptionsItemSelected(item);
    }



}

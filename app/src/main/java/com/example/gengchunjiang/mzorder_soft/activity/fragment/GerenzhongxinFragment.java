package com.example.gengchunjiang.mzorder_soft.activity.fragment;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.activity.AboutUsActivity;
import com.example.gengchunjiang.mzorder_soft.activity.activity.FeedBackActivity;
import com.example.gengchunjiang.mzorder_soft.activity.activity.LoginActivity;
import com.example.gengchunjiang.mzorder_soft.activity.activity.MyBalanceActivity;
import com.example.gengchunjiang.mzorder_soft.activity.activity.ResetPasswordActivity;


/**
 * Created by gengchunjiang on 2017/3/15.
 */

public class GerenzhongxinFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button but_zhuxiao, but_guanyuwomen;
    private RelativeLayout rl_about_us,rl_yijian,rl_xiugai,rl_yu_e;
    private RelativeLayout rl_phone_number;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gerenzhongxin, container, false);

        initView();

        return view;
    }

    private void initView() {
        but_zhuxiao = (Button) view.findViewById(R.id.but_zhuxiao);
        but_zhuxiao.setOnClickListener(this);
//        but_guanyuwomen = (Button) view.findViewById(R.id.but_guanyuwomen);
//        but_guanyuwomen.setOnClickListener(this);
        rl_about_us =(RelativeLayout)view.findViewById(R.id.rl_about_us);
        rl_about_us.setOnClickListener(this);
        rl_yijian = (RelativeLayout)view.findViewById(R.id.rl_yijian);
        rl_yijian.setOnClickListener(this);
        rl_xiugai = (RelativeLayout)view.findViewById(R.id.rl_xiugai);
        rl_xiugai.setOnClickListener(this);
        rl_yu_e = (RelativeLayout)view.findViewById(R.id.rl_yu_e);
        rl_yu_e.setOnClickListener(this);
        rl_phone_number = (RelativeLayout) view.findViewById(R.id.rl_phone_number);
        rl_phone_number.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_zhuxiao:
                getActivity().finish();
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_about_us:
                //跳转到关于我们的activity
                Intent intent2 = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_yijian:
                //跳转到意见反馈activity
                Intent intent3 = new Intent(getActivity(), FeedBackActivity.class);
                startActivity(intent3);
                break;
            case R.id.rl_xiugai:
                Intent intent4 = new Intent(getActivity(), ResetPasswordActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_yu_e:
                Intent intent5 = new Intent(getActivity(), MyBalanceActivity.class);
                startActivity(intent5);
                break;
            case R.id.rl_phone_number:

        }
    }
}

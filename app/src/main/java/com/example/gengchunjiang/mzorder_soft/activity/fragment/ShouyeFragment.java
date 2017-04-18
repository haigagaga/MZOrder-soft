package com.example.gengchunjiang.mzorder_soft.activity.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.activity.SearchFoodActivity;
import com.example.gengchunjiang.mzorder_soft.activity.adapter.WelAdapter;

import com.example.gengchunjiang.mzorder_soft.activity.entity.FoodEntity;
import com.example.gengchunjiang.mzorder_soft.activity.utils.GetPostUtil;
import com.example.gengchunjiang.mzorder_soft.activity.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengchunjiang on 2017/3/15.
 */

public class ShouyeFragment extends Fragment {


    private View view;
    //new的实例时候一定要写在onCreateView里面，不然效果会不一样
    private List<FoodEntity> list;
    private WelAdapter welAdapter;
    private GridView gridView;
    private ImageView iv_search;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shouye
                , container, false);
        initView();
        initData();
        initAdapter();
        itemOnclick();
        return view;
    }

    public void initView() {
        gridView = (GridView) view.findViewById(R.id.gv_shouye);
        iv_search=(ImageView)view.findViewById(R.id.iv_search);
    }

    public void initData() {
        list = new ArrayList<>();
        //添加数据

        Bitmap tempBitmap1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.hongshaorou);
        Bitmap tempBitmap2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.hongshaopaigu);
        list.add(new FoodEntity("红烧排骨", tempBitmap1));
        list.add(new FoodEntity("红烧肉", tempBitmap2));
        list.add(new FoodEntity("红烧排骨", tempBitmap1));
        list.add(new FoodEntity("红烧肉", tempBitmap2));
        list.add(new FoodEntity("红烧排骨", tempBitmap1));
        list.add(new FoodEntity("红烧肉", tempBitmap2));
        list.add(new FoodEntity("红烧排骨", tempBitmap1));
        list.add(new FoodEntity("红烧肉", tempBitmap2));
        list.add(new FoodEntity("红烧排骨", tempBitmap1));
        list.add(new FoodEntity("红烧肉", tempBitmap2));
    }

    public void initAdapter() {
        welAdapter = new WelAdapter(getContext(), list);
        gridView.setAdapter(welAdapter);
    }

    public void itemOnclick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                switch (position){
//                    case 0:
//                        Toast.makeText(getActivity(), "第一个", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1:
//                        Toast.makeText(getActivity(), "第er个", Toast.LENGTH_SHORT).show();
//                    default:
//                        break;
//                }

                Toast.makeText(getActivity(), "第" + position + "个", Toast.LENGTH_SHORT).show();
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                Bitmap bitmap = ImageUtil.getImg("http://172.18.15.164:8080/pic/logo.png");
                                Message msg = new Message();
                                msg.obj = bitmap;
                                msg.what = position;
                                handler.sendMessage(msg);
                            }
                        }
                ).start();


            }
        });
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                edit_search.setVisibility(View.VISIBLE);
//                tv_tuijian.setVisibility(View.GONE);
//                gridView.setVisibility(View.GONE);
//                bt_search.setVisibility(View.VISIBLE);
//                iv_search.setVisibility(View.GONE);
                //进入布局即显示软键盘
//                iv_search.requestFocus();
//                iv_search.setFocusable(true);
//                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInputFromInputMethod(iv_search.getWindowToken(),0);
                Intent intent1 = new Intent(getActivity(), SearchFoodActivity.class);
                startActivity(intent1);
            }
        });


    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private MyHandler handler = new MyHandler();

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }


}

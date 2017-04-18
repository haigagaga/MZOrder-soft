package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.gengchunjiang.mzorder_soft.R;

import com.example.gengchunjiang.mzorder_soft.activity.utils.GetPostUtil;

import java.net.URLDecoder;


public class SearchFoodActivity extends AppCompatActivity {

    private ImageButton bt_search;
    private EditText edit_search;
    public static final String SearchFood_URL = "http://172.18.15.164:8080/MZOrder_Server/SearchFoodServlet";

    private ListView lv_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        initView();
    }

    private void initView() {
        bt_search = (ImageButton) findViewById(R.id.ok);
        edit_search = (EditText) findViewById(R.id.query);
        edit_search.requestFocus();
        lv_search = (ListView) findViewById(R.id.list);
    }



    public void search(View view) {
        String search = edit_search.getText().toString();
        final String search_info = "search_info=" + search;
        lv_search.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //[{Food:{foodId=2, placeId=0, foodName=豉汁蒸排骨, foodPrice=26.0, foodType=热, foodPic=chizhizhengpaigu.jpg, foodInfo=豉汁蒸排骨是一道广东经典的传统菜肴。属于粤菜，肉鲜美而有豉、蒜香味。}},
                // {Food:{foodId=4, placeId=0, foodName=红菇排骨煲, foodPrice=30.0, foodType=热, foodPic=honggupaigutang.jpg, foodInfo=新鲜的红菇和排骨混合在一起散发的香气让人久久不能释怀，好吃不贵}}]

                String json_str = GetPostUtil.sendPost(SearchFood_URL, search_info);
                /**
                 * 要用编码解码来解决传递的字符串中有中文会出现乱码的情况
                 */
                String a=  URLDecoder.decode(json_str);
                    Log.d("-------------",a);


            }
        }).start();



    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };






    public void back(View view){
        finish();
    }


}

package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;

import com.example.gengchunjiang.mzorder_soft.activity.adapter.SearchFoodAdapter;
import com.example.gengchunjiang.mzorder_soft.activity.entity.FoodEntity;
import com.example.gengchunjiang.mzorder_soft.activity.utils.GetPostUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


public class SearchFoodActivity extends AppCompatActivity {

    private ImageButton bt_search;
    private EditText edit_search;
    public static final String SearchFood_URL = "http://172.18.15.164:8080/MZOrder_Server/SearchFoodServlet";

    private ListView lv_search;
    private SearchFoodAdapter searchFoodAdapter;
    private List<FoodEntity> foodlist = new ArrayList<>();
//    private Context context ;

    private ImageView iv_avatar;
    private TextView tv_foodName, tv_foodPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        initView();
    }



    private void initView() {
        tv_foodPlace = (TextView) findViewById(R.id.foodplace);
        tv_foodName = (TextView) findViewById(R.id.foodName);
        iv_avatar = (ImageView) findViewById(R.id.avatar);
        bt_search = (ImageButton) findViewById(R.id.ok);
        edit_search = (EditText) findViewById(R.id.query);
        edit_search.requestFocus();
        lv_search = (ListView) findViewById(R.id.list);
        searchFoodAdapter = new SearchFoodAdapter(foodlist, getApplicationContext());
        lv_search.setAdapter(searchFoodAdapter);
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
                String str = URLDecoder.decode(json_str);
                Log.d("---------", str);
//                int time = str.indexOf("{");
                try {
                    //[{"foodPrice":26,"foodName":"豉汁蒸排骨","foodInfo":"豉汁蒸排骨是一道广东经典的传统菜肴。属于粤菜，肉鲜美而有豉、蒜香味。","foodType":"热","foodId":2,"foodPic":"chizhizhengpaigu.jpg"},{"foodPrice":30,"foodName":"红菇排骨煲","foodInfo":"新鲜的红菇和排骨混合在一起散发的香气让人久久不能释怀，好吃不贵","foodType":"热","foodId":4,"foodPic":"honggupaigutang.jpg"}]
                    JSONArray jsonArray = new JSONArray(str);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
//                            String foodName = object.getString("foodName");
//                            float foodPrice = object.getInt("foodPrice");
//                            String foodPic = object.getString("foodPic");
//                            FoodEntity food = new FoodEntity();
//                            food.setFoodName(foodName);
////                            food.setFoodPlace(foodPlace);
//                            food.setFoodPrice(foodPrice);
                        Message msg = new Message();
                        msg.obj = object;
//                            Bundle bundle = new Bundle();
//                            bundle.putSerializable("food",food);
//                            msg.setData(bundle);
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).start();


    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
//                   Bundle buddle = msg.getData();
//                   Serializable food = buddle.getSerializable("food");
                    JSONObject object = (JSONObject) msg.obj;
                    try {
                        String foodName = object.getString("foodName");
                        float foodPrice = object.getInt("foodPrice");
//                            String foodPic = object.getString("foodPic");
                        FoodEntity food = new FoodEntity();
                        food.setFoodName(foodName);
//                            food.setFoodPlace(foodPlace);
                        food.setFoodPrice(foodPrice);
                        foodlist.add(food);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }

        }
    };


    public void back(View view) {
        finish();
    }


}

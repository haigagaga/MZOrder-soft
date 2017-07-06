package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gengchunjiang.mzorder_soft.R;

import com.example.gengchunjiang.mzorder_soft.activity.adapter.SearchFoodAdapter;
import com.example.gengchunjiang.mzorder_soft.activity.entity.FoodEntity;
import com.example.gengchunjiang.mzorder_soft.activity.utils.GetPostUtil;
import com.example.gengchunjiang.mzorder_soft.activity.utils.URLUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


public class SearchFoodActivity extends AppCompatActivity {

    private ImageButton bt_search;
    private EditText edit_search;
    public static final String SearchFood_URL = URLUtils.HTTPURL + "SearchFoodServlet";

    private ListView lv_search;
    private SearchFoodAdapter searchFoodAdapter;
    private List<FoodEntity> foodlist = new ArrayList<>();
    private RelativeLayout rl_listLayout;
//    private Context context ;

    private ImageView iv_avatar;
    private TextView tv_foodName, tv_foodPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search_food);
        initView();
    }



    private void initView() {
        rl_listLayout = (RelativeLayout) findViewById(R.id.rl_listLayout);
        tv_foodPlace = (TextView) findViewById(R.id.foodplace);
        tv_foodName = (TextView) findViewById(R.id.foodName);
        iv_avatar = (ImageView) findViewById(R.id.avatar);
        bt_search = (ImageButton) findViewById(R.id.ok);
        edit_search = (EditText) findViewById(R.id.query);
        edit_search.requestFocus();
        lv_search = (ListView) findViewById(R.id.list);

    }


    public void search(View view) {
        String search = edit_search.getText().toString();
        final String search_info = "search_info=" + search;
//        lv_search.setVisibility(View.VISIBLE);
//        rl_listLayout.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //[{Food:{foodId=2, placeId=0, foodName=豉汁蒸排骨, foodPrice=26.0, foodType=热, foodPic=chizhizhengpaigu.jpg, foodInfo=豉汁蒸排骨是一道广东经典的传统菜肴。属于粤菜，肉鲜美而有豉、蒜香味。}},
                // {Food:{foodId=4, placeId=0, foodName=红菇排骨煲, foodPrice=30.0, foodType=热, foodPic=honggupaigutang.jpg, foodInfo=新鲜的红菇和排骨混合在一起散发的香气让人久久不能释怀，好吃不贵}}]
                foodlist.removeAll(foodlist);
                String json_str = GetPostUtil.sendPost(SearchFood_URL, search_info);
                /**
                 * 要用编码解码来解决传递的字符串中有中文会出现乱码的情况
                 */
                String str = URLDecoder.decode(json_str);
                System.out.println(str);
                try {
                    JSONArray jsonArray = new JSONArray(str);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Gson gosn = new Gson();
                        FoodEntity foodEntity = gosn.fromJson(jsonArray.getJSONObject(i).toString(), FoodEntity.class);
                        foodlist.add(foodEntity);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.obj = foodlist;
                msg.what = 1;
                handler.sendMessage(msg);
            }

        }).start();


    }


    public void back(View view) {
        finish();
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Object obj = msg.obj;
                    foodlist = (List<FoodEntity>) obj;
                    Log.e("gcj", foodlist.toString());
                    if (foodlist.toString().equals("[]")) {
                        Toast.makeText(SearchFoodActivity.this, "未找到该菜品", Toast.LENGTH_SHORT).show();
                    } else {
                        rl_listLayout.setVisibility(View.VISIBLE);
                        searchFoodAdapter = new SearchFoodAdapter(foodlist, SearchFoodActivity.this);
                        lv_search.setAdapter(searchFoodAdapter);
                        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), FoodDetailsActivity.class);
                                intent.putExtra("food_id", foodlist.get(position).getFoodId() + "");
                                startActivity(intent);
                            }
                        });
                    }
            }
        }
    };
}

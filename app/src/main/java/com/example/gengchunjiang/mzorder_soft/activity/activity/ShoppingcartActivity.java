package com.example.gengchunjiang.mzorder_soft.activity.activity;

import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.adapter.ShopCartAdapter;
import com.example.gengchunjiang.mzorder_soft.activity.entity.FoodEntity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingcartActivity extends BaseActivity implements ShopCartAdapter.ModifyCountInterface
        , ShopCartAdapter.CheckInterface, View.OnClickListener {

    private TextView tv_title, tv_settlement, tv_show_price;
    private TextView tv_all_check;
    private TextView tv_edit;
    private CheckBox ck_all;
    private ListView list_shopping_cart;
    private ShopCartAdapter shopCartAdapter;
    private boolean flag = false;//用来控制是否显示”编辑“界面
    private List<FoodEntity> foodEntityList = new ArrayList<>();//不初始化会报空指针
    private boolean mSelect;
    private double totalPrice = 0.00; //商品总价
    private int totalCount = 0;  //商品总量
    private int checkNum = 0; //记录选中条目数

    /**
     * 批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();


    @Override
    protected int getLayout() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    protected void initView() {
        list_shopping_cart = bindView(R.id.list_shopping_cart);
        ck_all = bindView(R.id.ck_all);
        ck_all.setOnClickListener(this);
        tv_show_price = bindView(R.id.tv_show_price);
        tv_settlement = bindView(R.id.tv_settlement);
        tv_settlement.setOnClickListener(this);
        tv_edit = bindView(R.id.tv_edit);
        tv_edit.setOnClickListener(this);
        shopCartAdapter = new ShopCartAdapter(this, foodEntityList
                , this, this);
        list_shopping_cart.setAdapter(shopCartAdapter);

    }

    @Override
    protected void initData() {
        for (int i = 0; i < 6; i++) {
            FoodEntity foodEntity = new FoodEntity();
            foodEntity.setFoodName("锅包肉");
            foodEntity.setFoodPlace("百苑食堂");
            foodEntity.setFoodType("热");
            foodEntity.setFoodPrice(28);
            foodEntity.setCount(1);
            foodEntityList.add(foodEntity);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选
            case R.id.ck_all:
                if (foodEntityList.size() != 0) {
                    for (int i = 0; i < foodEntityList.size(); i++) {
                        foodEntityList.get(i).setChoosed(true);
                    }
                    shopCartAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < foodEntityList.size(); i++) {
                        foodEntityList.get(i).setChoosed(false);
                    }
                    shopCartAdapter.notifyDataSetChanged();
                }
//                    for (int i = 0; i < foodEntityList.size(); i++) {
//                        FoodEntity food = foodEntityList.get(i);
//                        if (food.isChoosed()) {
//                            checkNum = totalCount++;
//                        }
//                    }

                statistics();
                break;
            case R.id.tv_edit:
                flag = !flag;
                if (flag) {
                    tv_edit.setText("完成");
                    shopCartAdapter.isShow(false);
                } else {
                    tv_edit.setText("编辑");
                    shopCartAdapter.isShow(true);
                }
                break;


        }
    }

    /**
     * 单选
     *
     * @param position  元素位置
     * @param isChecked 元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        foodEntityList.get(position).setChoosed(isChecked);

        if (isAllCheck()) {
            ck_all.setChecked(true);
        } else {
            ck_all.setChecked(false);
        }

        shopCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 遍历list集合
     *
     * @return
     */

    public boolean isAllCheck() {
        for (FoodEntity group : foodEntityList) {
            if (!group.isChoosed) {
                return false;
            }
        }
        return true;
    }


    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < foodEntityList.size(); i++) {
            FoodEntity food = foodEntityList.get(i);
            if (food.isChoosed()) {
                totalCount++;
                totalPrice += food.getFoodPrice() * food.getCount();
            }
        }
        tv_show_price.setText("合计:" + totalPrice);
        tv_settlement.setText("结算(" + totalCount + ")");
    }

    /**
     * 增加
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        FoodEntity food = foodEntityList.get(position);
        int currentCount = food.getCount();
        currentCount++;
        food.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shopCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 删减
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        FoodEntity food = foodEntityList.get(position);
        int currentCount = food.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        food.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shopCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 删除
     *
     * @param position
     */

    @Override
    public void childDelete(int position) {
        foodEntityList.remove(position);
        shopCartAdapter.notifyDataSetChanged();
        statistics();
    }


}

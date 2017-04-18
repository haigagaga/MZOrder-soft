package com.example.gengchunjiang.mzorder_soft.activity.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.entity.FoodEntity;
import com.example.gengchunjiang.mzorder_soft.activity.fragment.ShoppingCartFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengchunjiang on 2017/4/5.
 */

public class ShopCartAdapter extends BaseAdapter {

    private boolean isShow = true; //是否显示编辑（完成）
    private List<FoodEntity> foodEntityList;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private Context context;
    private ShoppingCartFragment shoppingCartFragment;

    public ShopCartAdapter(Context context, List<FoodEntity> foodEntities,
                           CheckInterface checkInterface1,
                           ModifyCountInterface modifyCountInterface1) {
        this.context = context;
        this.foodEntityList = foodEntities;
        this.checkInterface = checkInterface1;
        this.modifyCountInterface = modifyCountInterface1;
        notifyDataSetChanged();
    }

    public ShopCartAdapter(ShoppingCartFragment shoppingCartFragment, List<FoodEntity> foodEntityList, ShoppingCartFragment checkInterface1, ShoppingCartFragment modifyCountInterface1) {
        this.shoppingCartFragment = shoppingCartFragment;
        this.foodEntityList = foodEntityList;
        this.checkInterface = checkInterface1;
        this.modifyCountInterface = modifyCountInterface1;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return foodEntityList == null ? 0 : foodEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 是否显示可编辑
     */

    public void isShow(boolean flag) {
        isShow = flag;
        notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(shoppingCartFragment.getActivity()).inflate(
                    R.layout.item_shopcart, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final FoodEntity foodEntity = foodEntityList.get(position);
        holder.ck_chose.setChecked(foodEntity.isChoosed());
        holder.tv_food_name.setText(foodEntity.getFoodName());
        holder.tv_food_place.setText(foodEntity.getFoodPlace());
        holder.tv_food_type.setText(foodEntity.getFoodType());
        holder.tv_price.setText("¥" + (int) foodEntity.getFoodPrice());
        holder.tv_show_num.setText(foodEntity.getCount()+"");
        holder.tv_num.setText("X"+foodEntity.getCount());


        //单选框按钮
        holder.ck_chose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        boolean flag1 = ((CheckBox) v).isChecked();
                        foodEntity.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );
//
//        //全选按钮
//        holder.ck_all.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // 遍历list的长度，将已选的设为未选，未选的设为已选
//                        for (int i = 0; i < list.size(); i++) {
//                            if (MyAdapter.getIsSelected().get(i)) {
//                                MyAdapter.getIsSelected().put(i, false);
//                                checkNum--;
//                            } else {
//                                MyAdapter.getIsSelected().put(i, true);
//                                checkNum++;
//                            }
//                        }
//                        // 刷新listview和TextView的显示
//                        dataChanged();
//                    }
//                    }
//                }
//        );





        //增加按钮
        holder.iv_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        modifyCountInterface.doIncrease(position, holder.tv_show_num, holder
                                .ck_chose.isChecked());   //暴露增加接口
                    }

                }
        );


        //删除按钮
        holder.iv_sub.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        modifyCountInterface.doDecrease(position, holder.tv_show_num, holder
                                .ck_chose.isChecked());   //暴露删减加接口
                    }

                }
        );

        //删除弹窗
        holder.tv_delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog alert = new AlertDialog.Builder(shoppingCartFragment.getActivity()).create();
                        alert.setTitle("操作提示");
                        alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                });
                        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        modifyCountInterface.childDelete(position);//目前只是从item中移除
                                    }
                                });
                        alert.show();
                    }
                });

        //判断是否在编辑状态下
        if (isShow){
            //不在编辑状态下
            holder.tv_food_name.setVisibility(View.VISIBLE);
            holder.tv_food_type.setVisibility(View.VISIBLE);
            holder.tv_food_place.setVisibility(View.VISIBLE);
            holder.tv_num.setVisibility(View.VISIBLE);
            holder.rl_edit.setVisibility(View.GONE);
            holder.tv_delete.setVisibility(View.GONE);
        }else{
            holder.tv_food_place.setVisibility(View.GONE);
            holder.tv_food_name.setVisibility(View.GONE);
            holder.tv_food_type.setVisibility(View.GONE);
            holder.rl_edit.setVisibility(View.VISIBLE);
            holder.tv_delete.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    class ViewHolder {
        ImageView iv_show_pic, iv_sub, iv_add;
        TextView tv_food_name, tv_food_type, tv_food_place, tv_price, tv_num, tv_delete, tv_show_num;
        CheckBox ck_chose,ck_all;
        RelativeLayout rl_edit;

        public ViewHolder(View itemView) {
            ck_all = (CheckBox)itemView.findViewById(R.id.ck_all);
            ck_chose = (CheckBox) itemView.findViewById(R.id.ck_chose);
            iv_show_pic = (ImageView) itemView.findViewById(R.id.iv_show_pic);
            iv_sub = (ImageView) itemView.findViewById(R.id.iv_sub);
            iv_add = (ImageView) itemView.findViewById(R.id.iv_add);
            tv_food_name = (TextView) itemView.findViewById(R.id.tv_food_name);
            tv_food_type = (TextView) itemView.findViewById(R.id.tv_food_type);
            tv_food_place = (TextView) itemView.findViewById(R.id.tv_food_place);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
            tv_show_num = (TextView) itemView.findViewById(R.id.tv_show_num);
            rl_edit = (RelativeLayout) itemView.findViewById(R.id.rl_edit);

        }
    }




    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int position, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int position, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param position
         */
        void childDelete(int position);
    }


}

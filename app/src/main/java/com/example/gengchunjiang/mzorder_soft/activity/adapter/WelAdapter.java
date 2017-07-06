package com.example.gengchunjiang.mzorder_soft.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.entity.FoodEntity;

import java.util.List;

/**
 * Created by gengchunjiang on 2017/3/30.
 */

public class WelAdapter extends BaseAdapter {

    private Context mContext;
    private List<FoodEntity> list;


    public WelAdapter(Context mContext, List<FoodEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.item_shouyezhanshi, parent, false);//item放入convertView
            holder = new ViewHolder();
            holder.food_image = (ImageView) convertView.findViewById(R.id.iv_item);
            holder.food_name = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        } else {
            //如果convertView不为空就getTag拿出来给holder
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.food_image.setImageBitmap(list.get(position).getFoodImage());
        holder.food_name.setText(list.get(position).getFoodName());
        return convertView;
    }

    //优化
    static class ViewHolder {
        ImageView food_image;
        TextView food_name;
    }

}

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
 * Created by gengchunjiang on 2017/4/11.
 */

public class SearchFoodAdapter extends BaseAdapter {

    private List<FoodEntity> foodEntityList;
    private LayoutInflater inflater;
    private Context context;
    private View view;

    public SearchFoodAdapter(List<FoodEntity> foodEntityList, Context context) {
        this.foodEntityList = foodEntityList;
        this.context = context;
        this.inflater = LayoutInflater.from((context));
    }

    @Override
    public int getCount() {
        return foodEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_search_food, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_foodPlace = (TextView) convertView.findViewById(R.id.foodplace);
            viewHolder.tv_foodName = (TextView) convertView.findViewById(R.id.foodName);
            viewHolder.iv_avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return view;
    }


    public class ViewHolder {
        ImageView iv_avatar;
        TextView tv_foodName;
        TextView tv_foodPlace;
    }

}

package com.example.gengchunjiang.mzorder_soft.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.entity.PlaceEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/1.
 */

public class PlaceAdapter extends BaseAdapter {


    private LayoutInflater inflater;
    private List<PlaceEntity> placeEntityList;
    private Context context;

    public PlaceAdapter(Context context, List<PlaceEntity> placeEntityList) {
        this.context = context;
        this.placeEntityList = placeEntityList;
        this.inflater = LayoutInflater.from((context));
    }

    //在此适配器中所代表的数据集中的条目数
    @Override
    public int getCount() {
        return placeEntityList.size();
    }

    //获取数据集中与指定索引对应的数据项
    @Override
    public Object getItem(int position) {
        return placeEntityList.get(position);
    }

    //取在列表中与指定索引对应的行id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_place_shouye, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv_like = (ImageView) convertView.findViewById(R.id.iv_like);
            viewHolder.tv_placeName = (TextView) convertView.findViewById(R.id.tv_placeName);
            viewHolder.tv_count = (TextView) convertView.findViewById(R.id.tv_count);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final PlaceEntity placeEntity = placeEntityList.get(position);
        if (placeEntity.getPlaceLike() == 0) {
            viewHolder.iv_like.setImageResource(R.mipmap.cinema_icon_like_0);
        } else if (placeEntity.getPlaceLike() == 100) {
            viewHolder.iv_like.setImageResource(R.mipmap.cinema_icon_like_100);
        } else if (placeEntity.getPlaceLike() > 0 && placeEntity.getPlaceLike() < 25) {
            viewHolder.iv_like.setImageResource(R.mipmap.cinema_icon_like_20);
        } else if (placeEntity.getPlaceLike() >= 25 && placeEntity.getPlaceLike() < 50) {
            viewHolder.iv_like.setImageResource(R.mipmap.cinema_icon_like_40);
        } else {
            viewHolder.iv_like.setImageResource(R.mipmap.cinema_icon_like_80);
        }

        viewHolder.tv_placeName.setText(placeEntity.getPlaceName());
        viewHolder.tv_count.setText(placeEntity.getPeopleCount() + "/500人");

        return convertView;
    }

    class ViewHolder {
        ImageView iv_like;
        TextView tv_placeName, tv_count;

    }
}

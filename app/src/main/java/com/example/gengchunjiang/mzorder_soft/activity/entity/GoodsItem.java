package com.example.gengchunjiang.mzorder_soft.activity.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoodsItem implements Serializable{
    public int id;
    public int typeId;
    public int rating;
    public String name;
    public String typeName;
    public double price;
    public int count;
    public String img;

    public GoodsItem(int id, double price, String name, int typeId, String typeName, String img) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        this.img = img;
        rating = new Random().nextInt(5) + 1;
    }

//    public GoodsItem(int id, double price, String name,  String typeName) {
//        this.id = id;
//        this.price = price;
//        this.name = name;
//
//        this.typeName = typeName;
//        rating = new Random().nextInt(5)+1;
//    }

    private static ArrayList<GoodsItem> goodsList;
    private static ArrayList<GoodsItem> typeList;
    private static ArrayList<String> typeLStr;

    private static void initData(List<FoodEntity> foodEntitieslist) {
        goodsList = new ArrayList<>();
        typeList = new ArrayList<>();
        typeLStr = new ArrayList<>();
        GoodsItem item = null;
//        for(int i=1;i<15;i++){
//            for(int j=1;j<10;j++){
//                item = new GoodsItem(100*i+j, Math.random()*100, "商品"+(100*i+j),i,"种类"+i);
//                goodsList.add(item);
//            }
//            typeList.add(item);
//        }
//        HashSet<String> set = new HashSet<>();
//        String typeName;
//        for (int i = 0; i < foodEntitieslist.size(); i++) {
//            FoodEntity food1 = foodEntitieslist.get(i);
//            typeName = food1.getTypeName();
//            set.add(typeName);
//        }
//        Iterator<String> iterator = set.iterator();
//            while(iterator.hasNext()){
//                String s = iterator.next();
//                Log.e("43534",iterator.next());
//                typeLStr.add(s);
//            }
//        //typeList.add(item);
//        for (int i = 0; i < typeLStr.size(); i++) {
//            for (int j = 0; j < foodEntitieslist.size(); j++) {
//                FoodEntity food = foodEntitieslist.get(i);
//                item = new GoodsItem(food.getFoodId(),food.getFoodPrice(),food.getFoodName(),
//                        i,food.getTypeName());  //food.getFoodId() 第一个食堂有4个foodd
//
//                goodsList.add(item);
//            }
//            //set.add(item);
//            typeList.add(item);
//            /*Iterator<GoodsItem> iterator = set.iterator();
//            while(iterator.hasNext()){
//                typeList.add(iterator.next());
//            }*/
//        }
//
//
//        for (int i = 0; i < foodEntitieslist.size(); i++) {
//            for (int j = 0; j < foodEntitieslist.size(); j++) {
//                FoodEntity food = foodEntitieslist.get(i);
//                item = new GoodsItem(food.getFoodId(),food.getFoodPrice(),food.getFoodName(),
//                        i,food.getTypeName());  //food.getFoodId() 第一个食堂有4个foodd
//
//                goodsList.add(item);
//            }
//            //set.add(item);
//            typeList.add(item);
//            /*Iterator<GoodsItem> iterator = set.iterator();
//            while(iterator.hasNext()){
//                typeList.add(iterator.next());
//            }*/
//        }
        //typeList.add(item);

        for (int i = 0; i < foodEntitieslist.size(); i++) {
            FoodEntity food = foodEntitieslist.get(i);
            item = new GoodsItem(food.getFoodId(), food.getFoodPrice(), food.getFoodName(),
                    i, food.getTypeName(), food.getFoodPic());  //food.getFoodId() 第一个食堂有4个foodd
            /*for (int j = 0; j < foodEntitieslist.size(); j++) {
                FoodEntity food = foodEntitieslist.get(i);
                item = new GoodsItem(food.getFoodId(),food.getFoodPrice(),food.getFoodName(),
                        i,food.getTypeName());  //food.getFoodId() 第一个食堂有4个foodd

                goodsList.add(item);
            }*/
            if (typeList.size() == 0) {
                typeList.add(item);
            } else {
                for (int j = 0; j < typeList.size(); j++) {
                    if (typeList.get(j).typeName.equals(food.getTypeName())) {
                        break;
                    } else if (j == typeList.size() - 1) {
                        typeList.add(item);
                    }
                }
            }


        }
        for (int i = 0; i < typeList.size(); i++) {
            for (int j = 0; j < foodEntitieslist.size(); j++) {
                if (foodEntitieslist.get(j).getTypeName().equals(typeList.get(i).typeName)) {
                    FoodEntity food = foodEntitieslist.get(j);
                    item = new GoodsItem(food.getFoodId(), food.getFoodPrice(), food.getFoodName(),
                            i, food.getTypeName(), food.getFoodPic());  //food.getFoodId() 第一个食堂有4个foodd
                    goodsList.add(item);
                }
            }
            //typeList.add(item);
        }
    }

    public static ArrayList<GoodsItem> getGoodsList(List<FoodEntity> foodEntitieslist) {
//        if(goodsList==null){
        initData(foodEntitieslist);
//        }
        return goodsList;
    }

    public static ArrayList<GoodsItem> getTypeList(List<FoodEntity> foodEntitieslist) {
//        if(typeList==null){
        initData(foodEntitieslist);
//        }
        return typeList;
    }
}

package com.example.gengchunjiang.mzorder_soft.activity.entity;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by gengchunjiang on 2017/3/30.
 */

public class FoodEntity {


    private int foodId;

    private String foodInfo;

    private String foodType;  //食物类型：凉、热

    private String foodName;

    private float foodPrice;

    private Bitmap foodImage;  //格式未确定

    private String foodPlace; //食物所在食堂

    public boolean isChoosed;

    public boolean isCheck = false;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public FoodEntity() {

    }


    public FoodEntity(String foodPlace, int foodId, String foodType, String foodName, float foodPrice, Bitmap foodImage) {
        this.foodId = foodId;
        this.foodType = foodType;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodImage = foodImage;
        this.foodPlace = foodPlace;
    }

    public FoodEntity(String foodName, Bitmap foodImage) {
        this.foodName = foodName;
        this.foodImage = foodImage;
    }

    public String getFoodPlace() {
        return foodPlace;
    }

    public void setFoodPlace(String foodPlace) {
        this.foodPlace = foodPlace;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Bitmap getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(Bitmap foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "foodPlace='" + foodPlace + '\'' +
                ", foodImage=" + foodImage +
                ", foodPrice=" + foodPrice +
                ", foodName='" + foodName + '\'' +
                ", foodType='" + foodType + '\'' +
                ", foodInfo='" + foodInfo + '\'' +
                ", foodId=" + foodId +
                '}';
    }
}

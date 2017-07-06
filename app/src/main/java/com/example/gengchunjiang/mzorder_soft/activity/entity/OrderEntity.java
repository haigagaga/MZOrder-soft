package com.example.gengchunjiang.mzorder_soft.activity.entity;

/**
 * Created by Administrator on 2017/6/3.
 */

public class OrderEntity {
    private int foodId;
    private int userId;
    private int orderId;
    private String orderTime;
    private float orderAmount;
    private String remark;
    private String orderType;
    private String foodName;
    private String userName;
    private int state;
    private String orderContent;
    private String placeName;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

package com.example.gengchunjiang.mzorder_soft.activity.entity;

import java.io.Serializable;

/**
 * Created by gengchunjiang on 2017/3/13.
 */

/**
 * 1.普通实体类  2.序列化（后期有时间可做）
 * <p>
 * 简单数据如：   data = {"username"="tom","password"="123"}
 */
public class UserEntity  {

    private int userId; //primary key

    private String username;

    private String password;

    private float balance;

    private String userPic;

    private String phoneNum;


    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }






    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public float getBalance() {
        return balance;
    }


    public void setBalance(float balance) {
        this.balance = balance;
    }


    public String getUserPic() {
        return userPic;
    }


    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }


    public String getPhoneNum() {
        return phoneNum;
    }


    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }




}

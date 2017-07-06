package com.example.gengchunjiang.mzorder_soft.activity.entity;

/**
 * Created by Administrator on 2017/5/1.
 */

public class PlaceEntity {
    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public float getPlaceLike() {
        return placeLike;
    }

    public void setPlaceLike(float placeLike) {
        this.placeLike = placeLike;
    }

    /**
     * 1.食堂名称  2.就餐人数  3.喜欢程度
     */

    private int placeId;

    private String placeName;

    private int peopleCount;

    private float placeLike;


}

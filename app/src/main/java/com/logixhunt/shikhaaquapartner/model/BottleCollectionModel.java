package com.logixhunt.shikhaaquapartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BottleCollectionModel {

    @SerializedName("m_user_id")
    @Expose
    private String mUserId;
    @SerializedName("m_user_name")
    @Expose
    private String mUserName;
    @SerializedName("m_user_mobile")
    @Expose
    private String mUserMobile;
    @SerializedName("m_user_address")
    @Expose
    private String mUserAddress;
    @SerializedName("m_total_bottle")
    @Expose
    private String mTotalBottle;
    @SerializedName("bottle_list")
    @Expose
    private List<BottleModel> bottleList;

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUserMobile() {
        return mUserMobile;
    }

    public void setmUserMobile(String mUserMobile) {
        this.mUserMobile = mUserMobile;
    }

    public String getmUserAddress() {
        return mUserAddress;
    }

    public void setmUserAddress(String mUserAddress) {
        this.mUserAddress = mUserAddress;
    }

    public List<BottleModel> getBottleList() {
        return bottleList;
    }

    public void setBottleList(List<BottleModel> bottleList) {
        this.bottleList = bottleList;
    }

    public String getmTotalBottle() {
        return mTotalBottle;
    }

    public void setmTotalBottle(String mTotalBottle) {
        this.mTotalBottle = mTotalBottle;
    }
}

package com.logixhunt.shikhaaquapartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BottleReportModel {

    @SerializedName("m_user_id")
    @Expose
    private String mUserId;
    @SerializedName("m_user_name")
    @Expose
    private String mUserName;
    @SerializedName("m_user_mobile")
    @Expose
    private String mUserMobile;
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

    public List<BottleModel> getBottleList() {
        return bottleList;
    }

    public void setBottleList(List<BottleModel> bottleList) {
        this.bottleList = bottleList;
    }
}

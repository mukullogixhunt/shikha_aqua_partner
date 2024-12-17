package com.logixhunt.shikhaaquapartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollectionModel {

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
    @SerializedName("total_balance_amt")
    @Expose
    private String totalBalanceAmt;

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

    public String getTotalBalanceAmt() {
        return totalBalanceAmt;
    }

    public void setTotalBalanceAmt(String totalBalanceAmt) {
        this.totalBalanceAmt = totalBalanceAmt;
    }
}

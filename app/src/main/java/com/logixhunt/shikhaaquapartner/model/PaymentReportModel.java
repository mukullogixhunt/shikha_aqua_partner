package com.logixhunt.shikhaaquapartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentReportModel {

    @SerializedName("m_trans_id")
    @Expose
    private String mTransId;
    @SerializedName("m_trans_amount")
    @Expose
    private String mTransAmount;
    @SerializedName("m_trans_date")
    @Expose
    private String mTransDate;
    @SerializedName("m_trans_paid_amount")
    @Expose
    private String mTransPaidAmount;
    @SerializedName("m_user_id")
    @Expose
    private String mUserId;
    @SerializedName("m_user_name")
    @Expose
    private String mUserName;
    @SerializedName("m_user_mobile")
    @Expose
    private String mUserMobile;

    public String getmTransId() {
        return mTransId;
    }

    public void setmTransId(String mTransId) {
        this.mTransId = mTransId;
    }

    public String getmTransAmount() {
        return mTransAmount;
    }

    public void setmTransAmount(String mTransAmount) {
        this.mTransAmount = mTransAmount;
    }

    public String getmTransDate() {
        return mTransDate;
    }

    public void setmTransDate(String mTransDate) {
        this.mTransDate = mTransDate;
    }

    public String getmTransPaidAmount() {
        return mTransPaidAmount;
    }

    public void setmTransPaidAmount(String mTransPaidAmount) {
        this.mTransPaidAmount = mTransPaidAmount;
    }

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
}

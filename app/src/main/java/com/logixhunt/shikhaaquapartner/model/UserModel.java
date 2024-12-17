package com.logixhunt.shikhaaquapartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("m_executive_id")
    @Expose
    private String mExecutiveId;
    @SerializedName("m_executive_name")
    @Expose
    private String mExecutiveName;
    @SerializedName("m_executive_mobile")
    @Expose
    private String mExecutiveMobile;
    @SerializedName("m_executive_email")
    @Expose
    private String mExecutiveEmail;
    @SerializedName("m_executive_gender")
    @Expose
    private String mExecutiveGender;
    @SerializedName("m_executive_dob")
    @Expose
    private String mExecutiveDob;
    @SerializedName("m_state_id")
    @Expose
    private String mStateId;
    @SerializedName("m_state_name")
    @Expose
    private String mStateName;
    @SerializedName("m_city_id")
    @Expose
    private String mCityId;
    @SerializedName("m_city_name")
    @Expose
    private String mCityName;
    @SerializedName("m_area_id")
    @Expose
    private String mAreaId;
    @SerializedName("m_area_name")
    @Expose
    private String mAreaName;
    @SerializedName("m_executive_address")
    @Expose
    private String mExecutiveAddress;


    public String getmExecutiveId() {
        return mExecutiveId;
    }

    public void setmExecutiveId(String mExecutiveId) {
        this.mExecutiveId = mExecutiveId;
    }

    public String getmExecutiveName() {
        return mExecutiveName;
    }

    public void setmExecutiveName(String mExecutiveName) {
        this.mExecutiveName = mExecutiveName;
    }

    public String getmExecutiveMobile() {
        return mExecutiveMobile;
    }

    public void setmExecutiveMobile(String mExecutiveMobile) {
        this.mExecutiveMobile = mExecutiveMobile;
    }

    public String getmExecutiveEmail() {
        return mExecutiveEmail;
    }

    public void setmExecutiveEmail(String mExecutiveEmail) {
        this.mExecutiveEmail = mExecutiveEmail;
    }

    public String getmExecutiveGender() {
        return mExecutiveGender;
    }

    public void setmExecutiveGender(String mExecutiveGender) {
        this.mExecutiveGender = mExecutiveGender;
    }

    public String getmExecutiveDob() {
        return mExecutiveDob;
    }

    public void setmExecutiveDob(String mExecutiveDob) {
        this.mExecutiveDob = mExecutiveDob;
    }

    public String getmStateId() {
        return mStateId;
    }

    public void setmStateId(String mStateId) {
        this.mStateId = mStateId;
    }

    public String getmStateName() {
        return mStateName;
    }

    public void setmStateName(String mStateName) {
        this.mStateName = mStateName;
    }

    public String getmCityId() {
        return mCityId;
    }

    public void setmCityId(String mCityId) {
        this.mCityId = mCityId;
    }

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmAreaId() {
        return mAreaId;
    }

    public void setmAreaId(String mAreaId) {
        this.mAreaId = mAreaId;
    }

    public String getmAreaName() {
        return mAreaName;
    }

    public void setmAreaName(String mAreaName) {
        this.mAreaName = mAreaName;
    }

    public String getmExecutiveAddress() {
        return mExecutiveAddress;
    }

    public void setmExecutiveAddress(String mExecutiveAddress) {
        this.mExecutiveAddress = mExecutiveAddress;
    }
}

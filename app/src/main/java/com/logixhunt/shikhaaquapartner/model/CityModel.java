package com.logixhunt.shikhaaquapartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityModel {

    @SerializedName("m_city_id")
    @Expose
    private String mCityId;
    @SerializedName("m_city_name")
    @Expose
    private String mCityName;

    public CityModel() {
    }

    public CityModel(String mCityId, String mCityName) {
        this.mCityId = mCityId;
        this.mCityName = mCityName;
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
}

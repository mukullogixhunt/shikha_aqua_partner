package com.logixhunt.shikhaaquapartner.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.model.CityModel;
import com.logixhunt.shikhaaquapartner.model.UserModel;

import java.util.List;

public class CityResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<CityModel> city;


    public List<CityModel> getCity() {
        return city;
    }

    public void setCity(List<CityModel> city) {
        this.city = city;
    }
}

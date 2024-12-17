package com.logixhunt.shikhaaquapartner.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.model.StateModel;
import com.logixhunt.shikhaaquapartner.model.UserModel;

import java.util.List;

public class StateResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<StateModel> state;

    public List<StateModel> getState() {
        return state;
    }

    public void setState(List<StateModel> state) {
        this.state = state;
    }
}

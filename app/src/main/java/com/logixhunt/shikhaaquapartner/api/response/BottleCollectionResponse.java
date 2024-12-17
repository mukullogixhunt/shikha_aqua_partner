package com.logixhunt.shikhaaquapartner.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.model.BottleCollectionModel;
import com.logixhunt.shikhaaquapartner.model.CollectionModel;

import java.util.List;

public class BottleCollectionResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<BottleCollectionModel> bottleCollectionModels;

    public List<BottleCollectionModel> getBottleCollectionModels() {
        return bottleCollectionModels;
    }

    public void setBottleCollectionModels(List<BottleCollectionModel> bottleCollectionModels) {
        this.bottleCollectionModels = bottleCollectionModels;
    }
}

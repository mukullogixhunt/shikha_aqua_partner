package com.logixhunt.shikhaaquapartner.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.model.CollectionModel;
import com.logixhunt.shikhaaquapartner.model.OrderModel;

import java.util.List;

public class CollectionResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<CollectionModel> collections;

    public List<CollectionModel> getCollections() {
        return collections;
    }

    public void setCollections(List<CollectionModel> collections) {
        this.collections = collections;
    }
}

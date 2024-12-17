package com.logixhunt.shikhaaquapartner.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.model.OrderModel;
import com.logixhunt.shikhaaquapartner.model.UserModel;

import java.util.List;

public class OrderResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<OrderModel> orders;

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }


}

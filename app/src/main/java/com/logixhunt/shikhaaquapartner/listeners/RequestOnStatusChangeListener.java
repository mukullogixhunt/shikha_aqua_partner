package com.logixhunt.shikhaaquapartner.listeners;


import com.logixhunt.shikhaaquapartner.model.OrderModel;

public interface RequestOnStatusChangeListener {
    void onStatusChange(OrderModel orderModel);
}

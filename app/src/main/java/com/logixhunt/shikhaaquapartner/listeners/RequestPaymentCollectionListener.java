package com.logixhunt.shikhaaquapartner.listeners;

import com.logixhunt.shikhaaquapartner.model.CollectionModel;
import com.logixhunt.shikhaaquapartner.model.OrderModel;

public interface RequestPaymentCollectionListener {
    void onPaymentCollect(CollectionModel collectionModel);
}

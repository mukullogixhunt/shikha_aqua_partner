package com.logixhunt.shikhaaquapartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BottleModel {

    @SerializedName("m_bottle_id")
    @Expose
    private String mBottleId;
    @SerializedName("m_bottle_company")
    @Expose
    private String mBottleCompany;
    @SerializedName("total_delivered_bottle")
    @Expose
    private String totalDeliveredBottle;
    @SerializedName("total_return_bottle")
    @Expose
    private String totalReturnBottle;
    @SerializedName("total_pending_bottle")
    @Expose
    private String totalPendingBottle;

    private String collectedQty = "0";

    public String getmBottleId() {
        return mBottleId;
    }

    public void setmBottleId(String mBottleId) {
        this.mBottleId = mBottleId;
    }

    public String getmBottleCompany() {
        return mBottleCompany;
    }

    public void setmBottleCompany(String mBottleCompany) {
        this.mBottleCompany = mBottleCompany;
    }

    public String getTotalDeliveredBottle() {
        return totalDeliveredBottle;
    }

    public void setTotalDeliveredBottle(String totalDeliveredBottle) {
        this.totalDeliveredBottle = totalDeliveredBottle;
    }

    public String getTotalReturnBottle() {
        return totalReturnBottle;
    }

    public void setTotalReturnBottle(String totalReturnBottle) {
        this.totalReturnBottle = totalReturnBottle;
    }

    public String getTotalPendingBottle() {
        return totalPendingBottle;
    }

    public void setTotalPendingBottle(String totalPendingBottle) {
        this.totalPendingBottle = totalPendingBottle;
    }

    public String getCollectedQty() {
        return collectedQty;
    }

    public void setCollectedQty(String collectedQty) {
        this.collectedQty = collectedQty;
    }
}

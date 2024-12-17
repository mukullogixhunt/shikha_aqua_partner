package com.logixhunt.shikhaaquapartner.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.model.BottleCollectionModel;
import com.logixhunt.shikhaaquapartner.model.PaymentReportModel;

import java.util.List;

public class PaymentReportResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<PaymentReportModel> paymentReports;

    public List<PaymentReportModel> getPaymentReports() {
        return paymentReports;
    }

    public void setPaymentReports(List<PaymentReportModel> paymentReports) {
        this.paymentReports = paymentReports;
    }
}

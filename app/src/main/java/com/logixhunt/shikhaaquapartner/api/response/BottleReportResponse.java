package com.logixhunt.shikhaaquapartner.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.model.BottleReportModel;
import com.logixhunt.shikhaaquapartner.model.PaymentReportModel;

import java.util.List;

public class BottleReportResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<BottleReportModel> bottleReports;

    public List<BottleReportModel> getBottleReports() {
        return bottleReports;
    }

    public void setBottleReports(List<BottleReportModel> bottleReports) {
        this.bottleReports = bottleReports;
    }
}

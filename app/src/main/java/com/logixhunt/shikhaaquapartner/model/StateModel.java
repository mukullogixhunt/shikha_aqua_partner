package com.logixhunt.shikhaaquapartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateModel {

    @SerializedName("m_state_id")
    @Expose
    private String mStateId;
    @SerializedName("m_state_name")
    @Expose
    private String mStateName;

    public StateModel(String mStateId, String mStateName) {
        this.mStateId = mStateId;
        this.mStateName = mStateName;
    }

    public StateModel() {
    }

    public String getmStateId() {
        return mStateId;
    }

    public void setmStateId(String mStateId) {
        this.mStateId = mStateId;
    }

    public String getmStateName() {
        return mStateName;
    }

    public void setmStateName(String mStateName) {
        this.mStateName = mStateName;
    }
}

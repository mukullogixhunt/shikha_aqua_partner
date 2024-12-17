package com.logixhunt.shikhaaquapartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderModel {


    @SerializedName("m_user_id")
    @Expose
    private String mUserId;
    @SerializedName("m_user_name")
    @Expose
    private String mUserName;
    @SerializedName("m_user_mobile")
    @Expose
    private String mUserMobile;
    @SerializedName("m_user_email")
    @Expose
    private String mUserEmail;
    @SerializedName("m_order_id")
    @Expose
    private String mOrderId;
    @SerializedName("m_order_bill_no")
    @Expose
    private String mOrderBillNo;
    @SerializedName("m_order_date")
    @Expose
    private String mOrderDate;
    @SerializedName("m_order_time")
    @Expose
    private String mOrderTime;
    @SerializedName("m_order_user")
    @Expose
    private String mOrderUser;
    @SerializedName("m_order_g_total")
    @Expose
    private String mOrderGTotal;
    @SerializedName("m_order_sub_total")
    @Expose
    private String mOrderSubTotal;
    @SerializedName("m_order_lat")
    @Expose
    private String mOrderLat;
    @SerializedName("m_order_long")
    @Expose
    private String mOrderLong;
    @SerializedName("m_order_address")
    @Expose
    private String mOrderAddress;
    @SerializedName("m_order_delv_date")
    @Expose
    private String mOrderDelvDate;
    @SerializedName("m_order_delv_time")
    @Expose
    private String mOrderDelvTime;
    @SerializedName("m_order_status")
    @Expose
    private String mOrderStatus;
    @SerializedName("m_order_paymode")
    @Expose
    private String mOrderPaymode;

    @SerializedName("m_order_delivered_date")
    @Expose
    private String mOrderDeliveredDate;

    @SerializedName("m_order_coupon_id")
    @Expose
    private String mOrderCouponId;
    @SerializedName("m_order_coupon_code")
    @Expose
    private String mOrderCouponCode;

    @SerializedName("m_order_coupon_discount")
    @Expose
    private String mOrderCouponDiscount;


    @SerializedName("m_order_delv_charges")
    @Expose
    private String mOrderDelvCharges;


    @SerializedName("order_items")
    @Expose
    private List<OrderItemModel> orderItems;

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUserMobile() {
        return mUserMobile;
    }

    public void setmUserMobile(String mUserMobile) {
        this.mUserMobile = mUserMobile;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public void setmUserEmail(String mUserEmail) {
        this.mUserEmail = mUserEmail;
    }

    public String getmOrderId() {
        return mOrderId;
    }

    public void setmOrderId(String mOrderId) {
        this.mOrderId = mOrderId;
    }

    public String getmOrderBillNo() {
        return mOrderBillNo;
    }

    public void setmOrderBillNo(String mOrderBillNo) {
        this.mOrderBillNo = mOrderBillNo;
    }

    public String getmOrderDate() {
        return mOrderDate;
    }

    public void setmOrderDate(String mOrderDate) {
        this.mOrderDate = mOrderDate;
    }

    public String getmOrderTime() {
        return mOrderTime;
    }

    public void setmOrderTime(String mOrderTime) {
        this.mOrderTime = mOrderTime;
    }

    public String getmOrderUser() {
        return mOrderUser;
    }

    public void setmOrderUser(String mOrderUser) {
        this.mOrderUser = mOrderUser;
    }

    public String getmOrderGTotal() {
        return mOrderGTotal;
    }

    public String getmOrderSubTotal() {
        return mOrderSubTotal;
    }

    public void setmOrderSubTotal(String mOrderSubTotal) {
        this.mOrderSubTotal = mOrderSubTotal;
    }

    public void setmOrderGTotal(String mOrderGTotal) {
        this.mOrderGTotal = mOrderGTotal;
    }

    public String getmOrderLat() {
        return mOrderLat;
    }

    public void setmOrderLat(String mOrderLat) {
        this.mOrderLat = mOrderLat;
    }

    public String getmOrderLong() {
        return mOrderLong;
    }

    public void setmOrderLong(String mOrderLong) {
        this.mOrderLong = mOrderLong;
    }

    public String getmOrderAddress() {
        return mOrderAddress;
    }

    public void setmOrderAddress(String mOrderAddress) {
        this.mOrderAddress = mOrderAddress;
    }

    public String getmOrderDelvDate() {
        return mOrderDelvDate;
    }

    public void setmOrderDelvDate(String mOrderDelvDate) {
        this.mOrderDelvDate = mOrderDelvDate;
    }

    public String getmOrderDelvTime() {
        return mOrderDelvTime;
    }

    public void setmOrderDelvTime(String mOrderDelvTime) {
        this.mOrderDelvTime = mOrderDelvTime;
    }

    public String getmOrderStatus() {
        return mOrderStatus;
    }

    public void setmOrderStatus(String mOrderStatus) {
        this.mOrderStatus = mOrderStatus;
    }

    public String getmOrderPaymode() {
        return mOrderPaymode;
    }

    public void setmOrderPaymode(String mOrderPaymode) {
        this.mOrderPaymode = mOrderPaymode;
    }

    public String getmOrderCouponId() {
        return mOrderCouponId;
    }

    public void setmOrderCouponId(String mOrderCouponId) {
        this.mOrderCouponId = mOrderCouponId;
    }

    public String getmOrderCouponCode() {
        return mOrderCouponCode;
    }

    public void setmOrderCouponCode(String mOrderCouponCode) {
        this.mOrderCouponCode = mOrderCouponCode;
    }

    public String getmOrderCouponDiscount() {
        return mOrderCouponDiscount;
    }

    public void setmOrderCouponDiscount(String mOrderCouponDiscount) {
        this.mOrderCouponDiscount = mOrderCouponDiscount;
    }

    public String getmOrderDelvCharges() {
        return mOrderDelvCharges;
    }

    public void setmOrderDelvCharges(String mOrderDelvCharges) {
        this.mOrderDelvCharges = mOrderDelvCharges;
    }

    public List<OrderItemModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemModel> orderItems) {
        this.orderItems = orderItems;
    }

    public String getmOrderDeliveredDate() {
        return mOrderDeliveredDate;
    }

    public void setmOrderDeliveredDate(String mOrderDeliveredDate) {
        this.mOrderDeliveredDate = mOrderDeliveredDate;
    }
}

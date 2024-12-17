package com.logixhunt.shikhaaquapartner.api;


import com.logixhunt.shikhaaquapartner.api.response.AreaResponse;
import com.logixhunt.shikhaaquapartner.api.response.BottleCollectionResponse;
import com.logixhunt.shikhaaquapartner.api.response.BottleReportResponse;
import com.logixhunt.shikhaaquapartner.api.response.CityResponse;
import com.logixhunt.shikhaaquapartner.api.response.CollectionResponse;
import com.logixhunt.shikhaaquapartner.api.response.NotificationResponse;
import com.logixhunt.shikhaaquapartner.api.response.OrderResponse;
import com.logixhunt.shikhaaquapartner.api.response.PaymentReportResponse;
import com.logixhunt.shikhaaquapartner.api.response.StateResponse;
import com.logixhunt.shikhaaquapartner.api.response.UserResponse;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.utils.Constant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST(Constant.EndPoint.EXECUTIVE_LOGIN)
    Call<UserResponse> userLogin(
            @Field(Constant.ApiKey.M_EXECUTIVE_MOBILE) String m_executive_mobile
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.VERIFY_EXECUTIVE_OTP)
    Call<UserResponse> verifyOtp(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id,
            @Field(Constant.ApiKey.M_EXECUTIVE_OTP) String m_executive_otp
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.SEND_EXECUTIVE_OTP)
    Call<UserResponse> sendOtp(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id
    );


    @POST(Constant.EndPoint.GET_STATE)
    Call<StateResponse> getState();

    @FormUrlEncoded
    @POST(Constant.EndPoint.GET_CITY)
    Call<CityResponse> getCity(
            @Field(Constant.ApiKey.M_STATE_ID) String m_state_id
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.GET_AREA)
    Call<AreaResponse> getArea(
            @Field(Constant.ApiKey.M_CITY_ID) String m_city_id
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.UPDATE_EXECUTIVE_PROFILE)
    Call<UserResponse> updateUserProfile(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id,
            @Field(Constant.ApiKey.M_EXECUTIVE_NAME) String m_executive_name,
            @Field(Constant.ApiKey.M_EXECUTIVE_EMAIL) String m_executive_email,
            @Field(Constant.ApiKey.M_EXECUTIVE_DOB) String m_executive_dob,
            @Field(Constant.ApiKey.M_EXECUTIVE_STATE) String m_executive_state,
            @Field(Constant.ApiKey.M_EXECUTIVE_CITY) String m_executive_city,
            @Field(Constant.ApiKey.M_EXECUTIVE_AREA) String m_executive_area,
            @Field(Constant.ApiKey.M_EXECUTIVE_ADDRESS) String m_executive_address
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.ORDER_LIST)
    Call<OrderResponse> getOrderList(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id,
            @Field(Constant.ApiKey.M_ORDER_STATUS) String m_order_status

    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.ORDER_DETAILS)
    Call<OrderResponse> getOrderDetails(
            @Field(Constant.ApiKey.M_ORDER_ID) String m_order_id
    );


    @FormUrlEncoded
    @POST(Constant.EndPoint.EXECUTIVE_ORDER_LIST)
    Call<OrderResponse> getExecutiveOrderList(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.UPDATE_ORDER_STATUS)
    Call<BaseResponse> updateOrderStatus(
            @Field(Constant.ApiKey.M_ORDER_ID) String m_order_id,
            @Field(Constant.ApiKey.M_ORDER_STATUS) String m_order_status,
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.UPDATE_FCM)
    Call<BaseResponse> updateFcm(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id,
            @Field(Constant.ApiKey.M_EXECUTIVE_FCM) String m_executive_fcm
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.USER_LIST_FOR_PAYMENT)
    Call<CollectionResponse> getCollections(
            @Field(Constant.ApiKey.M_EXECUTIVE_AREA) String m_executive_area
    );


    @FormUrlEncoded
    @POST(Constant.EndPoint.USER_LIST_FOR_BOTTLE_COLLECTION)
    Call<BottleCollectionResponse> getBottleCollections(
            @Field(Constant.ApiKey.M_EXECUTIVE_AREA) String m_executive_area
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.INSERT_PAYMENT)
    Call<BaseResponse> insertPaymentCollection(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id,
            @Field(Constant.ApiKey.M_USER_ID) String m_user_id,
            @Field(Constant.ApiKey.M_PAID_AMOUNT) String m_paid_amount
    );


    @FormUrlEncoded
    @POST(Constant.EndPoint.RETURN_BOTTLE)
    Call<BaseResponse> insertBottleCollection(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id,
            @Field(Constant.ApiKey.M_USER_ID) String m_user_id,
            @FieldMap HashMap<String, String> m_bottle_id,
            @FieldMap HashMap<String, String> m_bottle_qty
    );


    @FormUrlEncoded
    @POST(Constant.EndPoint.PAYMENT_COLLECTION_REPORT)
    Call<PaymentReportResponse> getPaymentReport(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.BOTTLE_COLLECTION_REPORT)
    Call<BottleReportResponse> getBottleReport(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id
    );

    @FormUrlEncoded
    @POST(Constant.EndPoint.GET_NOTIFICATION)
    Call<NotificationResponse> getNotifications(
            @Field(Constant.ApiKey.M_EXECUTIVE_ID) String m_executive_id
    );



}

package com.logixhunt.shikhaaquapartner.utils;

public class Constant {

    public static final String SUCCESS_RESPONSE_CODE = "200";
    public static final String SUCCESS_RESPONSE = "success";

    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String ddMMMyyyy = "dd-MMM-yyyy";

    public static final String ddMMM = "dd-MMM";
    public static final String HHmmss = "HH:mm:ss";
    public static final String hhmma = "hh:mm a";
    public static final String MM = "MM";

    public static final String EEddMMM = "EE, dd MMM";

    public static final String EEEEddMMMM = "EEEE, dd MMMM";
    public static String WEBVIEW_TITLE = "";
    public static String WEBVIEW_URL = "";

    public static final String HOME_SCREEN = "home";
    public static final String OTP_SCREEN = "otp";
    public static final String DB_NAME = "AppDatabase";


    public interface PreferenceConstant {
        String USER_DATA = "user_data";
        String IS_LOGGED_IN = "is_logged_in";
        String NOTIFICATION_COUNT = "notification_count";
        String FIREBASE_TOKEN = "firebase_token";
        String FIRST_LAUNCH_COMPLETE = "first_launch_complete";

    }

    public interface ApiKey {


        String M_EXECUTIVE_MOBILE = "m_executive_mobile";
        String M_EXECUTIVE_ID = "m_executive_id";
        String M_EXECUTIVE_OTP = "m_executive_otp";
        String M_CITY_ID = "m_city_id";
        String M_STATE_ID = "m_state_id";

        String M_EXECUTIVE_NAME = "m_executive_name";
        String M_EXECUTIVE_EMAIL = "m_executive_email";
        String M_EXECUTIVE_DOB = "m_executive_dob";
        String M_EXECUTIVE_STATE = "m_executive_state";
        String M_EXECUTIVE_CITY = "m_executive_city";
        String M_EXECUTIVE_AREA = "m_executive_area";
        String M_EXECUTIVE_ADDRESS = "m_executive_address";
        String M_ORDER_STATUS = "m_order_status";
        String M_ORDER_ID = "m_order_id";
        String M_ORDER_AREA = "m_order_area";
        String M_EXECUTIVE_FCM = "m_executive_fcm";

        String M_USER_ID = "m_user_id";
        String M_PAID_AMOUNT = "m_paid_amount";
    }

    public interface EndPoint {

        String EXECUTIVE_LOGIN = "executive_login";
        String SEND_EXECUTIVE_OTP = "send_executive_otp";
        String VERIFY_EXECUTIVE_OTP = "verify_executive_otp";
        String GET_STATE = "get_state";
        String GET_CITY = "get_city";
        String GET_AREA = "get_area";
        String UPDATE_EXECUTIVE_PROFILE = "update_executive_profile";
        String ORDER_LIST = "order_list";
        String ORDER_DETAILS = "order_details";
        String UPDATE_ORDER_STATUS = "update_order_status";
        String EXECUTIVE_ORDER_LIST = "executive_order_list";
        String UPDATE_FCM = "update_fcm";
        String USER_LIST_FOR_PAYMENT = "user_list_for_payment";
        String USER_LIST_FOR_BOTTLE_COLLECTION = "user_list_for_bottle_collection";
        String INSERT_PAYMENT = "insert_payment";
        String RETURN_BOTTLE = "return_bottle";
        String PAYMENT_COLLECTION_REPORT = "payment_collection_report";
        String BOTTLE_COLLECTION_REPORT = "bottle_collection_report";
        String GET_NOTIFICATION = "get_notification";
    }

    public interface BundleExtras {


        String USER_ID = "user_id";
        String ORDER_ID = "OrderId";
    }

}

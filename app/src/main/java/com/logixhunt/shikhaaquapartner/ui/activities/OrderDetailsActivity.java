package com.logixhunt.shikhaaquapartner.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.logixhunt.shikhaaquapartner.R;
import com.logixhunt.shikhaaquapartner.api.ApiClient;
import com.logixhunt.shikhaaquapartner.api.ApiInterface;
import com.logixhunt.shikhaaquapartner.api.response.OrderResponse;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.databinding.ActivityOrderDetailsBinding;
import com.logixhunt.shikhaaquapartner.gps.GpsTracker;
import com.logixhunt.shikhaaquapartner.model.OrderModel;
import com.logixhunt.shikhaaquapartner.model.UserModel;
import com.logixhunt.shikhaaquapartner.ui.adapters.OrderItemAdapter;
import com.logixhunt.shikhaaquapartner.ui.common.BaseActivity;
import com.logixhunt.shikhaaquapartner.utils.Constant;
import com.logixhunt.shikhaaquapartner.utils.IndianCurrencyFormat;
import com.logixhunt.shikhaaquapartner.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends BaseActivity {

    private ActivityOrderDetailsBinding binding;

    private OrderModel orderModel = new OrderModel();
    private String order_id = "";
    private UserModel userModel = new UserModel();

    private GpsTracker gpsTracker;

    private OrderItemAdapter orderItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getPreferenceData();
        initialization();
    }

    private void getPreferenceData() {
        userModel = getUserData(OrderDetailsActivity.this);
        order_id = getIntent().getStringExtra(Constant.BundleExtras.ORDER_ID);
    }

    private void initialization() {

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.tvGoToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(OrderDetailsActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                    allowPermissionDialog();
                } else {
                    String[] latLng = getLocation();
                    if (latLng != null) {
                        if (latLng[0].equalsIgnoreCase(getResources().getString(R.string._00))
                                || latLng[1].equalsIgnoreCase(getResources().getString(R.string._00))) {
                            Toast.makeText(OrderDetailsActivity.this, "" + getResources().getString(R.string.unable_to_find_location), Toast.LENGTH_SHORT).show();
                        } else {

                            if (latLng != null) {
                                if (latLng[0].equalsIgnoreCase( getResources().getString(R.string._00))
                                        || latLng[1].equalsIgnoreCase( getResources().getString(R.string._00))) {
                                    Toast.makeText(OrderDetailsActivity.this, "" +  getResources().getString(R.string.unable_to_find_location), Toast.LENGTH_SHORT).show();
                                } else {
                                    String sourceLatitude = latLng[0];
                                    String sourceLongitude = latLng[1];

                                    if (!orderModel.getmOrderLat().isEmpty() && !orderModel.getmOrderLong().isEmpty()) {

                                        String uri = "http://maps.google.com/maps?saddr=" + sourceLatitude + "," + sourceLongitude + "&daddr=" + orderModel.getmOrderLat() + "," + orderModel.getmOrderLong();
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(OrderDetailsActivity.this, "User's location not found! Please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }

            }
        });

        binding.tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + orderModel.getmUserMobile()));
                startActivity(intent);
            }
        });

        getOrderDetails();

    }

    private void allowPermissionDialog() {
        ActivityCompat.requestPermissions(OrderDetailsActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    public String[] getLocation() {
        gpsTracker = new GpsTracker(OrderDetailsActivity.this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            String[] latLng = new String[2];
            latLng[0] = String.valueOf(latitude);
            latLng[1] = String.valueOf(longitude);
            return latLng;
        } else {
            gpsTracker.showSettingsAlert();
            return null;
        }

    }

    private void getOrderDetails() {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<OrderResponse> call = apiService.getOrderDetails(order_id);
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                hideLoader();
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {

                            orderModel = response.body().getOrders().get(0);

                            setBookingData();
                        } else {

                        }
                    } else {

                    }
                } catch (Exception e) {
                    log_e(this.getClass().getSimpleName(), "onResponse: ", e);

                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                hideLoader();
                // Log error here since request failed
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void setBookingData() {

        switch (orderModel.getmOrderStatus()) {
            case "1":
                binding.ivPending.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.primary));
                binding.btnAccept.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.accepted));
                binding.btnAccept.setText(R.string.accept);
                binding.checkoutCard.setVisibility(View.VISIBLE);
                break;
            case "2":
                binding.view1.setBackgroundColor(getResources().getColor(R.color.primary));
                binding.ivAccepted.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.primary));
                binding.btnAccept.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.running));
                binding.btnAccept.setText(R.string.dispatch_);
                binding.checkoutCard.setVisibility(View.VISIBLE);
                break;
            case "3":
                binding.view1.setBackgroundColor(getResources().getColor(R.color.primary));
                binding.ivAccepted.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.primary));
                binding.view2.setBackgroundColor(getResources().getColor(R.color.primary));
                binding.ivRunning.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.primary));
                binding.btnAccept.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.completed));
                binding.btnAccept.setText(R.string.mark_as_complete);
                binding.checkoutCard.setVisibility(View.VISIBLE);
                break;
            case "4":
                binding.view1.setBackgroundColor(getResources().getColor(R.color.primary));
                binding.ivAccepted.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.primary));
                binding.view2.setBackgroundColor(getResources().getColor(R.color.primary));
                binding.ivRunning.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.primary));
                binding.view3.setBackgroundColor(getResources().getColor(R.color.primary));
                binding.ivCompleted.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.primary));
                binding.checkoutCard.setVisibility(View.GONE);
                break;
        }


        binding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderModel.getmOrderStatus()) {
                    case "1":
                        updateStatusApi(orderModel.getmOrderId(), "2");
                        break;
                    case "2":
                        updateStatusApi(orderModel.getmOrderId(), "3");
                        break;
                    case "3":
                        updateStatusApi(orderModel.getmOrderId(), "4");
                        break;
                }
            }
        });

        binding.orderId.setText(String.format("#%s", orderModel.getmOrderBillNo()));

        binding.tvTime.setText(Utils.changeDateFormat(Constant.HHmmss, Constant.hhmma, orderModel.getmOrderTime()));
        binding.tvDate.setText(Utils.changeDateFormat(Constant.yyyyMMdd, Constant.EEEEddMMMM, orderModel.getmOrderDate()));


        binding.tvDeliveryTime.setText(orderModel.getmOrderDelvTime());

        if (orderModel.getmOrderDeliveredDate().equals("0000-00-00 00:00:00")){
            binding.tvDeliveryDate.setText("Not Delivered");
        }else {
            binding.tvDeliveryDate.setText(Utils.changeDateFormat(Constant.yyyyMMdd, Constant.EEEEddMMMM, orderModel.getmOrderDeliveredDate()));
        }


        binding.tvDeliveryDateSlot.setText(Utils.changeDateFormat(Constant.yyyyMMdd, Constant.EEEEddMMMM, orderModel.getmOrderDelvDate()));
        binding.tvAddress.setText(orderModel.getmOrderAddress());
        binding.tvCustomerName.setText(orderModel.getmUserName());




        binding.itemTotal.setText(String.format("%s/-", new IndianCurrencyFormat().inCuFormatText(orderModel.getmOrderSubTotal())));
        binding.taxes.setText(String.format("%s/-", new IndianCurrencyFormat().inCuFormatText("0")));


//        if (orderModel.getmOrderCouponCode().isEmpty() || orderModel.getmOrderCouponDiscount().isEmpty()) {
//            binding.selectedCouponLayout.setVisibility(View.GONE);
//        } else {
//            binding.selectedCouponLayout.setVisibility(View.VISIBLE);
//            binding.couponCode.setText(String.format("Coupon - (%s)", orderModel.getmOrderCouponCode()));
            binding.couponDiscount.setText(String.format("-%s/-", new IndianCurrencyFormat().inCuFormatText(orderModel.getmOrderCouponDiscount())));
//        }


        binding.grandTotal.setText(String.format("%s/-", new IndianCurrencyFormat().inCuFormatText(orderModel.getmOrderGTotal())));

        binding.payMode.setText(orderModel.getmOrderPaymode());

//        binding.tvViewMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openBottomSheetForCartItem(orderModel.getOrderItem());
//            }
//        });

        binding.rvItems.setLayoutManager(new LinearLayoutManager(OrderDetailsActivity.this));
        orderItemAdapter = new OrderItemAdapter(orderModel.getOrderItems(), this);
        binding.rvItems.setAdapter(orderItemAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrderDetails();
    }

    private void updateStatusApi(String bookingId, String status) {
        showLoader();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BaseResponse> call = apiService.updateOrderStatus(bookingId, status, userModel.getmExecutiveId());
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                hideLoader();
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        showError(response.body().getMessage());
                        getOrderDetails();
                    } else {
                        showError(response.message());
                    }
                } catch (Exception e) {
                    hideLoader();
                    log_e(this.getClass().getSimpleName(), "onResponse: ", e);

                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                hideLoader();
                Log.e("Failure", t.toString());
                showError("Something went wrong");

            }
        });
    }
}
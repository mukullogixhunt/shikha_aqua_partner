package com.logixhunt.shikhaaquapartner.ui.fragments.bookingtabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.logixhunt.shikhaaquapartner.R;
import com.logixhunt.shikhaaquapartner.api.ApiClient;
import com.logixhunt.shikhaaquapartner.api.ApiInterface;
import com.logixhunt.shikhaaquapartner.api.response.OrderResponse;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.databinding.FragmentAcceptedBookingBinding;
import com.logixhunt.shikhaaquapartner.listeners.RequestOnStatusChangeListener;
import com.logixhunt.shikhaaquapartner.model.OrderModel;
import com.logixhunt.shikhaaquapartner.model.UserModel;
import com.logixhunt.shikhaaquapartner.ui.adapters.OrderAdapter;
import com.logixhunt.shikhaaquapartner.ui.common.BaseFragment;
import com.logixhunt.shikhaaquapartner.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AcceptedBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AcceptedBookingFragment extends BaseFragment implements RequestOnStatusChangeListener {

    private FragmentAcceptedBookingBinding binding;

    private UserModel userModel = new UserModel();
    private List<OrderModel> orderList = new ArrayList<>();
    private OrderAdapter orderAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AcceptedBookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AcceptedBookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AcceptedBookingFragment newInstance(String param1, String param2) {
        AcceptedBookingFragment fragment = new AcceptedBookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAcceptedBookingBinding.inflate(getLayoutInflater(), container, false);
        getPreferenceData();
        initialization();
        return binding.getRoot();
    }

    private void getPreferenceData() {
        userModel = getUserData(requireActivity());
    }

    private void initialization() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.rvOrders.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(requireContext(), orderList, this);
        binding.rvOrders.setAdapter(orderAdapter);
        getOrdersApi();

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOrdersApi();
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.swipeRefresh.setRefreshing(false);
                    }
                }, 2000);*/
            }
        });
    }

    private void getOrdersApi() {
        binding.swipeRefresh.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<OrderResponse> call = apiInterface.getOrderList(userModel.getmExecutiveId(),"4");
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                binding.swipeRefresh.setRefreshing(false);
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            orderList.clear();
                            orderList.addAll(response.body().getOrders());
                            orderAdapter.notifyDataSetChanged();
                            binding.noData.setVisibility(View.GONE);
                            binding.rvOrders.setVisibility(View.VISIBLE);
                        } else {
                            binding.noData.setVisibility(View.VISIBLE);
                            binding.rvOrders.setVisibility(View.GONE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    binding.noData.setVisibility(View.VISIBLE);
                    binding.rvOrders.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                binding.swipeRefresh.setRefreshing(false);
                binding.noData.setVisibility(View.VISIBLE);
                binding.rvOrders.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onStatusChange(OrderModel orderModel) {
        switch (orderModel.getmOrderStatus()) {
            case "2":
                updateStatusApi(orderModel.getmOrderId(),"3");
                break;
            case "3":
                updateStatusApi(orderModel.getmOrderId(),"4");
                break;
        }

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
                        getOrdersApi();
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

    @Override
    public void onResume() {
        super.onResume();
        getOrdersApi();
    }
}
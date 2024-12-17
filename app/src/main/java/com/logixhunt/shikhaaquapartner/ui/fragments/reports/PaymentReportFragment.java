package com.logixhunt.shikhaaquapartner.ui.fragments.reports;

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
import com.logixhunt.shikhaaquapartner.api.response.CollectionResponse;
import com.logixhunt.shikhaaquapartner.api.response.PaymentReportResponse;
import com.logixhunt.shikhaaquapartner.databinding.FragmentPaymentReportBinding;
import com.logixhunt.shikhaaquapartner.model.CollectionModel;
import com.logixhunt.shikhaaquapartner.model.PaymentReportModel;
import com.logixhunt.shikhaaquapartner.model.UserModel;
import com.logixhunt.shikhaaquapartner.ui.adapters.CollectionAdapter;
import com.logixhunt.shikhaaquapartner.ui.adapters.PaymentReportAdapter;
import com.logixhunt.shikhaaquapartner.ui.common.BaseFragment;
import com.logixhunt.shikhaaquapartner.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentReportFragment extends BaseFragment {

    private FragmentPaymentReportBinding binding;

    private UserModel userModel = new UserModel();

    private List<PaymentReportModel> paymentReportList = new ArrayList<>();
    private PaymentReportAdapter paymentReportAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PaymentReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentReportFragment newInstance(String param1, String param2) {
        PaymentReportFragment fragment = new PaymentReportFragment();
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
        binding = FragmentPaymentReportBinding.inflate(getLayoutInflater(),container,false);
        getPreferenceData();
        initialization();
        return binding.getRoot();
    }

    private void getPreferenceData() {
        userModel = getUserData(requireActivity());
    }
    private void initialization() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.rvCollections.setLayoutManager(linearLayoutManager);
        paymentReportAdapter = new PaymentReportAdapter(requireContext(), paymentReportList);
        binding.rvCollections.setAdapter(paymentReportAdapter);
        getPaymentReportApi();

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPaymentReportApi();
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.swipeRefresh.setRefreshing(false);
                    }
                }, 2000);*/
            }
        });
    }

    private void getPaymentReportApi() {
        binding.swipeRefresh.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<PaymentReportResponse> call = apiInterface.getPaymentReport(userModel.getmExecutiveId());
        call.enqueue(new Callback<PaymentReportResponse>() {
            @Override
            public void onResponse(Call<PaymentReportResponse> call, Response<PaymentReportResponse> response) {
                binding.swipeRefresh.setRefreshing(false);
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            if (response.body().getPaymentReports().size()>0){
                                paymentReportList.clear();
                                paymentReportList.addAll(response.body().getPaymentReports());
                                paymentReportAdapter.notifyDataSetChanged();
                                binding.noData.setVisibility(View.GONE);
                                binding.rvCollections.setVisibility(View.VISIBLE);
                                binding.tvCollections.setVisibility(View.VISIBLE);
                            }else {
                                binding.noData.setVisibility(View.VISIBLE);
                                binding.rvCollections.setVisibility(View.GONE);
                                binding.tvCollections.setVisibility(View.GONE);
                            }
                        } else {
                            binding.noData.setVisibility(View.VISIBLE);
                            binding.rvCollections.setVisibility(View.GONE);
                            binding.tvCollections.setVisibility(View.GONE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    binding.noData.setVisibility(View.VISIBLE);
                    binding.rvCollections.setVisibility(View.GONE);
                    binding.tvCollections.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<PaymentReportResponse> call, Throwable t) {
                binding.swipeRefresh.setRefreshing(false);
                binding.noData.setVisibility(View.VISIBLE);
                binding.rvCollections.setVisibility(View.GONE);
                binding.tvCollections.setVisibility(View.GONE);
            }
        });
    }


}
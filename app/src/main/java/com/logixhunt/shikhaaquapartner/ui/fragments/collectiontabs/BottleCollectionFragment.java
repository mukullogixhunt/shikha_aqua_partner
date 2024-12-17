package com.logixhunt.shikhaaquapartner.ui.fragments.collectiontabs;

import android.app.Dialog;
import android.content.Intent;
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
import com.logixhunt.shikhaaquapartner.api.response.BottleCollectionResponse;
import com.logixhunt.shikhaaquapartner.api.response.CollectionResponse;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.databinding.BottleCollectionAdapterBinding;
import com.logixhunt.shikhaaquapartner.databinding.BottleCollectionDialogBinding;
import com.logixhunt.shikhaaquapartner.databinding.CollectionSuccessDialogBinding;
import com.logixhunt.shikhaaquapartner.databinding.FragmentBottleCollectionBinding;
import com.logixhunt.shikhaaquapartner.databinding.PaymentCollectionDialogBinding;
import com.logixhunt.shikhaaquapartner.listeners.RequestBottleCollectionListener;
import com.logixhunt.shikhaaquapartner.model.BottleCollectionModel;
import com.logixhunt.shikhaaquapartner.model.CollectionModel;
import com.logixhunt.shikhaaquapartner.model.UserModel;
import com.logixhunt.shikhaaquapartner.ui.activities.MainActivity;
import com.logixhunt.shikhaaquapartner.ui.adapters.BottleAdapter;
import com.logixhunt.shikhaaquapartner.ui.adapters.BottleCollectionAdapter;
import com.logixhunt.shikhaaquapartner.ui.adapters.CollectionAdapter;
import com.logixhunt.shikhaaquapartner.ui.common.BaseFragment;
import com.logixhunt.shikhaaquapartner.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottleCollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottleCollectionFragment extends BaseFragment implements RequestBottleCollectionListener {

    private FragmentBottleCollectionBinding binding;
    private UserModel userModel = new UserModel();

    private List<BottleCollectionModel> collectionList = new ArrayList<>();
    private BottleCollectionAdapter bottleCollectionAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BottleCollectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottleCollectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottleCollectionFragment newInstance(String param1, String param2) {
        BottleCollectionFragment fragment = new BottleCollectionFragment();
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
        binding = FragmentBottleCollectionBinding.inflate(getLayoutInflater(), container, false);
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
        bottleCollectionAdapter = new BottleCollectionAdapter(requireContext(), collectionList, this);
        binding.rvCollections.setAdapter(bottleCollectionAdapter);
        getCollectionApi();

        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCollectionApi();
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.swipeRefresh.setRefreshing(false);
                    }
                }, 2000);*/
            }
        });
    }

    private void getCollectionApi() {
        binding.swipeRefresh.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<BottleCollectionResponse> call = apiInterface.getBottleCollections(userModel.getmAreaId());
        Log.d("TAG", "getOrdersApi: " + userModel.getmAreaId());
        call.enqueue(new Callback<BottleCollectionResponse>() {
            @Override
            public void onResponse(Call<BottleCollectionResponse> call, Response<BottleCollectionResponse> response) {
                binding.swipeRefresh.setRefreshing(false);
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {

                            if (response.body().getBottleCollectionModels().size() > 0) {
                                collectionList.clear();
                                collectionList.addAll(response.body().getBottleCollectionModels());
                                bottleCollectionAdapter.notifyDataSetChanged();
                                binding.noData.setVisibility(View.GONE);
                                binding.rvCollections.setVisibility(View.VISIBLE);
                                binding.tvCollections.setVisibility(View.VISIBLE);
                            } else {
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
            public void onFailure(Call<BottleCollectionResponse> call, Throwable t) {
                binding.swipeRefresh.setRefreshing(false);
                binding.noData.setVisibility(View.VISIBLE);
                binding.rvCollections.setVisibility(View.GONE);
                binding.tvCollections.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBottleCollect(BottleCollectionModel bottleModel) {
        bottleCollectionAlert(bottleModel);
    }

    private void bottleCollectionAlert(BottleCollectionModel bottleModel) {
        Dialog dialog = new Dialog(requireActivity(), R.style.my_dialog);
        BottleCollectionDialogBinding dialogBinding = BottleCollectionDialogBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());

        dialogBinding.tvName.setText(bottleModel.getmUserName());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        dialogBinding.rvBottles.setLayoutManager(linearLayoutManager);
        BottleAdapter bottleAdapter = new BottleAdapter(requireActivity(), bottleModel.getBottleList());
        dialogBinding.rvBottles.setAdapter(bottleAdapter);

        dialogBinding.btnCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCollectBottleApi(bottleModel);
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void callCollectBottleApi(BottleCollectionModel bottleModel) {

        HashMap<String, String> idMap = new HashMap<>();
        HashMap<String, String> qtyMap = new HashMap<>();

        for (int i = 0; i < bottleModel.getBottleList().size(); i++) {
            idMap.put("product[" + i + "][m_bottle_id]", bottleModel.getBottleList().get(i).getmBottleId());
            qtyMap.put("product[" + i + "][m_bottle_qty]", bottleModel.getBottleList().get(i).getCollectedQty());
        }

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<BaseResponse> call = apiInterface.insertBottleCollection(userModel.getmExecutiveId(), bottleModel.getmUserId(), idMap, qtyMap);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            Log.d("TAG", "FCM UPDATE:  Successful");
                            collectionCompleteAlert(true);
                        } else {
                            Log.d("TAG", "FCM UPDATE:  Failed");
                            collectionCompleteAlert(false);
                        }
                    } else {
                        Log.d("TAG", "FCM UPDATE:  Failed");
                        collectionCompleteAlert(false);
                    }
                } catch (Exception e) {
                    log_e(this.getClass().getSimpleName(), "onResponse: ", e);
                    collectionCompleteAlert(false);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("Failure", t.toString());
                collectionCompleteAlert(false);
            }
        });
    }

    private void collectionCompleteAlert(boolean isSuccess) {
        Dialog dialog = new Dialog(requireActivity(), R.style.my_dialog2);
        CollectionSuccessDialogBinding dialogBinding = CollectionSuccessDialogBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());

        if (isSuccess) {
            dialogBinding.ivStatus.setImageResource(R.drawable.order_success);
            dialogBinding.ivStatus.setColorFilter(requireActivity().getColor(R.color.success), android.graphics.PorterDuff.Mode.SRC_IN);
            dialogBinding.btnMyOrder.setVisibility(View.VISIBLE);
            dialogBinding.btnTryAgain.setVisibility(View.GONE);
            dialogBinding.tvTitleOrder.setText(R.string.bottle_collected_successfully);
            dialogBinding.btnMyOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    getCollectionApi();
                }
            });
        } else {
            dialogBinding.ivStatus.setImageResource(R.drawable.order_failed);
            dialogBinding.ivStatus.setColorFilter(requireActivity().getColor(R.color.error), android.graphics.PorterDuff.Mode.SRC_IN);
            dialogBinding.btnMyOrder.setVisibility(View.GONE);
            dialogBinding.btnTryAgain.setVisibility(View.VISIBLE);
            dialogBinding.tvTitleOrder.setText(R.string.failed_to_collect_bottle);
            dialogBinding.btnTryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });


        }

        dialog.show();

    }

}
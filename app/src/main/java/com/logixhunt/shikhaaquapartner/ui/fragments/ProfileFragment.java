package com.logixhunt.shikhaaquapartner.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.logixhunt.shikhaaquapartner.R;
import com.logixhunt.shikhaaquapartner.databinding.FragmentProfileBinding;
import com.logixhunt.shikhaaquapartner.model.UserModel;
import com.logixhunt.shikhaaquapartner.ui.activities.DailyReportActivity;
import com.logixhunt.shikhaaquapartner.ui.activities.MainActivity;
import com.logixhunt.shikhaaquapartner.ui.activities.NotificationsActivity;
import com.logixhunt.shikhaaquapartner.ui.activities.UpdateProfileActivity;
import com.logixhunt.shikhaaquapartner.ui.common.BaseFragment;
import com.logixhunt.shikhaaquapartner.utils.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends BaseFragment {

    private FragmentProfileBinding binding;

    private UserModel userModel = new UserModel();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        binding = FragmentProfileBinding.inflate(getLayoutInflater(),container,false);
        getPreferenceData();
        initialization();
        return binding.getRoot();
    }

    private void getPreferenceData() {
        userModel = getUserData(requireActivity());
    }
    private void initialization() {
        binding.tvName.setText(String.format(userModel.getmExecutiveName()));
        binding.tvInitial.setText(userModel.getmExecutiveName().substring(0, 1));
        binding.tvMobile.setText(String.format("+91-%s", String.format(userModel.getmExecutiveMobile())));

        binding.walletLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(requireContext(), WalletActivity.class);
//                startActivity(intent);
            }
        });

        binding.bookingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(requireContext(), BookingHistoryActivity.class);
//                startActivity(intent);
            }
        });

        binding.profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        binding.reportLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), DailyReportActivity.class);
                startActivity(intent);
            }
        });

        binding.ivNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), NotificationsActivity.class);
                startActivity(intent);
            }
        });

        binding.aboutUsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Constant.WEBVIEW_TITLE = getString(R.string.about_us);
//                Constant.WEBVIEW_URL = getString(R.string.about_us_url);
//                Intent intent = new Intent(requireActivity(), WebViewActivity.class);
//                startActivity(intent);
            }
        });

        binding.privacyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Constant.WEBVIEW_TITLE = getString(R.string.privacy_and_policy);
//                Constant.WEBVIEW_URL = getString(R.string.privacy_policy_url);
//                Intent intent = new Intent(requireActivity(), WebViewActivity.class);
//                startActivity(intent);
            }
        });


        binding.logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.openBottomSheetForExitAndLogout(true);
            }
        });

    }


}
package com.logixhunt.shikhaaquapartner.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayoutMediator;
import com.logixhunt.shikhaaquapartner.R;
import com.logixhunt.shikhaaquapartner.databinding.ActivityDailyReportBinding;
import com.logixhunt.shikhaaquapartner.model.UserModel;
import com.logixhunt.shikhaaquapartner.ui.common.BaseActivity;
import com.logixhunt.shikhaaquapartner.ui.fragments.bookingtabs.AcceptedBookingFragment;
import com.logixhunt.shikhaaquapartner.ui.fragments.bookingtabs.PendingBookingFragment;
import com.logixhunt.shikhaaquapartner.ui.fragments.reports.BottleReportFragment;
import com.logixhunt.shikhaaquapartner.ui.fragments.reports.PaymentReportFragment;
import com.logixhunt.shikhaaquapartner.widgets.CustomFragmentPagerAdapter;

public class DailyReportActivity extends BaseActivity {

    private ActivityDailyReportBinding binding;

    private UserModel userModel = new UserModel();

    private CustomFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDailyReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getPreferenceData();
        initialization();
    }

    private void getPreferenceData() {
        userModel = getUserData(DailyReportActivity.this);
    }

    private void initialization() {

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        myFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle());
        myFragmentPagerAdapter.addFragment(new PaymentReportFragment());
        myFragmentPagerAdapter.addFragment(new BottleReportFragment());
        binding.viewPager.setAdapter(myFragmentPagerAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Payment Collection Report");
                            break;
                        case 1:
                            tab.setText("Bottle Collection Report");
                            break;
                    }
                }).attach();

    }
}
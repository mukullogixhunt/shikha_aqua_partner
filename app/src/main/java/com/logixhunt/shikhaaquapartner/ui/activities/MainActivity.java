package com.logixhunt.shikhaaquapartner.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.logixhunt.shikhaaquapartner.R;
import com.logixhunt.shikhaaquapartner.api.ApiClient;
import com.logixhunt.shikhaaquapartner.api.ApiInterface;
import com.logixhunt.shikhaaquapartner.api.response.commonresponse.BaseResponse;
import com.logixhunt.shikhaaquapartner.databinding.ActivityMainBinding;
import com.logixhunt.shikhaaquapartner.databinding.BottomSheetExitBinding;
import com.logixhunt.shikhaaquapartner.model.UserModel;
import com.logixhunt.shikhaaquapartner.ui.common.BaseActivity;
import com.logixhunt.shikhaaquapartner.utils.Constant;
import com.logixhunt.shikhaaquapartner.utils.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private UserModel userModel = new UserModel();
    public static MainActivity mainActivity;
    public static NavController bottomNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainActivity = this;
        getPreferenceData();
        initialization();
    }


    private void getPreferenceData() {
        userModel = getUserData(MainActivity.this);
    }

    private void initialization() {
        bottomNavController = Navigation.findNavController(MainActivity.this, R.id.bottom_nav_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavigation, bottomNavController);

        binding.bottomNavigation.getMenu().findItem(R.id.nav_home).setOnMenuItemClickListener(menuItem -> {
            if (bottomNavController.getCurrentDestination().getId() != R.id.nav_home) {
                navigateToFragment(R.id.nav_home);
            }
            return false;
        });
    }

    public void navigateToFragment(int id) {
        bottomNavController.navigate(id);
    }

    public void navigateToFragment(int id, Bundle bundle) {
        bottomNavController.navigate(id, bundle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (bottomNavController.getCurrentDestination().getId() == R.id.nav_home) {
            openBottomSheetForExitAndLogout(false);
        } else {
            super.onBackPressed();
        }
    }

    public void openBottomSheetForExitAndLogout(boolean isLogout) {


        BottomSheetExitBinding exitBinding = BottomSheetExitBinding.inflate(getLayoutInflater());
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.bottomSheetDialog); // Style her
        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        bottomSheetDialog.setContentView(exitBinding.getRoot());

        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) exitBinding.getRoot().getParent());
        bottomSheetDialog.setOnShowListener(dialogInterface -> {
            mBehavior.setPeekHeight(exitBinding.getRoot().getHeight());//get the height dynamically
        });

        bottomSheetDialog.show();


        if (isLogout) {
            exitBinding.tvMessage.setText(getResources().getString(R.string.are_you_sure_want_to_logout));
        } else {
            exitBinding.tvMessage.setText(getResources().getString(R.string.are_you_sure_want_to_exit));
        }

        exitBinding.btnNo.setOnClickListener(v -> bottomSheetDialog.dismiss());
        exitBinding.btnYes.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            if (isLogout) {
                PreferenceUtils.clearAll(MainActivity.this);

                FirebaseApp.initializeApp(this);
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        log_e("TAG", "", task.getException());
                        return;
                    }
                    String notificationToken = task.getResult();
                    log_d("TAG", "token=====>" + notificationToken);
                    PreferenceUtils.setString(
                            Constant.PreferenceConstant.FIREBASE_TOKEN,
                            notificationToken,
                            MainActivity.this
                    );
                });

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            } else {
                finishAffinity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFcmToken();
    }

    private void updateFcmToken() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<BaseResponse> call = apiInterface.updateFcm(userModel.getmExecutiveId(), PreferenceUtils.getString(Constant.PreferenceConstant.FIREBASE_TOKEN, this));
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            Log.d("TAG", "FCM UPDATE:  Successful");
                        } else {
                            Log.d("TAG", "FCM UPDATE:  Failed");
                        }
                    } else {
                        Log.d("TAG", "FCM UPDATE:  Failed");
                    }
                } catch (Exception e) {
                    log_e(this.getClass().getSimpleName(), "onResponse: ", e);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("Failure", t.toString());
            }
        });
    }

}

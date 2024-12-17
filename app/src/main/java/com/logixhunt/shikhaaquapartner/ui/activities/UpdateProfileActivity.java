package com.logixhunt.shikhaaquapartner.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.logixhunt.shikhaaquapartner.R;
import com.logixhunt.shikhaaquapartner.api.ApiClient;
import com.logixhunt.shikhaaquapartner.api.ApiInterface;
import com.logixhunt.shikhaaquapartner.api.response.AreaResponse;
import com.logixhunt.shikhaaquapartner.api.response.CityResponse;
import com.logixhunt.shikhaaquapartner.api.response.StateResponse;
import com.logixhunt.shikhaaquapartner.api.response.UserResponse;
import com.logixhunt.shikhaaquapartner.databinding.ActivityUpdateProfileBinding;
import com.logixhunt.shikhaaquapartner.model.AreaModel;
import com.logixhunt.shikhaaquapartner.model.CityModel;
import com.logixhunt.shikhaaquapartner.model.StateModel;
import com.logixhunt.shikhaaquapartner.model.UserModel;
import com.logixhunt.shikhaaquapartner.ui.adapters.spinner.AreaSpinnerAdapter;
import com.logixhunt.shikhaaquapartner.ui.adapters.spinner.CitySpinnerAdapter;
import com.logixhunt.shikhaaquapartner.ui.adapters.spinner.StateSpinnerAdapter;
import com.logixhunt.shikhaaquapartner.ui.common.BaseActivity;
import com.logixhunt.shikhaaquapartner.utils.Constant;
import com.logixhunt.shikhaaquapartner.utils.PreferenceUtils;
import com.logixhunt.shikhaaquapartner.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends BaseActivity {

    private ActivityUpdateProfileBinding binding;

    private UserModel userModel = new UserModel();

    private final Calendar myCalendar = Calendar.getInstance();

    private StateSpinnerAdapter stateSpinnerAdapter;
    private CitySpinnerAdapter citySpinnerAdapter;
    private AreaSpinnerAdapter areaSpinnerAdapter;

    private List<StateModel> stateList = new ArrayList<>();
    private List<CityModel> cityList = new ArrayList<>();
    private List<AreaModel> areaList = new ArrayList<>();

    private String dob = "";

    private String stateId = "";
    private String cityId = "";
    private String areaId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getPreferenceData();
        initialization();
    }

    private void getPreferenceData() {
        userModel = getUserData(UpdateProfileActivity.this);
        setData();
    }

    private void setData() {

//        if (userModel.getmSpImage() != null) {
//            Glide.with(UpdateUserActivity.this)
//                    .load(ImagePathDecider.getPartnerImagePath() + userModel.getmSpImage())
//                    .error(R.drawable.ic_user_img)
//                    .into(binding.ivUser);
//        }

        if (userModel.getmExecutiveName() != null) {
            binding.etName.setText(userModel.getmExecutiveName());
        }

        if (userModel.getmExecutiveEmail() != null) {
            binding.etEmail.setText(userModel.getmExecutiveEmail());
        }

        if (userModel.getmExecutiveDob() != null) {
            binding.etDob.setText(Utils.changeDateFormat(Constant.yyyyMMdd, Constant.ddMMMyyyy, userModel.getmExecutiveDob()));
        }

        if (userModel.getmExecutiveAddress() != null) {
            binding.etAddress.setText(userModel.getmExecutiveAddress());
        }

    }


    private void initialization() {

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.etMobile.setText(String.format("+91-%s", userModel.getmExecutiveMobile()));


        binding.etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(binding.etDob);
            }
        });

        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isDataUpdated();
            }
        });

        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isDataUpdated();
            }
        });

        binding.etDob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isDataUpdated();
            }
        });

        binding.etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isDataUpdated();
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    callUpdateProfileApi();
                }

            }
        });

        getStateApi();

    }

    private boolean validate() {
        boolean valid = true;
        if (binding.etName.getText().toString().isEmpty()) {
            binding.etName.setError("Please Enter Your Name");
            valid = false;
        }

        return valid;
    }

    private void isDataUpdated() {
        boolean isUpdated = false;

        if (!binding.etName.getText().toString().equals(userModel.getmExecutiveName())) {
            isUpdated = true;
        } else if (!binding.etEmail.getText().toString().equals(userModel.getmExecutiveEmail())) {
            isUpdated = true;
        } else if (!binding.etDob.getText().toString().equals(userModel.getmExecutiveDob())) {
            isUpdated = true;
        } else if (!binding.etAddress.getText().toString().equals(userModel.getmExecutiveAddress())) {
            isUpdated = true;
        }

        if (isUpdated) {
            binding.btnSave.setEnabled(true);
        } else {
            binding.btnSave.setEnabled(false);
        }

    }


    private void getStateApi() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StateResponse> call = apiService.getState();
        call.enqueue(new Callback<StateResponse>() {
            @Override
            public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            stateList.clear();
                            stateList.add(new StateModel("", "Select"));
                            stateList.addAll(response.body().getState());
                            setUpStateSpinner();

                        } else {
                            showError(response.body().getMessage());
                        }
                    } else {
                        showError(response.message());
                    }
                } catch (Exception e) {
                    log_e(this.getClass().getSimpleName(), "onResponse: ", e);
                }
            }

            @Override
            public void onFailure(Call<StateResponse> call, Throwable t) {
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void setUpStateSpinner() {
        stateSpinnerAdapter = new StateSpinnerAdapter(UpdateProfileActivity.this, stateList);
        binding.spState.setAdapter(stateSpinnerAdapter);


        if (userModel.getmStateId() != null && !userModel.getmStateId().isEmpty()) {
            int selectedCity = 0;
            for (int i = 0; i < stateList.size(); i++) {
                if (stateList.get(i).getmStateId().equals(userModel.getmStateId())) {
                    selectedCity = i;
                }
            }
            binding.spState.setSelection(selectedCity);
        }


        binding.spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    stateId = stateList.get(position).getmStateId();
                    getCityApi(stateId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void getCityApi(String stateId) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CityResponse> call = apiService.getCity(stateId);
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            cityList.clear();
                            cityList.add(new CityModel("", "Select"));
                            cityList.addAll(response.body().getCity());
                            setUpCitySpinner();

                        } else {
                            showError(response.body().getMessage());
                        }
                    } else {
                        showError(response.message());
                    }
                } catch (Exception e) {
                    log_e(this.getClass().getSimpleName(), "onResponse: ", e);
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void setUpCitySpinner() {
        citySpinnerAdapter = new CitySpinnerAdapter(UpdateProfileActivity.this, cityList);
        binding.spCity.setAdapter(citySpinnerAdapter);


        if (userModel.getmCityId() != null && !userModel.getmCityId().isEmpty()) {
            int selectedCity = 0;
            for (int i = 0; i < cityList.size(); i++) {
                if (cityList.get(i).getmCityId().equals(userModel.getmCityId())) {
                    selectedCity = i;
                }
            }
            binding.spCity.setSelection(selectedCity);
        }


        binding.spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    cityId = cityList.get(position).getmCityId();
                    getAreaApi(cityId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void getAreaApi(String cityId) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AreaResponse> call = apiService.getArea(cityId);
        call.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            areaList.clear();
                            areaList.add(new AreaModel("", "Select Area"));
                            areaList.addAll(response.body().getAreas());
                            setUpAreaSpinner();

                        } else {
                            showError(response.body().getMessage());
                        }
                    } else {
                        showError(response.message());
                    }
                } catch (Exception e) {
                    log_e(this.getClass().getSimpleName(), "onResponse: ", e);
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t) {
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

    private void setUpAreaSpinner() {
        areaSpinnerAdapter = new AreaSpinnerAdapter(UpdateProfileActivity.this, areaList);
        binding.spArea.setAdapter(areaSpinnerAdapter);


        if (userModel.getmAreaId() != null && !userModel.getmAreaId().isEmpty()) {
            int selectedCity = 0;
            for (int i = 0; i < areaList.size(); i++) {
                if (areaList.get(i).getmAreaId().equals(userModel.getmAreaId())) {
                    selectedCity = i;
                }
            }
            binding.spArea.setSelection(selectedCity);
        }


        binding.spArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    areaId = areaList.get(position).getmAreaId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void openDatePickerDialog(EditText editText) {

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                editText.setText(Utils.getDate(myCalendar.getTimeInMillis(), Constant.ddMMMyyyy));
                dob = Utils.getDate(myCalendar.getTimeInMillis(), Constant.yyyyMMdd);
            }

        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateProfileActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();

    }


    private void callUpdateProfileApi() {
        showLoader();
        dob = Utils.changeDateFormat(Constant.ddMMMyyyy, Constant.yyyyMMdd, binding.etDob.getText().toString());
        String userId = userModel.getmExecutiveId();
        String userName = binding.etName.getText().toString().trim();
        String userEmail = binding.etEmail.getText().toString().trim();
        String userDob = dob;
        String userAddress = binding.etAddress.getText().toString().trim();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> call = apiService.updateUserProfile(userId, userName, userEmail, userDob, stateId, cityId, areaId, userAddress);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                hideLoader();
                try {
                    if (String.valueOf(response.code()).equalsIgnoreCase(Constant.SUCCESS_RESPONSE_CODE)) {
                        if (response.body().getResult().equalsIgnoreCase(Constant.SUCCESS_RESPONSE)) {
                            if (response.body().getUsers().size() > 0) {
                                showAlert(response.body().getMessage());


                                PreferenceUtils.setString(
                                        Constant.PreferenceConstant.USER_DATA,
                                        new Gson().toJson(response.body().getUsers().get(0)),
                                        UpdateProfileActivity.this);

                                getPreferenceData();

                                Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            }
                        } else {
                            showError(response.body().getMessage());
                        }
                    } else {
                        showError(response.message());
                    }
                } catch (Exception e) {
                    hideLoader();
                    log_e(this.getClass().getSimpleName(), "onResponse: ", e);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("Failure", t.toString());
                showError("Something went wrong");
            }
        });
    }

}
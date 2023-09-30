package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Toast;

import com.example.cea.Apis.ApiInterface;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.LoginModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.co_oridinator.Activities.Co_HomeActivity;
import com.example.cea.co_oridinator.Models.Co_LoginModel;
import com.example.cea.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private Activity activity;
    private ProgressDialog pd;
    private ApiInterface apiInterface;
    private Session session;
    private boolean isPasswordShow = false;
    private boolean isPrivacyAccepted = false;
    private boolean imSeller = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        pd = new ProgressDialog(activity);
        apiInterface = RetrofitClient.getClient(activity);
        session = new Session(activity);

        binding.llLogin.setOnClickListener(view -> {
            if (isValidate()) {
                if (imSeller) {
                    doCorOrdinationLogin();
                } else {
                    doLogin();
                }
            }
        });

        binding.imSeller.setOnCheckedChangeListener((compoundButton, b) -> imSeller = b);

        binding.textSkip.setOnClickListener(view -> startActivity(new Intent(activity, HomeActivity.class)));

        binding.acceptTermCondition.setOnCheckedChangeListener((compoundButton, b) -> isPrivacyAccepted = b);

        binding.imagePassVisibility.setOnClickListener(view -> {
            if (isPasswordShow) {
                isPasswordShow = false;
                binding.edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.imagePassVisibility.setImageResource(R.drawable.eye_slash);
            } else {
                isPasswordShow = true;
                binding.edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.imagePassVisibility.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
            }
        });

        binding.textSignup.setOnClickListener(view -> startActivity(new Intent(activity, SignupActivity.class)));

        binding.textForgetPassword.setOnClickListener(view -> startActivity(new Intent(activity, ForgetPasswordActivity.class)));


        binding.termsnsevice.setOnClickListener(view -> startActivity(new Intent(activity, TermsAndConditionActivity.class)));
        binding.pravicypolicy.setOnClickListener(view -> startActivity(new Intent(activity, PrivacyPolicyActivity.class)));


    }

    private void doLogin() {

        pd.show();
        apiInterface.loginUser(
                "customer",
                binding.edtPhone.getText().toString(),
                binding.edtPassword.getText().toString(),
                "fdasffwt327327e763"
        ).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
//                            Toast.makeText(activity, "Login Success..", Toast.LENGTH_SHORT).show();
                            session.setPas(binding.edtPassword.getText().toString());
                            session.setName(response.body().getData().get(0).getCustomerFname());
                            session.setLastname(response.body().getData().get(0).getCustomerLname());
                            session.setUser_id(response.body().getData().get(0).getCustomerId());
                            session.setReferCode(response.body().getData().get(0).getCustomerReferalCode());
                            session.setMobile(response.body().getData().get(0).getCustomerMobileNo());
                            session.setEmail(response.body().getData().get(0).getCustomerEmail());
                            session.setPincode(response.body().getData().get(0).getCustomerPincode());
                            session.setWalletAmount(response.body().getData().get(0).getCustomerTotalWalletAmount());
                            session.setImage(BaseUrls.ImageBaseUrl + response.body().getData().get(0).getCustomerProfileImg());
                            session.setType("user");
                            startActivity(new Intent(activity, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            finish();
                        } else {
                            Toast.makeText(activity, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server not responding..", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }


    private void doCorOrdinationLogin() {

        pd.show();
        apiInterface.loginCo_ordination(
                binding.edtPhone.getText().toString(),
                binding.edtPassword.getText().toString(),
                "coordination"
        ).enqueue(new Callback<Co_LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<Co_LoginModel> call, @NonNull Response<Co_LoginModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
//                            Toast.makeText(activity, "Login Success..", Toast.LENGTH_SHORT).show();
                            session.setName(response.body().getData().get(0).getCoordinationName());
                            session.setLastname(response.body().getData().get(0).getCoordinationName());
                            session.setUser_id(response.body().getData().get(0).getCoordinationId());
                            session.setMobile(response.body().getData().get(0).getCoordinationMobileNo());
                            session.setEmail(response.body().getData().get(0).getCoordinationEmail());
                            session.setPassword(binding.edtPassword.getText().toString());
                            session.setImage(BaseUrls.ImageBaseUrl + response.body().getData().get(0).getCoordinationProfile());
                            session.setType("cordinate");
                            startActivity(new Intent(activity, Co_HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            finish();
                        } else {
                            Toast.makeText(activity, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<Co_LoginModel> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server not responding..", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });


    }

    private boolean isValidate() {

        if (binding.edtPhone.getText().toString().equalsIgnoreCase("")) {
            binding.edtPhone.setError("Enter your mobile.!");
            binding.edtPhone.requestFocus();
            return false;
        } else if (binding.edtPassword.getText().toString().equalsIgnoreCase("")) {
            binding.edtPassword.setError("Enter your password.!");
            binding.edtPassword.requestFocus();
            return false;
        } else if (binding.edtPassword.getText().toString().length() < 6) {
            binding.edtPassword.setError("Password must be greater then 6 characters");
            binding.edtPassword.requestFocus();
            return false;
        } else if (!isPrivacyAccepted) {
            Toast.makeText(activity, "Please Accept Terms And Condition", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

}
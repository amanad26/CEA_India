package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.cea.Apis.ApiInterface;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.LoginModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.databinding.ActivitySignupBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    Activity activity;
    private ProgressDialog pd;
    private ApiInterface apiInterface;
    private Session session;
    private boolean isPasswordShow = false;
    private boolean isPrivacyAccepted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        pd = new ProgressDialog(activity);
        apiInterface = RetrofitClient.getClient(activity);
        session = new Session(activity);

        binding.llLogin.setOnClickListener(view -> {
            if (isValidate())
                checkMobile();
        });

        binding.imagePassVisibility.setOnClickListener(view -> {
            if (isPasswordShow) {
                isPasswordShow = false;
                binding.edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.edtConformPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.imagePassVisibility.setImageResource(R.drawable.eye_slash);
            } else {
                isPasswordShow = true;
                binding.edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.edtConformPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.imagePassVisibility.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
            }
        });

        binding.acceptTermCondition.setOnCheckedChangeListener((compoundButton, b) -> isPrivacyAccepted = b);


        binding.termsnsevice.setOnClickListener(view -> startActivity(new Intent(activity, TermsAndConditionActivity.class)));
        binding.pravicypolicy.setOnClickListener(view -> startActivity(new Intent(activity, PrivacyPolicyActivity.class)));


    }

    private void checkMobile() {
        pd.show();
        apiInterface.checkMobile(
                "mobile",
                binding.edtPhone.getText().toString()
        ).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            pd.dismiss();
                            Toast.makeText(activity, "This Mobile Number Already Register..", Toast.LENGTH_SHORT).show();
                        } else {
                            checkEmail();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server not responding..", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }

    private void checkEmail() {
        pd.show();
        apiInterface.checkMobile(
                "email",
                binding.edtEmail.getText().toString()
        ).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            pd.dismiss();
                            Toast.makeText(activity, "This Email Id Already Register..", Toast.LENGTH_SHORT).show();
                        } else {
                            doSignUp();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server not responding..", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }

    private void doSignUp() {
        pd.show();
        apiInterface.signupUser(
                binding.edtFirstname.getText().toString(),
                binding.edtLastname.getText().toString(),
                binding.edtPhone.getText().toString(),
                binding.edtEmail.getText().toString(),
                binding.edtPassword.getText().toString(),
                binding.edtReferralcode.getText().toString()
        ).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(activity, "SignUp Success..", Toast.LENGTH_SHORT).show();
                            session.setPas(binding.edtPassword.getText().toString());
                            session.setName(response.body().getData().get(0).getCustomerFname());
                            session.setLastname(response.body().getData().get(0).getCustomerLname());
                            session.setUser_id(response.body().getData().get(0).getCustomerId());
                            session.setMobile(response.body().getData().get(0).getCustomerMobileNo());
                            session.setEmail(response.body().getData().get(0).getCustomerEmail());
                            session.setReferCode(response.body().getData().get(0).getCustomerReferalCode());
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

    private boolean isValidate() {
        if (binding.edtPhone.getText().toString().equalsIgnoreCase("")) {
            binding.edtPhone.setError("Enter your mobile.!");
            binding.edtPhone.requestFocus();
            return false;
        } else if (binding.edtPassword.getText().toString().equalsIgnoreCase("")) {
            binding.edtPassword.setError("Enter your password.!");
            binding.edtPassword.requestFocus();
            return false;
        } else if (binding.edtFirstname.getText().toString().equalsIgnoreCase("")) {
            binding.edtFirstname.setError("Enter First Name.!");
            binding.edtFirstname.requestFocus();
            return false;
        } else if (binding.edtLastname.getText().toString().equalsIgnoreCase("")) {
            binding.edtLastname.setError("Enter Last Name.!");
            binding.edtLastname.requestFocus();
            return false;
        } else if (binding.edtEmail.getText().toString().equalsIgnoreCase("")) {
            binding.edtEmail.setError("Enter your email Address.!");
            binding.edtEmail.requestFocus();
            return false;
        } else if (binding.edtConformPassword.getText().toString().equalsIgnoreCase("")) {
            binding.edtConformPassword.setError("Enter your Confirm password.!");
            binding.edtConformPassword.requestFocus();
            return false;
        } else if (!binding.edtConformPassword.getText().toString().equalsIgnoreCase(binding.edtPassword.getText().toString())) {
            binding.edtConformPassword.setError("Password not match..!");
            binding.edtConformPassword.requestFocus();
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
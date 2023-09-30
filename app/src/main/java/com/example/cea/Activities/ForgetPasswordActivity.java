package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cea.Apis.ApiInterface;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.LoginModel;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.databinding.ActivityForgetPasswordBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    Activity activity;
    private ProgressDialog pd;
    private ApiInterface apiInterface;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        pd = new ProgressDialog(activity);
        apiInterface = RetrofitClient.getClient(activity);
        session = new Session(activity);

        binding.llLogin.setOnClickListener(view -> {
            if (binding.edtPhone.getText().toString().equalsIgnoreCase("")) {
                binding.edtPhone.setError("Enter your mobile.!");
                binding.edtPhone.requestFocus();
            } else {
                forgotPassword();
            }
        });


    }

    private void forgotPassword() {
        pd.show();
        apiInterface.forgotPassword(binding.edtPhone.getText().toString()).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(activity, "Password Forgot success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, "Mobile Not Register...!", Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server not responding..", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }
}
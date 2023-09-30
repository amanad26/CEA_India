package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.UpdateProfileModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.databinding.ActivityChangePasswordBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    ActivityChangePasswordBinding binding;
    ChangePasswordActivity activity;
    Session session;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        pd = new ProgressDialog(activity);

        binding.llLogin.setOnClickListener(view -> {
            if (binding.edtPhone.getText().toString().equalsIgnoreCase("")) {
                binding.edtPhone.setError("Enter your mobile.!");
                binding.edtPhone.requestFocus();
            } else if (binding.edtPassword.getText().toString().equalsIgnoreCase("")) {
                binding.edtPassword.setError("Enter your password.!");
                binding.edtPassword.requestFocus();
            } else if (binding.edtPassword.getText().toString().length() < 6) {
                binding.edtPassword.setError("Password must be greater then 6 digits");
                binding.edtPassword.requestFocus();
            } else {
                changePassword();
            }
        });

    }

    private void changePassword() {
        pd.show();
        RetrofitClient.getClient(activity).changePassword(binding.edtPhone.getText().toString()
                , binding.edtPassword.getText().toString()).enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateProfileModel> call, @NonNull Response<UpdateProfileModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(activity, "Password Changed..!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(activity, HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateProfileModel> call, @NonNull Throwable t) {
                pd.dismiss();
            }
        });


    }
}
package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.PrivacyPolicyModel;
import com.example.cea.R;
import com.example.cea.databinding.ActivityTermsAndConditionBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsAndConditionActivity extends AppCompatActivity {

    String privacy_policy = "tern_n_conditions";
    TermsAndConditionActivity activity;
    ActivityTermsAndConditionBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermsAndConditionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activity = this;

        binding.ivBack.setOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPrivacy();
    }

    private void getPrivacy() {
        RetrofitClient.getClient(activity).getPrivacyAndOther(privacy_policy).enqueue(new Callback<PrivacyPolicyModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call<PrivacyPolicyModel> call, @NonNull Response<PrivacyPolicyModel> response) {
                binding.progresBar.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            binding.abutText.setText(Html.fromHtml(response.body().getData(), Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<PrivacyPolicyModel> call, @NonNull Throwable t) {
                binding.progresBar.setVisibility(View.GONE);
                Toast.makeText(activity, "Server not responding..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
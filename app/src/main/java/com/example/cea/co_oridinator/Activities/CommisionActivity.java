package com.example.cea.co_oridinator.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cea.Apis.RetrofitClient;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Adapters.CommisionAdapter;
import com.example.cea.co_oridinator.Models.CommisionModel;
import com.example.cea.databinding.ActivityCommisionctivityBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommisionActivity extends AppCompatActivity {

    ActivityCommisionctivityBinding binding;
    Activity activity;
    Session session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommisionctivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);

        binding.backBtn.setOnClickListener(view -> onBackPressed());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCommision();
    }

    private void getCommision() {

        RetrofitClient.getClient(activity).getCommissionList(session.getUserId()).enqueue(new Callback<CommisionModel>() {
            @Override
            public void onResponse(@NonNull Call<CommisionModel> call, @NonNull Response<CommisionModel> response) {
                binding.progress.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                            linearLayoutManager.setReverseLayout(true);
                            linearLayoutManager.setStackFromEnd(true);
                            binding.homeRecycler.setLayoutManager(linearLayoutManager);
                            binding.homeRecycler.setAdapter(new CommisionAdapter(activity, response.body().getData()));
                        }

            }

            @Override
            public void onFailure(@NonNull Call<CommisionModel> call, @NonNull Throwable t) {
                binding.progress.setVisibility(View.GONE);
            }
        });

    }


}

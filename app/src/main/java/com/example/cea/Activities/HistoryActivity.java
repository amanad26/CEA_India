package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cea.Adapters.OrderHistoryAdapter;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.OrderListModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.ActivityHistoryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    ActivityHistoryBinding binding;
    HistoryActivity activity;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);
        binding.ivBack.setOnClickListener(view -> onBackPressed());

        binding.llLogin.setOnClickListener(view -> startActivity(new Intent(activity, HomeActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getHistory();
    }

    private void getHistory() {

        RetrofitClient.getClient(activity).getOrderHistory(session.getUserId()).enqueue(new Callback<OrderListModel>() {
            @Override
            public void onResponse(@NonNull Call<OrderListModel> call, @NonNull Response<OrderListModel> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            binding.maineDataLinear.setVisibility(View.VISIBLE);
                            binding.historyRecyer.setLayoutManager(new LinearLayoutManager(activity));
                            binding.historyRecyer.setAdapter(new OrderHistoryAdapter(activity, response.body().getData()));
                        } else {
                            binding.noData.setVisibility(View.VISIBLE);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<OrderListModel> call, @NonNull Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.noData.setVisibility(View.VISIBLE);
            }
        });

    }

}
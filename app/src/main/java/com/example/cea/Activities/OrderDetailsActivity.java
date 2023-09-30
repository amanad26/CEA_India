package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.cea.Adapters.OrderDetailAdapter;
import com.example.cea.Adapters.OrderHistoryAdapter;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.OderDetailsModel;
import com.example.cea.Models.OrderListModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.ActivityOrderDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {

    ActivityOrderDetailsBinding binding;
    OrderDetailsActivity activity;
    Session session;
    String saleId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activity = this;
        session = new Session(activity);
        saleId = getIntent().getStringExtra("saleId");

        binding.ivBack.setOnClickListener(view -> onBackPressed());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getHistory();
    }

    private void getHistory() {

        RetrofitClient.getClient(activity).getOrderDetails(session.getUserId(), saleId).enqueue(new Callback<OderDetailsModel>() {
            @Override
            public void onResponse(@NonNull Call<OderDetailsModel> call, @NonNull Response<OderDetailsModel> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            binding.maineDataLinear.setVisibility(View.VISIBLE);
                            binding.historyRecyer.setLayoutManager(new LinearLayoutManager(activity));
                            binding.historyRecyer.setAdapter(new OrderDetailAdapter(activity, response.body().getData()));
                        } else {
                            binding.noData.setVisibility(View.VISIBLE);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<OderDetailsModel> call, @NonNull Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.noData.setVisibility(View.VISIBLE);
            }
        });

    }
}
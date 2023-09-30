package com.example.cea.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Activities.HomeActivity;
import com.example.cea.Adapters.WalletAdapter;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.WalletHistoryModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.FragmentWalletSpendingBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletSpendingFragment extends Fragment {

    Activity activity;
    FragmentWalletSpendingBinding binding;
    Session session;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWalletSpendingBinding.inflate(getLayoutInflater());

        activity = requireActivity();
        session = new Session(activity);

        binding.llLogin.setOnClickListener(view -> startActivity(new Intent(activity, HomeActivity.class)));

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getWalletDetails();
    }

    private void getWalletDetails() {
        RetrofitClient.getClient(activity).getWalletHistory(session.getUserId()).enqueue(new Callback<WalletHistoryModel>() {
            @Override
            public void onResponse(@NonNull Call<WalletHistoryModel> call, @NonNull Response<WalletHistoryModel> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            binding.maineDataLinear.setVisibility(View.VISIBLE);
                            binding.historyRecyer.setLayoutManager(new LinearLayoutManager(activity));
                            List<WalletHistoryModel.Datum> wallet = new ArrayList<>();
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                if (response.body().getData().get(i).getWalletStatus().equalsIgnoreCase("Debit")) {
                                    wallet.add(response.body().getData().get(i));
                                }
                            }
                            binding.historyRecyer.setAdapter(new WalletAdapter(activity, wallet));
                        } else {
                            binding.noData.setVisibility(View.VISIBLE);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<WalletHistoryModel> call, @NonNull Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}
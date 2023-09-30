package com.example.cea.co_oridinator.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Activities.AddSaleActivity;
import com.example.cea.co_oridinator.Adapters.SaleAdapter;
import com.example.cea.co_oridinator.Models.MySalesModel;
import com.example.cea.databinding.FragmentCoSaleBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Co_SaleFragment extends Fragment {

    FragmentCoSaleBinding binding;
    Activity activity;
    Session session;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCoSaleBinding.inflate(getLayoutInflater());
        activity = requireActivity();
        session = new Session(activity);
        init();
        return binding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        getSales();
    }

    private void init() {
        binding.addWallking.setOnClickListener(view -> startActivity(new Intent(activity, AddSaleActivity.class)));
    }


    private void getSales() {
        RetrofitClient.getClient(activity).getMySale(session.getUserId()).enqueue(new Callback<MySalesModel>() {
            @Override
            public void onResponse(@NonNull Call<MySalesModel> call, @NonNull Response<MySalesModel> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                            linearLayoutManager.setReverseLayout(true);
                            linearLayoutManager.setStackFromEnd(true);
                            binding.homeRecycler.setLayoutManager(linearLayoutManager);
                            binding.homeRecycler.setAdapter(new SaleAdapter(activity, response.body().getData()));
                        }
            }

            @Override
            public void onFailure(@NonNull Call<MySalesModel> call, @NonNull Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }


}
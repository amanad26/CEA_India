package com.example.cea.co_oridinator.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Apis.RetrofitClient;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Adapters.DailyCallCustomerAdapter;
import com.example.cea.co_oridinator.Adapters.HomeSliderAdapter;
import com.example.cea.co_oridinator.Models.ProductModelCo;
import com.example.cea.co_oridinator.Models.TodayFollowUpModel;
import com.example.cea.databinding.FragmentCoHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Co_HomeFragment extends Fragment {

    FragmentCoHomeBinding binding;
    Activity activity;
    Session session;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCoHomeBinding.inflate(getLayoutInflater());
        activity = requireActivity();
        session = new Session(activity);
        init();
        return binding.getRoot();

    }

    private void init() {


    }

    @Override
    public void onResume() {
        super.onResume();
        getNewestProduct();
        getTodayFollowUp();
    }

    private void getNewestProduct() {
        RetrofitClient.getClient(activity).getNewestProducts().enqueue(new Callback<ProductModelCo>() {
            @Override
            public void onResponse(@NonNull Call<ProductModelCo> call, @NonNull Response<ProductModelCo> response) {
                binding.progress1.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                          if(response.body().getData().size() != 0){
                              LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                              linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                              binding.homeRecycler.setLayoutManager(linearLayoutManager);
                              binding.homeRecycler.setAdapter(new HomeSliderAdapter(activity, response.body().getData()));
                          }
                        }

            }

            @Override
            public void onFailure(@NonNull Call<ProductModelCo> call, @NonNull Throwable t) {
                binding.progress1.setVisibility(View.GONE);
            }
        });
    }


    private void getTodayFollowUp() {
        RetrofitClient.getClient(activity).todayFollowUp(session.getUserId()).enqueue(new Callback<TodayFollowUpModel>() {
            @Override
            public void onResponse(@NonNull Call<TodayFollowUpModel> call, @NonNull Response<TodayFollowUpModel> response) {
                binding.progress2.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                            linearLayoutManager.setReverseLayout(true);
                            linearLayoutManager.setStackFromEnd(true);
                            binding.callRecyceler.setLayoutManager(linearLayoutManager);
                            binding.callRecyceler.setAdapter(new DailyCallCustomerAdapter(activity, response.body().getData()));
                        }
            }

            @Override
            public void onFailure(@NonNull Call<TodayFollowUpModel> call, @NonNull Throwable t) {
                binding.progress2.setVisibility(View.GONE);
            }
        });

    }


}
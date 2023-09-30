package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.cea.Adapters.FavoriteAdapter;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.FavoriteListModel;
import com.example.cea.Models.FavoriteModel;

import com.example.cea.Session.Session;
import com.example.cea.databinding.ActivityMyFavoriteListBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFavoriteListActivity extends AppCompatActivity {

    ActivityMyFavoriteListBinding binding;
    MyFavoriteListActivity activity;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyFavoriteListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);

        binding.ivBack.setOnClickListener(view -> onBackPressed());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyFavoriteList();
    }

    private void getMyFavoriteList() {

        RetrofitClient.getClient(activity).myFavoriteProductList(
                session.getUserId(),
                "0"
        ).enqueue(new Callback<FavoriteListModel>() {
            @Override
            public void onResponse(@NonNull Call<FavoriteListModel> call, @NonNull Response<FavoriteListModel> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            if (response.body().getData().size() != 0) {
                                binding.productRecycler.setLayoutManager(new GridLayoutManager(activity, 2));
                                binding.productRecycler.setAdapter(new FavoriteAdapter(activity, response.body().getData()));

                            } else {
                                binding.noResultLinear.setVisibility(View.VISIBLE);
                            }

                        } else {
                            binding.noResultLinear.setVisibility(View.VISIBLE);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<FavoriteListModel> call, @NonNull Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.noResultLinear.setVisibility(View.VISIBLE);
            }
        });


    }
}
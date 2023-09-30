package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cea.Adapters.ReviewListAdapter;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.ReviewListModel;
import com.example.cea.R;
import com.example.cea.databinding.ActivityReviewListBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewListActivity extends AppCompatActivity {

    ActivityReviewListBinding binding;
    ReviewListActivity activity;
    String productId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        productId = getIntent().getStringExtra("product_id");

        binding.ivBack.setOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getReviewList();
    }

    private void getReviewList() {

        RetrofitClient.getClient(activity).getReviewList(productId).enqueue(new Callback<ReviewListModel>() {
            @Override
            public void onResponse(@NonNull Call<ReviewListModel> call, @NonNull Response<ReviewListModel> response) {
                binding.progress.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            int c = 0;
                            if (response.body().getData().size() != 0) {

                                c = response.body().getData().size();

                                binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                                binding.recyclerView.setAdapter(new ReviewListAdapter(activity, response.body().getData(), c));
                            } else {
                                Toast.makeText(activity, "No review found ..", Toast.LENGTH_SHORT).show();
                            }
                        }

            }

            @Override
            public void onFailure(@NonNull Call<ReviewListModel> call, @NonNull Throwable t) {
                binding.progress.setVisibility(View.GONE);
            }
        });
    }
}
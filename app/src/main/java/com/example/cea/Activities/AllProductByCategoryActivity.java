package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cea.Adapters.ProductListAdapter;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.ProductListModel;
import com.example.cea.Session.Session;
import com.example.cea.databinding.ActivityAllProductByCategoryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductByCategoryActivity extends AppCompatActivity {

    ActivityAllProductByCategoryBinding binding;
    AllProductByCategoryActivity activity;
    String cat_id = "", cat_name = "";
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllProductByCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);

        cat_id = getIntent().getStringExtra("cat_id");
        cat_name = getIntent().getStringExtra("cat_name");
        binding.titleText.setText(cat_name);

        binding.ivBack.setOnClickListener(view -> onBackPressed());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProduct();
    }

    private void getProduct() {
        RetrofitClient.getClient(activity).getProductListByCategory(
                cat_id,
                session.getPincode(),
                session.getUserId(),
                "newest",
                "0"
        ).enqueue(new Callback<ProductListModel>() {
            @Override
            public void onResponse(@NonNull Call<ProductListModel> call, @NonNull Response<ProductListModel> response) {
                binding.progresBar.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            binding.productRecycler.setLayoutManager(new GridLayoutManager(activity, 2));
                            binding.productRecycler.setAdapter(new ProductListAdapter(activity, response.body().getData()));
                        } else {
                            binding.noResultLinear.setVisibility(View.VISIBLE);
                            Toast.makeText(activity, "No Products Found..", Toast.LENGTH_SHORT).show();
                        }

            }

            @Override
            public void onFailure(@NonNull Call<ProductListModel> call, @NonNull Throwable t) {
                binding.progresBar.setVisibility(View.GONE);
                binding.noResultLinear.setVisibility(View.VISIBLE);
                Toast.makeText(activity, "Server Not Respond..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
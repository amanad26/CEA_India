package com.example.cea.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Adapters.HorizontalCategoryAdapter;
import com.example.cea.Adapters.VerticalCategoryAdapter;
import com.example.cea.Apis.ApiInterface;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Apis.SubCategoryInterface;
import com.example.cea.Models.CategoryModel;
import com.example.cea.Models.SubCategoryModel;
import com.example.cea.databinding.FragmentCategoryBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment implements SubCategoryInterface {
    FragmentCategoryBinding binding;
    ApiInterface apiInterface;
    Activity activity;
    VerticalCategoryAdapter verticalCategoryAdapter;
    private List<CategoryModel.Datum> data = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        activity = requireActivity();
        apiInterface = RetrofitClient.getClient(activity);


        data.clear();
        data = getCategory();
        binding.verticalCategory.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        verticalCategoryAdapter = new VerticalCategoryAdapter(getContext(), data, this, 1);


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private List<CategoryModel.Datum> getCategory() {

        List<CategoryModel.Datum> data2 = new ArrayList<>();
        apiInterface.getCategory().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(@NonNull Call<CategoryModel> call, @NonNull Response<CategoryModel> response) {

                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            data2.clear();
                            data2.addAll(response.body().getData());
                            binding.verticalCategory.setAdapter(verticalCategoryAdapter);

                        }
            }

            @Override
            public void onFailure(@NonNull Call<CategoryModel> call, @NonNull Throwable t) {

            }
        });

        return data2;
    }

    @Override
    public void onSelect(String id) {

        binding.progress.setVisibility(View.VISIBLE);
        RetrofitClient.getClient(activity).getSubCategory(id).enqueue(new Callback<SubCategoryModel>() {
            @Override
            public void onResponse(@NonNull Call<SubCategoryModel> call, @NonNull Response<SubCategoryModel> response) {
                binding.progress.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            HorizontalCategoryAdapter horizontalCategoryAdapter = new HorizontalCategoryAdapter(getContext(), response.body().getData());
                            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                            binding.horizontalCategory.setLayoutManager(layoutManager);
                            binding.horizontalCategory.setAdapter(horizontalCategoryAdapter);
                        }

            }

            @Override
            public void onFailure(@NonNull Call<SubCategoryModel> call, @NonNull Throwable t) {
                binding.progress.setVisibility(View.GONE);
            }
        });


    }
}
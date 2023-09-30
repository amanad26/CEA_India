package com.example.cea.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Activities.QrScanActivity;
import com.example.cea.Adapters.HomeCategoryAdapter;
import com.example.cea.Adapters.SliderAdapter;
import com.example.cea.Adapters.VerticalCategoryAdapter;
import com.example.cea.Apis.ApiInterface;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Apis.SubCategoryInterface;
import com.example.cea.Models.CategoryModel;
import com.example.cea.Models.HomePageDataModel;
import com.example.cea.Models.OfferListModel;
import com.example.cea.Session.Session;
import com.example.cea.databinding.FragmentHomeBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements SubCategoryInterface {
    FragmentHomeBinding binding;
    Activity activity;
    ApiInterface apiInterface;
    Session session;
    VerticalCategoryAdapter verticalCategoryAdapter;
    private List<CategoryModel.Datum> data = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        activity = requireActivity();
        session = new Session(activity);
        apiInterface = RetrofitClient.getClient(activity);

        binding.imageScan.setOnClickListener(view -> startActivity(new Intent(activity, QrScanActivity.class)
                .putExtra("activity", "0")));

//        binding.toDetails.setOnClickListener(view -> {
//            Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
//            startActivity(intent);
//        });
        getOfferList();
        data.clear();
        data = getCategory();
        getHomePagedData();


        binding.homeCateHoriList.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        verticalCategoryAdapter = new VerticalCategoryAdapter(activity, data, this, 0);


        Log.e("TAG", "onCreateView: Size" + data.size());

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getHomePagedData();
    }

    private List<CategoryModel.Datum> getCategory() {

        List<CategoryModel.Datum> data2 = new ArrayList<>();
        apiInterface.getCategory().enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(@NonNull Call<CategoryModel> call, @NonNull Response<CategoryModel> response) {
                binding.progressSecond.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            data2.clear();
                            data2.addAll(response.body().getData());;
                            binding.homeCateHoriList.setAdapter(verticalCategoryAdapter);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<CategoryModel> call, @NonNull Throwable t) {
                binding.progressSecond.setVisibility(View.GONE);
            }
        });

        return data2;
    }

    private void getOfferList() {

        apiInterface.getOffers().enqueue(new Callback<OfferListModel>() {
            @Override
            public void onResponse(@NonNull Call<OfferListModel> call, @NonNull Response<OfferListModel> response) {
         binding.progressTop.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {


                            binding.imageSlider.setSliderAdapter(new SliderAdapter(activity, response.body().getData()));

                            binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                            binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                            binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                            binding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
                            binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
                            binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
                            binding.imageSlider.startAutoCycle();
//                            ArrayList<SlideModel> slideModels = new ArrayList<>();
//
//                            for (OfferListModel.Datum datum : response.body().getData()) {
//                                slideModels.add(new SlideModel(response.body().getBaseUrl() + datum.getOfferImage(),datum.getOfferName(), ScaleTypes.FIT));
//                            }
//
//                            binding.imageSlider.setImageList(slideModels, ScaleTypes.FIT);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<OfferListModel> call, @NonNull Throwable t) {
                binding.progressTop.setVisibility(View.GONE);
            }
        });

    }


    private void getHomePagedData() {

        apiInterface.getHomeData(
                session.getPincode(),
                session.getUserId(),
                "0"
        ).enqueue(new Callback<HomePageDataModel>() {
            @Override
            public void onResponse(@NonNull Call<HomePageDataModel> call, @NonNull Response<HomePageDataModel> response) {

                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            binding.mainDatarecycler.setLayoutManager(new LinearLayoutManager(activity));
                            binding.mainDatarecycler.setAdapter(new HomeCategoryAdapter(activity, response.body().getCategoryData()));
                        }
            }

            @Override
            public void onFailure(@NonNull Call<HomePageDataModel> call, @NonNull Throwable t) {

            }
        });

    }

    @Override
    public void onSelect(String id) {

    }
}
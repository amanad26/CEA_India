package com.example.cea.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.R;
import com.example.cea.databinding.FragmentVehicleImageItemBinding;

public class VehicleImageItemFragment extends Fragment {
    private FragmentVehicleImageItemBinding binding;
    private String image;
    Activity activity;

    public VehicleImageItemFragment(String image) {
        this.image = image;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVehicleImageItemBinding.inflate(inflater, container, false);

        activity = requireActivity();
        Glide.with(activity).load(BaseUrls.ImageBaseUrl + image).placeholder(R.drawable.model_1).into(binding.fullscreenImage);

        return binding.getRoot();
    }
}
package com.example.cea.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Apis.BaseUrls;
import com.example.cea.R;
import com.example.cea.databinding.FragmentOnboarding2Binding;
import com.squareup.picasso.Picasso;


public class OnboardingFragment4 extends Fragment {

    String description, title;
    String imageResourceAnInt = "";


    private String mParam1;
    private String mParam2;
    FragmentOnboarding2Binding binding;


    public OnboardingFragment4(String title, String description, String imageResource) {
        this.description = description;
        this.imageResourceAnInt = imageResource;
        this.title = title;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOnboarding2Binding.inflate(getLayoutInflater());

        Picasso.get().load(BaseUrls.ImageBaseUrl + imageResourceAnInt).into(binding.imageOnboarding);

        binding.textOnboardingDescription.setText(description);
        binding.textOnboardingTitle.setText(title);
        return binding.getRoot();
    }
}
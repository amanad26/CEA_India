package com.example.cea.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cea.R;
import com.example.cea.databinding.FragmentAllProductsBinding;


public class AllProductsFragment extends Fragment {
    FragmentAllProductsBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentAllProductsBinding.inflate(inflater, container, false);





       return binding.getRoot();
    }
}
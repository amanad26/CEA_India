package com.example.cea.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Adapters.AllAellerAdapter;
import com.example.cea.R;
import com.example.cea.databinding.FragmentAllSellersfragmentBinding;


public class AllSellersfragment extends Fragment {
    FragmentAllSellersfragmentBinding binding;

    RecyclerView recycler_all_sellers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentAllSellersfragmentBinding.inflate(inflater, container, false);

        binding.recyclerAllSellers.setAdapter(new AllAellerAdapter(getContext()));
        binding.recyclerAllSellers.setLayoutManager(new LinearLayoutManager(getContext()));


        return binding.getRoot();


    }
}
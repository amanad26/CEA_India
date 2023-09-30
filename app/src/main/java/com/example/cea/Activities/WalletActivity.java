package com.example.cea.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cea.Adapters.WalletPageAdapter;
import com.example.cea.R;
import com.example.cea.databinding.ActivityWalletBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class WalletActivity extends AppCompatActivity {

    ActivityWalletBinding binding;
    WalletActivity activity;

    private String[] items = {"Earning", "Spending"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        activity = this;
        setContentView(binding.getRoot());
        WalletPageAdapter adapter = new WalletPageAdapter(activity);
        binding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tab, binding.viewPager, (tab, position) -> tab.setText(items[position])).attach();

    }
}
package com.example.cea.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.ActivityBillingBinding;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class BillingActivity extends AppCompatActivity {

    ActivityBillingBinding binding;
    BillingActivity activity;
    Session session;
    String selectedCountry = "";
    List<String> country = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBillingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);

        binding.address.setText(session.getAddress());
        binding.phone.setText(session.getMobile());
        binding.pinCode.setText(session.getPincode());
        binding.lastName.setText(session.getLastname());
        binding.email.setText(session.getEmail());
        binding.firstName.setText(session.getName());


        country.add("India");
        country.add("USA");
        country.add("Nepal");

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems(country);
        spinner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> selectedCountry = country.get(position));


        binding.ivBack.setOnClickListener(view -> onBackPressed());
    }
}
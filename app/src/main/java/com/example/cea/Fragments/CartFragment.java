package com.example.cea.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Activities.BillingActivity;
import com.example.cea.Activities.CheckoutActivity;
import com.example.cea.Activities.HomeActivity;
import com.example.cea.Adapters.CartAdapter;
import com.example.cea.Apis.CatUpdateInterface;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.CartListModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.databinding.FragmentCartBinding;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment implements CatUpdateInterface {
    FragmentCartBinding binding;
    Session session;
    Activity activity;
    double totalAmount = 0;
    ProgressDialog pd ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);

        activity = requireActivity();
        session = new Session(activity);
        pd  = new ProgressDialog(activity);

        binding.checkOutBtn.setOnClickListener(view -> startActivity(new Intent(activity, CheckoutActivity.class)));
        binding.backToHome.setOnClickListener(view -> {
            startActivity(new Intent(activity, HomeActivity.class));
            activity.finish();
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getCartData();
    }

    public void getCartData() {
        pd.show();
        RetrofitClient.getClient(activity).getCartList(session.getUserId(), session.getPincode()).enqueue(new Callback<CartListModel>() {
            @Override
            public void onResponse(@NonNull Call<CartListModel> call, @NonNull Response<CartListModel> response) {
                pd.dismiss();
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            totalAmount = 0;
                            if (response.body().getData().size() != 0) {
                                binding.llBottom.setVisibility(View.VISIBLE);
                                binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                                binding.recyclerView.setAdapter(new CartAdapter(activity, response.body().getData(), CartFragment.this));
                                float newValue = 0;
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    float p = Float.parseFloat(response.body().getData().get(i).getProductFinalAmount());
                                    Log.e("TAG", "onResponse: Price  " + p);
                                    totalAmount = totalAmount + p;
                                    BigDecimal bd = new BigDecimal(totalAmount).setScale(2, RoundingMode.HALF_UP);
                                     newValue = bd.floatValue();
                                }

                                binding.totalPrice.setText("$" + String.valueOf(newValue));

                            } else {
                                binding.noData.setVisibility(View.VISIBLE);
                                binding.llBottom.setVisibility(View.GONE);
                            }


                        } else {
                            binding.llBottom.setVisibility(View.GONE);
                            binding.noData.setVisibility(View.VISIBLE);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<CartListModel> call, @NonNull Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                pd.dismiss();
            }
        });


    }

    @Override
    public void onCatUpdate() {
        getCartData();
    }
}


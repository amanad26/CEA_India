package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.example.cea.Apis.RefreshCart;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Fragments.CartFragment;
import com.example.cea.Fragments.CategoryFragment;
import com.example.cea.Fragments.ExploreFragment;
import com.example.cea.Fragments.HomeFragment;
import com.example.cea.Fragments.ProfileFragment;
import com.example.cea.Models.CartListModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.ActivityHomeBinding;
import com.example.cea.databinding.PaymentConfirmLayoutBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements RefreshCart {
    ActivityHomeBinding binding;
    Activity activity;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);

        binding.notify.setOnClickListener(view -> startActivity(new Intent(activity, NotificationActivity.class)));
        setupNavBar();
        changeFragment(0);
        binding.rlHome.performClick();


        binding.imageScan.setOnClickListener(view -> startActivity(new Intent(activity, QrScanActivity.class)
                .putExtra("activity", "0")));

    }

    private void setupNavBar() {
        binding.homeAnim.setMinAndMaxProgress(0.0f, 0.6f);
        binding.cartAnim.setMinAndMaxProgress(0.0f, 0.6f);
//        binding.catAnim.setMinAndMaxProgress(0.0f, 0.6f);
        binding.exploreAnim.setMinAndMaxProgress(0.0f, 0.5f);

        binding.homeAnim.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
        );

        binding.catAnim.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
        );

        binding.exploreAnim.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
        );

        binding.cartAnim.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
        );

        binding.profileAnim.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
        );

        binding.rlHome.setOnClickListener(view -> {
            binding.homeAnim.playAnimation();
            changeFragment(0);
        });
        binding.rlCategory.setOnClickListener(view -> {
            binding.catAnim.playAnimation();
            changeFragment(1);
        });
        binding.rlExplore.setOnClickListener(view -> {
            binding.exploreAnim.playAnimation();
            changeFragment(2);
        });
        binding.rlCart.setOnClickListener(view -> {
            binding.cartAnim.playAnimation();
            changeFragment(3);
        });
        binding.rlProfile.setOnClickListener(view -> {
            binding.profileAnim.playAnimation();
            changeFragment(4);
        });


        binding.searchLocation.setOnClickListener(view -> {
            changeFragment(2);
            binding.exploreAnim.playAnimation();
        });

        binding.favIcon.setOnClickListener(view -> startActivity(new Intent(activity, MyFavoriteListActivity.class)));

    }

    private void changeFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                binding.cardSerch.setVisibility(View.VISIBLE);
                binding.header.setVisibility(View.VISIBLE);
                break;
            case 3:
                if (session.sharedPreferences.contains("user_id")) {
                    fragment = new CartFragment();
                    binding.cardSerch.setVisibility(View.GONE);
                    binding.header.setVisibility(View.GONE);
                } else loginRedirect();
                break;
            case 2:
                fragment = new ExploreFragment();
                binding.cardSerch.setVisibility(View.GONE);
                binding.header.setVisibility(View.GONE);
                break;
            case 1:
                fragment = new CategoryFragment();
                binding.cardSerch.setVisibility(View.GONE);
                binding.header.setVisibility(View.GONE);
                break;
            case 4:
                if (session.sharedPreferences.contains("user_id")) {
                    fragment = new ProfileFragment();
                    binding.cardSerch.setVisibility(View.GONE);
                    binding.header.setVisibility(View.GONE);
                } else {
                    loginRedirect();
                }
                //showPaymentConfirmDialog("100");
                break;

            default:
                new HomeFragment();
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), fragment);
            ft.commit();
        }
    }

    private void loginRedirect() {
        startActivity(new Intent(activity, LoginActivity.class));
    }

    @Override
    public List<CartListModel.Datum> onRemove(Context context, String userId) {
        List<CartListModel.Datum> list = new ArrayList<>();
        RetrofitClient.getClient(context).getCartList(userId, session.getPincode()).enqueue(new Callback<CartListModel>() {
            @Override
            public void onResponse(@NonNull Call<CartListModel> call, @NonNull Response<CartListModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            if (response.body().getData().size() != 0) {
                                list.addAll(response.body().getData());
                            }
                        }
            }

            @Override
            public void onFailure(@NonNull Call<CartListModel> call, @NonNull Throwable t) {

            }
        });

        return list;
    }


    private void showPaymentConfirmDialog(String price) {

        final Dialog dialog = new Dialog(activity);
        PaymentConfirmLayoutBinding binding = PaymentConfirmLayoutBinding.inflate(getLayoutInflater());
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().setDimAmount(0.80f);

        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        dialog.show();

        binding.walletAmount.setText(session.getWalletAmount());
        binding.userName.setText(session.getName() + " " + session.getLastname());
        binding.totalAmount.setText(price);

        float walletPrice = Float.parseFloat(session.getWalletAmount());
        float amount = Float.parseFloat(price);
        float finalPrice = amount - walletPrice;

        binding.payableAmount.setText(String.valueOf(finalPrice));

        binding.placeOrder.setOnClickListener(view -> startActivity(new Intent(activity, PaymentActivity.class)
                .putExtra("price", String.valueOf(finalPrice))));

    }


    @Override
    public void onRefresh() {

    }
}
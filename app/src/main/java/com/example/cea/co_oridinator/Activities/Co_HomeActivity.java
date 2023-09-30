package com.example.cea.co_oridinator.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.example.cea.Activities.LoginActivity;
import com.example.cea.Activities.MyFavoriteListActivity;
import com.example.cea.Activities.NotificationActivity;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Fragments.CartFragment;
import com.example.cea.Fragments.CategoryFragment;
import com.example.cea.Fragments.ExploreFragment;
import com.example.cea.Fragments.HomeFragment;
import com.example.cea.Fragments.ProfileFragment;
import com.example.cea.Models.CartListModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Fragments.Co_HomeFragment;
import com.example.cea.co_oridinator.Fragments.Co_SaleFragment;
import com.example.cea.co_oridinator.Fragments.Co_To_DoFragment;
import com.example.cea.co_oridinator.Fragments.Co_WalkingFragment;
import com.example.cea.co_oridinator.Fragments.ProfileFragmentCordinate;
import com.example.cea.databinding.ActivityCoHomeBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Co_HomeActivity extends AppCompatActivity {

    Activity activity;
    private Session session;
    ActivityCoHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCoHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        activity = this;
        session = new Session(activity);

        setupNavBar();
        changeFragment(0);
        // binding.rlHome.performClick();

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
//
//        binding.cartAnim.addValueCallback(
//                new KeyPath("**"),
//                LottieProperty.COLOR_FILTER,
//                frameInfo -> new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
//        );

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


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!session.getImage().equalsIgnoreCase(""))
            Picasso.get().load(session.getImage()).placeholder(R.drawable.ic_users).into(binding.userImage);

        if (!session.getName().equalsIgnoreCase("")) {
            binding.userNameTv.setText(session.getName());
        }

    }

    private void changeFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Co_HomeFragment();
                binding.header.setVisibility(View.VISIBLE);
                break;
            case 3:
                if (session.sharedPreferences.contains("user_id")) {
                    fragment = new Co_To_DoFragment();
                    binding.header.setVisibility(View.VISIBLE);
                } else loginRedirect();
                break;
            case 2:
                fragment = new Co_SaleFragment();
                binding.header.setVisibility(View.VISIBLE);
                break;
            case 1:
                fragment = new Co_WalkingFragment();
                binding.header.setVisibility(View.VISIBLE);
                break;
            case 4:
                if (session.sharedPreferences.contains("user_id")) {
                    fragment = new ProfileFragmentCordinate();
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


}
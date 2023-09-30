package com.example.cea.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cea.Adapters.PageChangeAdapter;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.BannerModel;
import com.example.cea.Models.OderDetailsModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.ActivityGetStartedActivityBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetStartedActivityActivity extends AppCompatActivity {

    ActivityGetStartedActivityBinding binding;
    Session session;
    private BannerModel.Datum data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetStartedActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        session = new Session(GetStartedActivityActivity.this);


        binding.btnNextStep.setOnClickListener(view -> {
            if (getItem(+1) > binding.viewPager.getChildCount() - 1) {
                startActivity(new Intent(GetStartedActivityActivity.this, LoginActivity.class));
                finish();
                session.setIsIntroViewed(true);
            } else {
                binding.viewPager.setCurrentItem(getItem(+1), true);
            }
        });

        binding.btnPreviousStep.setOnClickListener(view -> {
            if (getItem(+1) == 1) {
                Toast.makeText(this, "Please Click On Next Button ", Toast.LENGTH_SHORT).show();
            } else {
                binding.viewPager.setCurrentItem(-1, true);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getBannerList();
    }

    private void getBannerList() {

        RetrofitClient.getClient(GetStartedActivityActivity.this).getBannerList().enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            data = response.body().getData().get(0);


                            binding.viewPager.setAdapter(new PageChangeAdapter(getSupportFragmentManager(), GetStartedActivityActivity.this, data));
                            binding.viewPager.setOffscreenPageLimit(1);

                            binding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                    if (position == 2) {
                                        binding.btnNextStep.setText(getString(R.string.finish));
                                    } else {
                                        binding.btnNextStep.setText(getString(R.string.next));

                                    }
                                }

                                @Override
                                public void onPageSelected(int position) {

                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });

                        }
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {

            }
        });
    }

    private int getItem(int i) {
        return binding.viewPager.getCurrentItem() + 1;

    }
}
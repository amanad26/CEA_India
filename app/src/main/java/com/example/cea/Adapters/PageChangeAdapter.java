package com.example.cea.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cea.Fragments.OnboardingFragment4;
import com.example.cea.Models.BannerModel;
import com.example.cea.Models.OderDetailsModel;
import com.example.cea.R;

public class PageChangeAdapter extends FragmentPagerAdapter {

    Context context;
    BannerModel.Datum data;

    public PageChangeAdapter(@NonNull FragmentManager fm, Context context, BannerModel.Datum data) {
        super(fm);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new OnboardingFragment4(
                    data.getTitle1(),
                    data.getDescription1(),
                    data.getImg1()
            );
        } else if (position == 1) {
            return new OnboardingFragment4(
                    data.getTitle2(),
                    data.getDescription2(),
                    data.getImg2()
            );
        } else if (position == 2) {
            return new OnboardingFragment4(
                    data.getTitle3(),
                    data.getDescription3(),
                    data.getImg3()
            );
        } else {
            return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}

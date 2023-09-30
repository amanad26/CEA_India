package com.example.cea.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cea.Fragments.AllProductsFragment;
import com.example.cea.Fragments.AllSellersfragment;

public class ViewPager extends FragmentStateAdapter {
    public ViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return new AllProductsFragment();
        return new AllSellersfragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

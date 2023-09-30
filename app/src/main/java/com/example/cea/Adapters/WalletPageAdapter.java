package com.example.cea.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cea.Fragments.WalletEarningFragment;
import com.example.cea.Fragments.WalletSpendingFragment;

public class WalletPageAdapter extends FragmentStateAdapter {

    public WalletPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new WalletEarningFragment();
        else return new WalletSpendingFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

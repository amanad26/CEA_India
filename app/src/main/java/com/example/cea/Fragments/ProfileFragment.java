package com.example.cea.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Activities.AboutUsActivity;
import com.example.cea.Activities.ChangePasswordActivity;
import com.example.cea.Activities.EditProfileActivity;
import com.example.cea.Activities.HistoryActivity;
import com.example.cea.Activities.LoginActivity;
import com.example.cea.Activities.MyFavoriteListActivity;
import com.example.cea.Activities.PrivacyPolicyActivity;
import com.example.cea.Activities.ReturnPolicyActivity;
import com.example.cea.Activities.TermsAndConditionActivity;
import com.example.cea.Activities.WalletActivity;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    private Session session;
    private Activity activity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        activity = requireActivity();
        session = new Session(activity);

        binding.logoutLayout.setOnClickListener(view -> {
            session.logOut();
            startActivity(new Intent(activity, LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
            );
            activity.finish();
        });

        binding.privacyPolicyLayout.setOnClickListener(view -> startActivity(new Intent(activity, PrivacyPolicyActivity.class)));
        binding.referealCode.setOnClickListener(view -> startActivity(new Intent(activity, MyReferCodeActivity.class)));
        binding.termsAndCondLayout.setOnClickListener(view -> startActivity(new Intent(activity, TermsAndConditionActivity.class)));
        binding.myOrderLayout.setOnClickListener(view -> startActivity(new Intent(activity, HistoryActivity.class)));
        binding.aboutUsLayout.setOnClickListener(view -> startActivity(new Intent(activity, AboutUsActivity.class)));
        binding.walletLayout.setOnClickListener(view -> startActivity(new Intent(activity, WalletActivity.class)));
        binding.changePasswordlayout.setOnClickListener(view -> {
            if (session.sharedPreferences.contains("user_id"))
                startActivity(new Intent(activity, ChangePasswordActivity.class));
            else
                loginRedirect();
        });
        binding.retrunPolicyLayout.setOnClickListener(view -> startActivity(new Intent(activity, ReturnPolicyActivity.class).putExtra("title_text", "Return Policy")
                .putExtra("type", "return_policy")));
        binding.cutomerSupportLayout.setOnClickListener(view -> startActivity(new Intent(activity, ReturnPolicyActivity.class).putExtra("title_text", "Customer Support")
                .putExtra("type", "support")));

        if (session.sharedPreferences.contains("user_id")) {
            binding.userMobile.setText(session.getMobile());
            binding.userName.setText(session.getName() + " " + session.getLastname());
        } else binding.textLoginRedirect.setVisibility(View.VISIBLE);

        binding.textLoginRedirect.setOnClickListener(view -> loginRedirect());

        binding.editProfile.setOnClickListener(view -> {
            if (session.sharedPreferences.contains("user_id"))
                startActivity(new Intent(activity, EditProfileActivity.class));
            else
                loginRedirect();
        });
        binding.favoriteListLayout.setOnClickListener(view -> {
            if (session.sharedPreferences.contains("user_id"))
                startActivity(new Intent(activity, MyFavoriteListActivity.class));
            else
                loginRedirect();
        });



        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "onResume: Image "+session.getImage());
        if (!session.getImage().equalsIgnoreCase(""))
            Picasso.get().load(session.getImage()).placeholder(R.drawable.ic_users).into(binding.userImage);
        binding.userWallet.setText("Wallet: ₹ " + session.getWalletAmount());
    }

    private void loginRedirect() {
        startActivity(new Intent(activity, LoginActivity.class));
    }
}
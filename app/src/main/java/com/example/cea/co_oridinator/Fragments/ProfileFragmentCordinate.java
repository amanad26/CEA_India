package com.example.cea.co_oridinator.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.example.cea.co_oridinator.Activities.CommisionActivity;
import com.example.cea.co_oridinator.Activities.EditProfileActivityCodinate;
import com.example.cea.co_oridinator.Activities.FaqActivity;
import com.example.cea.databinding.FragmentProfileBinding;
import com.example.cea.databinding.FragmentProfileCordinateBinding;
import com.squareup.picasso.Picasso;


public class ProfileFragmentCordinate extends Fragment {

    FragmentProfileCordinateBinding binding;
    private Session session;
    private Activity activity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileCordinateBinding.inflate(inflater, container, false);
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
        binding.termsAndCondLayout.setOnClickListener(view -> startActivity(new Intent(activity, TermsAndConditionActivity.class)));
        binding.commision.setOnClickListener(view -> startActivity(new Intent(activity, CommisionActivity.class)));
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
            binding.userName.setText(session.getName());
        } else binding.textLoginRedirect.setVisibility(View.VISIBLE);

        binding.textLoginRedirect.setOnClickListener(view -> loginRedirect());

        binding.editProfile.setOnClickListener(view -> {
            if (session.sharedPreferences.contains("user_id"))
                startActivity(new Intent(activity, EditProfileActivityCodinate.class));
            else
                loginRedirect();
        });

        binding.favoriteListLayout.setOnClickListener(view -> {
            if (session.sharedPreferences.contains("user_id"))
                startActivity(new Intent(activity, MyFavoriteListActivity.class));
            else
                loginRedirect();
        });

        binding.cardFaq.setOnClickListener(view -> startActivity(new Intent(activity, FaqActivity.class)));

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "onResume: Image " + session.getImage());
        if (!session.getImage().equalsIgnoreCase(""))
            Picasso.get().load(session.getImage()).placeholder(R.drawable.ic_users).into(binding.userImage);

        if (session.sharedPreferences.contains("user_id")) {
            binding.userMobile.setText(session.getMobile());
            binding.userName.setText(session.getName());
        }

    }

    private void loginRedirect() {
        startActivity(new Intent(activity, LoginActivity.class));
    }
}
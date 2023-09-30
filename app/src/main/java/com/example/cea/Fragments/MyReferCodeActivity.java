package com.example.cea.Fragments;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cea.BuildConfig;
import com.example.cea.Session.Session;
import com.example.cea.databinding.MyReferCodeActivityBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyReferCodeActivity extends AppCompatActivity {
    private MyReferCodeActivityBinding binding;
    Session session;
    Activity activity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MyReferCodeActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(this);

        binding.yourReCodeOriginal.setText(session.getReferCode());

        binding.shareLinear.setOnClickListener(view -> sendWhatsapp("My refer code is (" + session.getReferCode() + ")  \n Download the app from : https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID));

        binding.shareLinear2.setOnClickListener(view -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "My refer code is (" + session.getReferCode() + ")  \n Download the app from : https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        });

        binding.copyLink.setOnClickListener(view -> {
            String text = "My refer code is (" + session.getReferCode() + ")  \n Download the app from : https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;

            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Copied Text", text);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(activity, "Text copied", Toast.LENGTH_SHORT).show();

        });


        binding.yourReCodeOriginal.setOnLongClickListener(view -> {
            String text = binding.yourReCodeOriginal.getText().toString();

            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Copied Text", text);
            clipboardManager.setPrimaryClip(clipData);
//            getSuccessToast(requireContext(), "Text Copied..!");
            Toast.makeText(activity, "Text copied", Toast.LENGTH_SHORT).show();
            return true;
        });

        binding.tapToCopy.setOnClickListener(v -> {
            String text = binding.yourReCodeOriginal.getText().toString();

            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Copied Text", text);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(activity, "Text Copied..!", Toast.LENGTH_SHORT).show();
        });


    }


    @Override
    public void onResume() {
        super.onResume();
    }


    private void sendWhatsapp(String message) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        } else {
            Toast.makeText(activity, "Whatsapp not installed", Toast.LENGTH_SHORT).show();
        }
    }

}
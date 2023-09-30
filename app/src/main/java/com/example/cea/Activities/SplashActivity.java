package com.example.cea.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Activities.Co_HomeActivity;
import com.example.cea.databinding.ActivitySplashBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    Activity activity;
    private static int SPLASH_SCREEN_TIME_OUT = 1900;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        activity = this;
        session = new Session(activity);

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.CAMERA
                ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        new Handler().postDelayed(() -> {
                            if (session.sharedPreferences.contains("user_id")) {

                                if (session.getType().equalsIgnoreCase("user")) {
                                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Intent i = new Intent(SplashActivity.this, Co_HomeActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            } else {
                                if (session.getIsIntroViewed()) {
                                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Intent i = new Intent(SplashActivity.this, GetStartedActivityActivity.class);
                                    startActivity(i);
                                    finish();
                                }

                            }

                        }, SPLASH_SCREEN_TIME_OUT);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                        Toast.makeText(activity, "Permissions Required to Scan!", Toast.LENGTH_SHORT).show();
                    }
                }).check();


    }
}
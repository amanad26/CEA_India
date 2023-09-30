package com.example.cea.Activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Adapters.NotificationItemListAdapter;
import com.example.cea.Adapters.VerticalCategoryAdapter;
import com.example.cea.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {
    ActivityNotificationBinding binding;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;




        NotificationItemListAdapter notificationItemListAdapter = new NotificationItemListAdapter(activity);
        binding.notificationItemRecy.setAdapter(notificationItemListAdapter);
        binding.notificationItemRecy.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));

    }
}
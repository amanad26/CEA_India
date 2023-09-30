package com.example.cea.Adapters;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cea.Fragments.VehicleImageItemFragment;
import com.example.cea.Models.ViewFulImage;
import com.example.cea.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VehicleImagesViewPager extends FragmentStateAdapter {
    private ArrayList<String> list;
    public Timer swipeTimer;
    final Handler handler = new Handler();

    public VehicleImagesViewPager(@NonNull FragmentActivity fragmentActivity, ArrayList<String> list) {
        super(fragmentActivity);
        this.list = list;
    }


    public void setTimer(final ViewPager2 myPager, int time) {
        final int size = list.size();
        final Runnable Update = new Runnable() {
            int NUM_PAGES = size;
            int currentPage = 0;

            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                myPager.setCurrentItem(currentPage++, true);
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, time * 1000);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new VehicleImageItemFragment(list.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ViewFulImage.class);
                intent.putExtra("image_id", list.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}


package com.example.cea.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.databinding.NotificationListLayoutBinding;

public class NotificationItemListAdapter extends RecyclerView.Adapter<NotificationItemListAdapter.ViewHolder> {
    Context context;


    public NotificationItemListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return new  ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leaguecricketlayout,parent,false)
        return new ViewHolder(NotificationListLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



    }


    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NotificationListLayoutBinding binding;

        public ViewHolder(@NonNull NotificationListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

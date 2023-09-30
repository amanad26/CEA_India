package com.example.cea.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.databinding.VerticalCategoryLayoutBinding;

public class HomeListCategoryHorizontalAdapter extends RecyclerView.Adapter<HomeListCategoryHorizontalAdapter.ViewHolder> {
    Context context;


    public HomeListCategoryHorizontalAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeListCategoryHorizontalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return new  ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leaguecricketlayout,parent,false)
        return new HomeListCategoryHorizontalAdapter.ViewHolder(VerticalCategoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListCategoryHorizontalAdapter.ViewHolder holder, int position) {



    }


    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        VerticalCategoryLayoutBinding binding;

        public ViewHolder(@NonNull VerticalCategoryLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}



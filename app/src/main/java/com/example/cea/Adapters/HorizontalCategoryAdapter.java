package com.example.cea.Adapters;

import static com.example.cea.Apis.BaseUrls.ImageBaseUrl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cea.Activities.AllProductByCategoryActivity;
import com.example.cea.Models.SubCategoryModel;
import com.example.cea.R;
import com.example.cea.databinding.VerticalCategoryLayoutBinding;

import java.util.List;

public class HorizontalCategoryAdapter extends RecyclerView.Adapter<HorizontalCategoryAdapter.ViewHolder> {

    Context context;
    List<SubCategoryModel.Datum> models;

    public HorizontalCategoryAdapter(Context context, List<SubCategoryModel.Datum> models) {
        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public HorizontalCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return new  ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leaguecricketlayout,parent,false)
        return new HorizontalCategoryAdapter.ViewHolder(VerticalCategoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalCategoryAdapter.ViewHolder holder, int position) {

        holder.binding.catName.setText(models.get(position).getCategoryName());
        holder.binding.details.setOnClickListener(view -> {

            Intent intent = new Intent(context, AllProductByCategoryActivity.class);
            intent.putExtra("cat_id", models.get(position).getCategoryId());
            intent.putExtra("cat_name", models.get(position).getCategoryName());
            context.startActivity(intent);

        });

        Glide.with(context).load(ImageBaseUrl + models.get(position).getCategoryImage()).placeholder(R.drawable.car1)
                .into(holder.binding.catImage);


    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        VerticalCategoryLayoutBinding binding;

        public ViewHolder(@NonNull VerticalCategoryLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}

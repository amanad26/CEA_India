package com.example.cea.Adapters;

import static com.example.cea.Apis.BaseUrls.ImageBaseUrl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cea.Activities.AllProductByCategoryActivity;
import com.example.cea.Activities.ProductDetailsActivity;
import com.example.cea.Apis.SubCategoryInterface;
import com.example.cea.Models.CategoryModel;
import com.example.cea.R;
import com.example.cea.databinding.VerticalCategoryLayoutBinding;

import java.util.List;

public class VerticalCategoryAdapter extends RecyclerView.Adapter<VerticalCategoryAdapter.ViewHolder> {

    Context context;
    List<CategoryModel.Datum> models;
    int type;
    SubCategoryInterface subCategoryInterface;

    public VerticalCategoryAdapter(Context context, List<CategoryModel.Datum> models, SubCategoryInterface subCategoryInterface, int type) {
        this.context = context;
        this.models = models;
        this.subCategoryInterface = subCategoryInterface;
        this.type = type;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(VerticalCategoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.binding.catName.setText(models.get(position).getCategoryName());
        holder.binding.details.setOnClickListener(view -> {

            if (type == 0) {
                Intent intent = new Intent(context, AllProductByCategoryActivity.class);
                intent.putExtra("cat_id", models.get(position).getCategoryId());
                intent.putExtra("cat_name", models.get(position).getCategoryName());
                context.startActivity(intent);
            } else {
                subCategoryInterface.onSelect(models.get(position).getCategoryId());
            }


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

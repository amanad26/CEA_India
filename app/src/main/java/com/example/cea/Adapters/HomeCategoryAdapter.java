package com.example.cea.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Activities.AllProductByCategoryActivity;
import com.example.cea.Models.HomePageDataModel;
import com.example.cea.R;
import com.example.cea.databinding.HomePageMainCatLayoutBinding;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    Context context;
    List<HomePageDataModel.CategoryDatum> models;

    public HomeCategoryAdapter(Context context, List<HomePageDataModel.CategoryDatum> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_main_cat_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.categoryName.setText(models.get(position).getCategoryHeading());

        holder.binding.recyelerView.setLayoutManager(new GridLayoutManager(context, 2));
        holder.binding.recyelerView.setAdapter(new HomeCategoryInnerAdapter(context, models.get(position).getData()));

        holder.binding.details.setOnClickListener(view -> {

            try {
                if (models.get(position).getData().size() != 0) {
                    context.startActivity(new Intent(context, AllProductByCategoryActivity.class)
                            .putExtra("cat_id", models.get(position).getCategory_id())
                            .putExtra("cat_name", models.get(position).getCategoryHeading())
                    );
                }

            } catch (Exception e) {
                e.getStackTrace();
            }


        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HomePageMainCatLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomePageMainCatLayoutBinding.bind(itemView);

        }
    }
}

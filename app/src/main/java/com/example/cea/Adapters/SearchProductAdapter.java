package com.example.cea.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cea.Activities.ProductDetailsActivity;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Models.HomePageDataModel;
import com.example.cea.Models.SearchModel;
import com.example.cea.R;
import com.example.cea.databinding.SingleProductLayoutBinding;

import java.util.List;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ViewHolder> {

    Context context;
    List<SearchModel.Datum> models;

    public SearchProductAdapter(Context context, List<SearchModel.Datum> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_product_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.productName.setText(models.get(position).getProductName());
        holder.binding.productPrice.setText(models.get(position).getProductPrice());
        holder.binding.productSalePrice.setText(models.get(position).getProductFinalAmount());
        holder.binding.productSaleDisc.setText(models.get(position).getProductMaxDiscount()+"%");
        Glide.with(context).load(BaseUrls.ImageBaseUrl + models.get(position).getProductImage())
                .placeholder(R.drawable.model_1).into(holder.binding.productImage);

        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, ProductDetailsActivity.class)
                .putExtra("pro_id", models.get(position).getProductId())));

    }

    @Override
    public int getItemCount() {
        return Math.min(models.size(), 8);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SingleProductLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleProductLayoutBinding.bind(itemView);

        }
    }
}

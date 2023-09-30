package com.example.cea.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Models.ProductDetailsModel;
import com.example.cea.R;
import com.example.cea.databinding.ImageLayoutBinding;
import com.example.cea.databinding.SpecificationLayoutBinding;

import java.util.List;

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.ViewHolder> {

    Context context;
    List<ProductDetailsModel.Datum.ProductSpecificationResult> models;

    public SpecificationAdapter(Context context, List<ProductDetailsModel.Datum.ProductSpecificationResult> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.specification_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.productName.setText(models.get(position).getSpecificationName());
    }

    @Override
    public int getItemCount() {
        return  models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SpecificationLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SpecificationLayoutBinding.bind(itemView);
        }
    }

}

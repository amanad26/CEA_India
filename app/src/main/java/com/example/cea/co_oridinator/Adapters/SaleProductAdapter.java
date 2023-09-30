package com.example.cea.co_oridinator.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.R;
import com.example.cea.co_oridinator.Models.SaleDetailsModel;
import com.example.cea.databinding.SaleListLayoutBinding;
import com.example.cea.databinding.SaleProductLayoutBinding;
import com.example.cea.databinding.SalproductListLayoutBinding;

import java.util.List;

public class SaleProductAdapter extends RecyclerView.Adapter<SaleProductAdapter.ViewHolder> {

    Context context;
    List<SaleDetailsModel.Datum.Product> models;

    public SaleProductAdapter(Context context, List<SaleDetailsModel.Datum.Product> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.salproduct_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.productName.setText(models.get(position).getProductName() + "   S.No: " + models.get(position).getProductSerialNo());
        holder.binding.productTotal.setText(models.get(position).getOrderAmount());
        holder.binding.productMRP.setText(models.get(position).getProductFinalAmount());
        holder.binding.productdiscount.setText(models.get(position).getProductDiscount());
        //set the MOP
        holder.binding.productMOP.setText(models.get(position).getProductDiscount());


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SalproductListLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SalproductListLayoutBinding.bind(itemView);
        }
    }
}

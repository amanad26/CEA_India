package com.example.cea.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Activities.AddReviewActivity;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Models.OderDetailsModel;
import com.example.cea.R;
import com.example.cea.databinding.OrderHistoryLayoutBinding;
import com.example.cea.databinding.OrderProductDetailsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {


    Context context;
    List<OderDetailsModel.Datum> models;

    public OrderDetailAdapter(Context context, List<OderDetailsModel.Datum> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_product_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.productName.setText(models.get(position).getProductName());
        holder.binding.specilization.setText("Specification : " + models.get(position).getSpecificationName());
        holder.binding.productQuantity.setText("Quantity : " + models.get(position).getSaleQty());
        holder.binding.productPrice.setText(models.get(position).getProductFinalAmount());
        holder.binding.offerPrice.setText(models.get(position).getOfferPrice());
        holder.binding.finalPrice.setText(models.get(position).getProductFinalAmount());
        holder.binding.totalAmount.setText(models.get(position).getOrderAmount());
        holder.binding.totalTax.setText(models.get(position).getProductTaxAmount() + " %");

        Picasso.get().load(BaseUrls.ImageBaseUrl + models.get(position).getProductImage()).into(holder.binding.productImage);

        if (models.get(position).getReview_status().equalsIgnoreCase("false")) {
            holder.binding.addReviewTv.setText("Add Review");
        } else {
            holder.binding.addReviewTv.setText("Review Added");
        }

        holder.binding.addreview.setOnClickListener(view -> {
            if (models.get(position).getReview_status().equalsIgnoreCase("false")) {
                context.startActivity(new Intent(context, AddReviewActivity.class)
                        .putExtra("productId", models.get(position).getProductId())
                );
            }

        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        OrderProductDetailsBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = OrderProductDetailsBinding.bind(itemView);
        }
    }
}

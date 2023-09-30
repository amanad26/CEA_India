package com.example.cea.co_oridinator.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Activities.SaleDetailsActivity;
import com.example.cea.co_oridinator.Models.MySalesModel;
import com.example.cea.databinding.SaleListLayoutBinding;

import java.util.List;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder> {

    Context context;
    List<MySalesModel.Datum> models;
    Session session;

    public SaleAdapter(Context context, List<MySalesModel.Datum> models) {
        this.context = context;
        this.models = models;
        session = new Session(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.customerName.setText(models.get(position).getCustomerFname() + " " + models.get(position).getCustomerLname());
        holder.binding.orderId.setText(models.get(position).getOrderId());

        holder.binding.userMobile.setText(models.get(position).getCustomer_mobile_no());

        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, SaleDetailsActivity.class)
                .putExtra("sale_id", models.get(position).getSaleId())));

        MySalesModel.Datum current = models.get(position);
        if (!current.getSaleDeliveryStatus().equalsIgnoreCase("Pending")) {
//            holder.binding.imageDelivered.setVisibility(View.GONE);
//            holder.binding.imageDeliveredGreen.setVisibility(View.VISIBLE);
            holder.binding.imageDelivered.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);

        }

        if (!current.getSaleStatus().equalsIgnoreCase("Placed")) {
            holder.binding.imageDispatch.setVisibility(View.GONE);
            holder.binding.imageDeliveredGreen.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SaleListLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SaleListLayoutBinding.bind(itemView);
        }
    }


}

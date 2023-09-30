package com.example.cea.co_oridinator.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Apis.BaseUrls;
import com.example.cea.R;
import com.example.cea.co_oridinator.Models.ProductModelCo;
import com.example.cea.databinding.HomeSliderLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeSliderAdapter extends RecyclerView.Adapter<HomeSliderAdapter.ViewHolder> {

    Context context;
    List<ProductModelCo.Datum> models;

    public HomeSliderAdapter(Context context, List<ProductModelCo.Datum> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_slider_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.nameTv.setText(models.get(position).getProductName());
        holder.binding.priceTv.setText(models.get(position).getProductFinalAmount());

        try {
            Picasso.get().load(BaseUrls.ImageBaseUrl + models.get(position).getProduct_image()).placeholder(R.drawable.cea_logo).into(holder.binding.image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        if (models.size() > 11)
            return 10;
        else return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HomeSliderLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomeSliderLayoutBinding.bind(itemView);
        }
    }


}

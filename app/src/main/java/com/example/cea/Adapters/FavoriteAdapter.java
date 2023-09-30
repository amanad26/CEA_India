package com.example.cea.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cea.Activities.ProductDetailsActivity;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.FavoriteListModel;
import com.example.cea.Models.FavoriteModel;
import com.example.cea.Models.UpdateProfileModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.SingleProductLayoutBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    Context context;
    List<FavoriteListModel.Datum> models;
    Session session;

    public FavoriteAdapter(Context context, List<FavoriteListModel.Datum> models) {
        this.context = context;
        this.models = models;
        session = new Session(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_product_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.productName.setText(models.get(position).getProductName());
        holder.binding.productPrice.setText(models.get(position).getProductMop());
        holder.binding.productSalePrice.setText(models.get(position).getProductFinalAmount());
        Glide.with(context).load(BaseUrls.ImageBaseUrl + models.get(position).getProductImage())
                .placeholder(R.drawable.model_1).into(holder.binding.productImage);

        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, ProductDetailsActivity.class)
                .putExtra("pro_id", models.get(position).getProductId())));

        holder.binding.moveToCart.setVisibility(View.VISIBLE);
        holder.binding.favIcon.setImageResource(R.drawable.ic_baseline_favorite_24);

        holder.binding.moveToCart.setOnClickListener(view -> RetrofitClient.getClient(context).moveToCart(
                models.get(position).getFavoriteId(),
                session.getUserId(),
                session.getPincode()
        ).enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateProfileModel> call, @NonNull Response<UpdateProfileModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(context, "Moved To Cart..", Toast.LENGTH_SHORT).show();
                            notifyItemChanged(position);
                            notifyItemRangeChanged(0, models.size());
                            models.remove(position);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateProfileModel> call, @NonNull Throwable t) {

            }
        }));


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SingleProductLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleProductLayoutBinding.bind(itemView);
        }
    }
}

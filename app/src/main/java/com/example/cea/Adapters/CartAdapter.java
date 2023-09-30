package com.example.cea.Adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.CatUpdateInterface;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.CartListModel;
import com.example.cea.Models.RemoveCartModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.CartItemLayoutBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<CartListModel.Datum> models;
    Session session;
    CatUpdateInterface anInterface;

    public CartAdapter(Context context, List<CartListModel.Datum> models, CatUpdateInterface c) {
        this.context = context;
        this.models = models;
        this.anInterface = c;
        session = new Session(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.productName.setText(models.get(position).getProductName());
        holder.binding.productMrp.setText(Html.fromHtml("<strike>" + models.get(position).getProductMop() + "</strike>"));
        holder.binding.productPrice.setText(models.get(position).getProductFinalAmount());
        holder.binding.productQuantity.setText(models.get(position).getProductQty());

        Glide.with(context).load(BaseUrls.ImageBaseUrl + models.get(position).getProductImage())
                .placeholder(R.drawable.model_1).into(holder.binding.productImage);

        holder.binding.productMinus.setOnClickListener(view -> {
            int q = Integer.parseInt(models.get(position).getProductQty());
            if (q > 0) q = q - 1;
            updateCart(q, models.get(position).getCartId());
            models.get(position).setProductQty(String.valueOf(q));
            notifyItemChanged(position);
            anInterface.onCatUpdate();
        });


        holder.binding.productPlus.setOnClickListener(view -> {
            int q = Integer.parseInt(models.get(position).getProductQty());
            if (q >= 0) q = q + 1;
            updateCart(q, models.get(position).getCartId());
            models.get(position).setProductQty(String.valueOf(q));
            notifyItemChanged(position);
            anInterface.onCatUpdate();
        });


        holder.binding.productDelete.setOnClickListener(view -> {
            String id = models.get(position).getCartId();
            notifyItemRemoved(position);
            notifyItemRangeChanged(0, models.size() - 1);
            models.remove(position);
            deleteCartItem(id);
            anInterface.onCatUpdate();
        });


    }

    private void updateCart(int q, String cartId) {

        RetrofitClient.getClient(context).updateCart(
                cartId,
                String.valueOf(q),
                session.getPincode()
        ).enqueue(new Callback<CartListModel>() {
            @Override
            public void onResponse(@NonNull Call<CartListModel> call, @NonNull Response<CartListModel> response) {

            }

            @Override
            public void onFailure(@NonNull Call<CartListModel> call, @NonNull Throwable t) {

            }
        });

    }

    private void deleteCartItem(String id) {
        RetrofitClient.getClient(context).removeCart(id).enqueue(new Callback<RemoveCartModel>() {
            @Override
            public void onResponse(@NonNull Call<RemoveCartModel> call, @NonNull Response<RemoveCartModel> response) {

            }

            @Override
            public void onFailure(@NonNull Call<RemoveCartModel> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CartItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartItemLayoutBinding.bind(itemView);
        }
    }

}

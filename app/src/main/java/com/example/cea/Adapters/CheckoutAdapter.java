package com.example.cea.Adapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.RefreshCart;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.CartListModel;
import com.example.cea.Models.CheckoutModel;
import com.example.cea.Models.RemoveCartModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.databinding.CartItemLayoutBinding;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {


    Context context;
    List<CheckoutModel.Datum> models;
    RefreshCart refreshCart;
    Session session;
    boolean isNot = false;

    public CheckoutAdapter(Context context, List<CheckoutModel.Datum> models, RefreshCart refreshCart, boolean isNot) {
        this.context = context;
        this.models = models;
        this.refreshCart = refreshCart;
        this.isNot = isNot;
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

        String priceNew = "";
        priceNew = models.get(position).getProductSalePrice() ;

        holder.binding.productPrice.setText(priceNew);
        holder.binding.productQuantity.setText(models.get(position).getProductQty());

        holder.binding.checkoutLinear.setVisibility(View.VISIBLE);
       // holder.binding.netAmount.setText(models.get(position).getProductSalePrice() + " x " + models.get(position).getProductQty());
        holder.binding.tax.setText(models.get(position).getProductTax() + " % ");

        if (isNot) {
            holder.binding.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.red_light));
            holder.binding.productDelete.setVisibility(View.GONE);
        }

        try {
            float p = Float.parseFloat(models.get(position).getProductSalePrice());
//            float p = Float.parseFloat(models.get(position).getProductFinalAmount());
            float q = Float.parseFloat(models.get(position).getProductQty());
            float tax = 0;

            float finalPrice = p * q;

            holder.binding.netAmount.setText(models.get(position).getSingle_product_price() + " * "+models.get(position).getProductQty());
            holder.binding.netTotalAmount.setText(models.get(position).getProductFinalAmount());

            if (models.get(position).getProductOffer() != null) {

                holder.binding.productOffer.setVisibility(View.VISIBLE);
                holder.binding.productDiscount.setVisibility(View.VISIBLE);
                holder.binding.offerLinear.setVisibility(View.VISIBLE);

                if (models.get(position).getProductOffer().getOfferType().equalsIgnoreCase("Percentage"))
                    holder.binding.productDiscount.setText(models.get(position).getProductOffer().getOfferAmount() + " % OFF");
                else
                    holder.binding.productDiscount.setText(models.get(position).getProductOffer().getOfferAmount() + "  OFF");


                holder.binding.productOffer.setText(models.get(position).getProductOffer().getOfferName());

                float amount = Float.parseFloat(models.get(position).getProductOffer().getOfferAmount());
                // amount = amount * q;

                if (models.get(position).getProductOffer().getOfferType().equalsIgnoreCase("Fixed")) {

                    holder.binding.offerAmount.setText(models.get(position).getProductOffer().getOfferAmount() + "");

                    finalPrice = finalPrice - amount * q;
                    holder.binding.afterOfferAmount.setText("- " + amount * q);

                    try {
                        float pr = Float.parseFloat(models.get(position).getProductFinalAmount());
                        float oa = Float.parseFloat(models.get(position).getProductOffer().getOfferAmount());
                        float quantity = Float.parseFloat(models.get(position).getProductQty());
                        float am = pr - (oa*quantity);
                        holder.binding.totalAmount.setText(String.valueOf(am));
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(e);
                    }
                } else {

                    holder.binding.offerAmount.setText(models.get(position).getProductOffer().getOfferAmount() + " %");

                    float price = finalPrice * amount;
                    price = price / 100;
                    finalPrice = finalPrice - price;
                    BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
                    holder.binding.afterOfferAmount.setText("- " + String.valueOf(bd.floatValue()));

                    try {
                        float pr = Float.parseFloat(models.get(position).getProductFinalAmount());
                        float oa = Float.parseFloat(models.get(position).getProductOffer().getOfferAmount());
                        float quantity = Float.parseFloat(models.get(position).getProductQty());
                        float am = pr - (oa*quantity);
                        holder.binding.totalAmount.setText(String.valueOf(am));
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(e);
                    }


                }

                tax = finalPrice * 18;
                tax = tax / 100;

            } else {
                tax = finalPrice * 18;
                tax = tax / 100;
            }

            BigDecimal bd = new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP);
            BigDecimal bdPrice = new BigDecimal(finalPrice + tax).setScale(2, RoundingMode.HALF_UP);

            holder.binding.totalTax.setText(String.valueOf(bd.floatValue()));
           // holder.binding.totalAmount.setText(String.valueOf(bdPrice.floatValue()));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Glide.with(context).load(BaseUrls.ImageBaseUrl + models.get(position).getProductImage())
                .placeholder(R.drawable.model_1).into(holder.binding.productImage);

        holder.binding.productMinus.setOnClickListener(view -> {
            int q = Integer.parseInt(models.get(position).getProductQty());
            if (q > 0) q = q - 1;
            updateCart(q, models.get(position).getCartId());
            models.get(position).setProductQty(String.valueOf(q));
            notifyItemChanged(position);
            refreshCart.onRefresh();
        });

        holder.binding.productPlus.setOnClickListener(view -> {
            int q = Integer.parseInt(models.get(position).getProductQty());
            if (q >= 0) q = q + 1;
            updateCart(q, models.get(position).getCartId());
            models.get(position).setProductQty(String.valueOf(q));
            notifyItemChanged(position);
            refreshCart.onRefresh();
        });

        holder.binding.productDelete.setOnClickListener(view -> {
            String id = models.get(position).getCartId();
            notifyItemRemoved(position);
            notifyItemRangeChanged(0, models.size() - 1);
            models.remove(position);
            deleteCartItem(id);
            refreshCart.onRefresh();
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        CartItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartItemLayoutBinding.bind(itemView);
        }
    }

}

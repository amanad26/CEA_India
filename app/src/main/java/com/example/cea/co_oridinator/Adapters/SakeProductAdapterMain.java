package com.example.cea.co_oridinator.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog;
import com.developer.kalert.KAlertDialog;
import com.example.cea.R;
import com.example.cea.co_oridinator.AddWalkingSpinnerInterface;
import com.example.cea.co_oridinator.Models.ProductModelCo;
import com.example.cea.databinding.SaleProductLayoutBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SakeProductAdapterMain extends RecyclerView.Adapter<SakeProductAdapterMain.ViewHolder> {

    Context context;
    List<ProductModelCo.Datum> models;
    AddWalkingSpinnerInterface addWalkingSpinnerInterface;


    public SakeProductAdapterMain(Context context, List<ProductModelCo.Datum> models, AddWalkingSpinnerInterface addWalkingSpinnerInterface) {
        this.context = context;
        this.models = models;
        this.addWalkingSpinnerInterface = addWalkingSpinnerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_product_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.productName.setText(models.get(position).getProductName());
        holder.binding.productMOP.setText(models.get(position).getProductMop());
//        holder.binding.productMRP.setText(models.get(position).getProductFinalAmount());
////        holder.binding.productMRP.setText(models.get(position).getProductPrice());
//        holder.binding.productTotal.setText(models.get(position).getProductFinalAmount());
        holder.binding.productDiscount.setText(models.get(position).getProductDiscount());
        holder.binding.productOffer.setText(models.get(position).getProduct_offer());

        if (!models.get(position).getProduct_offer().equalsIgnoreCase("0")) {
            float price = Float.parseFloat(models.get(position).getProductFinalAmount());
            price = price - Float.parseFloat(models.get(position).getProduct_offer());
            models.get(position).setOriginalPrice(String.valueOf(price));
        } else {
            models.get(position).setOriginalPrice(models.get(position).getProductFinalAmount());
        }

        holder.binding.productMRP.setText(models.get(position).getProductMrp());
//        holder.binding.productMRP.setText(models.get(position).getProductPrice());

//        float price = Float.parseFloat(models.get(position).getOriginalPrice());
//        if(!models.get(position).getProduct_offer().equalsIgnoreCase("0")) {
//            price = price - Float.parseFloat(models.get(position).getProduct_offer());
//            holder.binding.productTotal.setText(String.valueOf(price));
//        }else {
//            holder.binding.productTotal.setText(models.get(position).getOriginalPrice());
//        }

        holder.binding.productTotal.setText(models.get(position).getOriginalPrice());
//        models.get(position).setOriginalPrice(models.get(position).getProductFinalAmount());
        holder.binding.productDiscount.setOnClickListener(view -> updateDiscount(models.get(position).getProductMaxDiscount(), position, holder));


        holder.itemView.setOnClickListener(view -> deleteItemDialog(position));

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SaleProductLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SaleProductLayoutBinding.bind(itemView);
        }
    }

    private void deleteItemDialog(int pos) {
        new KAlertDialog(context, KAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("you want to delete this product ?")
                .setCancelClickListener("No!", new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismissWithAnimation();
                    }
                })
                .setConfirmClickListener("Yes,delete it!", new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        addWalkingSpinnerInterface.onDelete(pos);
                    }
                }).show();
    }


    private void updateDiscount(String productMaxDiscount, int pos, ViewHolder holder) {
        RoundedBottomSheetDialog bottomSheetDialog = new RoundedBottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.update_price_layout);

        CardView update_btn = bottomSheetDialog.findViewById(R.id.update_btn);
        EditText wallet_amount = bottomSheetDialog.findViewById(R.id.wallet_amount);

        wallet_amount.setText(productMaxDiscount);

        ImageView ic_back = bottomSheetDialog.findViewById(R.id.ic_back);
        ic_back.setOnClickListener(View -> bottomSheetDialog.dismiss());

        update_btn.setOnClickListener(view -> {
            if (wallet_amount.getText().toString().equalsIgnoreCase("")) {
                wallet_amount.setError("Enter Discount...!");
                wallet_amount.requestFocus();
            } else {
                int dis = Integer.parseInt(wallet_amount.getText().toString());
                if (dis > Integer.parseInt(productMaxDiscount)) {
                    wallet_amount.setError("Max Discount is " + productMaxDiscount);
                } else {
                    bottomSheetDialog.dismiss();
                    models.get(pos).setProductDiscount(wallet_amount.getText().toString());

                    int finalPrice = Integer.parseInt(models.get(pos).getProductMrp());
                    finalPrice = finalPrice - Integer.parseInt(wallet_amount.getText().toString());
                    models.get(pos).setProductFinalAmount(String.valueOf(finalPrice));

                    holder.binding.productDiscount.setText(wallet_amount.getText().toString());
                    holder.binding.productTotal.setText(String.valueOf(finalPrice));

                    addWalkingSpinnerInterface.onChange(pos, String.valueOf(finalPrice), wallet_amount.getText().toString());
                   notifyItemChanged(pos);
                }
            }
        });

        bottomSheetDialog.show();

    }
}
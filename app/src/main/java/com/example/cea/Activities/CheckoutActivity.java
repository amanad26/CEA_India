package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Adapters.CartAdapter;
import com.example.cea.Adapters.CheckoutAdapter;
import com.example.cea.Apis.RefreshCart;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.CartListModel;
import com.example.cea.Models.CheckoutModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.databinding.ActivityCheckoutBinding;
import com.example.cea.databinding.PaymentConfirmLayoutBinding;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity implements RefreshCart {

    ActivityCheckoutBinding binding;
    CheckoutActivity activity;
    Session session;
    private float totalAmount = 0;
    private float totalAmountNew = 0;
    private float totalTax = 0;
    ProgressDialog pd;
    boolean isClosed = true;
    float finalPrice = 0;
    float tax = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);
        pd = new ProgressDialog(activity);

        binding.addressTv.setText(session.getName() + " " + session.getLastname() + ", " + session.getAddress() + ", " + session.getPincode() + ", " + session.getMobile());

        binding.ivBack.setOnClickListener(view -> onBackPressed());

        binding.checkOutBtn.setOnClickListener(view -> startActivity(new Intent(activity, BillingActivity.class)));

        binding.checkOutBtn.setOnClickListener(view -> {
            if (finalPrice > 0) {
                showPaymentConfirmDialog(finalPrice);
            }
        });

    }

    private void showPaymentConfirmDialog(float price) {

        final Dialog dialog = new Dialog(activity);
        PaymentConfirmLayoutBinding binding = PaymentConfirmLayoutBinding.inflate(getLayoutInflater());
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().setDimAmount(0.80f);

        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        dialog.show();

        binding.walletAmount.setText(session.getWalletAmount());
        binding.userName.setText(session.getName() + " " + session.getLastname());
        binding.totalAmount.setText(String.valueOf(finalPrice));

        float walletPrice = Float.parseFloat(session.getWalletAmount());
        float finalPrice = price - walletPrice;

        binding.payableAmount.setText(String.valueOf(finalPrice));

        binding.placeOrder.setOnClickListener(view -> {
            startActivity(new Intent(activity, PaymentActivity.class).putExtra("price", String.valueOf(finalPrice)));
            dialog.dismiss();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCheckoutItems();
        getCheckoutItemsNotAvailable();
    }

    private void getCheckoutItems() {
        pd.show();
        RetrofitClient.getClient(activity).getCheckOutProduct(session.getUserId(), session.getPincode()).enqueue(new Callback<CheckoutModel>() {
            @Override
            public void onResponse(@NonNull Call<CheckoutModel> call, @NonNull Response<CheckoutModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            if (response.body().getData().size() != 0) {
                                binding.itemsList.setLayoutManager(new LinearLayoutManager(activity));
                                binding.itemsList.setAdapter(new CheckoutAdapter(activity, response.body().getData(), CheckoutActivity.this, false));

                                totalAmount = 0;
                                totalAmountNew  =  0 ;
                                totalTax = 0  ;

                                for (int i = 0; i < response.body().getData().size(); i++) {

                                    float a = Float.parseFloat(response.body().getData().get(i).getProductFinalAmount());
                                    float q = Float.parseFloat(response.body().getData().get(i).getProductQty());
                                    float t = Float.parseFloat(response.body().getData().get(i).getProductTaxAmount());
                                    float p ;
//                                    float p = a * q;

                                    totalTax  = totalTax + t ;

                                    // totalAmountNew = totalAmount + Float.parseFloat(response.body().getData().get(i).getProductFinalAmount());
                                    p =  Float.parseFloat(response.body().getData().get(i).getProductFinalAmount());
                                    Log.e("TAG", "onResponse:  Original Price " + i + +p + " q " + q);
                                    if (response.body().getData().get(i).getProductOffer() != null) {

                                        float amount = Float.parseFloat(response.body().getData().get(i).getProductOffer().getOfferAmount());

                                        if (response.body().getData().get(i).getProductOffer().getOfferType().equalsIgnoreCase("Fixed")) {
                                            amount = amount * q;
                                            p = p - amount;
                                        } else {
                                            float price = p * amount;
                                            price = price / 100;
                                            p = p - price;
                                            Log.e("TAG", "onResponse: Price pos  " + i + "  " + price);


                                        }
                                    }

                                    totalAmountNew = totalAmountNew + p ;
                                    totalAmount = totalAmount + p;
                                }

                                tax = totalAmount * 18;
                                tax = tax / 100;
                                totalAmount = totalAmount + tax;

                                BigDecimal bd = new BigDecimal(totalAmountNew).setScale(2, RoundingMode.HALF_UP);
//                                BigDecimal bd = new BigDecimal(totalAmount).setScale(2, RoundingMode.HALF_UP);
                                BigDecimal bdTax = new BigDecimal(totalTax).setScale(2, RoundingMode.HALF_UP);
                                finalPrice = 0;
                                finalPrice = bd.floatValue();
                                binding.totalPrice.setText(String.valueOf(bd.floatValue()));
                                binding.tax.setText("Tax " + String.valueOf(bdTax.floatValue()));
                                binding.totalItems.setText(String.valueOf(response.body().getData().size()) + " Items");
                                tax = 0;
                            } else {
                                binding.noData.setVisibility(View.VISIBLE);
                                binding.itemsList.setVisibility(View.GONE);
                            }


                        } else {
                            binding.noData.setVisibility(View.VISIBLE);
                            binding.itemsList.setVisibility(View.GONE);
                        }

            }

            @Override
            public void onFailure(@NonNull Call<CheckoutModel> call, @NonNull Throwable t) {
                pd.dismiss();
            }
        });
    }

    private void getCheckoutItemsNotAvailable() {
        pd.show();
        RetrofitClient.getClient(activity).getCheckOutProductNotAvailable(session.getUserId(), session.getPincode()).enqueue(new Callback<CheckoutModel>() {
            @Override
            public void onResponse(@NonNull Call<CheckoutModel> call, @NonNull Response<CheckoutModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            if (response.body().getData().size() != 0) {
                                binding.notAvailableProductLinear.setVisibility(View.VISIBLE);
                                binding.notAvailabelProduct.setLayoutManager(new LinearLayoutManager(activity));
                                binding.notAvailabelProduct.setAdapter(new CheckoutAdapter(activity, response.body().getData(), CheckoutActivity.this, true));

                            /*
                             totalAmount = 0;
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    float p = Float.parseFloat(response.body().getData().get(i).getProductFinalAmount());
                                    if (response.body().getData().get(i).getProductOffer() != null) {

                                        float amount = Float.parseFloat(response.body().getData().get(i).getProductOffer().getOfferAmount());
                                        float price = p * amount;
                                        price = price / 100;
                                        p = p - price;
                                    }

                                    totalAmount = totalAmount + p;
                                }
*/

                                binding.iconArrow.setOnClickListener(view -> {
                                    if (isClosed) {
                                        binding.notAvailabelProduct.setVisibility(View.VISIBLE);
                                        isClosed = false;
                                        binding.iconArrow.setImageResource(R.drawable.ic_arrow_up);
                                    } else {
                                        binding.notAvailabelProduct.setVisibility(View.GONE);
                                        isClosed = true;
                                        binding.iconArrow.setImageResource(R.drawable.ic_arrow_down);
                                    }
                                });

                            }


                        }

            }

            @Override
            public void onFailure(@NonNull Call<CheckoutModel> call, @NonNull Throwable t) {
                pd.dismiss();
            }
        });
    }


    @Override
    public List<CartListModel.Datum> onRemove(Context context, String userId) {
        return null;
    }

    @Override
    public void onRefresh() {
        getCheckoutItems();
    }
}
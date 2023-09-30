package com.example.cea.co_oridinator.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.R;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.co_oridinator.Adapters.InvoiceListAdapter;
import com.example.cea.co_oridinator.Adapters.SaleProductAdapter;
import com.example.cea.co_oridinator.Models.InvoiceListModel;
import com.example.cea.co_oridinator.Models.MySalesModel;
import com.example.cea.co_oridinator.Models.SaleDetailsModel;
import com.example.cea.databinding.ActivityAddSaleBinding;
import com.example.cea.databinding.ActivitySaleDetailsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleDetailsActivity extends AppCompatActivity {

    ActivitySaleDetailsBinding binding;
    Activity activity;
    ProgressDialog pd;

    String saleId = "";
    boolean isCustomerOpen = false, isBankOpen = false, isPaymentOpen = false, isFirmOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaleDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        saleId = getIntent().getStringExtra("sale_id");

        activity = this;
        pd = new ProgressDialog(activity);

        binding.backBtn.setOnClickListener(view -> onBackPressed());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSaleDetails();
        getFirmData();

    }

    private void getSaleDetails() {
        pd.show();
        RetrofitClient.getClient(activity).getMySaleDetails(saleId).enqueue(new Callback<SaleDetailsModel>() {
            @Override
            public void onResponse(@NonNull Call<SaleDetailsModel> call, @NonNull Response<SaleDetailsModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            SaleDetailsModel.Datum data = response.body().getData().get(0);
                            //  binding.editName.setText(data.getC() + " " + data.getCustomerLname());
                            binding.editBankName.setText(data.getBankName());
                            binding.editAccountNumber.setText(data.getBankAcNo());
                            binding.editIfscCode.setText(data.getBankIfscCode());
                            binding.payAmount.setText(data.getPayAmount());
                            binding.totalAmount.setText(data.getTotalOrderAmount());
                            binding.totalDiscount.setText(data.getAdditionalDiscount());
                            binding.saleProductRecy.setLayoutManager(new LinearLayoutManager(activity));
                            binding.saleProductRecy.setAdapter(new SaleProductAdapter(activity, data.getProductList()));

                            binding.edtName.setText(data.getCustomerFname() + data.getCustomerLname());
                            binding.edtAddress.setText(data.getCustomerAddress());
                            binding.edtMobile.setText(data.getCustomerMobileNo());
                            binding.edtWhatsapp.setText(data.getCustomerWhatsppNo());

                            binding.customerLin.setOnClickListener(view -> {
                                if (isCustomerOpen) {
                                    binding.customerDetailsLinear.setVisibility(View.GONE);
                                    isCustomerOpen = false;
                                    binding.downArrowImage1.setImageResource(R.drawable.ic_arrow_down);
                                } else {
                                    isCustomerOpen = true;
                                    binding.customerDetailsLinear.setVisibility(View.VISIBLE);
                                    binding.downArrowImage1.setImageResource(R.drawable.ic_arrow_up);
                                }
                            });


                            binding.firmWaiseInvoiceLinear.setOnClickListener(view -> {
                                if (isFirmOpen) {
                                    binding.firmWiaseData.setVisibility(View.GONE);
                                    isFirmOpen = false;
                                    binding.downArrowImage4.setImageResource(R.drawable.ic_arrow_down);
                                } else {
                                    isFirmOpen = true;
                                    binding.firmWiaseData.setVisibility(View.VISIBLE);
                                    binding.downArrowImage4.setImageResource(R.drawable.ic_arrow_up);
                                }
                            });


                            if (data.getPaymentType().equalsIgnoreCase("online")) {
                                binding.linearPayment.setVisibility(View.VISIBLE);
                                Picasso.get().load(BaseUrls.ImageBaseUrl + data.getPaymentReceipt()).into(binding.paymentReiept);
                                binding.paymentReiept.setOnClickListener(view -> showQRDialog(BaseUrls.ImageBaseUrl + data.getPaymentReceipt()));

                                binding.linearPayment.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (isPaymentOpen) {
                                            binding.onlineMode.setVisibility(View.GONE);
                                            isPaymentOpen = false;
                                            binding.downArrowImagePayment3.setImageResource(R.drawable.ic_arrow_down);
                                        } else {
                                            isPaymentOpen = true;
                                            binding.onlineMode.setVisibility(View.VISIBLE);
                                            binding.downArrowImagePayment3.setImageResource(R.drawable.ic_arrow_up);
                                        }
                                    }
                                });

                            } else {
                                binding.linearBank.setVisibility(View.VISIBLE);

                                binding.linearBank.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (isBankOpen) {
                                            binding.offlineMode.setVisibility(View.GONE);
                                            isBankOpen = false;
                                            binding.downArrowImageBank2.setImageResource(R.drawable.ic_arrow_down);
                                        } else {
                                            isBankOpen = true;
                                            binding.offlineMode.setVisibility(View.VISIBLE);
                                            binding.downArrowImageBank2.setImageResource(R.drawable.ic_arrow_up);
                                        }
                                    }
                                });

                            }

                        }
            }

            @Override
            public void onFailure(@NonNull Call<SaleDetailsModel> call, @NonNull Throwable t) {
                pd.dismiss();
            }
        });

    }

    private void showQRDialog(String url) {

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.full_image_view);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        ImageView icClose = dialog.findViewById(R.id.icClose);
        ImageView imageView = dialog.findViewById(R.id.imageView);
        icClose.setOnClickListener(View -> dialog.dismiss());

        Picasso.get().load(url).into(imageView);
        dialog.show();

    }


    private void getFirmData() {


//        RetrofitClient.getClient(activity).getFirmInvoice("35").enqueue(new Callback<InvoiceListModel>() {
        RetrofitClient.getClient(activity).getFirmInvoice(saleId).enqueue(new Callback<InvoiceListModel>() {
            @Override
            public void onResponse(Call<InvoiceListModel> call, Response<InvoiceListModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            if (response.body().getData().size() != 0) {

                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                                binding.firmRecyCler.setLayoutManager(linearLayoutManager);
                                binding.firmRecyCler.setAdapter(new InvoiceListAdapter(activity, response.body().getData()));

                            } else {
                                binding.noFirmData.setVisibility(View.VISIBLE);
                            }
                        } else {
                            binding.noFirmData.setVisibility(View.VISIBLE);
                        }
            }

            @Override
            public void onFailure(Call<InvoiceListModel> call, Throwable t) {

            }
        });


    }
}
package com.example.cea.co_oridinator.Fragments;


import static android.app.Activity.RESULT_OK;

import static com.example.cea.Activities.QrScanActivity.qrFinalCode;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.developer.kalert.KAlertDialog;
import com.example.cea.Activities.QrScanActivity;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.co_oridinator.Adapters.AddWallkingSpinnerAdapter;
import com.example.cea.co_oridinator.Adapters.WallkingAdapter;
import com.example.cea.co_oridinator.Models.AddWallkingCustomerModel;
import com.example.cea.co_oridinator.Models.OnItemDelete;
import com.example.cea.co_oridinator.Models.ProductModelCo;
import com.example.cea.co_oridinator.Models.QrCodeProductModel;
import com.example.cea.co_oridinator.Models.WallkingModelCo;
import com.example.cea.databinding.FragmentCoWalkingBinding;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.leo.searchablespinner.SearchableSpinner;
import com.leo.searchablespinner.interfaces.OnItemSelectListener;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Co_WalkingFragment extends Fragment implements OnItemDelete {

    FragmentCoWalkingBinding binding;
    Activity activity;
    private List<ProductModelCo.Datum> allProductList = new ArrayList<>();
    public static List<ProductModelCo.Datum> selectedProduct = new ArrayList<>();
    private Session session;
    private ArrayList<String> productListNames = new ArrayList<>();
    int selectedPos = -1;
    int count = 1;
    ProgressDialog pd;
    AddWallkingSpinnerAdapter adpter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCoWalkingBinding.inflate(getLayoutInflater());
        activity = requireActivity();
        session = new Session(activity);
        pd = new ProgressDialog(activity);
        init();
        return binding.getRoot();
    }

    private void init() {
        selectedProduct.clear();
        binding.addWallking.setOnClickListener(view -> showDialog());
    }


    private void showSearchableSpinner(TextView textView) {


        final SearchableSpinner searchableSpinner = new SearchableSpinner(activity);
        //Optional Parameters
        searchableSpinner.setWindowTitle("SEARCHABLE SPINNER");

        //Setting up list items for spinner
        searchableSpinner.setSpinnerListItems(productListNames);

        searchableSpinner.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void setOnItemSelectListener(int k, @NonNull String s) {
                ProductModelCo.Datum data = allProductList.get(k);
                boolean isAdded = false;
                for (int i = 0; i < selectedProduct.size(); i++) {
                    if (selectedProduct.get(i).getProductId().equalsIgnoreCase(data.getProductId())) {
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded) {
                    selectedProduct.add(data);
                    adpter.notifyDataSetChanged();
                    Log.e("TAG", "onResponse: Addedd");
                } else {
                    Toast.makeText(activity, "Product Already Added..!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        searchableSpinner.show();
    }

    private void showDialog() {
        selectedPos = -1;
        count = 1;
        selectedProduct.clear();

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.add_walking_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        LinearLayout submit = dialog.findViewById(R.id.submit_linear);
        LinearLayout proLinear = dialog.findViewById(R.id.proLinear);
        LinearLayout cancle_linear = dialog.findViewById(R.id.cancle_linear);
        RecyclerView productSelectRecycler = dialog.findViewById(R.id.productSelectRecycler);
        MaterialSpinner spinner = dialog.findViewById(R.id.spinner);
        ImageView add_btn = dialog.findViewById(R.id.add_btn);
        CircleImageView scanQR = dialog.findViewById(R.id.scanQR);
        EditText edit_address = dialog.findViewById(R.id.edit_address);
        EditText edit_whatsappNumber = dialog.findViewById(R.id.edit_whatsappNumber);
        EditText edit_number = dialog.findViewById(R.id.edit_number);
        EditText edit_name = dialog.findViewById(R.id.edit_name);
        TextView nameOfProduct = dialog.findViewById(R.id.nameOfProduct);

        scanQR.setOnClickListener(view -> {
            Intent intent = new Intent(activity, QrScanActivity.class);
            intent.putExtra("activity", "2");
            startActivityForResult(intent, 1000);
        });

        proLinear.setOnClickListener(view -> showSearchableSpinner(nameOfProduct));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (activity, android.R.layout.simple_spinner_item,
                        productListNames); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener((view, position, id, item) -> {

            ProductModelCo.Datum data = allProductList.get(position);
            boolean isAdded = false;
            for (int i = 0; i < selectedProduct.size(); i++) {
                if (selectedProduct.get(i).getProductId().equalsIgnoreCase(data.getProductId())) {
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                selectedProduct.add(data);
                adpter.notifyDataSetChanged();
                Log.e("TAG", "onResponse: Addedd");

                ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>
                        (activity, android.R.layout.simple_spinner_item,
                                productListNames); //selected item will look like a spinner set from XML
                spinnerArrayAdapter2.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);

                spinner.setAdapter(spinnerArrayAdapter2);
//                spinner.setAdapter(spinnerArrayAdapter);
            } else {
                Toast.makeText(activity, "Product Already Added..!", Toast.LENGTH_SHORT).show();
            }

        });

        submit.setOnClickListener(view -> {
            if (isValidate(edit_address,
                    edit_name,
                    edit_number,
                    edit_whatsappNumber)) {

                addCustomerWallking(edit_address,
                        edit_name,
                        edit_number,
                        edit_whatsappNumber);
                dialog.dismiss();
            }

        });
        cancle_linear.setOnClickListener(View -> dialog.dismiss());

        List<String> names = new ArrayList<>();
        names.add(String.valueOf(count));

        adpter = new AddWallkingSpinnerAdapter(activity, selectedProduct, this);

        productSelectRecycler.setLayoutManager(new LinearLayoutManager(activity));
        productSelectRecycler.setAdapter(adpter);

        add_btn.setOnClickListener(view -> {
            if (allProductList.size() == count) {
                Toast.makeText(activity, "Max " + String.valueOf(count) + " Product can selectable", Toast.LENGTH_SHORT).show();
            } else {
                // names.add(String.valueOf(count + 1));
                adpter.notifyDataSetChanged();
                count++;
            }

        });


        dialog.show();

    }

    private void addCustomerWallking(EditText edit_address, EditText edit_name, EditText edit_number, EditText edit_whatsappNumber) {

        ProgressDialog pd = new ProgressDialog(activity);
        pd.show();


        String text = "[";

        for (int i = 0; i < selectedProduct.size(); i++) {

            text = text + "{";

            text = text + "\"" + "product_id" + "\"" + ":" + "\"" + selectedProduct.get(i).getProductId() + "\"" + "}";

            if (i != selectedProduct.size() - 1)
                text = text + ",";
        }

        text = text + "]";

        RetrofitClient.getClient(activity).addCustomerWallking(
                session.getUserId(),
                edit_name.getText().toString(),
                edit_number.getText().toString(),
                edit_whatsappNumber.getText().toString(),
                edit_address.getText().toString(),
                text
        ).enqueue(new Callback<AddWallkingCustomerModel>() {
            @Override
            public void onResponse(@NonNull Call<AddWallkingCustomerModel> call, @NonNull Response<AddWallkingCustomerModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            //Toast.makeText(activity, "Wallking Added...", Toast.LENGTH_SHORT).show();
                            showSuccessDialog();
                        }


            }

            @Override
            public void onFailure(@NonNull Call<AddWallkingCustomerModel> call, @NonNull Throwable t) {
                pd.dismiss();
                Toast.makeText(activity, "Server Not Respond...", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void showSuccessDialog() {

        new KAlertDialog(activity, KAlertDialog.SUCCESS_TYPE)
                .setTitleText("Congratulation!")
                .setContentText("Wallking Added..")
                .setConfirmText("Okay")
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismissWithAnimation();
                        getWallking();
                    }
                })
                .show();

    }


    private boolean isValidate(EditText address, EditText name, EditText number, EditText whatsapp) {

        if (name.getText().toString().equalsIgnoreCase("")) {
            name.setError("Enter Customer Name");
            name.requestFocus();
            return false;
        } else if (number.getText().toString().equalsIgnoreCase("")) {
            number.setError("Enter Customer Mobile");
            number.requestFocus();
            return false;
        } else if (whatsapp.getText().toString().equalsIgnoreCase("")) {
            whatsapp.setError("Enter Customer Whatsapp Mobile");
            whatsapp.requestFocus();
            return false;
        } else if (address.getText().toString().equalsIgnoreCase("")) {
            address.setError("Enter Customer Address");
            address.requestFocus();
            return false;
        } else if (selectedProduct.size() == 0) {
            Toast.makeText(activity, "Select Product..!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getNewestProduct();
        getWallking();

        try {
            if (!qrFinalCode.equalsIgnoreCase("")) {
                getProductByQrCode(qrFinalCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getNewestProduct() {
        RetrofitClient.getClient(activity).getAllProducts().enqueue(new Callback<ProductModelCo>() {
            @Override
            public void onResponse(@NonNull Call<ProductModelCo> call, @NonNull Response<ProductModelCo> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            allProductList.clear();
                            if (response.body().getData().size() != 0) {
                                allProductList = response.body().getData();
                                productListNames.clear();
                                for (int i = 0; i < allProductList.size(); i++) {
                                    productListNames.add(allProductList.get(i).getProductQrcode() + " " + allProductList.get(i).getProductName());
                                }

                            }
                        }
            }

            @Override
            public void onFailure(@NonNull Call<ProductModelCo> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server Not Respond..!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getWallking() {
        RetrofitClient.getClient(activity).getCustomerWallking(session.getUserId()).enqueue(new Callback<WallkingModelCo>() {
            @Override
            public void onResponse(@NonNull Call<WallkingModelCo> call, @NonNull Response<WallkingModelCo> response) {
                binding.progress1.setVisibility(View.GONE);
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            if (response.body().getData().size() != 0) {
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                                linearLayoutManager.setReverseLayout(true);
                                linearLayoutManager.setStackFromEnd(true);
                                binding.homeRecycler.setLayoutManager(linearLayoutManager);
                                binding.homeRecycler.setAdapter(new WallkingAdapter(activity, response.body().getData()));
                            } else {
                                binding.noData.setVisibility(View.GONE);
                            }

                        } else {
                            binding.noData.setVisibility(View.GONE);
                        }

            }

            @Override
            public void onFailure(@NonNull Call<WallkingModelCo> call, @NonNull Throwable t) {
                binding.progress1.setVisibility(View.GONE);
                Toast.makeText(activity, "Server Not Respond..!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProductByQrCode(String code) {
        pd.show();
        RetrofitClient.getClient(activity).getProductByQrCode(code).enqueue(new Callback<QrCodeProductModel>() {
            @Override
            public void onResponse(@NonNull Call<QrCodeProductModel> call, @NonNull Response<QrCodeProductModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            QrCodeProductModel.Datum data2 = response.body().getData().get(0);
                            ProductModelCo.Datum data = new ProductModelCo.Datum(
                                    data2.getStockId(),
                                    data2.getFirmId(),
                                    data2.getInvoiceNo(),
                                    data2.getCommonStatus(),
                                    data2.getPurchaseStatus(),
                                    data2.getStoreStatus(),
                                    data2.getStockCreatedDate(),
                                    data2.getPurchaseReturnDate(),
                                    data2.getStoreAssignDate(),
                                    data2.getStoreReturnDate(),
                                    data2.getSaleCreatedDate(),
                                    data2.getSaleReturnDate(),
                                    data2.getVendorId(),
                                    data2.getProductId(),
                                    data2.getProductName(),
                                    data2.getProductType(),
                                    data2.getProductSubType(),
                                    data2.getShowAppStatus(),
                                    data2.getPincodeNumber(),
                                    data2.getCategoryId(),
                                    data2.getCategoryIdLevel(),
                                    data2.getPurchaseId(),
                                    data2.getPurchaseProductId(),
                                    data2.getProductQrcode(),
                                    data2.getProductPrice(),
                                    data2.getProductSalePrice(),
                                    data2.getProductMinSalePrice(),
                                    data2.getProductMaxDiscount(),
                                    data2.getProductSupportNo(),
                                    data2.getBrandId(),
                                    data2.getModelId(),
                                    data2.getProductSpecification(),
                                    data2.getProductServices(),
                                    data2.getProductTax(),
                                    data2.getProductHsn(),
                                    data2.getProductQty(),
                                    data2.getStoreId(),
                                    data2.getStockOutId(),
                                    data2.getSaleUniqueInvoiceNo(),
                                    data2.getSaleInvoicePath(),
                                    data2.getSaleId(),
                                    data2.getSaleProductId(),
                                    data2.getReturnId(),
                                    data2.getIsPrinted(),
                                    data2.getBookingUniqueInvoiceNo(),
                                    data2.getInvoiceNo(),
                                    data2.getBookingId(),
                                    data2.getSaleBookingType(),
                                    data2.getProductDetailJson(),
                                    data2.getProductMop(),
                                    data2.getProductSerialNo(),
                                    data2.getProductTaxAmount(),
                                    data2.getProductFinalAmount(),
                                    data2.getSpecificationName(),
                                    data2.getProductDiscount());

                            selectedProduct.size();
                            boolean isAdded = false;
                            for (int i = 0; i < selectedProduct.size(); i++) {
                                if (selectedProduct.get(i).getProductId().equalsIgnoreCase(data.getProductId())) {
                                    isAdded = true;
                                    break;
                                }
                            }
                            if (!isAdded) {
                                selectedProduct.add(data);
                                adpter.notifyDataSetChanged();
                                //calculatePrice();
                                Log.e("TAG", "onResponse: Addedd");
                            } else {
                                Toast.makeText(activity, "Product Already Added..!", Toast.LENGTH_SHORT).show();
                            }

                            qrFinalCode = "";
                        } else {
                            Toast.makeText(activity, "Product Not Available..!", Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<QrCodeProductModel> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Failed...", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            if (data != null) {
//             getProductByQrCode("16916643662");
                getProductByQrCode(data.getStringExtra("resultKey"));
                Log.e("TAG", "onActivityResult: Data " + data.getStringExtra("resultKey"));
            }
        }
    }

    @Override
    public void onDelete(int pos) {
        selectedProduct.remove(pos);
        adpter.notifyDataSetChanged();
    }
}
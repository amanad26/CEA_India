package com.example.cea.co_oridinator.Activities;

import static com.example.cea.Activities.EditProfileActivity.bitmapToFile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.developer.kalert.KAlertDialog;
import com.example.cea.Activities.QrScanActivity;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.Utills.searchablespinner.OnItemSelectListener;
import com.example.cea.Utills.searchablespinner.SearchableSpinner;
import com.example.cea.co_oridinator.Adapters.SakeProductAdapterMain;
import com.example.cea.co_oridinator.AddWalkingSpinnerInterface;
import com.example.cea.co_oridinator.Models.AddCustomerModel;
import com.example.cea.co_oridinator.Models.AddSaleModel;
import com.example.cea.co_oridinator.Models.ProductModelCo;
import com.example.cea.co_oridinator.Models.QrCodeProductModel;
import com.example.cea.databinding.ActivityAddSaleBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSaleActivity extends AppCompatActivity implements AddWalkingSpinnerInterface {


    ActivityAddSaleBinding binding;
    Activity activity;
    ProgressDialog pd;
    private File paymentInvoice = null;
    private Uri filepath;
    String paymentInvoiceName = "";
    Bitmap bitmap;
    private final int gallery_req_code = 200;
    private List<ProductModelCo.Datum> allProductList = new ArrayList<>();
    private List<ProductModelCo.Datum> selectedProduct = new ArrayList<>();
    private List<ProductModelCo.Datum> modelForPrice = new ArrayList<>();
    private Session session;
    private ArrayList<String> productListNames = new ArrayList<>();
    private List<AddCustomerModel.Datum> customerList = new ArrayList<>();
    private AddCustomerModel.Datum selectedCustomer = null;
    private List<String> customerNamesList = new ArrayList<>();
    private SakeProductAdapterMain adapter;
    String selectedPaymentMode = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddSaleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        pd = new ProgressDialog(activity);
        session = new Session(activity);

        binding.backBtn.setOnClickListener(View -> onBackPressed());
        binding.addCustomer.setOnClickListener(View -> showDialog());

        adapter = new SakeProductAdapterMain(activity, selectedProduct, this);
        setUpRecyclerView();

        binding.scanQr.setOnClickListener(view -> {
            Intent intent = new Intent(activity, QrScanActivity.class);
            intent.putExtra("activity", "1");
            startActivityForResult(intent, 1000);
        });

        binding.chosePaymetImage.setOnClickListener(view -> {

            ImagePicker.with(this)
                    .crop()//Crop image(Optional), Check Customization for more option
                    .cameraOnly()
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start(gallery_req_code);

//            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//            i.setType("image/*");
//            startActivityForResult(i, gallery_req_code);
        });

        binding.proLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchableSpinner();
            }
        });

        binding.addSale.setOnClickListener(view -> {

            if (selectedProduct.size() == 0) {
                Toast.makeText(activity, "Select Product", Toast.LENGTH_SHORT).show();
            } else if (selectedCustomer == null) {
                Toast.makeText(activity, "Select Customer", Toast.LENGTH_SHORT).show();
            } else if (selectedPaymentMode.equalsIgnoreCase("")) {
                Toast.makeText(activity, "Select Payment Mode", Toast.LENGTH_SHORT).show();
            } else {

                if (selectedPaymentMode.equalsIgnoreCase("online")) {
                    if (paymentInvoice == null) {
                        Toast.makeText(activity, "Select Payment Receipt..! ", Toast.LENGTH_SHORT).show();
                    } else {
                        callAddSaleApis(0);
                    }
                } else {
                    callAddSaleApis(1);
                }
            }


        });

        binding.viewQR.setOnClickListener(view ->

                showQRDialog(1));

    }

    private void callAddSaleApis(int count) {
        pd.show();

        String text = "[";

        for (int i = 0; i < selectedProduct.size(); i++) {

            text = text + "{";

            text = text + "\"" + "stock_id" + "\"" + ":" + "\"" + selectedProduct.get(i).getStockId() + "\"" + ",";
            text = text + "\"" + "sale_qty" + "\"" + ":" + "\"" + "1" + "\"" + ",";
            text = text + "\"" + "product_final_amount" + "\"" + ":" + "\"" + selectedProduct.get(i).getProductFinalAmount() + "\"" + ",";
            text = text + "\"" + "product_discount" + "\"" + ":" + "\"" + selectedProduct.get(i).getProductDiscount() + "\"" + ",";
            text = text + "\"" + "order_amount" + "\"" + ":" + "\"" + selectedProduct.get(i).getProductFinalAmount() + "\"" + "}";

            if (i != selectedProduct.size() - 1)
                text = text + ",";
        }

        text = text + "]";

        Log.e("TAG", "callAddSaleApis: Text  " + text);

        if (count == 0) {


//
//            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), paymentInvoice);
//            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("payment_receipt_url", paymentInvoice.getName(), requestBody);
//
//            RetrofitClient.getClient(activity).addSaleWithFile(
//                    imagePart,
//                    selectedCustomer.getCustomerId(),
//                    String.valueOf(selectedProduct.size()),
//                    binding.totalPrice.getText().toString(),
//                    binding.payAmount.getText().toString(),
//                    binding.totalDiscount.getText().toString(),
//                    session.getUserId(),
//                    selectedPaymentMode,
//                    text,
//                    "",
//                    "",
//                    "",
//                    "",
//                    ""
//            ).enqueue(new Callback<AddSaleModel>() {
//                @Override
//                public void onResponse(@NonNull Call<AddSaleModel> call, @NonNull Response<AddSaleModel> response) {
//                    pd.dismiss();
//                    if (response.code() == 200)
//                        if (response.body() != null)
//                            if (response.body().getStatus() == 1) {
//                                Toast.makeText(activity, "Sale Added..!", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<AddSaleModel> call, @NonNull Throwable t) {
//                    Log.e("TAG", "onFailure: ", t);
//                    Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
//                    Log.e("TAG", "onFailure: " + t.getMessage());
//                    Log.e("TAG", "onFailure: " + t.toString());
//                    pd.dismiss();
//                }
//            });
//
//            ANRequest.MultiPartBuilder anAdd = AndroidNetworking.upload((BaseUrls.URL + addCoordinationSale));
//            anAdd.addMultipartParameter("customer_id", selectedCustomer.getCustomerId());
//            anAdd.addMultipartParameter("total_sale_qty", String.valueOf(selectedProduct.size()));
//            anAdd.addMultipartParameter("total_order_amount", binding.totalPrice.getText().toString());
//            anAdd.addMultipartParameter("pay_amount", binding.payAmount.getText().toString());
//            anAdd.addMultipartParameter("total_discount", binding.totalDiscount.getText().toString());
//            anAdd.addMultipartParameter("coordination_id", session.getUserId());
//            anAdd.addMultipartParameter("payment_type", selectedPaymentMode);
//            anAdd.addMultipartParameter("products", text);
//            if (paymentInvoice != null)
//                anAdd.addMultipartFile("payment_receipt_url", paymentInvoice);
//
//
//            anAdd.setPriority(Priority.HIGH);
//            anAdd.build()
//                    .getAsJSONObject(new JSONObjectRequestListener() {
//                        @Override
//                        public void onResponse(JSONObject jsonObject) {
//                            pd.dismiss();
//                            try {
//                                Log.d("---rrrProfile", "save_postsave_post" + jsonObject.toString());
//                                int result = jsonObject.getInt("status");
//
//                                if (result == 1) {
//                                    showSuccessDialog();
//                                } else {
//                                    Toast.makeText(activity, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
//                                }
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                        @Override
//                        public void onError(ANError anError) {
//                            Log.e("Error On Server ", "Error " + anError.toString());
//                            Toast.makeText(activity, "Some  Thing Went Wrong", Toast.LENGTH_SHORT).show();
//                            pd.dismiss();
//
//                        }
//                    });
//

            AndroidNetworking.upload(BaseUrls.URL + BaseUrls.addCoordinationSale)
                    .addMultipartFile("payment_receipt", paymentInvoice)
                    .addMultipartParameter("customer_id", selectedCustomer.getCustomerId())
                    .addMultipartParameter("total_sale_qty", String.valueOf(selectedProduct.size()))
                    .addMultipartParameter("total_order_amount", binding.totalPrice.getText().toString())
                    .addMultipartParameter("pay_amount", binding.payAmount.getText().toString())
                    .addMultipartParameter("total_discount", binding.totalDiscount.getText().toString())
                    .addMultipartParameter("coordination_id", session.getUserId())
                    .addMultipartParameter("bank_name", "")
                    .addMultipartParameter("bank_ifsc_code", "")
                    .addMultipartParameter("bank_branch", "")
                    .addMultipartParameter("bank_ac_no", "")
                    .addMultipartParameter("bank_address", "")
                    .addMultipartParameter("payment_type", selectedPaymentMode)
                    .addMultipartParameter("products", text)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            pd.dismiss();
                            try {
                                Log.d("---rrrProfile", "save_postsave_post" + jsonObject.toString());
                                int result = jsonObject.getInt("status");

                                if (result == 1) {
                                    showSuccessDialog();
                                } else {
                                    Toast.makeText(activity, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
//                        progressDialog.dismiss();
                            Log.e("Error On Server ", "Error " + anError.toString());
                            Toast.makeText(activity, "Some Thing Went Wrong", Toast.LENGTH_SHORT).show();
                            pd.dismiss();

                        }
                    });


        } else {

            RetrofitClient.getClient(activity).addSale(
                    selectedCustomer.getCustomerId(),
                    String.valueOf(selectedProduct.size()),
                    binding.totalPrice.getText().toString(),
                    binding.payAmount.getText().toString(),
                    binding.totalDiscount.getText().toString(),
                    session.getUserId(),
                    selectedPaymentMode,
                    "",
                   "",
                    "",
                    "",
                    "",
                    text,
                    ""
            ).enqueue(new Callback<AddSaleModel>() {
                @Override
                public void onResponse(@NonNull Call<AddSaleModel> call, @NonNull Response<AddSaleModel> response) {
                    pd.dismiss();
                    if (response.code() == 200)
                        if (response.body() != null)
                            if (response.body().getStatus() == 1) {
                                //Toast.makeText(activity, "Sale Added..!", Toast.LENGTH_SHORT).show();
                                showSuccessDialog();
                            }
                }

                @Override
                public void onFailure(@NonNull Call<AddSaleModel> call, @NonNull Throwable t) {
                    pd.dismiss();
                }
            });

        }


    }

    private void showSuccessDialog() {

        new KAlertDialog(activity, KAlertDialog.SUCCESS_TYPE)
                .setTitleText("Sale Added")
                .setContentText("Your Sale Added!")
                .setConfirmText("Okay")
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismissWithAnimation();
                        finish();
                    }
                })
                .show();

    }

    private void showDialog() {

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.add_customer);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        LinearLayout cancle_linear = dialog.findViewById(R.id.cancle_linear);
        LinearLayout submit_linear = dialog.findViewById(R.id.submit_linear);

        EditText edit_address = dialog.findViewById(R.id.editPincode);
        EditText lastName = dialog.findViewById(R.id.lastName);
        EditText edit_number = dialog.findViewById(R.id.mobileNumber);
        EditText edit_name = dialog.findViewById(R.id.editNmae);

        cancle_linear.setOnClickListener(View -> dialog.dismiss());

        submit_linear.setOnClickListener(view -> {
            if (edit_name.getText().toString().equalsIgnoreCase("")) {
                edit_name.setError("Enter Customer Name.!");
                edit_name.requestFocus();
            } else if (lastName.getText().toString().equalsIgnoreCase("")) {
                lastName.setError("Enter Customer Last Name.!");
                lastName.requestFocus();
            } else if (edit_number.getText().toString().equalsIgnoreCase("")) {
                edit_number.setError("Enter Customer Mobile.!");
                edit_number.requestFocus();
            } else if (edit_address.getText().toString().equalsIgnoreCase("")) {
                edit_address.setError("Enter Customer Pincode.!");
                edit_address.requestFocus();
            } else {
                pd.show();
                dialog.dismiss();
                RetrofitClient.getClient(activity).addNewCustomer(
                        session.getUserId(),
                        edit_name.getText().toString(),
                        lastName.getText().toString(),
                        edit_number.getText().toString(),
                        edit_address.getText().toString()
                ).enqueue(new Callback<AddCustomerModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AddCustomerModel> call, @NonNull Response<AddCustomerModel> response) {
                        pd.dismiss();
                        if (response.code() == 200)
                            if (response.body() != null)
                                if (response.body().getStatus() == 1) {
                                    Toast.makeText(activity, "Customer Added..", Toast.LENGTH_SHORT).show();
                                    getCustomerList(response.body().getData().get(0).getCustomerFname());
                                }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AddCustomerModel> call, @NonNull Throwable t) {
                        pd.dismiss();
                    }
                });
            }
        });

        dialog.show();


    }

    private void showQRDialog(int type) {

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.full_image_view);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;


        ImageView icClose = dialog.findViewById(R.id.icClose);
        icClose.setOnClickListener(View -> dialog.dismiss());


        dialog.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCustomerList("0");
        getNewestProduct();
    }

    private void getCustomerList(String m) {
        customerList.clear();
        customerNamesList.clear();
        RetrofitClient.getClient(activity).getCustomerList(session.getUserId()).enqueue(new Callback<AddCustomerModel>() {
            @Override
            public void onResponse(@NonNull Call<AddCustomerModel> call, @NonNull Response<AddCustomerModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            int pos = 0;
                            customerList.addAll(response.body().getData());
                            for (int i = 0; i < customerList.size(); i++) {
                                customerNamesList.add(customerList.get(i).getCustomerFname());
                                if (m.equalsIgnoreCase(customerList.get(i).getCustomerFname())) {
                                    pos = i;
                                    selectedCustomer = customerList.get(i);
                                }
                            }

                            if (!m.equalsIgnoreCase("0")) {
                                setUpCustomerSpinner2(pos);
                            } else {
                                setUpCustomerSpinner();
                            }
                        }
            }

            @Override
            public void onFailure(@NonNull Call<AddCustomerModel> call, @NonNull Throwable t) {
                pd.dismiss();
            }
        });
    }

    private void setUpCustomerSpinner() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (activity, android.R.layout.simple_spinner_item,
                        customerNamesList); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        binding.customerSpiner.setAdapter(spinnerArrayAdapter);

        binding.customerSpiner.setOnItemSelectedListener((view, position, id, item) -> selectedCustomer = customerList.get(position));


        List<String> payMode = new ArrayList<>();
        payMode.add("online");
        payMode.add("cash");
//        payMode.add("offline");

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>
                (activity, android.R.layout.simple_spinner_item,
                        payMode); //selected item will look like a spinner set from XML
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        binding.paymentSpinner.setAdapter(spinnerArrayAdapter2);

        binding.paymentSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            selectedPaymentMode = payMode.get(position);
            if (selectedPaymentMode.equalsIgnoreCase("online")) {
                binding.onlinePayment.setVisibility(View.VISIBLE);
                binding.offlinePayment.setVisibility(View.GONE);
            } else {
//                binding.offlinePayment.setVisibility(View.VISIBLE);
                binding.onlinePayment.setVisibility(View.GONE);
            }
        });


    }

    private void setUpCustomerSpinner2(int pos) {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (activity, android.R.layout.simple_spinner_item,
                        customerNamesList); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        binding.customerSpiner.setAdapter(spinnerArrayAdapter);

        binding.customerSpiner.setOnItemSelectedListener((view, position, id, item) -> selectedCustomer = customerList.get(position));

        try {
            binding.customerSpiner.setSelectedIndex(pos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> payMode = new ArrayList<>();
        payMode.add("online");
        payMode.add("cash");
//        payMode.add("offline");

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>
                (activity, android.R.layout.simple_spinner_item,
                        payMode); //selected item will look like a spinner set from XML
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        binding.paymentSpinner.setAdapter(spinnerArrayAdapter2);

        binding.paymentSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            selectedPaymentMode = payMode.get(position);
            if (selectedPaymentMode.equalsIgnoreCase("online")) {
                binding.onlinePayment.setVisibility(View.VISIBLE);
                binding.offlinePayment.setVisibility(View.GONE);
            } else {
//                binding.offlinePayment.setVisibility(View.VISIBLE);
                binding.onlinePayment.setVisibility(View.GONE);
            }
        });


    }

    private void getNewestProduct() {
        // allProductList.clear();
        RetrofitClient.getClient(activity).getAllProducts().enqueue(new Callback<ProductModelCo>() {
            @Override
            public void onResponse(@NonNull Call<ProductModelCo> call, @NonNull Response<ProductModelCo> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            allProductList.clear();
                            if (response.body().getData().size() != 0) {

                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    ProductModelCo.Datum current = response.body().getData().get(i);

                                    current.setProductMrp(current.getProductFinalAmount());

                                    allProductList.add(current);
                                }

//                              allProductList.addAll(response.body().getData());
                                productListNames.clear();
                                for (int i = 0; i < allProductList.size(); i++) {
                                    productListNames.add(allProductList.get(i).getProductQrcode() + " " + allProductList.get(i).getProductName());
                                }
                                setUpProductSpinner();
                            }
                        }

            }

            @Override
            public void onFailure(@NonNull Call<ProductModelCo> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server Not Respond..!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpProductSpinner() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (activity, android.R.layout.simple_spinner_item,
                        productListNames); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        binding.productSpiner.setAdapter(spinnerArrayAdapter);
        updateProductSpinner();
    }

    private void showSearchableSpinner() {
        final SearchableSpinner searchableSpinner = new SearchableSpinner(this);
        //Optional Parameters
        searchableSpinner.setWindowTitle("SEARCHABLE SPINNER");
        searchableSpinner.setShowKeyboardByDefault(false);

        //Setting up list items for spinner
        searchableSpinner.setSpinnerListItems(productListNames);

        searchableSpinner.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void setOnItemSelectListener(int k, @NonNull String s) {
                binding.nameOfProduct.setText(s);
                ProductModelCo.Datum newProduct = allProductList.get(k);
                boolean isAdded = false;
                for (int i = 0; i < selectedProduct.size(); i++) {
                    if (selectedProduct.get(i).getProductId().equalsIgnoreCase(newProduct.getProductId())) {
                        isAdded = true;
                        break;
                    }
                }

                if (!isAdded) {
                    selectedProduct.add(newProduct);
                    modelForPrice.add(newProduct);
                    updateProductSpinner();
                } else {
                    Toast.makeText(activity, "Product Already Selected", Toast.LENGTH_SHORT).show();
                }


                adapter.notifyDataSetChanged();
                calculatePrice();
                calBefore();
                Log.e("TAG", "showDialog: Selected Item Size " + selectedProduct.size());

            }
        });


        searchableSpinner.show();
    }

    private void updateProductSpinner() {

        binding.productSpiner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> {
            ProductModelCo.Datum newProduct = allProductList.get(position);
            boolean isAdded = false;
            for (int i = 0; i < selectedProduct.size(); i++) {
                if (selectedProduct.get(i).getProductId().equalsIgnoreCase(newProduct.getProductId())) {
                    isAdded = true;
                    break;
                }
            }

            if (!isAdded) {
                selectedProduct.add(newProduct);
                modelForPrice.add(newProduct);
                updateProductSpinner();
            } else {
                Toast.makeText(activity, "Product Already Selected", Toast.LENGTH_SHORT).show();
            }

            adapter.notifyDataSetChanged();
            calculatePrice();
            calBefore();
            Log.e("TAG", "showDialog: Selected Item Size " + selectedProduct.size());
        });
    }

    private void setUpRecyclerView() {
        binding.saleProductRecy.setLayoutManager(new LinearLayoutManager(activity));
        binding.saleProductRecy.setAdapter(adapter);
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
                            QrCodeProductModel.Datum data2 = response.body().getData();
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
                                modelForPrice.add(data);
                                adapter.notifyDataSetChanged();
                                calculatePrice();
                                calBefore();
                                Log.e("TAG", "onResponse: Addedd");
                            } else {
                                Toast.makeText(activity, "Product Already Added..!", Toast.LENGTH_SHORT).show();
                            }

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

    private void calculatePrice() {

        if (selectedProduct.size() != 0) {
            float finalPrice = 0;
            float discount = 0;
            for (int i = 0; i < selectedProduct.size(); i++) {
                float price = Float.parseFloat(selectedProduct.get(i).getProductFinalAmount());
                if (selectedProduct.get(i).getProductDiscount() != null) {
                    float dis = Float.parseFloat(selectedProduct.get(i).getProductDiscount());
                    discount = discount + dis;
                }
                finalPrice = finalPrice + price;

            }

            binding.payAmount.setText(String.valueOf(finalPrice));
            binding.totalDiscount.setText(String.valueOf(discount));

        }

    }

    private void calBefore() {
        float productPrice = 0;

        for (int k = 0; k < allProductList.size(); k++) {
            for (int i = 0; i < selectedProduct.size(); i++) {
                if (allProductList.get(k).getStockId().equalsIgnoreCase(selectedProduct.get(i).getStockId())) {
                    float ab = Float.parseFloat(allProductList.get(k).getProductMrp());
                    productPrice = productPrice + ab;
                    break;
                }
            }
        }

        binding.totalPrice.setText(String.valueOf(productPrice));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            if (data != null) {
//             getProductByQrCode("16916643662");
                getProductByQrCode(data.getStringExtra("resultKey"));
            }
        } else if (requestCode == gallery_req_code && resultCode == RESULT_OK) {
            if (data != null) {
                filepath = data.getData();

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                    binding.paymentReceptImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                paymentInvoice = bitmapToFile(activity, bitmap);
                //uploadPaymentInvoice();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectedProduct.clear();
    }

    @Override
    public void onChange(int pos, String price, String discount) {
        selectedProduct.get(pos).setProductFinalAmount(price);
        selectedProduct.get(pos).setProductDiscount(discount);
        calculatePrice();
    }

    @Override
    public void onDelete(int pos) {
        selectedProduct.remove(pos);
        // modelForPrice.remove(pos);
        adapter.notifyDataSetChanged();
        calculatePrice();
        calBefore();
    }

    private boolean isValidate() {
        if (binding.accountNumer.getText().toString().equalsIgnoreCase("")) {
            binding.accountNumer.setError("Enter Account Number..!");
            binding.accountNumer.requestFocus();
            return false;
        } else if (binding.bankName.getText().toString().equalsIgnoreCase("")) {
            binding.bankName.setError("Enter Bank Name..!");
            binding.bankName.requestFocus();
            return false;
        } else if (binding.editifscCode.getText().toString().equalsIgnoreCase("")) {
            binding.editifscCode.setError("Enter IFSC Code ..!");
            binding.editifscCode.requestFocus();
            return false;
        } else if (binding.branch.getText().toString().equalsIgnoreCase("")) {
            binding.branch.setError("Enter Branch Name ..!");
            binding.branch.requestFocus();
            return false;
        } else if (binding.addressTv.getText().toString().equalsIgnoreCase("")) {
            binding.addressTv.setError("Enter Bank Address ..!");
            binding.addressTv.requestFocus();
            return false;
        } else {
            return true;
        }


    }

    private void uploadPaymentInvoice() {
        AndroidNetworking.upload(BaseUrls.URL + BaseUrls.uploadPaymentReceipt)
                .addMultipartFile("payment_receipt", paymentInvoice)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        pd.dismiss();
                        try {
                            Log.d("---rrrProfile", "save_postsave_post" + jsonObject.toString());
                            int result = jsonObject.getInt("status");

                            if (result == 1) {
                                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                                paymentInvoiceName = jsonObject1.getString("payment_receipt_url");
                                Log.e("TAG", "onResponse: Payment Invoice " + paymentInvoiceName);
                            } else {
                                Toast.makeText(activity, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
//                        progressDialog.dismiss();
                        Log.e("Error On Server ", "Error " + anError.toString());
                        Toast.makeText(activity, "Some Thing Went Wrong", Toast.LENGTH_SHORT).show();
                        pd.dismiss();

                    }
                });

    }
}
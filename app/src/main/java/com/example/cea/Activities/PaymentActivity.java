package com.example.cea.Activities;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.LoginModel;
import com.example.cea.Models.OrderPlaceModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.databinding.ActivityPaymentBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    String price = "10";
    Session session;
    PaymentActivity activity;
    ActivityPaymentBinding binding;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activity = this;
        session = new Session(activity);
        pd = new ProgressDialog(activity);

        price = getIntent().getStringExtra("price");

        pd.show();
        makePayment();

    }

    private void makePayment() {

        int amount = Math.round(Float.parseFloat(price) * 100);

        // initialize Razorpay account.
        Checkout checkout = new Checkout();

        // set your id as below
        checkout.setKeyID(BaseUrls.RazorPayKey);

        // set image
        checkout.setImage(R.drawable.cea_logo);

        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name

            object.put("name", "CEA");

            // put description
            object.put("description", "Product Purchase");

            // to set theme color
//            object.put("theme.color", "");

            // put the currency
            object.put("currency", "INR");

            // put amount
            object.put("amount", amount);

            // put mobile number
            object.put("prefill.contact", session.getMobile());

            // put email
            object.put("prefill.email", session.getEmail());


            // open razorpay to checkout activity
            checkout.open(PaymentActivity.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void placeOrder(String paymentId) {
        pd.show();
        RetrofitClient.getClient(activity).placeOrder(
                session.getUserId(),
                paymentId,
                session.getPincode()
        ).enqueue(new Callback<OrderPlaceModel>() {
            @Override
            public void onResponse(@NonNull Call<OrderPlaceModel> call, @NonNull Response<OrderPlaceModel> response) {
                pd.dismiss();
                if (response.code() == 200) {
                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
                            doLogin();
                            Toast.makeText(activity, "Order Placed....", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(activity, HistoryActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();
                        } else {
                            startActivity(new Intent(activity, CheckoutActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();
                            Toast.makeText(activity, "Failed....", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(activity, "Payment Error! Contact Support!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<OrderPlaceModel> call, @NonNull Throwable t) {
                pd.dismiss();
                Toast.makeText(activity, "Server Not Respond....", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(activity, CheckoutActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });


    }

    private void doLogin() {

        pd.show();
        RetrofitClient.getClient(activity).loginUser(
                "customer",
                session.getMobile(),
                session.getPas(),
                "fdasffwt327327e763"
        ).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
//                            Toast.makeText(activity, "Login Success..", Toast.LENGTH_SHORT).show();
                            session.setName(response.body().getData().get(0).getCustomerFname());
                            session.setLastname(response.body().getData().get(0).getCustomerLname());
                            session.setUser_id(response.body().getData().get(0).getCustomerId());
                            session.setReferCode(response.body().getData().get(0).getCustomerReferalCode());
                            session.setMobile(response.body().getData().get(0).getCustomerMobileNo());
                            session.setEmail(response.body().getData().get(0).getCustomerEmail());
                            session.setPincode(response.body().getData().get(0).getCustomerPincode());
                            session.setWalletAmount(response.body().getData().get(0).getCustomerTotalWalletAmount());
                            session.setImage(BaseUrls.ImageBaseUrl + response.body().getData().get(0).getCustomerProfileImg());
                            session.setType("user");
                        }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Server not responding..", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onPaymentSuccess() called with: s = [" + s + "]");
        binding.success.setVisibility(View.VISIBLE);
        placeOrder(s);
        pd.dismiss();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onPaymentError() called with: i = [" + i + "], s = [" + s + "]");
        binding.failed.setVisibility(View.VISIBLE);
        pd.dismiss();
        new Thread(() -> {
            try {
                sleep(2000);
                startActivity(new Intent(activity, CheckoutActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
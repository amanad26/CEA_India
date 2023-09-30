package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.AddReviewModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.databinding.ActivityAddReviewBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReviewActivity extends AppCompatActivity {
    ActivityAddReviewBinding binding;
    Activity activity;
    String productId = "";
    Session session;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);
        pd = new ProgressDialog(activity);


        productId = getIntent().getStringExtra("productId");
        binding.ivBack.setOnClickListener(view -> onBackPressed());

        binding.submit.setOnClickListener(view -> {
            if (binding.editReview.getText().toString().equalsIgnoreCase("")) {
                binding.editReview.setError("Enter Your Review..");
                binding.editReview.requestFocus();
            } else if (binding.reviewRatingBar.getRating() == 0) {
                Toast.makeText(activity, "Please Provide Rating...", Toast.LENGTH_SHORT).show();
            } else {

                addReview();
            }
        });

    }

    private void addReview() {

        pd.show();
        RetrofitClient.getClient(activity).addReview(
                session.getUserId(),
                productId,
                String.valueOf(binding.reviewRatingBar.getRating()),
                binding.editReview.getText().toString()
        ).enqueue(new Callback<AddReviewModel>() {
            @Override
            public void onResponse(@NonNull Call<AddReviewModel> call, @NonNull Response<AddReviewModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(activity, "Thank You For Your Feedback...", Toast.LENGTH_SHORT).show();
                            finish();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<AddReviewModel> call, @NonNull Throwable t) {
                pd.dismiss();
                Toast.makeText(activity, "Server Not Respond..", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
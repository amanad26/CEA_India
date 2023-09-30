package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.R;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.co_oridinator.Activities.AddSaleActivity;
import com.example.cea.co_oridinator.Models.ProductModelCo;
import com.example.cea.co_oridinator.Models.QrCodeProductModel;
import com.example.cea.databinding.ActivityQrScanBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrScanActivity extends AppCompatActivity {
    private Activity activity;
    private ActivityQrScanBinding binding;
    String activityString = "";
    public static String qrFinalCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrScanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        activityString = getIntent().getStringExtra("activity");

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            setQr();
        } else
            Dexter.withContext(this)
                    .withPermissions(
                            Manifest.permission.CAMERA
                    ).withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            setQr();
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> list, PermissionToken permissionToken) {
                            permissionToken.continuePermissionRequest();
                            Toast.makeText(activity, "Permissions Required to Scan!", Toast.LENGTH_SHORT).show();
                        }
                    }).check();
    }

    private CodeScanner mCodeScanner;

    private void setQr() {
        CodeScannerView scannerView = findViewById(R.id.scanner);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {

            Log.e("TAG", "setQr: resutl " + result.getText());

            if (activityString.equalsIgnoreCase("0")) {
                // startActivity(new Intent(activity, HomeActivity.class).putExtra("result", result.getText().trim()));
                String key = result.getText().trim();
                getProductByQrCode(key);
                //finish();
            } else if (activityString.equalsIgnoreCase("2")) {
                qrFinalCode = result.getText().toString().trim();
                finish();
            } else {
                Intent resultIntent = new Intent(activity, AddSaleActivity.class);
                resultIntent.putExtra("resultKey", result.getText().trim());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }

        }));

        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());
    }

    private void getProductByQrCode(String code) {
        ProgressDialog pd = new ProgressDialog(activity);
        pd.show();
        RetrofitClient.getClient(activity).getProductByQrCode(code).enqueue(new Callback<QrCodeProductModel>() {
            @Override
            public void onResponse(@NonNull Call<QrCodeProductModel> call, @NonNull Response<QrCodeProductModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            String pId = response.body().getData().getProductId();
                            startActivity(new Intent(activity, ProductDetailsActivity.class)
                                    .putExtra("pro_id", pId));
                            finish();
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
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }


    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}
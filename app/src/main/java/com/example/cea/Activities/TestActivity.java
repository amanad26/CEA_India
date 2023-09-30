package com.example.cea.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.ScanMode;
import com.example.cea.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class TestActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private static final int REQUEST_CODE_QR_SCAN = 101;

    Button clickMe ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        clickMe= findViewById(R.id.clickMe);

        clickMe.setOnClickListener(view -> {
            Intent intent = new Intent(TestActivity.this, QrCodeActivity.class);
            startActivityForResult(intent, REQUEST_CODE_QR_SCAN);
        });

        Dexter.withContext(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {


//                        CodeScannerView scannerView = findViewById(R.id.scanner_view);
//                        mCodeScanner = new CodeScanner(TestActivity.this, scannerView);
//
//                        mCodeScanner.setCamera(CodeScanner.CAMERA_BACK);
//                        ;// or CAMERA_FRONT or specific camera id
//                        mCodeScanner.setFormats(CodeScanner.ALL_FORMATS);   // list of type BarcodeFormat,
//                        // ex. listOf(BarcodeFormat.QR_CODE)
//                        mCodeScanner.setAutoFocusMode(AutoFocusMode.SAFE);  // or CONTINUOUS
//                        mCodeScanner.setScanMode(ScanMode.SINGLE);  // or CONTINUOUS or PREVIEW
//                        mCodeScanner.setAutoFocusEnabled(true);  // Whether to enable auto focus or not
//                        mCodeScanner.setFlashEnabled(false);
//                        mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> Toast.makeText(TestActivity.this, result.getText(), Toast.LENGTH_SHORT).show()));
//                        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());
//
//                        mCodeScanner.setErrorCallback(thrown -> Log.e("TAG", "onError: " + thrown.getLocalizedMessage()));


                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
    }

/*    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }*/



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (resultCode != Activity.RESULT_OK) {
            Log.d("LOGTAG", "COULD NOT GET A GOOD RESULT.");
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (result != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(TestActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            Log.e("TAG", "onActivityResult: Called " );
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.d("LOGTAG", "Have scan result in your app activity :" + result);
            AlertDialog alertDialog = new AlertDialog.Builder(TestActivity.this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
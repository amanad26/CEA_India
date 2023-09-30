package com.example.cea.Models;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.R;

public class ViewFulImage extends AppCompatActivity {

    String url;
    ProgressDialog pd;
    ImageView vehicle_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ful_image);

        url = getIntent().getStringExtra("image_id");

        pd = new ProgressDialog(this);
        pd.show();

        vehicle_image = findViewById(R.id.vehicle_image);

        Glide.with(ViewFulImage.this).load(BaseUrls.ImageBaseUrl+url).placeholder(R.drawable.model_1).into(vehicle_image);

        pd.dismiss();


    }
}
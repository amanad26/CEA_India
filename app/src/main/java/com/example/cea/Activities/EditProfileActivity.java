package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.example.cea.Apis.ApiInterface;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.UpdateProfileModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.databinding.ActivityEditProfileBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    ActivityEditProfileBinding binding;
    EditProfileActivity activity;
    Session session;
    ApiInterface apiInterface;
    private File Profile_Image = null;
    private Uri filepath;
    Bitmap bitmap;
    private final int gallery_req_code = 200;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        apiInterface = RetrofitClient.getClient(activity);
        pd = new ProgressDialog(activity);

        session = new Session(activity);

        binding.edtFirstname.setText(session.getName());
        binding.edtLastname.setText(session.getLastname());
        binding.edtPhone.setText(session.getMobile());
        binding.edtEmail.setText(session.getEmail());

        if (!session.getImage().equalsIgnoreCase(""))
            Picasso.get().load(session.getImage()).placeholder(R.drawable.ic_users).into(binding.userImage);

        binding.chooseImage.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.setType("image/*");
            startActivityForResult(i, gallery_req_code);
        });

        binding.selectAnniversary.setOnClickListener(view -> selectDate(binding.selectAnniversary));
        binding.editDob.setOnClickListener(view -> selectDate(binding.editDob));

        binding.llLogin.setOnClickListener(view -> {
            if (!binding.editPincode.getText().toString().equalsIgnoreCase("")) {
                if (Profile_Image == null)
                    updateProfileWithoutImage();
                else
                    updateProfileWithImage();
            } else {
                Toast.makeText(activity, "Enter Pincode", Toast.LENGTH_SHORT).show();
                binding.editPincode.requestFocus();
            }
        });

    }

    private void updateProfileWithImage() {
        AndroidNetworking.upload(BaseUrls.URL + BaseUrls.updateProfile)
                .addMultipartFile("customer_profile_img", Profile_Image)
                .addMultipartParameter("customer_id", session.getUserId())
                .addMultipartParameter("customer_fname", binding.edtFirstname.getText().toString())
                .addMultipartParameter("customer_lname", binding.edtLastname.getText().toString())
                .addMultipartParameter("customer_anniversery_date", binding.selectAnniversary.getText().toString())
                .addMultipartParameter("customer_dob", binding.editDob.getText().toString())
                .addMultipartParameter("customer_whatspp_no", binding.edtPhoneWhatsapp.getText().toString())
                .addMultipartParameter("customer_address", binding.address.getText().toString())
                .addMultipartParameter("customer_pincode", binding.editPincode.getText().toString())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        pd.dismiss();
                        try {
                            Log.d("---rrrProfile", "save_postsave_post" + jsonObject.toString());
                            int result = jsonObject.getInt("status");

                            if (result == 1) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                JSONObject data = jsonArray.getJSONObject(0);
                                String fName = data.getString("customer_fname");
                                String customer_lname = data.getString("customer_lname");
                                String customer_address = data.getString("customer_address");
                                String customer_pincode = data.getString("customer_pincode");
                                String customer_dob = data.getString("customer_dob");
                                String customer_whatspp_no = data.getString("customer_whatspp_no");
                                String customer_anniversery_date = data.getString("customer_anniversery_date");
                                String customer_profile_img = data.getString("customer_profile_img");

                                session.setLastname(customer_lname);
                                session.setName(fName);
                                session.setAddress(customer_address);
                                session.setPincode(customer_pincode);
                                session.setImage(BaseUrls.ImageBaseUrl + customer_profile_img);
                                session.setAnniversary(customer_anniversery_date);
                                session.setWhatsappNumber(customer_whatspp_no);
                                session.setDob(customer_dob);
                                Toast.makeText(activity, "Updated....", Toast.LENGTH_SHORT).show();

                                Log.e("TAG", "onResponse: " + session.getImage());
                                Log.e("TAG", "onResponse: IMage " + customer_profile_img);
                                setData();
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

    private void selectDate(TextView DateBtn) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year1, month1, day1) -> {
            String date = year1 + " -" + (month1 + 1) + "-" +
                    "-" + day1;
            DateBtn.setText(date);
        }, year, month, day);
        datePickerDialog.show();
    }

    public String getMonthName(int month) {

        String monthString;
        switch (month) {
            case 1:
                monthString = "Jan";
                break;
            case 2:
                monthString = "Feb";
                break;
            case 3:
                monthString = "Mar";
                break;
            case 4:
                monthString = "Apr";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "Jun";
                break;
            case 7:
                monthString = "Jul";
                break;
            case 8:
                monthString = "Aug";
                break;
            case 9:
                monthString = "Sep";
                break;
            case 10:
                monthString = "Oct";
                break;
            case 11:
                monthString = "Nov";
                break;
            case 12:
                monthString = "Dec";
                break;
            default:
                monthString = "Inv month";
                break;
        }
        System.out.println(monthString);

        return monthString;
    }

    private void updateProfileWithoutImage() {

        pd.show();
        apiInterface.updateUserProfile(
                session.getUserId(),
                binding.edtFirstname.getText().toString(),
                binding.edtLastname.getText().toString(),
                binding.selectAnniversary.getText().toString(),
                binding.edtPhoneWhatsapp.getText().toString(),
                binding.editDob.getText().toString(),
                binding.address.getText().toString(),
                binding.editPincode.getText().toString()
        ).enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateProfileModel> call, @NonNull Response<UpdateProfileModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            UpdateProfileModel.Datum data = response.body().getData().get(0);

                            session.setLastname(data.getCustomerLname());
                            session.setName(data.getCustomerFname());
                            session.setAddress(data.getCustomerAddress());
                            session.setPincode(data.getCustomerPincode());
                            session.setImage(BaseUrls.ImageBaseUrl + data.getCustomerProfileImg());
                            session.setAnniversary(data.getCustomerAnniverseryDate());
                            session.setWhatsappNumber(data.getCustomerWhatsppNo());
                            session.setDob(data.getCustomerDob());

                            setData();

                            Toast.makeText(activity, "Updated...", Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateProfileModel> call, @NonNull Throwable t) {
                pd.dismiss();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == gallery_req_code && resultCode == RESULT_OK) {
            if (data != null) {
                filepath = data.getData();

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                    binding.userImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Profile_Image = bitmapToFile(activity, bitmap);
            }
        }

    }

    public static File bitmapToFile(Context mContext, Bitmap bitmap) {
        try {
            String name = System.currentTimeMillis() + ".png";
            File file = new File(mContext.getCacheDir(), name);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 60, bos);
            byte[] bArr = bos.toByteArray();
            bos.flush();
            bos.close();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bArr);
            fos.flush();
            fos.close();

            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();

    }

    private void setData() {
        binding.edtFirstname.setText(session.getName());
        binding.edtLastname.setText(session.getLastname());
        binding.edtPhone.setText(session.getMobile());
        binding.edtEmail.setText(session.getEmail());

        if (!session.getPincode().equalsIgnoreCase(""))
            binding.editPincode.setText(session.getPincode());

        if (!session.getAddress().equalsIgnoreCase(""))
            binding.address.setText(session.getAddress());

        if (!session.getWhatsappNumber().equalsIgnoreCase(""))
            binding.edtPhoneWhatsapp.setText(session.getWhatsappNumber());

        if (!session.getDob().equalsIgnoreCase(""))
            binding.editDob.setText(session.getDob());

        if (!session.getAnniversary().equalsIgnoreCase(""))
            binding.selectAnniversary.setText(session.getAnniversary());

        if (!session.getImage().equalsIgnoreCase("")) {
            Picasso.get().load(session.getImage()).into(binding.userImage);
        }

    }
}
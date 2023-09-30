package com.example.cea.co_oridinator.Adapters;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.kalert.KAlertDialog;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.MainActivity;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.co_oridinator.Models.AddFollowUpModel;
import com.example.cea.co_oridinator.Models.AddWallkingCustomerModel;
import com.example.cea.co_oridinator.Models.GetWallkingModel;
import com.example.cea.co_oridinator.Models.WallkingModelCo;
import com.example.cea.databinding.WalletLayoutBinding;
import com.example.cea.databinding.WallkingLayoutBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WallkingAdapter extends RecyclerView.Adapter<WallkingAdapter.ViewHolder> {

    Context context;
    List<WallkingModelCo.Datum> models;
    Session session;


    public WallkingAdapter(Context context, List<WallkingModelCo.Datum> models) {
        this.context = context;
        this.models = models;
        session = new Session(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.wallking_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.binding.userNameTv.setText(models.get(position).getCustomerName());
            holder.binding.totalProduct.setText(String.valueOf(models.get(position).getProductCount()));
            holder.binding.userMobile.setText(models.get(position).getCustomerMobileNo());

            holder.binding.view.setOnClickListener(view -> showDialog(models.get(position).getCustomerWiId()));

            holder.binding.followUp.setOnClickListener(view -> addFollowUp(models.get(position)));

            holder.binding.call.setOnClickListener(view -> callToUser(models.get(position).getCustomerMobileNo()));
            holder.binding.whatsapp.setOnClickListener(view -> openWhatsapp(models.get(position).getCustomerMobileNo()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showDatePickerDialog(EditText textView) {
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, year1, monthOfYear, dayOfMonth) -> textView.setText(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth),
                year, month, day);
        datePickerDialog.show();
    }

    private void showDialog(String id) {


        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.view_walking_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        LinearLayout submit = dialog.findViewById(R.id.submit_linear23);

        RecyclerView productSelectRecycler = dialog.findViewById(R.id.productSelectRecycler);
        EditText edit_address = dialog.findViewById(R.id.edit_address);
        EditText edit_whatsappNumber = dialog.findViewById(R.id.edit_whatsappNumber);
        EditText edit_number = dialog.findViewById(R.id.edit_number);
        EditText edit_name = dialog.findViewById(R.id.edit_name);


        submit.setOnClickListener(View -> dialog.dismiss());


        RetrofitClient.getClient(context).getCustomerWallkingDetails(id).enqueue(new Callback<GetWallkingModel>() {
            @Override
            public void onResponse(@NonNull Call<GetWallkingModel> call, @NonNull Response<GetWallkingModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {

                            GetWallkingModel.Datum data = response.body().getData().get(0);

                            edit_address.setText(data.getCustomerAddress());
                            edit_name.setText(data.getCustomerName());
                            edit_number.setText(data.getCustomerMobileNo());
                            edit_whatsappNumber.setText(data.getCustomerWhatsppNo());

                            productSelectRecycler.setLayoutManager(new LinearLayoutManager(context));
                            productSelectRecycler.setAdapter(new ViewWallkingProductAdapter(context, response.body().getData().get(0).getProductList()));
                        }
            }

            @Override
            public void onFailure(@NonNull Call<GetWallkingModel> call, @NonNull Throwable t) {

            }
        });

        dialog.show();

    }

    private void callToUser(String mobile) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + mobile));//change the number
        context.startActivity(callIntent);
    }

    private void openWhatsapp(String mobile) {
        String text = "https://api.whatsapp.com/send?phone=+91" + mobile + "&text='Hello User'";
        Uri mUri = Uri.parse(text);
        Intent intent = new Intent("android.intent.action.VIEW", mUri);
        intent.setPackage("com.whatsapp");
        try {
            context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "Whatsapp not installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void addFollowUp(WallkingModelCo.Datum data) {


        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.add_follow_up);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        LinearLayout submit = dialog.findViewById(R.id.submit_linear);
        LinearLayout cancle_linear = dialog.findViewById(R.id.cancle_linear);

        RecyclerView productSelectRecycler = dialog.findViewById(R.id.productSelectRecycler);
        EditText edit_address = dialog.findViewById(R.id.edit_address);
        EditText edit_whatsappNumber = dialog.findViewById(R.id.edit_whatsappNumber);
        EditText edit_number = dialog.findViewById(R.id.edit_number);
        EditText edit_date = dialog.findViewById(R.id.edit_date);
        EditText edit_name = dialog.findViewById(R.id.edit_name);
        EditText edit_description = dialog.findViewById(R.id.edit_description);

        edit_date.setOnClickListener(view -> showDatePickerDialog(edit_date));

        submit.setOnClickListener(View -> {
            if (edit_description.getText().toString().equalsIgnoreCase("")) {
                edit_description.setError("Enter Follow Up Description");
                edit_address.requestFocus();
            } else if (edit_date.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(context, "Select Date", Toast.LENGTH_SHORT).show();
            } else {
                addFollowApi(data.getCustomerWiId(), edit_description.getText().toString(), edit_date.getText().toString());
                dialog.dismiss();
            }
        });
        cancle_linear.setOnClickListener(View -> dialog.dismiss());

        edit_name.setText(data.getCustomerName());
        edit_address.setText(data.getCustomerAddress());
        edit_number.setText(data.getCustomerMobileNo());
        edit_whatsappNumber.setText(data.getCustomerWhatsppNo());

//
//        RetrofitClient.getClient(context).getCustomerWallkingDetails(id).enqueue(new Callback<GetWallkingModel>() {
//            @Override
//            public void onResponse(@NonNull Call<GetWallkingModel> call, @NonNull Response<GetWallkingModel> response) {
//                if (response.code() == 200)
//                    if (response.body() != null)
//                        if (response.body().getStatus() == 1) {
//
//                            GetWallkingModel.Datum data = response.body().getData().get(0);
//
//                            edit_address.setText(data.getCustomerAddress());
//                            edit_name.setText(data.getCustomerName());
//                            edit_number.setText(data.getCustomerMobileNo());
//                            edit_whatsappNumber.setText(data.getCustomerWhatsppNo());
//
//                            productSelectRecycler.setLayoutManager(new LinearLayoutManager(context));
//                            productSelectRecycler.setAdapter(new ViewWallkingProductAdapter(context, response.body().getData().get(0).getProductList()));
//                        }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<GetWallkingModel> call, @NonNull Throwable t) {
//
//            }
//        });


        dialog.show();

    }

    private void addFollowApi(String customerWiId, String s, String date) {

        ProgressDialog pd = new ProgressDialog(context);
        pd.show();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);
        RetrofitClient.getClient(context).addFollowUp(
                customerWiId,
                session.getUserId(),
                date,
                s
        ).enqueue(new Callback<AddFollowUpModel>() {
            @Override
            public void onResponse(@NonNull Call<AddFollowUpModel> call, @NonNull Response<AddFollowUpModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            // Toast.makeText(context, "Follow Up Added..", Toast.LENGTH_SHORT).show();
                            showSuccessDialogFollowUp();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<AddFollowUpModel> call, @NonNull Throwable t) {
                pd.dismiss();
                Toast.makeText(context, "Server Not Respond..", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        WallkingLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = WallkingLayoutBinding.bind(itemView);
        }
    }

    private void showSuccessDialogFollowUp() {

        new KAlertDialog(context, KAlertDialog.SUCCESS_TYPE)
                .setTitleText("Congratulation!")
                .setContentText("Follow Up Added..")
                .setConfirmText("Okay")
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismissWithAnimation();
                    }
                })
                .show();

    }

}

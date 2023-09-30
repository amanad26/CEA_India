package com.example.cea.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Models.WalletHistoryModel;
import com.example.cea.R;
import com.example.cea.databinding.WalletLayoutBinding;

import java.util.List;


public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {
    Context context;
    List<WalletHistoryModel.Datum> models;


    public WalletAdapter(Context context, List<WalletHistoryModel.Datum> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // return new  ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leaguecricketlayout,parent,false)
        return new ViewHolder(WalletLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.amount.setText(models.get(position).getWalletAmount());
        holder.binding.referBy.setText(models.get(position).getReferCustomerFname() + " " + models.get(position).getReferCustomerLname());
        holder.binding.date.setText(formatDate(models.get(position).getWallectCreatedDatetime()));
        holder.binding.orderId.setText(models.get(position).getOrderId());
        holder.binding.status.setText(models.get(position).getWalletStatus());
        holder.binding.sNumber.setText(String.valueOf(position + 1));

        if (models.get(position).getWalletStatus().equalsIgnoreCase("Credit")) {
            holder.binding.status.setTextColor(context.getResources().getColor(R.color.green));
            holder.binding.orderidLinear.setVisibility(View.GONE);
            holder.binding.orderidView.setVisibility(View.GONE);

            holder.binding.referByLinear.setVisibility(View.VISIBLE);
            holder.binding.referByView.setVisibility(View.VISIBLE);
        } else {
            holder.binding.status.setTextColor(context.getResources().getColor(R.color.red));
            holder.binding.orderidLinear.setVisibility(View.VISIBLE);
            holder.binding.orderidView.setVisibility(View.VISIBLE);

            holder.binding.referByLinear.setVisibility(View.GONE);
            holder.binding.referByView.setVisibility(View.GONE);
        }


    }

    public static String formatDate(String s) {
        //  s = "2022-10-21 08:08:18";

        String[] dateTime = s.split(" ");

        String dateS = dateTime[0];
//

        String[] datess = dateS.split("-");

        String year = datess[0];
        String month = datess[1];
        String day = datess[2];

        return day + " " + getMonthName(Integer.parseInt(month)) + " " + year + " ";
    }

    public static String getMonthName(int month) {

        String monthString;
        switch (month) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        System.out.println(monthString);

        return monthString;
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        WalletLayoutBinding binding;

        public ViewHolder(@NonNull WalletLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
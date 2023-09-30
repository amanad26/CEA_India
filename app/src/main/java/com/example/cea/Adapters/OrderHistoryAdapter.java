package com.example.cea.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Activities.OrderDetailsActivity;
import com.example.cea.Activities.PaymentActivity;
import com.example.cea.Models.OrderListModel;
import com.example.cea.R;
import com.example.cea.databinding.OrderHistoryLayoutBinding;
import com.example.cea.databinding.PaymentConfirmLayoutBinding;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    Context context;
    List<OrderListModel.Datum> models;

    public OrderHistoryAdapter(Context context, List<OrderListModel.Datum> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_history_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.amount.setText(models.get(position).getPayAmount());
        holder.binding.orderId.setText(models.get(position).getOrderId());
        holder.binding.totalQty.setText(models.get(position).getTotalSaleQty());
        holder.binding.sNumber.setText(String.valueOf(position + 1));
        holder.binding.date.setText(formatDate(models.get(position).getSaleCreatedDate()));

        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, OrderDetailsActivity.class)
                .putExtra("saleId", models.get(position).getSaleId())));

    }

    @Override
    public int getItemCount() {
        return models.size();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        OrderHistoryLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = OrderHistoryLayoutBinding.bind(itemView);
        }
    }
}

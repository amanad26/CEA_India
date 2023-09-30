package com.example.cea.co_oridinator.Adapters;

import static com.example.cea.co_oridinator.Adapters.ToDoAdapter.formatDate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.R;
import com.example.cea.co_oridinator.Models.TodayFollowUpModel;
import com.example.cea.co_oridinator.Models.TodoListModel;
import com.example.cea.databinding.DailyCallCustomerLayputBinding;

import java.util.List;

public class DailyCallCustomerAdapter extends RecyclerView.Adapter<DailyCallCustomerAdapter.ViewHolder> {

    Context context;
    List<TodayFollowUpModel.Datum> models;


    public DailyCallCustomerAdapter(Context context, List<TodayFollowUpModel.Datum> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_call_customer_layput, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.serial.setText(position + 1 + ". ");

        holder.binding.description.setText(models.get(position).getFollowUpDescription());
        holder.binding.userName.setText(models.get(position).getCustomerName());
        holder.binding.mobile.setText(models.get(position).getCustomerMobileNo());
        holder.binding.date.setText(formatDate(models.get(position).getFollowUpDate()));

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DailyCallCustomerLayputBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DailyCallCustomerLayputBinding.bind(itemView);
        }
    }


}

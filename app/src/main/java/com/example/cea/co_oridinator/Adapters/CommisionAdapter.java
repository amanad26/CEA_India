package com.example.cea.co_oridinator.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Apis.RetrofitClient;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Models.CommisionModel;
import com.example.cea.co_oridinator.Models.TodoListModel;
import com.example.cea.databinding.CommisionLayputBinding;
import com.example.cea.databinding.ToDoLayoutBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommisionAdapter extends RecyclerView.Adapter<CommisionAdapter.ViewHolder> {


    Context context;
    List<CommisionModel.Datum> models;
    Session session;

    public CommisionAdapter(Context context, List<CommisionModel.Datum> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.commision_layput, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.binding.productNmae.setText(models.get(position).getProductName());
        holder.binding.invoiceNumber.setText(models.get(position).getOrderId());
        holder.binding.mobileNUmber.setText(models.get(position).getCustomerMobileNo());
        holder.binding.comiision.setText(models.get(position).getCommisionAmount());


    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CommisionLayputBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CommisionLayputBinding.bind(itemView);
        }
    }


}

package com.example.cea.co_oridinator.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.co_oridinator.Activities.SaleDetailsActivity;
import com.example.cea.co_oridinator.Models.InvoiceListModel;
import com.example.cea.co_oridinator.Models.MySalesModel;
import com.example.cea.databinding.InvoiceListLayoutBinding;
import com.example.cea.databinding.SaleListLayoutBinding;

import java.util.List;

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.ViewHolder> {

    Context context;
    List<InvoiceListModel.Datum> models;

    public InvoiceListAdapter(Context context, List<InvoiceListModel.Datum> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.invoiceName.setText(models.get(position).getFirmName());

        holder.itemView.setOnClickListener(view -> {
            Log.e("TAG", "onBindViewHolder: Sale Invoice Path "+models.get(position).getSaleInvoicePath() );
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(models.get(position).getSaleInvoicePath()));
//            context.startActivity(i);


            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(models.get(position).getSaleInvoicePath()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                // Chrome browser presumably not installed so allow user to choose instead
                intent.setPackage(null);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        InvoiceListLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = InvoiceListLayoutBinding.bind(itemView);
        }
    }


}

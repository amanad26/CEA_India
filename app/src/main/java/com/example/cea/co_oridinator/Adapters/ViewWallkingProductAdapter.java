package com.example.cea.co_oridinator.Adapters;

import static com.example.cea.co_oridinator.Fragments.Co_WalkingFragment.selectedProduct;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.R;
import com.example.cea.co_oridinator.Models.GetWallkingModel;
import com.example.cea.co_oridinator.Models.ProductModelCo;
import com.example.cea.databinding.AddWallkingSpinnerLayoutBinding;
import com.example.cea.databinding.ViewWallkingProductLayoutBinding;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

public class ViewWallkingProductAdapter extends RecyclerView.Adapter<ViewWallkingProductAdapter.ViewHolder> {

    Context context;


    List<GetWallkingModel.Datum.Product> models;

    public ViewWallkingProductAdapter(Context context, List<GetWallkingModel.Datum.Product> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_wallking_product_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        try {

            holder.binding.userNameTv.setText(models.get(pos).getProductName());
//            holder.binding.userNameTv.setText(models.get(pos).getProductName() + " ( " + models.get(pos).getProductId() + " )");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewWallkingProductLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ViewWallkingProductLayoutBinding.bind(itemView);
        }
    }


}

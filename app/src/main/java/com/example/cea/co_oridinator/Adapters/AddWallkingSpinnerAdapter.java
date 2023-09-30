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

import com.developer.kalert.KAlertDialog;
import com.example.cea.R;
import com.example.cea.co_oridinator.Models.OnItemDelete;
import com.example.cea.co_oridinator.Models.ProductModelCo;
import com.example.cea.co_oridinator.Models.WallkingModelCo;
import com.example.cea.databinding.AddWallkingSpinnerLayoutBinding;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

public class AddWallkingSpinnerAdapter extends RecyclerView.Adapter<AddWallkingSpinnerAdapter.ViewHolder> {

    Context context;
    List<String> models;
    List<String> productNames;
    List<ProductModelCo.Datum> productList;
    OnItemDelete onItemDelete;

    public AddWallkingSpinnerAdapter(Context context, List<ProductModelCo.Datum> productList, OnItemDelete itemDelete) {
        this.context = context;
        this.productList = productList;
        this.onItemDelete = itemDelete;
    }

    //    public AddWallkingSpinnerAdapter(Context context, List<String> models, List<String> productNames, List<ProductModelCo.Datum> productList) {
//        this.context = context;
//        this.models = models;
//        this.productNames = productNames;
//        this.productList = productList;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.add_wallking_spinner_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        try {


            holder.binding.userNameTv.setText(productList.get(pos).getProductName());

            holder.itemView.setOnClickListener(view -> deleteItemDialog(pos));

//            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
//                    (context, android.R.layout.simple_spinner_item,
//                            productNames); //selected item will look like a spinner set from XML
//            spinnerArrayAdapter.setDropDownViewResource(android.R.layout
//                    .simple_spinner_dropdown_item);
//
//            holder.binding.spinner.setAdapter(spinnerArrayAdapter);
//
//            holder.binding.spinner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> {
////
//                ProductModelCo.Datum data = productList.get(position);
////                if (selectedProduct.size() == 0) {
////                    selectedProduct.add(productList.get(position));
////                    holder.binding.spinner.setVisibility(View.GONE);
////                    holder.binding.userNameTv.setText(item);
////                    holder.binding.linearData.setVisibility(View.VISIBLE);
////                } else {
////                    ProductModelCo.Datum newProduct = productList.get(position);
////                    if (!selectedProduct.contains(newProduct)) {
////                        selectedProduct.add(newProduct);
////                        holder.binding.spinner.setVisibility(View.GONE);
////                        holder.binding.userNameTv.setText(item);
////                        holder.binding.linearData.setVisibility(View.VISIBLE);
////                    } else {
////                        notifyItemChanged(pos);
////                        Toast.makeText(context, "Product Already Selected", Toast.LENGTH_SHORT).show();
////                    }
////                }
//                boolean isAdded = false;
//                for (int i = 0; i < selectedProduct.size(); i++) {
//                    if (selectedProduct.get(i).getProductId().equalsIgnoreCase(data.getProductId())) {
//                        isAdded = true;
//                        break;
//                    }
//                }
//                if (!isAdded) {
//                    selectedProduct.add(data);
//                    //notifyItemChanged(pos);
//                    //calculatePrice();
//                    holder.binding.spinner.setVisibility(View.GONE);
//                    holder.binding.userNameTv.setText(item);
//                    holder.binding.linearData.setVisibility(View.VISIBLE);
//                    Log.e("TAG", "onResponse: Addedd");
//                } else {
//                    notifyItemChanged(pos);
//                    Toast.makeText(context, "Product Already Added..!", Toast.LENGTH_SHORT).show();
//                }
//
//
//                Log.e("TAG", "showDialog: Selected Item Size " + selectedProduct.size());
//            });
//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void deleteItemDialog(int pos) {
        new KAlertDialog(context, KAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("you want to delete this product ?")
                .setCancelClickListener("No!", new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismissWithAnimation();
                    }
                })
                .setConfirmClickListener("Yes,delete it!", new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        onItemDelete.onDelete(pos);
//                        notifyItemRemoved(pos);
//                        notifyItemRangeChanged(0, productList.size());
//                        productList.remove(pos);

                    }
                }).show();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AddWallkingSpinnerLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = AddWallkingSpinnerLayoutBinding.bind(itemView);
        }
    }


}

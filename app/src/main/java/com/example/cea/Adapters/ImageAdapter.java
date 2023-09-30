package com.example.cea.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.MyCarListInterface;
import com.example.cea.Models.ProductDetailsModel;
import com.example.cea.Models.ProductListModel;
import com.example.cea.R;
import com.example.cea.databinding.ImageLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    Context context;
    List<ProductDetailsModel.Datum.ProductImgRe> models;
    ArrayList<String> images;
    MyCarListInterface anInterface;

//    public ImageAdapter(Context context, List<ProductDetailsModel.Datum.ProductImgRe> models) {
//        this.context = context;
//        this.models = models;
//    }


    public ImageAdapter(Context context, ArrayList<String> images, MyCarListInterface anInterface) {
        this.context = context;
        this.images = images;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.image_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(BaseUrls.ImageBaseUrl + images.get(position))
                .placeholder(R.drawable.model_1).into(holder.binding.productImage);

        holder.binding.productImage.setOnClickListener(view -> anInterface.onSelect(holder.getAdapterPosition()));

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ImageLayoutBinding.bind(itemView);
        }
    }

}

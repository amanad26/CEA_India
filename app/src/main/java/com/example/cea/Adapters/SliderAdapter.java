package com.example.cea.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cea.Apis.BaseUrls;
import com.example.cea.Models.OfferListModel;
import com.example.cea.R;
import com.example.cea.databinding.OfferListLayoutBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {

    Context context;
    List<OfferListModel.Datum> models;

    public SliderAdapter(Context context, List<OfferListModel.Datum> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.offer_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Picasso.get().load(BaseUrls.ImageBaseUrl + models.get(position).getOfferImage()).placeholder(R.drawable.car1).into(viewHolder.binding.image);

        viewHolder.binding.amount.setText(models.get(position).getOfferName());
        viewHolder.binding.price.setText(models.get(position).getOfferAmount() + " %OFF");


    }

    @Override
    public int getCount() {
        return models.size();
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {
        OfferListLayoutBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = OfferListLayoutBinding.bind(itemView);
        }
    }
}

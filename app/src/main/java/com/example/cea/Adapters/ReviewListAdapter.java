package com.example.cea.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cea.Models.ReviewListModel;
import com.example.cea.R;
import com.example.cea.databinding.ReviewListLayoutBinding;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {

    Context context;
    List<ReviewListModel.Datum> models;
    int count;

    public ReviewListAdapter(Context context, List<ReviewListModel.Datum> models, int count) {
        this.context = context;
        this.models = models;
        this.count = count;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.review_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.date.setText(models.get(position).getReviewCratedDateTime());
        holder.binding.name.setText(models.get(position).getCustomerFname() + " " + models.get(position).getCustomerLname());
        holder.binding.ratingCount.setText(models.get(position).getRating());
        holder.binding.review.setText(models.get(position).getReview());

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ReviewListLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ReviewListLayoutBinding.bind(itemView);
        }
    }

}

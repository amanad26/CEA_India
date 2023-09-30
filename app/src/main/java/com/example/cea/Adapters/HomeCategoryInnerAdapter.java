package com.example.cea.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cea.Activities.ProductDetailsActivity;
import com.example.cea.Apis.BaseUrls;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.HomePageDataModel;
import com.example.cea.Models.ReviewListModel;
import com.example.cea.R;
import com.example.cea.databinding.HomePageMainCatLayoutBinding;
import com.example.cea.databinding.SingleProductLayoutBinding;
import com.taufiqrahman.reviewratings.BarLabels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeCategoryInnerAdapter extends RecyclerView.Adapter<HomeCategoryInnerAdapter.ViewHolder> {

    Context context;
    float avgRating = 0;
    List<HomePageDataModel.CategoryDatum.Datum__1> models;

    List<String> rating1 = new ArrayList<>();
    List<String> rating2 = new ArrayList<>();
    List<String> rating3 = new ArrayList<>();
    List<String> rating4 = new ArrayList<>();
    List<String> rating5 = new ArrayList<>();

    public HomeCategoryInnerAdapter(Context context, List<HomePageDataModel.CategoryDatum.Datum__1> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_product_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.productName.setText(models.get(position).getProductName());
        holder.binding.productPrice.setText(models.get(position).getProductMop());
        holder.binding.productSalePrice.setText(models.get(position).getProductFinalAmount());
        holder.binding.productType.setText(models.get(position).getProductSubType());
        holder.binding.productSaleDisc.setText(models.get(position).getProductMaxDiscount() + "%");
        Glide.with(context).load(BaseUrls.ImageBaseUrl + models.get(position).getProductImage())
                .placeholder(R.drawable.model_1).into(holder.binding.productImage);

        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, ProductDetailsActivity.class)
                .putExtra("pro_id", models.get(position).getProductId())));

        try {

            holder.binding.favIcon.setVisibility(View.GONE);

            holder.binding.textRating.setText("0");
            getReviewList(models.get(position).getProductId(), holder);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getReviewList(String pId, ViewHolder holder) {

        avgRating = 0;

        rating1 = new ArrayList<>();
        rating2 = new ArrayList<>();
        rating3 = new ArrayList<>();
        rating4 = new ArrayList<>();
        rating5 = new ArrayList<>();

        RetrofitClient.getClient(context).getReviewList(pId).enqueue(new Callback<ReviewListModel>() {
            @Override
            public void onResponse(@NonNull Call<ReviewListModel> call, @NonNull Response<ReviewListModel> response) {

                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            int c = 0;
                            if (response.body().getData().size() != 0) {

                                for (int i = 0; i < response.body().getData().size(); i++) {

                                    switch (response.body().getData().get(i).getRating()) {
                                        case "1":
                                            rating1.add(response.body().getData().get(i).getRating());
                                            break;
                                        case "2":
                                            rating2.add(response.body().getData().get(i).getRating());
                                            break;
                                        case "3":
                                            rating3.add(response.body().getData().get(i).getRating());
                                            break;
                                        case "4":
                                            rating4.add(response.body().getData().get(i).getRating());
                                            break;
                                        case "5":
                                            rating5.add(response.body().getData().get(i).getRating());
                                            break;
                                    }
                                }

                                holder.binding.textRating.setText(String.valueOf(calculateRating(response.body().getData().size())));
                                Log.e("TAG", "onResponse: " + avgRating);
                            }
                        }


            }


            @Override
            public void onFailure(@NonNull Call<ReviewListModel> call, @NonNull Throwable t) {

            }
        });


    }

    private float calculateRating(int count) {
        int r1 = rating1.size();
        int r2 = rating2.size();
        int r3 = rating3.size();
        int r4 = rating4.size();
        int r5 = rating5.size();

        float avg = (r1 * 1) + (r2 * 2) + (r3 * 3) + (r4 * 4) + (r5 * 5);
        avg = avg / count;

        return avg;

    }


    @Override
    public int getItemCount() {
        return Math.min(models.size(), 8);
        // return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SingleProductLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleProductLayoutBinding.bind(itemView);

        }
    }
}

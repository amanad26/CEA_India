package com.example.cea.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.cea.Adapters.ImageAdapter;
import com.example.cea.Adapters.ReviewListAdapter;
import com.example.cea.Adapters.VehicleImagesViewPager;
import com.example.cea.Apis.ApiInterface;
import com.example.cea.Apis.MyCarListInterface;
import com.example.cea.Apis.RetrofitClient;
import com.example.cea.Models.AddCartModel;
import com.example.cea.Models.CartListModel;
import com.example.cea.Models.CheckCartModel;
import com.example.cea.Models.ProductDetailsModel;
import com.example.cea.Models.ReviewListModel;
import com.example.cea.Models.UpdateProfileModel;
import com.example.cea.R;
import com.example.cea.Session.Session;
import com.example.cea.Utills.ProgressDialog;
import com.example.cea.databinding.ActivityProductDetailsBinding;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.taufiqrahman.reviewratings.BarLabels;
import com.vdx.designertoast.DesignerToast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity implements MyCarListInterface {

    ActivityProductDetailsBinding binding;
    String selectedSpecificationID = "";
    Activity activity;
    String productId = "";
    ApiInterface apiInterface;
    Session session;
    ProgressDialog pd;
    String specificationId = "0", productTaxamount = "", productFinalAmount = "", productMrp = "", productTax = "", productSalePrice = "";
    List<String> rating1 = new ArrayList<>();
    List<String> rating2 = new ArrayList<>();
    List<String> rating3 = new ArrayList<>();
    List<String> rating4 = new ArrayList<>();
    List<String> rating5 = new ArrayList<>();
    List<String> specificationNamesList = new ArrayList<>();
    List<String> specificationNamesListId = new ArrayList<>();
    List<String> productTaxList = new ArrayList<>();
    List<String> productTaxAmountList = new ArrayList<>();
    List<String> productSalePriceList = new ArrayList<>();
    List<String> productMrpList = new ArrayList<>();
    List<String> productFinalAmountList = new ArrayList<>();
    List<String> specificationPrice = new ArrayList<>();
    ProductDetailsModel.Datum productData;
    boolean isLiked = false;
    ArrayList<String> imagesList = new ArrayList<>();
    private VehicleImagesViewPager viewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;
        session = new Session(activity);
        apiInterface = RetrofitClient.getClient(activity);
        pd = new ProgressDialog(activity);


        productId = getIntent().getStringExtra("pro_id");

        binding.favVehicle2.setOnClickListener(view -> {
            if (isLiked) {
                binding.favVehicle2.setImageResource(R.drawable.ic_fav_icon);
                isLiked = false;
            } else {
                binding.favVehicle2.setImageResource(R.drawable.ic_baseline_favorite_24);
                isLiked = true;
            }

            LikeUnlikeProduct();
        });
        binding.backFromPDetails.setOnClickListener(view -> onBackPressed());

        binding.addToCart.setOnClickListener(view -> {
            if (session.sharedPreferences.contains("user_id")) {
                if (selectedSpecificationID.equalsIgnoreCase(""))
                    Toast.makeText(activity, "Select Specification", Toast.LENGTH_SHORT).show();
                else checkCartProduct();
            } else {
                Toast.makeText(activity, "Please Login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(activity, LoginActivity.class));
                finish();
            }

        });


        binding.viewMore.setOnClickListener(view -> startActivity(new Intent(activity, ReviewListActivity.class).putExtra("product_id", productId)));

        binding.buyNowBtn.setOnClickListener(view -> {
            if (session.sharedPreferences.contains("user_id")) {
                if (selectedSpecificationID.equalsIgnoreCase(""))
                    Toast.makeText(activity, "Select Specification", Toast.LENGTH_SHORT).show();
                else addToCart(true);
            } else {
                Toast.makeText(activity, "Please Login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(activity, LoginActivity.class));
                finish();
            }


        });

    }

    private void addToCart(boolean isBuy) {

        apiInterface.addToCart(
                session.getUserId(),
                productId,
                "1",
                productSalePrice,
                selectedSpecificationID,
                productTax,
                productTaxamount,
                productFinalAmount,
                productMrp,
                session.getPincode()
        ).enqueue(new Callback<AddCartModel>() {
            @Override
            public void onResponse(@NonNull Call<AddCartModel> call, @NonNull Response<AddCartModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            DesignerToast.Success(activity, "Success", "Product Added..", Gravity.BOTTOM, Toast.LENGTH_SHORT, DesignerToast.STYLE_DARK);

                            if (isBuy)
                                startActivity(new Intent(activity, CheckoutActivity.class));

                        } else {
                            DesignerToast.Warning(activity, "Warning", "Failed...", Gravity.CENTER, Toast.LENGTH_SHORT, DesignerToast.STYLE_DARK);

                        }
            }

            @Override
            public void onFailure(@NonNull Call<AddCartModel> call, @NonNull Throwable t) {
                DesignerToast.Info(activity, "Info", "Server Not Respond...", Gravity.CENTER, Toast.LENGTH_SHORT, DesignerToast.STYLE_DARK);
            }
        });


    }

    private void checkCartProduct() {

        RetrofitClient.getClient(activity).checkCartProduct(
                session.getUserId(),
                productId,
                specificationId
        ).enqueue(new Callback<CheckCartModel>() {
            @Override
            public void onResponse(@NonNull Call<CheckCartModel> call, @NonNull Response<CheckCartModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            int q = Integer.parseInt(response.body().getData().getProductQty());
                            q++;
                            updateCart(q, response.body().getData().getCartId());
                            //Toast.makeText(activity, "Already Added into cart", Toast.LENGTH_SHORT).show();
                        } else {
                            addToCart(false);
                        }
            }

            @Override
            public void onFailure(@NonNull Call<CheckCartModel> call, @NonNull Throwable t) {

            }
        });


    }


    private void updateCart(int q, String cartId) {

        RetrofitClient.getClient(activity).updateCart(
                cartId,
                String.valueOf(q),
                session.getPincode()
        ).enqueue(new Callback<CartListModel>() {
            @Override
            public void onResponse(@NonNull Call<CartListModel> call, @NonNull Response<CartListModel> response) {
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            Toast.makeText(activity, "Added into cart", Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onFailure(@NonNull Call<CartListModel> call, @NonNull Throwable t) {

            }
        });

    }

    private void LikeUnlikeProduct() {
        apiInterface.addRemoveFavorite(
                session.getUserId(),
                productId,
                specificationId,
                session.getPincode()
        ).enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateProfileModel> call, @NonNull Response<UpdateProfileModel> response) {

            }

            @Override
            public void onFailure(@NonNull Call<UpdateProfileModel> call, @NonNull Throwable t) {

            }
        });

    }

    private void getReviewList() {

        rating1.clear();
        rating2.clear();
        rating3.clear();
        rating4.clear();
        rating5.clear();


        apiInterface.getReviewList(productId).enqueue(new Callback<ReviewListModel>() {
            @Override
            public void onResponse(@NonNull Call<ReviewListModel> call, @NonNull Response<ReviewListModel> response) {

                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            int c = 0;
                            if (response.body().getData().size() != 0) {

                                c = Math.min(2, response.body().getData().size());

                                binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                                binding.recyclerView.setAdapter(new ReviewListAdapter(activity, response.body().getData(), c));

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

                                binding.toalReviewAvg.setText(String.valueOf(calculateRating(response.body().getData().size())));
                                binding.toalReviewCount.setText(String.valueOf(response.body().getData().size()));
                                binding.ratingCount.setText(String.valueOf(response.body().getData().size()) + " Ratings");
                                binding.reviewRatingBar.setRating(calculateRating(response.body().getData().size()));

                                int[] colors = new int[]{
                                        Color.parseColor("#04914c"),
                                        Color.parseColor("#04914c"),
                                        Color.parseColor("#04914c"),
                                        Color.parseColor("#04914c"),
                                        Color.parseColor("#04914c")};

                                int[] raters = new int[]{
                                        rating5.size(),
                                        rating4.size(),
                                        rating3.size(),
                                        rating2.size(),
                                        rating1.size()
                                };

                                binding.ratingReviews.createRatingBars(100, BarLabels.STYPE3, colors, raters);
                            } else {
                                binding.viewMore.setVisibility(View.GONE);
                            }
                        } else {
                            binding.viewMore.setVisibility(View.GONE);
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
//
//        float avg = (r1) + (r2 * 2) + (r3 * 3) + (r4 * 4) + (r5 * 5);
//        avg = avg / count;

        Log.e("TAG", "calculateRating() called with: r1 = [" + r1 + "]");
        Log.e("TAG", "calculateRating() called with: r2 = [" + r2 + "]");
        Log.e("TAG", "calculateRating() called with: r3 = [" + r3 + "]");
        Log.e("TAG", "calculateRating() called with: r4 = [" + r4 + "]");
        Log.e("TAG", "calculateRating() called with: r5 = [" + r5 + "]");

        float avg = 0;

        float c = (r1) + (r2) + (r3) + (r4) + (r5);
        float a = (r1) + (r2 * 2) + (r3 * 3) + (r4 * 4) + (r5 * 5);
        avg = a / c;
        Log.e("TAG", "calculateRating() called with: c = [" + c + "]");
        Log.e("TAG", "calculateRating() called with: a = [" + a + "]");
        Log.e("TAG", "calculateRating() called with: avg = [" + avg + "]");

        return avg;

    }

    @Override
    protected void onResume() {
        super.onResume();
        getProductDetails();
        getReviewList();
    }

    private void getProductDetails() {

        apiInterface.getSingleProductDetail(
                productId,
                session.getUserId()
        ).enqueue(new Callback<ProductDetailsModel>() {
            @Override
            public void onResponse(@NonNull Call<ProductDetailsModel> call, @NonNull Response<ProductDetailsModel> response) {
                pd.dismiss();
                if (response.code() == 200)
                    if (response.body() != null)
                        if (response.body().getStatus() == 1) {
                            productData = response.body().getData().get(0);

                            binding.productName.setText(productData.getProductName());
                            // binding.productSubType.setText(productData.getProductSubType());
                            binding.sCDescription.setText(Html.fromHtml(productData.getProductDescription()));
                            binding.price.setText(productData.getProductFinalAmount());
                            binding.catName.setText("Category : " + productData.getCategory_name());

                            binding.productMrp.setText(Html.fromHtml("<strike> MOP " + productData.getProductMop() + "</strike>"));
                            // binding.ratingCount.setText(productData.getTotalStarCount() + " Ratings");

                            if (productData.getProductOffer() != null) {
                                if (productData.getProductOffer().getOfferType().equalsIgnoreCase("Percentage"))
                                    binding.discount.setText(productData.getProductOffer().getOfferAmount() + " % OFF");
                                else
                                    binding.discount.setText(productData.getProductOffer().getOfferAmount() + " OFF");
                            }


                            imagesList.clear();
                            for (int i = 0; i < productData.getProductImgRes().size(); i++) {
                                imagesList.add(productData.getProductImgRes().get(i).getProductImages());
                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                            binding.sCImagesRecy.setLayoutManager(linearLayoutManager);
                            binding.sCImagesRecy.setAdapter(new ImageAdapter(activity, imagesList, ProductDetailsActivity.this));

                            viewpagerAdapter = new VehicleImagesViewPager(ProductDetailsActivity.this, imagesList);
                            binding.imageViewpager.setAdapter(viewpagerAdapter);
                            viewpagerAdapter.setTimer(binding.imageViewpager, 4);

                            specificationId = productData.getProductSpecification();

                            specificationPrice.add("0");
                            specificationNamesListId.add("0");
                            specificationNamesList.add("Select specification");
                            productMrpList.add("0");
                            productFinalAmountList.add("0");
                            productTaxAmountList.add("0");
                            productSalePriceList.add("0");
                            productTaxList.add("0");

                            if (productData.getProductSpecificationResult().size() != 0) {
                                for (int i = 0; i < productData.getProductSpecificationResult().size(); i++) {
                                    specificationNamesList.add(productData.getProductSpecificationResult().get(i).getSpecificationName());
                                    specificationNamesListId.add(productData.getProductSpecificationResult().get(i).getSpecificationId());
                                    specificationPrice.add(productData.getProductSpecificationResult().get(i).getProductFinalAmount());
                                    productMrpList.add(productData.getProductSpecificationResult().get(i).getProductMop());
                                    productFinalAmountList.add(productData.getProductSpecificationResult().get(i).getProductFinalAmount());
                                    productTaxList.add(productData.getProductSpecificationResult().get(i).getProductTax());
                                    productTaxAmountList.add(productData.getProductSpecificationResult().get(i).getProductTaxAmount());
                                    productSalePriceList.add(productData.getProductSpecificationResult().get(i).getProductFinalAmount());
                                }
//                                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(activity);
//                                linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
//                                binding.specificationRecycelr.setLayoutManager(linearLayoutManager2);
//                                binding.specificationRecycelr.setAdapter(new SpecificationAdapter(activity, productData.getProductSpecificationResult()));

                                MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
                                spinner.setItems(specificationNamesList);


                                spinner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> {
                                    if (!specificationNamesList.get(position).equalsIgnoreCase("Select specification")) {
                                        selectedSpecificationID = specificationNamesListId.get(position);
                                        binding.price.setText(specificationPrice.get(position));
                                        productTax = productTaxList.get(position);
                                        productTaxamount = productTaxAmountList.get(position);
                                        productMrp = productMrpList.get(position);
                                        productFinalAmount = productFinalAmountList.get(position);
                                        productSalePrice = productSalePriceList.get(position);
                                    }
                                });

                            }

                            if (productData.getFavoriteProductRes().equalsIgnoreCase("true")) {
                                isLiked = true;
                                binding.favVehicle2.setImageResource(R.drawable.ic_baseline_favorite_24);
                            }


                        }
            }

            @Override
            public void onFailure(@NonNull Call<ProductDetailsModel> call, @NonNull Throwable t) {
                pd.dismiss();
                Toast.makeText(activity, "Server Not Respond..", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onSelect(int pos) {
        binding.imageViewpager.setCurrentItem(pos);
    }
}
package com.example.cea.Apis;

import static com.example.cea.Apis.BaseUrls.addCoordinationSale;
import static com.example.cea.Apis.BaseUrls.addFollowup;
import static com.example.cea.Apis.BaseUrls.addNewCustomer;
import static com.example.cea.Apis.BaseUrls.addRemoveFavorite;
import static com.example.cea.Apis.BaseUrls.addReview;
import static com.example.cea.Apis.BaseUrls.addToCart;
import static com.example.cea.Apis.BaseUrls.cartList;
import static com.example.cea.Apis.BaseUrls.categoryList;
import static com.example.cea.Apis.BaseUrls.changePassword;
import static com.example.cea.Apis.BaseUrls.checkCartProduct;
import static com.example.cea.Apis.BaseUrls.checkMobileEmailAddress;
import static com.example.cea.Apis.BaseUrls.checkoutList;
import static com.example.cea.Apis.BaseUrls.checkoutProductList;
import static com.example.cea.Apis.BaseUrls.checkoutscreen;
import static com.example.cea.Apis.BaseUrls.checkoutscreenNew;
import static com.example.cea.Apis.BaseUrls.commisionList;
import static com.example.cea.Apis.BaseUrls.completeTodo;
import static com.example.cea.Apis.BaseUrls.customerList;
import static com.example.cea.Apis.BaseUrls.favoriteList;
import static com.example.cea.Apis.BaseUrls.firmWiseInvoice;
import static com.example.cea.Apis.BaseUrls.forgotPassword;
import static com.example.cea.Apis.BaseUrls.getAvailableProductList;
import static com.example.cea.Apis.BaseUrls.getCategoryWiseProduct;
import static com.example.cea.Apis.BaseUrls.getProductDetails;
import static com.example.cea.Apis.BaseUrls.homePageCategoryProduct;
import static com.example.cea.Apis.BaseUrls.howToUseList;
import static com.example.cea.Apis.BaseUrls.login;
import static com.example.cea.Apis.BaseUrls.movetoCart;
import static com.example.cea.Apis.BaseUrls.newestProduct;
import static com.example.cea.Apis.BaseUrls.offerList;
import static com.example.cea.Apis.BaseUrls.payment;
import static com.example.cea.Apis.BaseUrls.productReviewList;
import static com.example.cea.Apis.BaseUrls.qrcodeScan;
import static com.example.cea.Apis.BaseUrls.removeCart;
import static com.example.cea.Apis.BaseUrls.saleHistory;
import static com.example.cea.Apis.BaseUrls.saleProductDetails;
import static com.example.cea.Apis.BaseUrls.searchProduct;
import static com.example.cea.Apis.BaseUrls.signup;
import static com.example.cea.Apis.BaseUrls.subcategoryList;
import static com.example.cea.Apis.BaseUrls.todayFollowup;
import static com.example.cea.Apis.BaseUrls.todoList;
import static com.example.cea.Apis.BaseUrls.updateCart;
import static com.example.cea.Apis.BaseUrls.updateCoordinationProfile;
import static com.example.cea.Apis.BaseUrls.updateProfile;
import static com.example.cea.Apis.BaseUrls.walkingCustomerAdd;
import static com.example.cea.Apis.BaseUrls.walkingCustomerFullDetails;
import static com.example.cea.Apis.BaseUrls.walkingCustomerList;
import static com.example.cea.Apis.BaseUrls.walletHistory;
import static com.example.cea.Apis.BaseUrls.webviewList;

import com.example.cea.Models.AddCartModel;
import com.example.cea.Models.AddReviewModel;
import com.example.cea.Models.BannerModel;
import com.example.cea.Models.CartListModel;
import com.example.cea.Models.CategoryModel;
import com.example.cea.Models.CheckCartModel;
import com.example.cea.Models.CheckoutModel;
import com.example.cea.Models.FavoriteListModel;
import com.example.cea.Models.FavoriteModel;
import com.example.cea.Models.HomePageDataModel;
import com.example.cea.Models.LoginModel;
import com.example.cea.Models.OderDetailsModel;
import com.example.cea.Models.OfferListModel;
import com.example.cea.Models.OrderListModel;
import com.example.cea.Models.OrderPlaceModel;
import com.example.cea.Models.PrivacyPolicyModel;
import com.example.cea.Models.ProductDetailsModel;
import com.example.cea.Models.ProductListModel;
import com.example.cea.Models.RemoveCartModel;
import com.example.cea.Models.ReviewListModel;
import com.example.cea.Models.SearchModel;
import com.example.cea.Models.SubCategoryModel;
import com.example.cea.Models.UpdateProfileModel;
import com.example.cea.Models.WalletHistoryModel;
import com.example.cea.co_oridinator.Models.AddCustomerModel;
import com.example.cea.co_oridinator.Models.AddFollowUpModel;
import com.example.cea.co_oridinator.Models.AddSaleModel;
import com.example.cea.co_oridinator.Models.AddWallkingCustomerModel;
import com.example.cea.co_oridinator.Models.AppLockModel;
import com.example.cea.co_oridinator.Models.Co_LoginModel;
import com.example.cea.co_oridinator.Models.CommisionModel;
import com.example.cea.co_oridinator.Models.GetWallkingModel;
import com.example.cea.co_oridinator.Models.InvoiceListModel;
import com.example.cea.co_oridinator.Models.MySalesModel;
import com.example.cea.co_oridinator.Models.ProductModelCo;
import com.example.cea.co_oridinator.Models.QrCodeProductModel;
import com.example.cea.co_oridinator.Models.SaleDetailsModel;
import com.example.cea.co_oridinator.Models.TodayFollowUpModel;
import com.example.cea.co_oridinator.Models.TodoListModel;
import com.example.cea.co_oridinator.Models.UpdateCordinateProfile;
import com.example.cea.co_oridinator.Models.WallkingModelCo;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {


    @FormUrlEncoded
    @POST(login)
    Call<LoginModel> loginUser(
            @Field("type") String type,
            @Field("mobile_no") String mobile_no,
            @Field("password") String password,
            @Field("fcm_token") String fcm_token
    );


    @FormUrlEncoded
    @POST(signup)
    Call<LoginModel> signupUser(
            @Field("customer_fname") String customer_fname,
            @Field("customer_lname") String customer_lname,
            @Field("customer_mobile_no") String customer_mobile_no,
            @Field("customer_email") String customer_email,
            @Field("customer_password") String customer_password,
            @Field("customer_referal_by") String customer_referal_by
    );

    @FormUrlEncoded
    @POST(checkMobileEmailAddress)
    Call<LoginModel> checkMobile(
            @Field("type") String type,
            @Field("mobile_email") String mobile_email
    );

    @FormUrlEncoded
    @POST(forgotPassword)
    Call<LoginModel> forgotPassword(
            @Field("customer_mobile_no") String customer_mobile_no
    );

    @FormUrlEncoded
    @POST(webviewList)
    Call<PrivacyPolicyModel> getPrivacyAndOther(
            @Field("type") String type
    );


    @POST(categoryList)
    Call<CategoryModel> getCategory();

    @POST(offerList)
    Call<OfferListModel> getOffers();

    @FormUrlEncoded
    @POST(getCategoryWiseProduct)
    Call<ProductListModel> getProductListByCategory(
            @Field("category_id") String category_id,
            @Field("pincode_number") String pincode_number,
            @Field("customer_id") String customer_id,
            @Field("sort") String sort,
            @Field("offset") String offset
    );

    @FormUrlEncoded
    @POST(getProductDetails)
    Call<ProductDetailsModel> getSingleProductDetail(
            @Field("product_id") String product_id,
            @Field("customer_id") String customer_id

    );

    @FormUrlEncoded
    @POST(updateProfile)
    Call<UpdateProfileModel> updateUserProfile(
            @Field("customer_id") String customer_id,
            @Field("customer_fname") String customer_fname,
            @Field("customer_lname") String customer_lname,
            @Field("customer_anniversery_date") String customer_anniversery_date,
            @Field("customer_whatspp_no") String customer_whatspp_no,
            @Field("customer_dob") String customer_dob,
            @Field("customer_address") String customer_address,
            @Field("customer_pincode") String customer_pincode
    );

    @FormUrlEncoded
    @POST(updateCoordinationProfile)
    Call<UpdateCordinateProfile> updateCodinateProfile(
            @Field("coordination_id") String coordination_id,
            @Field("coordination_name") String coordination_name,
            @Field("coordination_address") String coordination_address
    );


    @FormUrlEncoded
    @POST(updateCoordinationProfile)
    Call<UpdateCordinateProfile> updateCodinateProfilePassword(
            @Field("coordination_id") String coordination_id,
            @Field("coordination_name") String coordination_name,
            @Field("coordination_password") String coordination_password,
            @Field("coordination_address") String coordination_address
    );


    @FormUrlEncoded
    @POST(changePassword)
    Call<UpdateProfileModel> changePassword(
            @Field("customer_mobile_no") String customer_mobile_no,
            @Field("customer_password") String customer_password
    );

    @FormUrlEncoded
    @POST(addRemoveFavorite)
    Call<UpdateProfileModel> addRemoveFavorite(
            @Field("customer_id") String customer_id,
            @Field("product_id") String product_id,
            @Field("product_specification") String product_specification,
            @Field("customer_pincode") String customer_pincode
    );

    @FormUrlEncoded
    @POST(movetoCart)
    Call<UpdateProfileModel> moveToCart(
            @Field("favorite_id") String favorite_id,
            @Field("customer_id") String customer_id,
            @Field("customer_pincode") String customer_pincode
    );


    @FormUrlEncoded
    @POST(favoriteList)
    Call<FavoriteListModel> myFavoriteProductList(
            @Field("customer_id") String customer_id,
            @Field("offset") String offset
    );


    @FormUrlEncoded
    @POST(addToCart)
    Call<AddCartModel> addToCart(
            @Field("customer_id") String customer_id,
            @Field("product_id") String product_id,
            @Field("product_qty") String product_qty,
            @Field("product_sale_price") String product_sale_price,
            @Field("specification_id") String specification_id,
            @Field("product_tax") String product_tax,
            @Field("product_tax_amount") String product_tax_amount,
            @Field("product_final_amount") String product_final_amount,
            @Field("product_mop") String product_mop,
            @Field("customer_pincode") String customer_pincode
    );


    @FormUrlEncoded
    @POST(checkCartProduct)
    Call<CheckCartModel> checkCartProduct(
            @Field("customer_id") String customer_id,
            @Field("product_id") String product_id,
            @Field("specification_id") String specification_id
    );


    @FormUrlEncoded
    @POST(cartList)
    Call<CartListModel> getCartList(
            @Field("customer_id") String customer_id,
            @Field("customer_pincode") String customer_pincode
    );


    @FormUrlEncoded
    @POST(updateCart)
    Call<CartListModel> updateCart(
            @Field("cart_id") String cart_id,
            @Field("product_qty") String product_qty,
            @Field("customer_pincode") String customer_pincode
    );

    @FormUrlEncoded
    @POST(removeCart)
    Call<RemoveCartModel> removeCart(
            @Field("cart_id") String cart_id
    );


    @FormUrlEncoded
    @POST(homePageCategoryProduct)
    Call<HomePageDataModel> getHomeData(
            @Field("pincode_number") String pincode_number,
            @Field("customer_id") String customer_id,
            @Field("offset") String offset
    );

    @FormUrlEncoded
    @POST(searchProduct)
    Call<SearchModel> searchProduct(
            @Field("keyword") String keyword,
            @Field("offset") String offset
    );

    @FormUrlEncoded
    @POST(checkoutscreen)
    Call<CheckoutModel> getCheckOutProduct(
            @Field("customer_id") String customer_id,
            @Field("customer_pincode") String customer_pincode
    );

    @FormUrlEncoded
    @POST(checkoutscreenNew)
    Call<CheckoutModel> getCheckOutProductNotAvailable(
            @Field("customer_id") String customer_id,
            @Field("customer_pincode") String customer_pincode
    );


    @FormUrlEncoded
    @POST(productReviewList)
    Call<ReviewListModel> getReviewList(
            @Field("product_id") String product_id
    );


    @FormUrlEncoded
    @POST(payment)
    Call<OrderPlaceModel> placeOrder(
            @Field("customer_id") String customer_id,
            @Field("transaction_id") String transaction_id,
            @Field("customer_pincode") String customer_pincode
    );

    @FormUrlEncoded
    @POST(checkoutList)
    Call<OrderListModel> getOrderHistory(
            @Field("customer_id") String customer_id
    );


    @FormUrlEncoded
    @POST(checkoutProductList)
    Call<OderDetailsModel> getOrderDetails(
            @Field("customer_id") String customer_id,
            @Field("sale_id") String sale_id
    );

    @FormUrlEncoded
    @POST(walletHistory)
    Call<WalletHistoryModel> getWalletHistory(
            @Field("customer_id") String customer_id
    );

    @FormUrlEncoded
    @POST(addReview)
    Call<AddReviewModel> addReview(
            @Field("customer_id") String customer_id,
            @Field("product_id") String product_id,
            @Field("rating") String rating,
            @Field("review") String review
    );

    @FormUrlEncoded
    @POST(subcategoryList)
    Call<SubCategoryModel> getSubCategory(
            @Field("category_id") String category_id
    );

    @GET(howToUseList)
    Call<BannerModel> getBannerList();


    ///Co-ordination

    @FormUrlEncoded
    @POST(login)
    Call<Co_LoginModel> loginCo_ordination(
            @Field("mobile_no") String mobile_no,
            @Field("password") String password,
            @Field("type") String type
    );


    @GET(newestProduct)
    Call<ProductModelCo> getNewestProducts();


    @GET(getAvailableProductList)
    Call<ProductModelCo> getAllProducts();


    @FormUrlEncoded
    @POST(walkingCustomerList)
    Call<WallkingModelCo> getCustomerWallking(@Field("coordination_id") String coordination_id);


    @FormUrlEncoded
    @POST(walkingCustomerAdd)
    Call<AddWallkingCustomerModel> addCustomerWallking(
            @Field("coordination_id") String coordination_id,
            @Field("customer_name") String customer_name,
            @Field("customer_mobile_no") String customer_mobile_no,
            @Field("customer_whatspp_no") String customer_whatspp_no,
            @Field("customer_address") String customer_address,
            @Field("products") String products
    );


    @FormUrlEncoded
    @POST(walkingCustomerFullDetails)
    Call<GetWallkingModel> getCustomerWallkingDetails(@Field("customer_wi_id") String customer_wi_id);

    @FormUrlEncoded
    @POST(addFollowup)
    Call<AddFollowUpModel> addFollowUp(
            @Field("customer_wi_id") String customer_wi_id,
            @Field("coordination_id") String coordination_id,
            @Field("follow_up_date") String follow_up_date,
            @Field("follow_up_description") String follow_up_description
    );

    @FormUrlEncoded
    @POST(saleHistory)
    Call<MySalesModel> getMySale(
            @Field("coordination_id") String coordination_id
    );


    @FormUrlEncoded
    @POST(saleProductDetails)
    Call<SaleDetailsModel> getMySaleDetails(
            @Field("sale_id") String sale_id
    );

    @FormUrlEncoded
    @POST(addNewCustomer)
    Call<AddCustomerModel> addNewCustomer(
            @Field("coordination_id") String coordination_id,
            @Field("customer_fname") String customer_fname,
            @Field("customer_lname") String customer_lname,
            @Field("customer_mobile_no") String customer_mobile_no,
            @Field("customer_pincode") String customer_pincode,
            @Field("customer_address") String customer_address
    );

    @FormUrlEncoded
    @POST(customerList)
    Call<AddCustomerModel> getCustomerList(
            @Field("coordination_id") String coordination_id
    );

    @FormUrlEncoded
    @POST(qrcodeScan)
    Call<QrCodeProductModel> getProductByQrCode(
            @Field("product_qrcode") String product_qrcode
    );

    @FormUrlEncoded
    @POST(todoList)
    Call<TodoListModel> getTodoList(
            @Field("coordination_id") String coordination_id
    );

    @FormUrlEncoded
    @POST(commisionList)
    Call<CommisionModel> getCommissionList(
            @Field("coordination_id") String coordination_id
    );

    @FormUrlEncoded
    @POST(completeTodo)
    Call<TodoListModel> completeTodo(
            @Field("coordination_id") String coordination_id,
            @Field("todo_assign_id") String todo_assign_id
    );

    @FormUrlEncoded
    @POST(todayFollowup)
    Call<TodayFollowUpModel> todayFollowUp(
            @Field("coordination_id") String coordination_id
    );

    @FormUrlEncoded
    @POST(addCoordinationSale)
    Call<AddSaleModel> addSale(
            @Field("customer_id") String customer_id,
            @Field("total_sale_qty") String total_sale_qty,
            @Field("total_order_amount") String total_order_amount,
            @Field("pay_amount") String pay_amount,
            @Field("total_discount") String total_discount,
            @Field("coordination_id") String coordination_id,
            @Field("payment_type") String payment_type,
            @Field("bank_name") String bank_name,
            @Field("bank_ifsc_code") String bank_ifsc_code,
            @Field("bank_branch") String bank_branch,
            @Field("bank_ac_no") String bank_ac_no,
            @Field("bank_address") String bank_address,
            @Field("products") String products,
            @Field("payment_receipt") String payment_receipt_url
//            @Field("payment_receipt_url") String payment_receipt_url
    );


    @Multipart
    @POST(addCoordinationSale)
    Call<AddSaleModel> addSaleWithFile(
            @Part MultipartBody.Part payment_receipt_url,
            @Part("customer_id") String customer_id,
            @Part("total_sale_qty") String total_sale_qty,
            @Part("total_order_amount") String total_order_amount,
            @Part("pay_amount") String pay_amount,
            @Part("total_discount") String total_discount,
            @Part("coordination_id") String coordination_id,
            @Part("payment_type") String payment_type,
            @Part("products") String products,
            @Part("bank_name") String bank_name,
            @Part("bank_ifsc_code") String bank_ifsc_code,
            @Part("bank_branch") String bank_branch,
            @Part("bank_ac_no") String bank_ac_no,
            @Part("bank_address") String bank_address
    );


    @FormUrlEncoded
    @POST(firmWiseInvoice)
    Call<InvoiceListModel> getFirmInvoice(@Field("sale_id") String sale_id);


    @FormUrlEncoded
    @POST("app_block.php")
    Call<AppLockModel> checkApp(@Field("id") String id);


}

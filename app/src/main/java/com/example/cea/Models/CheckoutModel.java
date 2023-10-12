package com.example.cea.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckoutModel {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("base_url")
    @Expose
    private String baseUrl;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public class Datum {

        @SerializedName("cart_id")
        @Expose
        private String cartId;
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("product_qty")
        @Expose
        private String productQty;
        @SerializedName("single_product_price")
        @Expose
        private String single_product_price;
        @SerializedName("specification_id")
        @Expose
        private String specificationId;
        @SerializedName("product_sale_price")
        @Expose
        private String productSalePrice;
        @SerializedName("product_mop")
        @Expose
        private String productMop;
        @SerializedName("product_tax")
        @Expose
        private String productTax;
        @SerializedName("product_tax_amount")
        @Expose
        private String productTaxAmount;
        @SerializedName("product_final_amount")
        @Expose
        private String productFinalAmount;
        @SerializedName("cart_created_datetime")
        @Expose
        private String cartCreatedDatetime;
        @SerializedName("customer_pincode")
        @Expose
        private String customerPincode;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("pincode_number")
        @Expose
        private String pincodeNumber;
        @SerializedName("product_type")
        @Expose
        private String productType;
        @SerializedName("product_sub_type")
        @Expose
        private String productSubType;
        @SerializedName("model_id")
        @Expose
        private String modelId;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("category_id_level")
        @Expose
        private String categoryIdLevel;
        @SerializedName("product_status")
        @Expose
        private String productStatus;
        @SerializedName("product_image")
        @Expose
        private String productImage;
        @SerializedName("product_created_date")
        @Expose
        private String productCreatedDate;
        @SerializedName("product_updated_date")
        @Expose
        private String productUpdatedDate;
        @SerializedName("product_description")
        @Expose
        private String productDescription;
        @SerializedName("product_created_by")
        @Expose
        private String productCreatedBy;
        @SerializedName("product_updated_by")
        @Expose
        private String productUpdatedBy;
        @SerializedName("brand_id")
        @Expose
        private String brandId;
        @SerializedName("product_support_no")
        @Expose
        private String productSupportNo;
        @SerializedName("product_offer")
        @Expose
        private ProductOffer productOffer;


        public String getSingle_product_price() {
            return single_product_price;
        }

        public void setSingle_product_price(String single_product_price) {
            this.single_product_price = single_product_price;
        }

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductQty() {
            return productQty;
        }

        public void setProductQty(String productQty) {
            this.productQty = productQty;
        }

        public String getSpecificationId() {
            return specificationId;
        }

        public void setSpecificationId(String specificationId) {
            this.specificationId = specificationId;
        }

        public String getProductSalePrice() {
            return productSalePrice;
        }

        public void setProductSalePrice(String productSalePrice) {
            this.productSalePrice = productSalePrice;
        }

        public String getProductMop() {
            return productMop;
        }

        public void setProductMop(String productMop) {
            this.productMop = productMop;
        }

        public String getProductTax() {
            return productTax;
        }

        public void setProductTax(String productTax) {
            this.productTax = productTax;
        }

        public String getProductTaxAmount() {
            return productTaxAmount;
        }

        public void setProductTaxAmount(String productTaxAmount) {
            this.productTaxAmount = productTaxAmount;
        }

        public String getProductFinalAmount() {
            return productFinalAmount;
        }

        public void setProductFinalAmount(String productFinalAmount) {
            this.productFinalAmount = productFinalAmount;
        }

        public String getCartCreatedDatetime() {
            return cartCreatedDatetime;
        }

        public void setCartCreatedDatetime(String cartCreatedDatetime) {
            this.cartCreatedDatetime = cartCreatedDatetime;
        }

        public String getCustomerPincode() {
            return customerPincode;
        }

        public void setCustomerPincode(String customerPincode) {
            this.customerPincode = customerPincode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getPincodeNumber() {
            return pincodeNumber;
        }

        public void setPincodeNumber(String pincodeNumber) {
            this.pincodeNumber = pincodeNumber;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getProductSubType() {
            return productSubType;
        }

        public void setProductSubType(String productSubType) {
            this.productSubType = productSubType;
        }

        public String getModelId() {
            return modelId;
        }

        public void setModelId(String modelId) {
            this.modelId = modelId;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryIdLevel() {
            return categoryIdLevel;
        }

        public void setCategoryIdLevel(String categoryIdLevel) {
            this.categoryIdLevel = categoryIdLevel;
        }

        public String getProductStatus() {
            return productStatus;
        }

        public void setProductStatus(String productStatus) {
            this.productStatus = productStatus;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getProductCreatedDate() {
            return productCreatedDate;
        }

        public void setProductCreatedDate(String productCreatedDate) {
            this.productCreatedDate = productCreatedDate;
        }

        public String getProductUpdatedDate() {
            return productUpdatedDate;
        }

        public void setProductUpdatedDate(String productUpdatedDate) {
            this.productUpdatedDate = productUpdatedDate;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(String productDescription) {
            this.productDescription = productDescription;
        }

        public String getProductCreatedBy() {
            return productCreatedBy;
        }

        public void setProductCreatedBy(String productCreatedBy) {
            this.productCreatedBy = productCreatedBy;
        }

        public String getProductUpdatedBy() {
            return productUpdatedBy;
        }

        public void setProductUpdatedBy(String productUpdatedBy) {
            this.productUpdatedBy = productUpdatedBy;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getProductSupportNo() {
            return productSupportNo;
        }

        public void setProductSupportNo(String productSupportNo) {
            this.productSupportNo = productSupportNo;
        }

        public ProductOffer getProductOffer() {
            return productOffer;
        }

        public void setProductOffer(ProductOffer productOffer) {
            this.productOffer = productOffer;
        }

        public class ProductOffer {

            @SerializedName("offer_name")
            @Expose
            private String offerName;
            @SerializedName("offer_by")
            @Expose
            private String offerBy;
            @SerializedName("offer_type")
            @Expose
            private String offerType;
            @SerializedName("offer_amount")
            @Expose
            private String offerAmount;

            public String getOfferName() {
                return offerName;
            }

            public void setOfferName(String offerName) {
                this.offerName = offerName;
            }

            public String getOfferBy() {
                return offerBy;
            }

            public void setOfferBy(String offerBy) {
                this.offerBy = offerBy;
            }

            public String getOfferType() {
                return offerType;
            }

            public void setOfferType(String offerType) {
                this.offerType = offerType;
            }

            public String getOfferAmount() {
                return offerAmount;
            }

            public void setOfferAmount(String offerAmount) {
                this.offerAmount = offerAmount;
            }

        }

    }
}

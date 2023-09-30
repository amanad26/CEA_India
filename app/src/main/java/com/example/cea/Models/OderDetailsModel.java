package com.example.cea.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OderDetailsModel {

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

        @SerializedName("sale_product_id")
        @Expose
        private String saleProductId;
        @SerializedName("sale_id")
        @Expose
        private String saleId;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("sale_qty")
        @Expose
        private String saleQty;
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("customer_pincode")
        @Expose
        private String customerPincode;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("product_sale_price")
        @Expose
        private String productSalePrice;
        @SerializedName("offer_price")
        @Expose
        private String offerPrice;
        @SerializedName("product_final_amount")
        @Expose
        private String productFinalAmount;
        @SerializedName("product_tax")
        @Expose
        private String productTax;
        @SerializedName("product_tax_amount")
        @Expose
        private String productTaxAmount;
        @SerializedName("order_amount")
        @Expose
        private String orderAmount;
        @SerializedName("product_serial_no")
        @Expose
        private String productSerialNo;
        @SerializedName("sale_product_created_date")
        @Expose
        private String saleProductCreatedDate;
        @SerializedName("specification_id")
        @Expose
        private String specificationId;
        @SerializedName("product_image")
        @Expose
        private String productImage;
        @SerializedName("specification_name")
        @Expose
        private String specificationName;
        @SerializedName("sale_delivery_status")
        @Expose
        private String saleDeliveryStatus;
        @SerializedName("sale_delivery_datetime")
        @Expose
        private Object saleDeliveryDatetime;
        @SerializedName("sale_status")
        @Expose
        private String saleStatus;
        @SerializedName("sale_invoice_path")
        @Expose
        private Object saleInvoicePath;
        @SerializedName("review_status")
        @Expose
        private String review_status;

        public String getReview_status() {
            return review_status;
        }

        public void setReview_status(String review_status) {
            this.review_status = review_status;
        }

        public String getSaleProductId() {
            return saleProductId;
        }

        public void setSaleProductId(String saleProductId) {
            this.saleProductId = saleProductId;
        }

        public String getSaleId() {
            return saleId;
        }

        public void setSaleId(String saleId) {
            this.saleId = saleId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSaleQty() {
            return saleQty;
        }

        public void setSaleQty(String saleQty) {
            this.saleQty = saleQty;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerPincode() {
            return customerPincode;
        }

        public void setCustomerPincode(String customerPincode) {
            this.customerPincode = customerPincode;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getProductSalePrice() {
            return productSalePrice;
        }

        public void setProductSalePrice(String productSalePrice) {
            this.productSalePrice = productSalePrice;
        }

        public String getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPrice(String offerPrice) {
            this.offerPrice = offerPrice;
        }

        public String getProductFinalAmount() {
            return productFinalAmount;
        }

        public void setProductFinalAmount(String productFinalAmount) {
            this.productFinalAmount = productFinalAmount;
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

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getProductSerialNo() {
            return productSerialNo;
        }

        public void setProductSerialNo(String productSerialNo) {
            this.productSerialNo = productSerialNo;
        }

        public String getSaleProductCreatedDate() {
            return saleProductCreatedDate;
        }

        public void setSaleProductCreatedDate(String saleProductCreatedDate) {
            this.saleProductCreatedDate = saleProductCreatedDate;
        }

        public String getSpecificationId() {
            return specificationId;
        }

        public void setSpecificationId(String specificationId) {
            this.specificationId = specificationId;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getSpecificationName() {
            return specificationName;
        }

        public void setSpecificationName(String specificationName) {
            this.specificationName = specificationName;
        }

        public String getSaleDeliveryStatus() {
            return saleDeliveryStatus;
        }

        public void setSaleDeliveryStatus(String saleDeliveryStatus) {
            this.saleDeliveryStatus = saleDeliveryStatus;
        }

        public Object getSaleDeliveryDatetime() {
            return saleDeliveryDatetime;
        }

        public void setSaleDeliveryDatetime(Object saleDeliveryDatetime) {
            this.saleDeliveryDatetime = saleDeliveryDatetime;
        }

        public String getSaleStatus() {
            return saleStatus;
        }

        public void setSaleStatus(String saleStatus) {
            this.saleStatus = saleStatus;
        }

        public Object getSaleInvoicePath() {
            return saleInvoicePath;
        }

        public void setSaleInvoicePath(Object saleInvoicePath) {
            this.saleInvoicePath = saleInvoicePath;
        }

    }
}

package com.example.cea.co_oridinator.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommisionModel {

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

        @SerializedName("commision_id")
        @Expose
        private String commisionId;
        @SerializedName("coordination_id")
        @Expose
        private String coordinationId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("sale_product_id")
        @Expose
        private String saleProductId;
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("commision_amount")
        @Expose
        private String commisionAmount;
        @SerializedName("commision_created_date")
        @Expose
        private String commisionCreatedDate;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("customer_fname")
        @Expose
        private String customerFname;
        @SerializedName("customer_lname")
        @Expose
        private String customerLname;
        @SerializedName("customer_mobile_no")
        @Expose
        private String customerMobileNo;

        public String getCommisionId() {
            return commisionId;
        }

        public void setCommisionId(String commisionId) {
            this.commisionId = commisionId;
        }

        public String getCoordinationId() {
            return coordinationId;
        }

        public void setCoordinationId(String coordinationId) {
            this.coordinationId = coordinationId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSaleProductId() {
            return saleProductId;
        }

        public void setSaleProductId(String saleProductId) {
            this.saleProductId = saleProductId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCommisionAmount() {
            return commisionAmount;
        }

        public void setCommisionAmount(String commisionAmount) {
            this.commisionAmount = commisionAmount;
        }

        public String getCommisionCreatedDate() {
            return commisionCreatedDate;
        }

        public void setCommisionCreatedDate(String commisionCreatedDate) {
            this.commisionCreatedDate = commisionCreatedDate;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getCustomerFname() {
            return customerFname;
        }

        public void setCustomerFname(String customerFname) {
            this.customerFname = customerFname;
        }

        public String getCustomerLname() {
            return customerLname;
        }

        public void setCustomerLname(String customerLname) {
            this.customerLname = customerLname;
        }

        public String getCustomerMobileNo() {
            return customerMobileNo;
        }

        public void setCustomerMobileNo(String customerMobileNo) {
            this.customerMobileNo = customerMobileNo;
        }

    }
}

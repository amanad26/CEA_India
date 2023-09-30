package com.example.cea.co_oridinator.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWallkingModel {

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

        @SerializedName("customer_wi_id")
        @Expose
        private String customerWiId;
        @SerializedName("coordination_id")
        @Expose
        private String coordinationId;
        @SerializedName("customer_name")
        @Expose
        private String customerName;
        @SerializedName("customer_mobile_no")
        @Expose
        private String customerMobileNo;
        @SerializedName("customer_whatspp_no")
        @Expose
        private String customerWhatsppNo;
        @SerializedName("customer_address")
        @Expose
        private String customerAddress;
        @SerializedName("customer_wi_status")
        @Expose
        private String customerWiStatus;
        @SerializedName("customer_created_date")
        @Expose
        private String customerCreatedDate;
        @SerializedName("customer_createdatetime")
        @Expose
        private String customerCreatedatetime;
        @SerializedName("product_list")
        @Expose
        private List<Product> productList;

        public String getCustomerWiId() {
            return customerWiId;
        }

        public void setCustomerWiId(String customerWiId) {
            this.customerWiId = customerWiId;
        }

        public String getCoordinationId() {
            return coordinationId;
        }

        public void setCoordinationId(String coordinationId) {
            this.coordinationId = coordinationId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerMobileNo() {
            return customerMobileNo;
        }

        public void setCustomerMobileNo(String customerMobileNo) {
            this.customerMobileNo = customerMobileNo;
        }

        public String getCustomerWhatsppNo() {
            return customerWhatsppNo;
        }

        public void setCustomerWhatsppNo(String customerWhatsppNo) {
            this.customerWhatsppNo = customerWhatsppNo;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public void setCustomerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
        }

        public String getCustomerWiStatus() {
            return customerWiStatus;
        }

        public void setCustomerWiStatus(String customerWiStatus) {
            this.customerWiStatus = customerWiStatus;
        }

        public String getCustomerCreatedDate() {
            return customerCreatedDate;
        }

        public void setCustomerCreatedDate(String customerCreatedDate) {
            this.customerCreatedDate = customerCreatedDate;
        }

        public String getCustomerCreatedatetime() {
            return customerCreatedatetime;
        }

        public void setCustomerCreatedatetime(String customerCreatedatetime) {
            this.customerCreatedatetime = customerCreatedatetime;
        }

        public List<Product> getProductList() {
            return productList;
        }

        public void setProductList(List<Product> productList) {
            this.productList = productList;
        }

        public class Product {

            @SerializedName("walkin_product_id")
            @Expose
            private String walkinProductId;
            @SerializedName("customer_wi_id")
            @Expose
            private String customerWiId;
            @SerializedName("coordination_id")
            @Expose
            private String coordinationId;
            @SerializedName("product_id")
            @Expose
            private String productId;
            @SerializedName("walkin_created_date")
            @Expose
            private String walkinCreatedDate;
            @SerializedName("product_name")
            @Expose
            private String productName;

            public String getWalkinProductId() {
                return walkinProductId;
            }

            public void setWalkinProductId(String walkinProductId) {
                this.walkinProductId = walkinProductId;
            }

            public String getCustomerWiId() {
                return customerWiId;
            }

            public void setCustomerWiId(String customerWiId) {
                this.customerWiId = customerWiId;
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

            public String getWalkinCreatedDate() {
                return walkinCreatedDate;
            }

            public void setWalkinCreatedDate(String walkinCreatedDate) {
                this.walkinCreatedDate = walkinCreatedDate;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

        }
    }

}

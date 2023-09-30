package com.example.cea.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderListModel {


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

        @SerializedName("sale_id")
        @Expose
        private String saleId;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("pincode_number")
        @Expose
        private String pincodeNumber;
        @SerializedName("total_sale_qty")
        @Expose
        private String totalSaleQty;
        @SerializedName("total_order_amount")
        @Expose
        private String totalOrderAmount;
        @SerializedName("pay_amount")
        @Expose
        private String payAmount;
        @SerializedName("additional_discount")
        @Expose
        private String additionalDiscount;
        @SerializedName("transaction_id")
        @Expose
        private Object transactionId;
        @SerializedName("payment_status")
        @Expose
        private String paymentStatus;
        @SerializedName("sale_created_date")
        @Expose
        private String saleCreatedDate;
        @SerializedName("sale_status")
        @Expose
        private String saleStatus;
        @SerializedName("sale_delivery_status")
        @Expose
        private String saleDeliveryStatus;
        @SerializedName("sale_delivery_datetime")
        @Expose
        private Object saleDeliveryDatetime;
        @SerializedName("sale_by")
        @Expose
        private String saleBy;
        @SerializedName("customer_pincode")
        @Expose
        private String customerPincode;
        @SerializedName("customer_fname")
        @Expose
        private String customerFname;
        @SerializedName("customer_lname")
        @Expose
        private String customerLname;

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

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getPincodeNumber() {
            return pincodeNumber;
        }

        public void setPincodeNumber(String pincodeNumber) {
            this.pincodeNumber = pincodeNumber;
        }

        public String getTotalSaleQty() {
            return totalSaleQty;
        }

        public void setTotalSaleQty(String totalSaleQty) {
            this.totalSaleQty = totalSaleQty;
        }

        public String getTotalOrderAmount() {
            return totalOrderAmount;
        }

        public void setTotalOrderAmount(String totalOrderAmount) {
            this.totalOrderAmount = totalOrderAmount;
        }

        public String getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(String payAmount) {
            this.payAmount = payAmount;
        }

        public String getAdditionalDiscount() {
            return additionalDiscount;
        }

        public void setAdditionalDiscount(String additionalDiscount) {
            this.additionalDiscount = additionalDiscount;
        }

        public Object getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(Object transactionId) {
            this.transactionId = transactionId;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getSaleCreatedDate() {
            return saleCreatedDate;
        }

        public void setSaleCreatedDate(String saleCreatedDate) {
            this.saleCreatedDate = saleCreatedDate;
        }

        public String getSaleStatus() {
            return saleStatus;
        }

        public void setSaleStatus(String saleStatus) {
            this.saleStatus = saleStatus;
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

        public String getSaleBy() {
            return saleBy;
        }

        public void setSaleBy(String saleBy) {
            this.saleBy = saleBy;
        }

        public String getCustomerPincode() {
            return customerPincode;
        }

        public void setCustomerPincode(String customerPincode) {
            this.customerPincode = customerPincode;
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

    }
}

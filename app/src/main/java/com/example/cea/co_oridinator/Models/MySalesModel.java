package com.example.cea.co_oridinator.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MySalesModel {

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
        private String transactionId;
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
        private String saleDeliveryDatetime;
        @SerializedName("sale_by")
        @Expose
        private String saleBy;
        @SerializedName("customer_pincode")
        @Expose
        private String customerPincode;
        @SerializedName("payment_type")
        @Expose
        private String paymentType;
        @SerializedName("bank_name")
        @Expose
        private String bankName;
        @SerializedName("bank_ifsc_code")
        @Expose
        private String bankIfscCode;
        @SerializedName("bank_branch")
        @Expose
        private String bankBranch;
        @SerializedName("bank_ac_no")
        @Expose
        private String bankAcNo;
        @SerializedName("bank_address")
        @Expose
        private String bankAddress;
        @SerializedName("payment_receipt")
        @Expose
        private String paymentReceipt;
        @SerializedName("coordination_id")
        @Expose
        private String coordinationId;
        @SerializedName("customer_fname")
        @Expose
        private String customerFname;
        @SerializedName("customer_lname")
        @Expose
        private String customerLname;
        @SerializedName("customer_mobile_no")
        @Expose
        private String customer_mobile_no;

        public String getCustomer_mobile_no() {
            return customer_mobile_no;
        }

        public void setCustomer_mobile_no(String customer_mobile_no) {
            this.customer_mobile_no = customer_mobile_no;
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

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
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

        public String getSaleDeliveryDatetime() {
            return saleDeliveryDatetime;
        }

        public void setSaleDeliveryDatetime(String saleDeliveryDatetime) {
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

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankIfscCode() {
            return bankIfscCode;
        }

        public void setBankIfscCode(String bankIfscCode) {
            this.bankIfscCode = bankIfscCode;
        }

        public String getBankBranch() {
            return bankBranch;
        }

        public void setBankBranch(String bankBranch) {
            this.bankBranch = bankBranch;
        }

        public String getBankAcNo() {
            return bankAcNo;
        }

        public void setBankAcNo(String bankAcNo) {
            this.bankAcNo = bankAcNo;
        }

        public String getBankAddress() {
            return bankAddress;
        }

        public void setBankAddress(String bankAddress) {
            this.bankAddress = bankAddress;
        }

        public String getPaymentReceipt() {
            return paymentReceipt;
        }

        public void setPaymentReceipt(String paymentReceipt) {
            this.paymentReceipt = paymentReceipt;
        }

        public String getCoordinationId() {
            return coordinationId;
        }

        public void setCoordinationId(String coordinationId) {
            this.coordinationId = coordinationId;
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

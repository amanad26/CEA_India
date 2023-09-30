package com.example.cea.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateProfileModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("msg")
    @Expose
    private String msg;

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

    public class Datum {

        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("customer_fname")
        @Expose
        private String customerFname;
        @SerializedName("customer_lname")
        @Expose
        private String customerLname;
        @SerializedName("customer_name")
        @Expose
        private Object customerName;
        @SerializedName("customer_mobile_no")
        @Expose
        private String customerMobileNo;
        @SerializedName("fcm_token")
        @Expose
        private Object fcmToken;
        @SerializedName("customer_password")
        @Expose
        private String customerPassword;
        @SerializedName("customer_referal_code")
        @Expose
        private String customerReferalCode;
        @SerializedName("customer_referal_by")
        @Expose
        private String customerReferalBy;
        @SerializedName("customer_email")
        @Expose
        private String customerEmail;
        @SerializedName("customer_address")
        @Expose
        private String customerAddress;
        @SerializedName("customer_pincode")
        @Expose
        private String customerPincode;
        @SerializedName("customer_created_date")
        @Expose
        private String customerCreatedDate;
        @SerializedName("customer_updated_date")
        @Expose
        private String customerUpdatedDate;
        @SerializedName("customer_created_by")
        @Expose
        private String customerCreatedBy;
        @SerializedName("customer_status")
        @Expose
        private String customerStatus;
        @SerializedName("customer_dob")
        @Expose
        private String customerDob;
        @SerializedName("customer_anniversery_date")
        @Expose
        private String customerAnniverseryDate;
        @SerializedName("customer_whatspp_no")
        @Expose
        private String customerWhatsppNo;
        @SerializedName("customer_profile_img")
        @Expose
        private String customerProfileImg;
        @SerializedName("customer_total_wallet_amount")
        @Expose
        private String customerTotalWalletAmount;
        @SerializedName("customer_state_id")
        @Expose
        private String customerStateId;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
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

        public Object getCustomerName() {
            return customerName;
        }

        public void setCustomerName(Object customerName) {
            this.customerName = customerName;
        }

        public String getCustomerMobileNo() {
            return customerMobileNo;
        }

        public void setCustomerMobileNo(String customerMobileNo) {
            this.customerMobileNo = customerMobileNo;
        }

        public Object getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(Object fcmToken) {
            this.fcmToken = fcmToken;
        }

        public String getCustomerPassword() {
            return customerPassword;
        }

        public void setCustomerPassword(String customerPassword) {
            this.customerPassword = customerPassword;
        }

        public String getCustomerReferalCode() {
            return customerReferalCode;
        }

        public void setCustomerReferalCode(String customerReferalCode) {
            this.customerReferalCode = customerReferalCode;
        }

        public String getCustomerReferalBy() {
            return customerReferalBy;
        }

        public void setCustomerReferalBy(String customerReferalBy) {
            this.customerReferalBy = customerReferalBy;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public void setCustomerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
        }

        public String getCustomerPincode() {
            return customerPincode;
        }

        public void setCustomerPincode(String customerPincode) {
            this.customerPincode = customerPincode;
        }

        public String getCustomerCreatedDate() {
            return customerCreatedDate;
        }

        public void setCustomerCreatedDate(String customerCreatedDate) {
            this.customerCreatedDate = customerCreatedDate;
        }

        public String getCustomerUpdatedDate() {
            return customerUpdatedDate;
        }

        public void setCustomerUpdatedDate(String customerUpdatedDate) {
            this.customerUpdatedDate = customerUpdatedDate;
        }

        public String getCustomerCreatedBy() {
            return customerCreatedBy;
        }

        public void setCustomerCreatedBy(String customerCreatedBy) {
            this.customerCreatedBy = customerCreatedBy;
        }

        public String getCustomerStatus() {
            return customerStatus;
        }

        public void setCustomerStatus(String customerStatus) {
            this.customerStatus = customerStatus;
        }

        public String getCustomerDob() {
            return customerDob;
        }

        public void setCustomerDob(String customerDob) {
            this.customerDob = customerDob;
        }

        public String getCustomerAnniverseryDate() {
            return customerAnniverseryDate;
        }

        public void setCustomerAnniverseryDate(String customerAnniverseryDate) {
            this.customerAnniverseryDate = customerAnniverseryDate;
        }

        public String getCustomerWhatsppNo() {
            return customerWhatsppNo;
        }

        public void setCustomerWhatsppNo(String customerWhatsppNo) {
            this.customerWhatsppNo = customerWhatsppNo;
        }

        public String getCustomerProfileImg() {
            return customerProfileImg;
        }

        public void setCustomerProfileImg(String customerProfileImg) {
            this.customerProfileImg = customerProfileImg;
        }

        public String getCustomerTotalWalletAmount() {
            return customerTotalWalletAmount;
        }

        public void setCustomerTotalWalletAmount(String customerTotalWalletAmount) {
            this.customerTotalWalletAmount = customerTotalWalletAmount;
        }

        public String getCustomerStateId() {
            return customerStateId;
        }

        public void setCustomerStateId(String customerStateId) {
            this.customerStateId = customerStateId;
        }

    }

}

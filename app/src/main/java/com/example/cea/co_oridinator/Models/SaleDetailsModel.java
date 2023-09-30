package com.example.cea.co_oridinator.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaleDetailsModel {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("base_url")
    @Expose
    private String baseUrl;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
        @SerializedName("customer_name")
        @Expose
        private String customerName;
        @SerializedName("customer_mobile_no")
        @Expose
        private String customerMobileNo;
        @SerializedName("fcm_token")
        @Expose
        private String fcmToken;
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
        @SerializedName("product_list")
        @Expose
        private List<Product> productList;

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

        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(String fcmToken) {
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

        public List<Product> getProductList() {
            return productList;
        }

        public void setProductList(List<Product> productList) {
            this.productList = productList;
        }
        public class Product {

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
            @SerializedName("product_discount")
            @Expose
            private String productDiscount;
            @SerializedName("product_final_amount")
            @Expose
            private String productFinalAmount;
            @SerializedName("single_product_price")
            @Expose
            private String singleProductPrice;
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
            @SerializedName("product_qrcode")
            @Expose
            private String productQrcode;
            @SerializedName("sale_product_created_date")
            @Expose
            private String saleProductCreatedDate;
            @SerializedName("specification_id")
            @Expose
            private String specificationId;

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

            public String getProductDiscount() {
                return productDiscount;
            }

            public void setProductDiscount(String productDiscount) {
                this.productDiscount = productDiscount;
            }

            public String getProductFinalAmount() {
                return productFinalAmount;
            }

            public void setProductFinalAmount(String productFinalAmount) {
                this.productFinalAmount = productFinalAmount;
            }

            public String getSingleProductPrice() {
                return singleProductPrice;
            }

            public void setSingleProductPrice(String singleProductPrice) {
                this.singleProductPrice = singleProductPrice;
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

            public String getProductQrcode() {
                return productQrcode;
            }

            public void setProductQrcode(String productQrcode) {
                this.productQrcode = productQrcode;
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

        }
    }

}

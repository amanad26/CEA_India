package com.example.cea.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListModel {

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

        @SerializedName("stock_id")
        @Expose
        private String stockId;
        @SerializedName("firm_id")
        @Expose
        private String firmId;
        @SerializedName("purchase_invoice_no")
        @Expose
        private String purchaseInvoiceNo;
        @SerializedName("common_status")
        @Expose
        private String commonStatus;
        @SerializedName("purchase_status")
        @Expose
        private Object purchaseStatus;
        @SerializedName("store_status")
        @Expose
        private Object storeStatus;
        @SerializedName("stock_created_date")
        @Expose
        private String stockCreatedDate;
        @SerializedName("purchase_return_date")
        @Expose
        private Object purchaseReturnDate;
        @SerializedName("store_assign_date")
        @Expose
        private Object storeAssignDate;
        @SerializedName("store_return_date")
        @Expose
        private Object storeReturnDate;
        @SerializedName("sale_created_date")
        @Expose
        private String saleCreatedDate;
        @SerializedName("sale_return_date")
        @Expose
        private String saleReturnDate;
        @SerializedName("vendor_id")
        @Expose
        private String vendorId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("product_type")
        @Expose
        private String productType;
        @SerializedName("product_sub_type")
        @Expose
        private String productSubType;
        @SerializedName("show_app_status")
        @Expose
        private String showAppStatus;
        @SerializedName("pincode_number")
        @Expose
        private String pincodeNumber;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("category_id_level")
        @Expose
        private String categoryIdLevel;
        @SerializedName("purchase_id")
        @Expose
        private String purchaseId;
        @SerializedName("purchase_product_id")
        @Expose
        private String purchaseProductId;
        @SerializedName("product_qrcode")
        @Expose
        private String productQrcode;
        @SerializedName("product_price")
        @Expose
        private String productPrice;
        @SerializedName("product_sale_price")
        @Expose
        private String productSalePrice;
        @SerializedName("product_min_sale_price")
        @Expose
        private String productMinSalePrice;
        @SerializedName("product_max_discount")
        @Expose
        private String productMaxDiscount;
        @SerializedName("product_support_no")
        @Expose
        private String productSupportNo;
        @SerializedName("brand_id")
        @Expose
        private String brandId;
        @SerializedName("model_id")
        @Expose
        private String modelId;
        @SerializedName("product_description")
        @Expose
        private String product_description;
        @SerializedName("product_specification")
        @Expose
        private String productSpecification;
        @SerializedName("product_services")
        @Expose
        private Object productServices;
        @SerializedName("product_tax")
        @Expose
        private String productTax;
        @SerializedName("product_hsn")
        @Expose
        private String productHsn;
        @SerializedName("product_qty")
        @Expose
        private String productQty;
        @SerializedName("store_id")
        @Expose
        private String storeId;
        @SerializedName("stock_out_id")
        @Expose
        private String stockOutId;
        @SerializedName("sale_unique_invoice_no")
        @Expose
        private String saleUniqueInvoiceNo;
        @SerializedName("sale_invoice_path")
        @Expose
        private String saleInvoicePath;
        @SerializedName("sale_id")
        @Expose
        private String saleId;
        @SerializedName("sale_product_id")
        @Expose
        private String saleProductId;
        @SerializedName("return_id")
        @Expose
        private String returnId;
        @SerializedName("is_printed")
        @Expose
        private String isPrinted;
        @SerializedName("booking_unique_invoice_no")
        @Expose
        private String bookingUniqueInvoiceNo;
        @SerializedName("invoice_no")
        @Expose
        private String invoiceNo;
        @SerializedName("booking_id")
        @Expose
        private String bookingId;
        @SerializedName("sale_booking_type")
        @Expose
        private String saleBookingType;
        @SerializedName("product_detail_json")
        @Expose
        private String productDetailJson;
        @SerializedName("product_mop")
        @Expose
        private String productMop;
        @SerializedName("product_serial_no")
        @Expose
        private String productSerialNo;
        @SerializedName("product_tax_amount")
        @Expose
        private String productTaxAmount;
        @SerializedName("product_final_amount")
        @Expose
        private String productFinalAmount;
        @SerializedName("product_image")
        @Expose
        private String productImage;
        @SerializedName("product_specification_result")
        @Expose
        private List<ProductSpecificationResult> productSpecificationResult;
        @SerializedName("favorite_product_res")
        @Expose
        private String favoriteProductRes;
        @SerializedName("total_star_count")
        @Expose
        private Integer totalStarCount;
        @SerializedName("product_review_count")
        @Expose
        private Integer productReviewCount;

        public String getStockId() {
            return stockId;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public String getFirmId() {
            return firmId;
        }

        public void setFirmId(String firmId) {
            this.firmId = firmId;
        }

        public String getPurchaseInvoiceNo() {
            return purchaseInvoiceNo;
        }

        public void setPurchaseInvoiceNo(String purchaseInvoiceNo) {
            this.purchaseInvoiceNo = purchaseInvoiceNo;
        }

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public String getCommonStatus() {
            return commonStatus;
        }

        public void setCommonStatus(String commonStatus) {
            this.commonStatus = commonStatus;
        }

        public Object getPurchaseStatus() {
            return purchaseStatus;
        }

        public void setPurchaseStatus(Object purchaseStatus) {
            this.purchaseStatus = purchaseStatus;
        }

        public Object getStoreStatus() {
            return storeStatus;
        }

        public void setStoreStatus(Object storeStatus) {
            this.storeStatus = storeStatus;
        }

        public String getStockCreatedDate() {
            return stockCreatedDate;
        }

        public void setStockCreatedDate(String stockCreatedDate) {
            this.stockCreatedDate = stockCreatedDate;
        }

        public Object getPurchaseReturnDate() {
            return purchaseReturnDate;
        }

        public void setPurchaseReturnDate(Object purchaseReturnDate) {
            this.purchaseReturnDate = purchaseReturnDate;
        }

        public Object getStoreAssignDate() {
            return storeAssignDate;
        }

        public void setStoreAssignDate(Object storeAssignDate) {
            this.storeAssignDate = storeAssignDate;
        }

        public Object getStoreReturnDate() {
            return storeReturnDate;
        }

        public void setStoreReturnDate(Object storeReturnDate) {
            this.storeReturnDate = storeReturnDate;
        }

        public String getSaleCreatedDate() {
            return saleCreatedDate;
        }

        public void setSaleCreatedDate(String saleCreatedDate) {
            this.saleCreatedDate = saleCreatedDate;
        }

        public String getSaleReturnDate() {
            return saleReturnDate;
        }

        public void setSaleReturnDate(String saleReturnDate) {
            this.saleReturnDate = saleReturnDate;
        }

        public String getVendorId() {
            return vendorId;
        }

        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
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

        public String getShowAppStatus() {
            return showAppStatus;
        }

        public void setShowAppStatus(String showAppStatus) {
            this.showAppStatus = showAppStatus;
        }

        public String getPincodeNumber() {
            return pincodeNumber;
        }

        public void setPincodeNumber(String pincodeNumber) {
            this.pincodeNumber = pincodeNumber;
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

        public String getPurchaseId() {
            return purchaseId;
        }

        public void setPurchaseId(String purchaseId) {
            this.purchaseId = purchaseId;
        }

        public String getPurchaseProductId() {
            return purchaseProductId;
        }

        public void setPurchaseProductId(String purchaseProductId) {
            this.purchaseProductId = purchaseProductId;
        }

        public String getProductQrcode() {
            return productQrcode;
        }

        public void setProductQrcode(String productQrcode) {
            this.productQrcode = productQrcode;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductSalePrice() {
            return productSalePrice;
        }

        public void setProductSalePrice(String productSalePrice) {
            this.productSalePrice = productSalePrice;
        }

        public String getProductMinSalePrice() {
            return productMinSalePrice;
        }

        public void setProductMinSalePrice(String productMinSalePrice) {
            this.productMinSalePrice = productMinSalePrice;
        }

        public String getProductMaxDiscount() {
            return productMaxDiscount;
        }

        public void setProductMaxDiscount(String productMaxDiscount) {
            this.productMaxDiscount = productMaxDiscount;
        }

        public String getProductSupportNo() {
            return productSupportNo;
        }

        public void setProductSupportNo(String productSupportNo) {
            this.productSupportNo = productSupportNo;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getModelId() {
            return modelId;
        }

        public void setModelId(String modelId) {
            this.modelId = modelId;
        }

        public String getProductSpecification() {
            return productSpecification;
        }

        public void setProductSpecification(String productSpecification) {
            this.productSpecification = productSpecification;
        }

        public Object getProductServices() {
            return productServices;
        }

        public void setProductServices(Object productServices) {
            this.productServices = productServices;
        }

        public String getProductTax() {
            return productTax;
        }

        public void setProductTax(String productTax) {
            this.productTax = productTax;
        }

        public String getProductHsn() {
            return productHsn;
        }

        public void setProductHsn(String productHsn) {
            this.productHsn = productHsn;
        }

        public String getProductQty() {
            return productQty;
        }

        public void setProductQty(String productQty) {
            this.productQty = productQty;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStockOutId() {
            return stockOutId;
        }

        public void setStockOutId(String stockOutId) {
            this.stockOutId = stockOutId;
        }

        public String getSaleUniqueInvoiceNo() {
            return saleUniqueInvoiceNo;
        }

        public void setSaleUniqueInvoiceNo(String saleUniqueInvoiceNo) {
            this.saleUniqueInvoiceNo = saleUniqueInvoiceNo;
        }

        public String getSaleInvoicePath() {
            return saleInvoicePath;
        }

        public void setSaleInvoicePath(String saleInvoicePath) {
            this.saleInvoicePath = saleInvoicePath;
        }

        public String getSaleId() {
            return saleId;
        }

        public void setSaleId(String saleId) {
            this.saleId = saleId;
        }

        public String getSaleProductId() {
            return saleProductId;
        }

        public void setSaleProductId(String saleProductId) {
            this.saleProductId = saleProductId;
        }

        public String getReturnId() {
            return returnId;
        }

        public void setReturnId(String returnId) {
            this.returnId = returnId;
        }

        public String getIsPrinted() {
            return isPrinted;
        }

        public void setIsPrinted(String isPrinted) {
            this.isPrinted = isPrinted;
        }

        public String getBookingUniqueInvoiceNo() {
            return bookingUniqueInvoiceNo;
        }

        public void setBookingUniqueInvoiceNo(String bookingUniqueInvoiceNo) {
            this.bookingUniqueInvoiceNo = bookingUniqueInvoiceNo;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getBookingId() {
            return bookingId;
        }

        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }

        public String getSaleBookingType() {
            return saleBookingType;
        }

        public void setSaleBookingType(String saleBookingType) {
            this.saleBookingType = saleBookingType;
        }

        public String getProductDetailJson() {
            return productDetailJson;
        }

        public void setProductDetailJson(String productDetailJson) {
            this.productDetailJson = productDetailJson;
        }

        public String getProductMop() {
            return productMop;
        }

        public void setProductMop(String productMop) {
            this.productMop = productMop;
        }

        public String getProductSerialNo() {
            return productSerialNo;
        }

        public void setProductSerialNo(String productSerialNo) {
            this.productSerialNo = productSerialNo;
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

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public List<ProductSpecificationResult> getProductSpecificationResult() {
            return productSpecificationResult;
        }

        public void setProductSpecificationResult(List<ProductSpecificationResult> productSpecificationResult) {
            this.productSpecificationResult = productSpecificationResult;
        }

        public String getFavoriteProductRes() {
            return favoriteProductRes;
        }

        public void setFavoriteProductRes(String favoriteProductRes) {
            this.favoriteProductRes = favoriteProductRes;
        }

        public Integer getTotalStarCount() {
            return totalStarCount;
        }

        public void setTotalStarCount(Integer totalStarCount) {
            this.totalStarCount = totalStarCount;
        }

        public Integer getProductReviewCount() {
            return productReviewCount;
        }

        public void setProductReviewCount(Integer productReviewCount) {
            this.productReviewCount = productReviewCount;
        }


        public class ProductSpecificationResult {

            @SerializedName("product_id")
            @Expose
            private String productId;
            @SerializedName("category_id_level")
            @Expose
            private String categoryIdLevel;
            @SerializedName("product_specification")
            @Expose
            private String productSpecification;
            @SerializedName("common_status")
            @Expose
            private String commonStatus;
            @SerializedName("show_app_status")
            @Expose
            private String showAppStatus;
            @SerializedName("specification_id")
            @Expose
            private String specificationId;
            @SerializedName("specification_name")
            @Expose
            private String specificationName;
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

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getCategoryIdLevel() {
                return categoryIdLevel;
            }

            public void setCategoryIdLevel(String categoryIdLevel) {
                this.categoryIdLevel = categoryIdLevel;
            }

            public String getProductSpecification() {
                return productSpecification;
            }

            public void setProductSpecification(String productSpecification) {
                this.productSpecification = productSpecification;
            }

            public String getCommonStatus() {
                return commonStatus;
            }

            public void setCommonStatus(String commonStatus) {
                this.commonStatus = commonStatus;
            }

            public String getShowAppStatus() {
                return showAppStatus;
            }

            public void setShowAppStatus(String showAppStatus) {
                this.showAppStatus = showAppStatus;
            }

            public String getSpecificationId() {
                return specificationId;
            }

            public void setSpecificationId(String specificationId) {
                this.specificationId = specificationId;
            }

            public String getSpecificationName() {
                return specificationName;
            }

            public void setSpecificationName(String specificationName) {
                this.specificationName = specificationName;
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

        }

    }

}

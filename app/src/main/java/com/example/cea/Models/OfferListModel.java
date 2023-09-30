package com.example.cea.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferListModel {

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

        @SerializedName("offer_id")
        @Expose
        private String offerId;
        @SerializedName("offer_name")
        @Expose
        private String offerName;
        @SerializedName("offer_status")
        @Expose
        private String offerStatus;
        @SerializedName("offer_description")
        @Expose
        private String offerDescription;
        @SerializedName("offer_by")
        @Expose
        private String offerBy;
        @SerializedName("offer_image")
        @Expose
        private String offerImage;
        @SerializedName("offer_start_date")
        @Expose
        private String offerStartDate;
        @SerializedName("offer_end_date")
        @Expose
        private String offerEndDate;
        @SerializedName("offer_created_date")
        @Expose
        private String offerCreatedDate;
        @SerializedName("offer_updated_date")
        @Expose
        private String offerUpdatedDate;
        @SerializedName("offer_created_by")
        @Expose
        private String offerCreatedBy;
        @SerializedName("offer_updated_by")
        @Expose
        private String offerUpdatedBy;
        @SerializedName("offer_type")
        @Expose
        private String offerType;
        @SerializedName("offer_amount")
        @Expose
        private String offerAmount;

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public String getOfferName() {
            return offerName;
        }

        public void setOfferName(String offerName) {
            this.offerName = offerName;
        }

        public String getOfferStatus() {
            return offerStatus;
        }

        public void setOfferStatus(String offerStatus) {
            this.offerStatus = offerStatus;
        }

        public String getOfferDescription() {
            return offerDescription;
        }

        public void setOfferDescription(String offerDescription) {
            this.offerDescription = offerDescription;
        }

        public String getOfferBy() {
            return offerBy;
        }

        public void setOfferBy(String offerBy) {
            this.offerBy = offerBy;
        }

        public String getOfferImage() {
            return offerImage;
        }

        public void setOfferImage(String offerImage) {
            this.offerImage = offerImage;
        }

        public String getOfferStartDate() {
            return offerStartDate;
        }

        public void setOfferStartDate(String offerStartDate) {
            this.offerStartDate = offerStartDate;
        }

        public String getOfferEndDate() {
            return offerEndDate;
        }

        public void setOfferEndDate(String offerEndDate) {
            this.offerEndDate = offerEndDate;
        }

        public String getOfferCreatedDate() {
            return offerCreatedDate;
        }

        public void setOfferCreatedDate(String offerCreatedDate) {
            this.offerCreatedDate = offerCreatedDate;
        }

        public String getOfferUpdatedDate() {
            return offerUpdatedDate;
        }

        public void setOfferUpdatedDate(String offerUpdatedDate) {
            this.offerUpdatedDate = offerUpdatedDate;
        }

        public String getOfferCreatedBy() {
            return offerCreatedBy;
        }

        public void setOfferCreatedBy(String offerCreatedBy) {
            this.offerCreatedBy = offerCreatedBy;
        }

        public String getOfferUpdatedBy() {
            return offerUpdatedBy;
        }

        public void setOfferUpdatedBy(String offerUpdatedBy) {
            this.offerUpdatedBy = offerUpdatedBy;
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

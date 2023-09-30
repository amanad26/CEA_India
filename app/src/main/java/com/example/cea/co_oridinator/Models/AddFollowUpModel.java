package com.example.cea.co_oridinator.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFollowUpModel {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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
    public class Data {

        @SerializedName("follow_up_id")
        @Expose
        private String followUpId;
        @SerializedName("customer_wi_id")
        @Expose
        private String customerWiId;
        @SerializedName("coordination_id")
        @Expose
        private String coordinationId;
        @SerializedName("follow_up_date")
        @Expose
        private String followUpDate;
        @SerializedName("follow_up_description")
        @Expose
        private String followUpDescription;
        @SerializedName("follow_up_created_date")
        @Expose
        private String followUpCreatedDate;

        public String getFollowUpId() {
            return followUpId;
        }

        public void setFollowUpId(String followUpId) {
            this.followUpId = followUpId;
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

        public String getFollowUpDate() {
            return followUpDate;
        }

        public void setFollowUpDate(String followUpDate) {
            this.followUpDate = followUpDate;
        }

        public String getFollowUpDescription() {
            return followUpDescription;
        }

        public void setFollowUpDescription(String followUpDescription) {
            this.followUpDescription = followUpDescription;
        }

        public String getFollowUpCreatedDate() {
            return followUpCreatedDate;
        }

        public void setFollowUpCreatedDate(String followUpCreatedDate) {
            this.followUpCreatedDate = followUpCreatedDate;
        }

    }
}

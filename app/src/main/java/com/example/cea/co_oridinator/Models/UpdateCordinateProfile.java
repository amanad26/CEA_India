package com.example.cea.co_oridinator.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateCordinateProfile {
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

        @SerializedName("coordination_id")
        @Expose
        private String coordinationId;
        @SerializedName("coordination_name")
        @Expose
        private String coordinationName;
        @SerializedName("coordination_mobile_no")
        @Expose
        private String coordinationMobileNo;
        @SerializedName("coordination_email")
        @Expose
        private String coordinationEmail;
        @SerializedName("coordination_password")
        @Expose
        private String coordinationPassword;
        @SerializedName("coordination_address")
        @Expose
        private String coordinationAddress;
        @SerializedName("coordination_created_date")
        @Expose
        private String coordinationCreatedDate;
        @SerializedName("coordination_updated_date")
        @Expose
        private String coordinationUpdatedDate;
        @SerializedName("coordination_created_by")
        @Expose
        private String coordinationCreatedBy;
        @SerializedName("coordination_status")
        @Expose
        private String coordinationStatus;
        @SerializedName("coordination_profile")
        @Expose
        private String coordinationProfile;

        public String getCoordinationId() {
            return coordinationId;
        }

        public void setCoordinationId(String coordinationId) {
            this.coordinationId = coordinationId;
        }

        public String getCoordinationName() {
            return coordinationName;
        }

        public void setCoordinationName(String coordinationName) {
            this.coordinationName = coordinationName;
        }

        public String getCoordinationMobileNo() {
            return coordinationMobileNo;
        }

        public void setCoordinationMobileNo(String coordinationMobileNo) {
            this.coordinationMobileNo = coordinationMobileNo;
        }

        public String getCoordinationEmail() {
            return coordinationEmail;
        }

        public void setCoordinationEmail(String coordinationEmail) {
            this.coordinationEmail = coordinationEmail;
        }

        public String getCoordinationPassword() {
            return coordinationPassword;
        }

        public void setCoordinationPassword(String coordinationPassword) {
            this.coordinationPassword = coordinationPassword;
        }

        public String getCoordinationAddress() {
            return coordinationAddress;
        }

        public void setCoordinationAddress(String coordinationAddress) {
            this.coordinationAddress = coordinationAddress;
        }

        public String getCoordinationCreatedDate() {
            return coordinationCreatedDate;
        }

        public void setCoordinationCreatedDate(String coordinationCreatedDate) {
            this.coordinationCreatedDate = coordinationCreatedDate;
        }

        public String getCoordinationUpdatedDate() {
            return coordinationUpdatedDate;
        }

        public void setCoordinationUpdatedDate(String coordinationUpdatedDate) {
            this.coordinationUpdatedDate = coordinationUpdatedDate;
        }

        public String getCoordinationCreatedBy() {
            return coordinationCreatedBy;
        }

        public void setCoordinationCreatedBy(String coordinationCreatedBy) {
            this.coordinationCreatedBy = coordinationCreatedBy;
        }

        public String getCoordinationStatus() {
            return coordinationStatus;
        }

        public void setCoordinationStatus(String coordinationStatus) {
            this.coordinationStatus = coordinationStatus;
        }

        public String getCoordinationProfile() {
            return coordinationProfile;
        }

        public void setCoordinationProfile(String coordinationProfile) {
            this.coordinationProfile = coordinationProfile;
        }

    }
}

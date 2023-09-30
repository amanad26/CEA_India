package com.example.cea.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerModel {

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

        @SerializedName("use_id")
        @Expose
        private String useId;
        @SerializedName("img_1")
        @Expose
        private String img1;
        @SerializedName("title_1")
        @Expose
        private String title1;
        @SerializedName("description_1")
        @Expose
        private String description1;
        @SerializedName("img_2")
        @Expose
        private String img2;
        @SerializedName("title_2")
        @Expose
        private String title2;
        @SerializedName("description_2")
        @Expose
        private String description2;
        @SerializedName("img_3")
        @Expose
        private String img3;
        @SerializedName("title_3")
        @Expose
        private String title3;
        @SerializedName("description_3")
        @Expose
        private String description3;
        @SerializedName("created_date")
        @Expose
        private String createdDate;
        @SerializedName("updated_date")
        @Expose
        private String updatedDate;

        public String getUseId() {
            return useId;
        }

        public void setUseId(String useId) {
            this.useId = useId;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getTitle1() {
            return title1;
        }

        public void setTitle1(String title1) {
            this.title1 = title1;
        }

        public String getDescription1() {
            return description1;
        }

        public void setDescription1(String description1) {
            this.description1 = description1;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getTitle2() {
            return title2;
        }

        public void setTitle2(String title2) {
            this.title2 = title2;
        }

        public String getDescription2() {
            return description2;
        }

        public void setDescription2(String description2) {
            this.description2 = description2;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public String getTitle3() {
            return title3;
        }

        public void setTitle3(String title3) {
            this.title3 = title3;
        }

        public String getDescription3() {
            return description3;
        }

        public void setDescription3(String description3) {
            this.description3 = description3;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            this.updatedDate = updatedDate;
        }

    }
}

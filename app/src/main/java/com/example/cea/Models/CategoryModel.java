package com.example.cea.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryModel {

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

        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("category_name")
        @Expose
        private String categoryName;
        @SerializedName("category_description")
        @Expose
        private String categoryDescription;
        @SerializedName("parent_category_id")
        @Expose
        private String parentCategoryId;
        @SerializedName("category_level")
        @Expose
        private String categoryLevel;
        @SerializedName("category_status")
        @Expose
        private String categoryStatus;
        @SerializedName("category_image")
        @Expose
        private String categoryImage;
        @SerializedName("tnc_id")
        @Expose
        private String tncId;
        @SerializedName("specification")
        @Expose
        private String specification;
        @SerializedName("services")
        @Expose
        private Object services;
        @SerializedName("hsn_id")
        @Expose
        private String hsnId;
        @SerializedName("tax_id")
        @Expose
        private String taxId;
        @SerializedName("category_created_by")
        @Expose
        private String categoryCreatedBy;
        @SerializedName("category_updated_by")
        @Expose
        private String categoryUpdatedBy;
        @SerializedName("category_created_date")
        @Expose
        private String categoryCreatedDate;
        @SerializedName("category_updated_date")
        @Expose
        private String categoryUpdatedDate;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryDescription() {
            return categoryDescription;
        }

        public void setCategoryDescription(String categoryDescription) {
            this.categoryDescription = categoryDescription;
        }

        public String getParentCategoryId() {
            return parentCategoryId;
        }

        public void setParentCategoryId(String parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
        }

        public String getCategoryLevel() {
            return categoryLevel;
        }

        public void setCategoryLevel(String categoryLevel) {
            this.categoryLevel = categoryLevel;
        }

        public String getCategoryStatus() {
            return categoryStatus;
        }

        public void setCategoryStatus(String categoryStatus) {
            this.categoryStatus = categoryStatus;
        }

        public String getCategoryImage() {
            return categoryImage;
        }

        public void setCategoryImage(String categoryImage) {
            this.categoryImage = categoryImage;
        }

        public String getTncId() {
            return tncId;
        }

        public void setTncId(String tncId) {
            this.tncId = tncId;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public Object getServices() {
            return services;
        }

        public void setServices(Object services) {
            this.services = services;
        }

        public String getHsnId() {
            return hsnId;
        }

        public void setHsnId(String hsnId) {
            this.hsnId = hsnId;
        }

        public String getTaxId() {
            return taxId;
        }

        public void setTaxId(String taxId) {
            this.taxId = taxId;
        }

        public String getCategoryCreatedBy() {
            return categoryCreatedBy;
        }

        public void setCategoryCreatedBy(String categoryCreatedBy) {
            this.categoryCreatedBy = categoryCreatedBy;
        }

        public String getCategoryUpdatedBy() {
            return categoryUpdatedBy;
        }

        public void setCategoryUpdatedBy(String categoryUpdatedBy) {
            this.categoryUpdatedBy = categoryUpdatedBy;
        }

        public String getCategoryCreatedDate() {
            return categoryCreatedDate;
        }

        public void setCategoryCreatedDate(String categoryCreatedDate) {
            this.categoryCreatedDate = categoryCreatedDate;
        }

        public String getCategoryUpdatedDate() {
            return categoryUpdatedDate;
        }

        public void setCategoryUpdatedDate(String categoryUpdatedDate) {
            this.categoryUpdatedDate = categoryUpdatedDate;
        }

    }

}

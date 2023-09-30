package com.example.cea.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoriteModel {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("data")
    @Expose
    private List<Object> newData;

    @SerializedName("data")
    @Expose
    private Integer data;

    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public List<Object> getNewData() {
        return newData;
    }

    public void setNewData(List<Object> newData) {
        this.newData = newData;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}

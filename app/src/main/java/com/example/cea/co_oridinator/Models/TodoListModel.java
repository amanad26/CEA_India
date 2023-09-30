package com.example.cea.co_oridinator.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TodoListModel {

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

        @SerializedName("todo_assign_id")
        @Expose
        private String todoAssignId;
        @SerializedName("todo_id")
        @Expose
        private String todoId;
        @SerializedName("coordination_id")
        @Expose
        private String coordinationId;
        @SerializedName("todo_assign_status")
        @Expose
        private String todoAssignStatus;
        @SerializedName("assign_date")
        @Expose
        private String assignDate;
        @SerializedName("finish_date")
        @Expose
        private String finishDate;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("todo_name")
        @Expose
        private String todoName;
        @SerializedName("todo_description")
        @Expose
        private String todoDescription;

        public String getTodoAssignId() {
            return todoAssignId;
        }

        public void setTodoAssignId(String todoAssignId) {
            this.todoAssignId = todoAssignId;
        }

        public String getTodoId() {
            return todoId;
        }

        public void setTodoId(String todoId) {
            this.todoId = todoId;
        }

        public String getCoordinationId() {
            return coordinationId;
        }

        public void setCoordinationId(String coordinationId) {
            this.coordinationId = coordinationId;
        }

        public String getTodoAssignStatus() {
            return todoAssignStatus;
        }

        public void setTodoAssignStatus(String todoAssignStatus) {
            this.todoAssignStatus = todoAssignStatus;
        }

        public String getAssignDate() {
            return assignDate;
        }

        public void setAssignDate(String assignDate) {
            this.assignDate = assignDate;
        }

        public String getFinishDate() {
            return finishDate;
        }

        public void setFinishDate(String finishDate) {
            this.finishDate = finishDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getTodoName() {
            return todoName;
        }

        public void setTodoName(String todoName) {
            this.todoName = todoName;
        }

        public String getTodoDescription() {
            return todoDescription;
        }

        public void setTodoDescription(String todoDescription) {
            this.todoDescription = todoDescription;
        }

    }
}

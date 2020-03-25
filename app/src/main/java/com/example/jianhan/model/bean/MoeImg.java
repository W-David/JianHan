package com.example.jianhan.model.bean;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoeImg {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<MoeDatum> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MoeDatum> getData() {
        return data;
    }

    public void setData(List<MoeDatum> data) {
        this.data = data;
    }

}


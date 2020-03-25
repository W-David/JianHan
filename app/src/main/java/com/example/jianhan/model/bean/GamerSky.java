package com.example.jianhan.model.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GamerSky {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<GamerDatum> data = null;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GamerDatum> getData() {
        return data;
    }

    public void setData(List<GamerDatum> data) {
        this.data = data;
    }
}

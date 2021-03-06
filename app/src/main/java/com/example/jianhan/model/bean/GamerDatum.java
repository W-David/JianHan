package com.example.jianhan.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GamerDatum implements Parcelable {
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sort")
    @Expose
    private String sort;
    @SerializedName("imgUrls")
    @Expose
    private List<String> imgUrls = null;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeStringList(this.imgUrls);
    }

    public GamerDatum() {
    }

    protected GamerDatum(Parcel in) {
        this.title = in.readString();
        this.imgUrls = in.createStringArrayList();
    }

    public static final Parcelable.Creator<GamerDatum> CREATOR = new Parcelable.Creator<GamerDatum>() {
        @Override
        public GamerDatum createFromParcel(Parcel source) {
            return new GamerDatum(source);
        }

        @Override
        public GamerDatum[] newArray(int size) {
            return new GamerDatum[size];
        }
    };
}

package com.example.jianhan.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CosDatum implements Parcelable {

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("originUrl")
    @Expose
    private String originUrl;
    @SerializedName("sort")
    @Expose
    private Integer sort;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("editor")
    @Expose
    private String editor;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("rawtime")
    @Expose
    private Integer rawtime;
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

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getRawtime() {
        return rawtime;
    }

    public void setRawtime(Integer rawtime) {
        this.rawtime = rawtime;
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
        dest.writeString(this.author);
        dest.writeString(this.editor);
        dest.writeString(this.datetime);
        dest.writeStringList(this.imgUrls);
    }

    public CosDatum() {
    }

    protected CosDatum(Parcel in) {
        this.title = in.readString();
        this.author = in.readString();
        this.editor = in.readString();
        this.datetime = in.readString();
        this.imgUrls = in.createStringArrayList();
    }

    public static final Parcelable.Creator<CosDatum> CREATOR = new Parcelable.Creator<CosDatum>() {
        @Override
        public CosDatum createFromParcel(Parcel source) {
            return new CosDatum(source);
        }

        @Override
        public CosDatum[] newArray(int size) {
            return new CosDatum[size];
        }
    };
}


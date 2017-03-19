package com.kennard.nytimesearch.model;

/**
 * Created by raprasad on 3/19/17.
 */

import android.os.Parcelable;


        import android.os.Parcel;
        import android.os.Parcelable;
        import android.os.Parcelable.Creator;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Multimedium implements Parcelable
{

    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("legacy")
    @Expose
    private Legacy legacy;
    @SerializedName("type")
    @Expose
    private String type;
    public final static Parcelable.Creator<Multimedium> CREATOR = new Creator<Multimedium>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Multimedium createFromParcel(Parcel in) {
            Multimedium instance = new Multimedium();
            instance.width = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.url = ((String) in.readValue((String.class.getClassLoader())));
            instance.rank = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.subtype = ((String) in.readValue((String.class.getClassLoader())));
            instance.legacy = ((Legacy) in.readValue((Legacy.class.getClassLoader())));
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Multimedium[] newArray(int size) {
            return (new Multimedium[size]);
        }

    }
            ;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(width);
        dest.writeValue(url);
        dest.writeValue(rank);
        dest.writeValue(height);
        dest.writeValue(subtype);
        dest.writeValue(legacy);
        dest.writeValue(type);
    }

    public int describeContents() {
        return 0;
    }

}

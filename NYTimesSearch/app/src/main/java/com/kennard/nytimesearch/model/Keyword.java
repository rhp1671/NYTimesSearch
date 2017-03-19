package com.kennard.nytimesearch.model;

/**
 * Created by raprasad on 3/19/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword implements Parcelable
{

    @SerializedName("isMajor")
    @Expose
    private String isMajor;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;
    public final static Parcelable.Creator<Keyword> CREATOR = new Creator<Keyword>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Keyword createFromParcel(Parcel in) {
            Keyword instance = new Keyword();
            instance.isMajor = ((String) in.readValue((String.class.getClassLoader())));
            instance.rank = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.value = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Keyword[] newArray(int size) {
            return (new Keyword[size]);
        }

    }
            ;

    public String getIsMajor() {
        return isMajor;
    }

    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isMajor);
        dest.writeValue(rank);
        dest.writeValue(name);
        dest.writeValue(value);
    }

    public int describeContents() {
        return 0;
    }

}


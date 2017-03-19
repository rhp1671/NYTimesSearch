package com.kennard.nytimesearch.model;

/**
 * Created by raprasad on 3/19/17.
 */

import android.os.Parcel;


        import android.os.Parcel;
        import android.os.Parcelable;
        import android.os.Parcelable.Creator;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Headline implements Parcelable
{

    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("kicker")
    @Expose
    private String kicker;
    @SerializedName("print_headline")
    @Expose
    private String printHeadline;
    @SerializedName("content_kicker")
    @Expose
    private String contentKicker;
    public final static Parcelable.Creator<Headline> CREATOR = new Creator<Headline>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Headline createFromParcel(Parcel in) {
            Headline instance = new Headline();
            instance.main = ((String) in.readValue((String.class.getClassLoader())));
            instance.kicker = ((String) in.readValue((String.class.getClassLoader())));
            instance.printHeadline = ((String) in.readValue((String.class.getClassLoader())));
            instance.contentKicker = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Headline[] newArray(int size) {
            return (new Headline[size]);
        }

    }
            ;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }

    public String getContentKicker() {
        return contentKicker;
    }

    public void setContentKicker(String contentKicker) {
        this.contentKicker = contentKicker;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(main);
        dest.writeValue(kicker);
        dest.writeValue(printHeadline);
        dest.writeValue(contentKicker);
    }

    public int describeContents() {
        return 0;
    }

}
package com.kennard.nytimesearch.model;

/**
 * Created by raprasad on 3/19/17.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Byline implements Parcelable
{

    @SerializedName("person")
    @Expose
    private List<Person> person = null;
    @SerializedName("original")
    @Expose
    private String original;
    public final static Parcelable.Creator<Byline> CREATOR = new Creator<Byline>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Byline createFromParcel(Parcel in) {
            Byline instance = new Byline();
            in.readList(instance.person, (com.kennard.nytimesearch.model.Person.class.getClassLoader()));
            instance.original = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Byline[] newArray(int size) {
            return (new Byline[size]);
        }

    }
            ;

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(person);
        dest.writeValue(original);
    }

    public int describeContents() {
        return 0;
    }

}


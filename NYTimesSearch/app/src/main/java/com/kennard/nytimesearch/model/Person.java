package com.kennard.nytimesearch.model;

/**
 * Created by raprasad on 3/19/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person implements Parcelable
{

    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    public final static Parcelable.Creator<Person> CREATOR = new Creator<Person>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Person createFromParcel(Parcel in) {
            Person instance = new Person();
            instance.organization = ((String) in.readValue((String.class.getClassLoader())));
            instance.role = ((String) in.readValue((String.class.getClassLoader())));
            instance.rank = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.firstname = ((String) in.readValue((String.class.getClassLoader())));
            instance.lastname = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Person[] newArray(int size) {
            return (new Person[size]);
        }

    }
            ;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(organization);
        dest.writeValue(role);
        dest.writeValue(rank);
        dest.writeValue(firstname);
        dest.writeValue(lastname);
    }

    public int describeContents() {
        return 0;
    }

}
package com.kennard.nytimesearch.model;

/**
 * Created by raprasad on 3/19/17.
 */

import com.google.gson.annotations.SerializedName;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NYTimesResponse {

    @SerializedName("response")
    @Expose
    private Response_ response;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;

    public Response_ getResponse() {
        return response;
    }

    public void setResponse(Response_ response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

}

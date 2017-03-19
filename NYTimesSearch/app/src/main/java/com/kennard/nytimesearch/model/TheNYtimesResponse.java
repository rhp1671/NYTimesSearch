package com.kennard.nytimesearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by raprasad on 03/17/17.
 */

public class TheNYtimesResponse {
    @SerializedName("docs")
    ArrayList<Article> articles;

    public TheNYtimesResponse() {
        this.articles = new ArrayList<>();
    }

    public ArrayList getArticles(){
        return articles;
    }

}

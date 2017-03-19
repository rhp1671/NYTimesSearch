package com.kennard.nytimesearch.network;

/**
 * Created by raprasad on 3/19/17.
 */


import com.kennard.nytimesearch.model.NYTimesResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.R.attr.y;



public interface ApiInterface {
    @GET("articlesearch.json")
    Call<NYTimesResponse> getArticles(
            @Query("api-key") String apiKey,
            @Query("q") String search,
            @Query("page") int page,
            @Query("fq") String fq,
            @Query("begin_date") String date,
            @Query("sort") String sort
           );
}


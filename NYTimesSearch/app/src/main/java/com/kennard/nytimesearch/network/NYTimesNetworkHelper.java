package com.kennard.nytimesearch.network;

/**
 * Created by raprasad on 3/19/17.
 */

import android.util.Log;
import android.widget.Toast;
import com.kennard.nytimesearch.activity.SearchActivity;
import com.kennard.nytimesearch.model.Doc;
import com.kennard.nytimesearch.model.NYTimesResponse;
import com.kennard.nytimesearch.utility.ArticlePrefs;
import com.loopj.android.http.RequestParams;

import java.util.List;

import kennard.com.nytimessearch.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class NYTimesNetworkHelper {

    public static String BASE_URL = "https://api.nytimes.com/svc/search/v2/";
    public static String API_KEY = "api-key";
    public static String key = "5c88ec8759eb4f3db07cef2229d74fc7";
    private SearchActivity mActivity;
    private static Retrofit retrofit = null;

    public static String QueryStringBuiler(String... params) {
        StringBuilder builder = new StringBuilder();
        if (params.length > 0) {
            builder.append("news_desk:(");
            for (int i = 0; i < params.length - 1; i++) {
                if (!params[i].isEmpty()) {
                    builder.append(params[i] + " ");
                }
            }
            builder.append(params[params.length - 1]);
            builder.append(")");
        }
        return builder.toString();
    }


    public NYTimesNetworkHelper(SearchActivity activity) {
        this.mActivity = activity;
    }
    

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void getArticles(ArticlePrefs.NYTFilter filter) {
        ApiInterface apiService =
                getClient().create(ApiInterface.class);

        Call<NYTimesResponse> call = apiService.getArticles(key, filter.searchParam, filter.pageNo, filter.newsDeskParam.isEmpty() ? null : filter.newsDeskParam,
                filter.beginDate.isEmpty() ? null : filter.beginDate, filter.sortOrder.isEmpty() ? null : filter.sortOrder);
        call.enqueue(new Callback<NYTimesResponse>() {
            @Override
            public void onResponse(Call<NYTimesResponse> call, Response<NYTimesResponse> response) {
                if (response != null && response.isSuccessful()) {
                    List<Doc> articles = response.body().getResponse().getDocs();
                    mActivity.setNYList(articles);
                    Log.d(TAG, "Number of articles received: " + articles.size());
                }
            }

            @Override
            public void onFailure(Call<NYTimesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Toast.makeText(mActivity, mActivity.getResources().getString(R.string.networkerror), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

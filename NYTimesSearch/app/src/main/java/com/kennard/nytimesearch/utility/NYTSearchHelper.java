package com.kennard.nytimesearch.utility;

import com.loopj.android.http.RequestParams;

import static android.R.attr.filter;
import static android.R.attr.key;
import static android.R.attr.value;
import static kennard.com.nytimessearch.R.string.search;

/**
 * Created by raprasad on 10/23/16.
 */

public class NYTSearchHelper {

    public static String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
    public static String API_KEY = "api-key";
    public static  String key = "5c88ec8759eb4f3db07cef2229d74fc7";

    public static RequestParams getRequestParams(SettingsUtil.NYFilter filter, int NoPages){
        RequestParams params = new RequestParams();
        params.put(API_KEY, key);
        params.put("q", filter.searchParam);
        params.put("page", NoPages);

        if (!filter.newsDeskParam.isEmpty()){
            params.put("fq", filter.newsDeskParam);
        }
        if (!filter.beginDate.isEmpty()){
            params.put("begin_date", filter.beginDate);
        }
        if (!filter.sortOrder.isEmpty()){
            params.put("sort", filter.sortOrder);
        }
        return params;
    }

    public static String QueryStringBuiler(String... params){
        StringBuilder builder = new StringBuilder();
        if (params.length > 0) {
            builder.append("news_desk:(");
            for (int i =0; i < params.length -1 ; i++) {
                if (!params[i].isEmpty()) {
                    builder.append(params[i] + " ");
                }
            }
            builder.append(params[params.length-1]);
            builder.append(")");
            }
            return builder.toString();
        }



}

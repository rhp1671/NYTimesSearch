package kennard.com.nytimessearch;

import com.loopj.android.http.RequestParams;

import static android.R.attr.key;
import static kennard.com.nytimessearch.R.string.search;

/**
 * Created by raprasad on 10/23/16.
 */

public class NYTSearchHelper {

    public static String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
    public static String API_KEY = "api-key";
    public static  String key = "5c88ec8759eb4f3db07cef2229d74fc7";

    public static RequestParams getRequestParams(String searchParam, int NoPages){
        RequestParams params = new RequestParams();
        params.put(API_KEY, key);
        params.put("q", searchParam);
        params.put("page", NoPages);
        return params;
    }


}

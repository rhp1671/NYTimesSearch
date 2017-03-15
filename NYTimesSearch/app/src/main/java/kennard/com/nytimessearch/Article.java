package kennard.com.nytimessearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by raprasad on 10/24/16.
 */

public class Article {

    String webUrl;

    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbline() {
        return thumbline;
    }

    String headline;
    String thumbline;

    public Article(JSONObject json) {
        try{
            this.webUrl = json.getString("web_url");
            this.headline = json.getJSONObject("headline").getString("main");

            JSONArray array = json.getJSONArray("multimedia");
            if (array.length() > 0){
                JSONObject jsonObject = array.getJSONObject(0);
                this.thumbline = "http://www.nytimes.com/" + jsonObject.getString("url");
            } else {
                this.thumbline = "";
            }
        } catch (Exception ex){

        }
    }

    public static ArrayList<Article> fromJSONArray(JSONArray array){
        ArrayList<Article> articles = new ArrayList<>();

        for (int i = 0; i < array.length();i++){
            try{
                articles.add(new Article(array.getJSONObject(i)));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

        return articles;
    }
}

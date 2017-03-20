package com.kennard.nytimesearch.utility;

import android.content.Context;
import android.content.SharedPreferences;


import java.text.SimpleDateFormat;
import java.util.Date;

import kennard.com.nytimessearch.R;

import static com.kennard.nytimesearch.activity.SettingsActivity.SHAREDPREFS;

/**
 * Created by raprasad on 3/17/17.
 */

public class ArticlePrefs {

    public String newsDeskValueString;
    public long timeinms;
    public String sortOrderString;
    SharedPreferences sharedPref;
    private Context mContext;
    private  boolean isValidateNewsDeskValueString = false;
    public static NYTFilter filter;
    public static String title;

    public static class NYTFilter {

        public String newsDeskParam = "";
        public String beginDate = "";
        public String sortOrder = "";
        public String searchParam = "";
        public int pageNo = 0;
    }

    public ArticlePrefs(Context context){

        this.sharedPref = context.getSharedPreferences (SHAREDPREFS, Context.MODE_PRIVATE);
        this.mContext = context;
        refresh();
    }

    public void refresh(){
        newsDeskValueString = sharedPref.getString(mContext.getResources().getString(R.string.newsdesk), "");
        sortOrderString = sharedPref.getString(mContext.getResources().getString(R.string.sortorder), "");
        timeinms = sharedPref.getLong("SELDATE", 0L);

        title = "";

        if (newsDeskValueString.contains(mContext.getResources().getString(R.string.ARTS)) ||
                newsDeskValueString.contains(mContext.getResources().getString(R.string.SPORTS)) ||
                newsDeskValueString.contains(mContext.getResources().getString(R.string.FASHION))){
            isValidateNewsDeskValueString = true;
        }

        if (newsDeskValueString.contains(mContext.getResources().getString(R.string.ARTS))) {
            title = mContext.getResources().getString(R.string.ARTS);
        }
        if (newsDeskValueString.contains(mContext.getResources().getString(R.string.SPORTS))){
            if (title.isEmpty()) {
                title = mContext.getResources().getString(R.string.SPORTS);
            } else {
                title += "   " + mContext.getResources().getString(R.string.SPORTS);
            }
        }
        if (newsDeskValueString.contains(mContext.getResources().getString(R.string.FASHION))){
            if (title.isEmpty()) {
                title = mContext.getResources().getString(R.string.FASHION);
            } else {
                title += "   " + mContext.getResources().getString(R.string.FASHION);
            }
        }

        if (title.isEmpty()){
            isValidateNewsDeskValueString = false;
        } else {
            isValidateNewsDeskValueString = true;
        }

        if (filter == null) {
            filter = new NYTFilter();
        }

        if (isValidateNewsDeskValueString) {
            filter.newsDeskParam = newsDeskValueString;
        }
        if (timeinms != 0){
            SimpleDateFormat dt = new SimpleDateFormat("yyyymmdd");
            String formattedDate = dt.format(new Date(timeinms));
            filter.beginDate = formattedDate;
        }
        if (!sortOrderString.isEmpty() ){
            filter.sortOrder = sortOrderString;
        }

    }

    public void persist(String newsDesk, long timeinms, String sortOrder){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        if (newsDesk != null) {
            editor.putString(mContext.getResources().getString(R.string.newsdesk), newsDesk);
        }
        if (timeinms != 0) {
            editor.putLong("SELDATE", timeinms);
        }
        if (!sortOrder.isEmpty()){
            editor.putString(mContext.getResources().getString(R.string.sortorder), sortOrder );
        }
        editor.commit();

    }

}

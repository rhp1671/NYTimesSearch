package com.kennard.nytimesearch.utility;

import android.content.Context;
import android.content.SharedPreferences;


import java.text.SimpleDateFormat;
import java.util.Date;

import kennard.com.nytimessearch.R;

import static android.os.Build.VERSION_CODES.N;
import static com.kennard.nytimesearch.activity.SettingsActivity.SHAREDPREFS;
import static kennard.com.nytimessearch.R.id.spSortOrder;

/**
 * Created by raprasad on 3/17/17.
 */

public class SettingsUtil {

    public String newsDeskValueString;
    public long timeinms;
    public String sortOrderString;
    SharedPreferences sharedPref;
    private Context mContext;
    private  boolean isValidateNewsDeskValueString = false;
    public static NYFilter filter;

    public static class NYFilter {

        public String newsDeskParam = "";
        public String beginDate = "";
        public String sortOrder = "";
        public String searchParam = "";
    }

    public SettingsUtil(Context context){

        this.sharedPref = context.getSharedPreferences (SHAREDPREFS, Context.MODE_PRIVATE);
        this.mContext = context;
        newsDeskValueString = sharedPref.getString(context.getResources().getString(R.string.newsdesk), "");
        sortOrderString = sharedPref.getString(context.getResources().getString(R.string.sortorder), "");
        timeinms = sharedPref.getLong("SELDATE", 0L);

        if (newsDeskValueString.contains(context.getResources().getString(R.string.ARTS)) ||
                newsDeskValueString.contains(context.getResources().getString(R.string.SPORTS)) ||
                newsDeskValueString.contains(context.getResources().getString(R.string.FASHION))){
            isValidateNewsDeskValueString = true;
        }
        if (filter == null) {
            filter = new NYFilter();
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

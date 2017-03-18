package com.kennard.nytimesearch.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kennard.com.nytimessearch.R;

/**
 * Created by raprasad on 10/24/16.
 */

public class ArticleArrayAdapter extends ArrayAdapter<Article> {


    public ArticleArrayAdapter(Context context, List<Article> articles){
        super(context, android.R.layout.simple_list_item_1, articles);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Article article = getItem(position);

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate( R.layout.item_article_result, parent, false);
        }

        ImageView ivView = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvHeadline = (TextView) convertView.findViewById(R.id.tvHeadline);

        ivView.setImageResource(0);
        tvHeadline.setText(article.getHeadline());

        if (!article.getThumbline().isEmpty()) {
            Picasso.with(getContext()).load(article.getThumbline()).into(ivView);
        }
        return convertView;
    }
}

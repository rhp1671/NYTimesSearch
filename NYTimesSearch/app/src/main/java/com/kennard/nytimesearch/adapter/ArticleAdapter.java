package com.kennard.nytimesearch.adapter;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kennard.nytimesearch.model.Article;
import com.kennard.nytimesearch.model.Doc;
import com.kennard.nytimesearch.model.Multimedium;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kennard.com.nytimessearch.R;


/**
 * Created by raprasad on 10/24/16.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

   private    List<Doc> articles;
    private Context context;
    private static Bitmap bitmap;
    public static final String baseURL = "http://www.nytimes.com/";


    public ArticleAdapter(Context context, List<Doc> articles){
       this.articles = articles;
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_action_share);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.ivImage)
        ImageView ivView;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvsnippet)
        TextView tvSnippet;

        Context context;
        List<Doc> articles;

        public ViewHolder(Context context, View v, List<Doc> articles) {

            super(v);
            ButterKnife.bind(this, v);
            this.articles = articles;
            this.context = context;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            Doc article = articles.get(getAdapterPosition());
            intent.putExtra(Intent.EXTRA_TEXT, Uri.parse(article.getWebUrl()));
            int requestCode = 100;

            PendingIntent pendingIntent = PendingIntent.getActivity(context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setActionButton(bitmap, "Share Link", pendingIntent, true);
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(article.getWebUrl()));
        }
    }



    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        View v = LayoutInflater.from(context).inflate(R.layout.item_article_cv,parent,false);
        ViewHolder vh = new ViewHolder(context, v, articles);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        Doc article = articles.get(position);

        if (article != null) {
            TextView tvHeadline = holder.tvTitle;
            ImageView ivImage = holder.ivView;
            TextView tvSnippet = holder.tvSnippet;

            ivImage.setImageResource(0);
            tvHeadline.setText(article.getHeadline().getMain());
            tvSnippet.setText(article.getLeadParagraph());


            List<Multimedium> multimedia = article.getMultimedia();

            if (multimedia.size() > 0) {
                Multimedium media = multimedia.get(0);
                String s= baseURL + media.getUrl();
                Glide.with(context)
                        .load(baseURL + media.getUrl()).into(ivImage);
            }
        }
    }

    @Override
    public int getItemCount(){
        return articles.size();
    }

}

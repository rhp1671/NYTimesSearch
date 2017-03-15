package kennard.com.nytimessearch.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kennard.com.nytimessearch.R;

import static kennard.com.nytimessearch.NYTSearchHelper.url;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        final String url = getIntent().getStringExtra("url");
        WebView wv = (WebView) findViewById(R.id.wvArticle);


        wv.setWebViewClient( new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading (WebView view, WebResourceRequest request){
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.loadUrl(request.getUrl().toString());
                    } else {
                        view.loadUrl(url);
                    }
                    return true;
            }
        });

        wv.loadUrl(url);
    }
}

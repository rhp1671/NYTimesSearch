package kennard.com.nytimessearch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import kennard.com.nytimessearch.Article;
import kennard.com.nytimessearch.ArticleArrayAdapter;
import kennard.com.nytimessearch.NYTSearchHelper;
import kennard.com.nytimessearch.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.loopj.android.http.AsyncHttpClient.LOG_TAG;
import static java.security.AccessController.getContext;
import static kennard.com.nytimessearch.R.id.toolbar;

public class SearchActivity extends AppCompatActivity {
    EditText etQuery;
    Button btnSearch;
    GridView gvResults;

    ArrayList<Article> articles;
    ArticleArrayAdapter articleArrayAdapter;

     EndlessScrollListener endlessScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViews();
    }

    protected void setupViews(){

        endlessScrollListener= new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                loadNextDataFromApi(page, totalItemsCount);
                return false;
            }
        };


        etQuery = (EditText) findViewById(R.id.etQuery) ;
        btnSearch = (Button) findViewById(R.id.btnSearch);
        gvResults = (GridView) findViewById(R.id.gvResults);
        articles = new ArrayList<>();
        articleArrayAdapter = new ArticleArrayAdapter(this, articles);
        gvResults.setAdapter(articleArrayAdapter);

       gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent launchArticle = new Intent(getApplicationContext(), ArticleActivity.class);
               Article article = articles.get(position);
               launchArticle.putExtra("url", article.getWebUrl());
               startActivity(launchArticle);
           }
       });

        gvResults.setOnScrollListener(endlessScrollListener);

    }

    public void loadNextDataFromApi(int offset, int totalItemsCount) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new androidata objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyDataSetChanged()`
        makeNetworkCall(offset);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makeNetworkCall(int pageNo){
        String s = etQuery.getText().toString();
        Toast.makeText(this, s, Toast.LENGTH_SHORT ).show();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get(NYTSearchHelper.url, NYTSearchHelper.getRequestParams(s,pageNo), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                JSONArray articleResults = null;

                try{
                    articleResults = response.getJSONObject("response").getJSONArray("docs");
                    articles.addAll(Article.fromJSONArray(articleResults));
                    articleArrayAdapter.notifyDataSetChanged();
                    Log.d("debug", articles.toString());

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    public void onArticleSearch(View view) {
        // 1. First, clear the array of data
        articles.clear();
// 2. Notify the adapter of the update
        articleArrayAdapter.notifyDataSetChanged(); // or notifyItemRangeRemoved
// 3. Reset endless scroll listener when performing a new search
        endlessScrollListener.resetState();
        makeNetworkCall(0);
    }
}

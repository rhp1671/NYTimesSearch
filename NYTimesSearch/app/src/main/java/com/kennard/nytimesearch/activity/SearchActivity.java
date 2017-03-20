package com.kennard.nytimesearch.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kennard.nytimesearch.adapter.ArticleAdapter;
import com.kennard.nytimesearch.model.Doc;
import com.kennard.nytimesearch.network.NYTimesNetworkHelper;
import com.kennard.nytimesearch.utility.ArticlePrefs;
import com.kennard.nytimesearch.utility.Utility;


import java.util.ArrayList;
import java.util.List;

import kennard.com.nytimessearch.R;
import kennard.com.nytimessearch.databinding.SearchActivityBinding;

import static kennard.com.nytimessearch.R.id.rvResults;



public class SearchActivity extends AppCompatActivity implements SettingsDialogFragment.SettingsDialogListener {

    private String sSearchParam;
    ArrayList<Doc> articles;
    ArticleAdapter articleAdapter;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;
    SearchActivityBinding binding;
    ArticlePrefs util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.search_activity);
        binding = DataBindingUtil.setContentView(this, R.layout.search_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViews();
        util = new ArticlePrefs(getApplicationContext());
        getSupportActionBar().setTitle(getResources().getString(R.string.title));


    }

    @Override
    protected void onResume() {
        super.onResume();
        util.refresh();
        if (!ArticlePrefs.title.isEmpty()) {
            getSupportActionBar().setSubtitle(ArticlePrefs.title);
        }
    }

    protected void setupViews() {

        articles = new ArrayList<>();
        articleAdapter = new ArticleAdapter(this, articles);

        mRecyclerView = binding.rvResults;
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(articleAdapter);

        SpacesItemDecoration decoration = new SpacesItemDecoration(12);
        mRecyclerView.addItemDecoration(decoration);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(page, totalItemsCount);
            }
        };

        mRecyclerView.addOnScrollListener(endlessScrollListener);

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

        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView view = (SearchView) MenuItemCompat.getActionView(searchItem);
        view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onArticleSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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
            //Intent settingsIntent = new Intent(this, SettingsActivity.class);
            //startActivity(settingsIntent);
            showSettingsDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makeNetworkCall(final int pageNo) {
        util.refresh();
        ArticlePrefs.NYTFilter filter = ArticlePrefs.filter;
        filter.searchParam = sSearchParam;
        filter.pageNo = pageNo;
        NYTimesNetworkHelper networkHelper = new NYTimesNetworkHelper(this);
        networkHelper.getArticles(filter);
    }

    public void onArticleSearch(String text) {
        //first check for connectivity
        if (!Utility.isNetworkAvailable(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.networkerror), Toast.LENGTH_SHORT).show();
            return;
        }
        if (articles != null) {
            articles.clear();
        }
        if (articleAdapter != null) {
            articleAdapter.notifyDataSetChanged(); // or notifyItemRangeRemoved
        }
        endlessScrollListener.resetState();
        sSearchParam = text;
        makeNetworkCall(0);
    }

    public void setNYList(List<Doc> docs) {
        articles.addAll(docs);
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                articleAdapter.notifyItemRangeInserted(articles.size(), articles.size() - 1);
            }
        });
    }

    private void showSettingsDialog() {
        FragmentManager fm = getSupportFragmentManager();
        SettingsDialogFragment editDialog = SettingsDialogFragment.newInstance(null);
        editDialog.show(fm, "fragment_alert");
    }

    @Override
    public void onFinishEditDialog() {
        util.refresh();
        if (!ArticlePrefs.title.isEmpty()) {
            getSupportActionBar().setSubtitle(ArticlePrefs.title);
        }
    }
}

package com.wtzn.qy.qydevm;

import android.app.SearchManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class SearchResultActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();
    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String searchContent = getIntent().getStringExtra(SearchManager.QUERY);
        Toast.makeText(this,searchContent, Toast.LENGTH_SHORT).show();
    }
}

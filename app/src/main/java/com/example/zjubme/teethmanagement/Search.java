package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        hideActionBar();

        //init Search Component
        SearchView searchView = (SearchView)findViewById(R.id.bar_search);
        TextView cancel = (TextView)findViewById(R.id.cancel);
        SearchComponent searchComponent = new SearchComponent(searchView, Search.this, cancel);
        searchComponent.init();
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

}

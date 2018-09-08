package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

public class ArticlePages extends AppCompatActivity {
    private static final int[] views = {R.layout.recyclerview_page, R.layout.recyclerview_page_1, R.layout.recyclerview_page_2};//views in viewPager
    private List<View> pages = new ArrayList<View>();
    private List<ArrayList<Article>> viewPages = new ArrayList<>();
    private ArrayList<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_pages);
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        bottomTabLayout.refreshSelect();
        hideActionBar();
        initSearchView();
        initArticles();
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    private void initSearchView(){
        Button button = (Button)findViewById(R.id.view_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArticlePages.this, Search.class);
                startActivity(intent);
            }
        });
    }

    private void initTablayoutAndViewPager(){
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout_page);
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        ArticleViewPagerAdapter adapter = new ArticleViewPagerAdapter(pages, viewPages);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViewPages(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for(int i = 0; i < views.length;i++){
            pages.add(layoutInflater.inflate(views[i], null, false));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        bottomTabLayout.refreshSelect();
    }

    private void initArticles(){
        for(int i = 0;i < 3;i++){
            viewPages.add(new ArrayList<Article>());
        }
        AVQuery<AVObject> query = new AVQuery<AVObject>("articles");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if(list == null){
                    return;
                }
                if(e == null){
                    for(int i = 0; i < list.size(); i++){
                        AVObject avObject = list.get(i);
                        viewPages.get(Integer.parseInt(avObject.getString("types")))
                                .add(new Article(avObject.getString("title"), avObject.getString("date"),
                                        avObject.getString("author"), avObject.getString("url")));
                    }
                    initViewPages();
                    initTablayoutAndViewPager();
                }
            }
        });
    }
}

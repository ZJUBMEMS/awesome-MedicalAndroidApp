package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ArticlePages extends AppCompatActivity {
    private static final int[] views = {R.layout.recyclerview_page, R.layout.recyclerview_page_1, R.layout.recyclerview_page_2};//views in viewPager
    private List<View> pages = new ArrayList<View>();
    private List<String> articles = new ArrayList<String>();
    private List<String> articles1 = new ArrayList<String>();
    private List<String> articles2 = new ArrayList<String>();
    public static List<Page> article = new ArrayList<Page>();

    class Page{
        private List<String> article;
        public Page(List<String> article){
            this.article = article;
        }

        public List<String> getArticle() {
            return article;
        }
    }

    public void initArticles(){
        for(int i = 0; i < 100; i++){
            articles.add("appleappleappleappleappleappleappleappleappleappleapple");
            articles1.add("pearpearpearpearpearpearpearpearpearpearpearpearpear");
            articles2.add("watermelonwatermelonwatermelonwatermelonwatermelonwatermelonwatermelonwatermelon");
        }
        article.add(new Page(articles));
        article.add(new Page(articles1));
        article.add(new Page(articles2));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_pages);
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        bottomTabLayout.refreshSelect();
        hideActionBar();
        initSearchView();
        initArticles();//需要重写
        initViewPages();
        initTablayoutAndViewPager();
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
        ArticleViewPagerAdapter adapter = new ArticleViewPagerAdapter(pages);
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
    protected void onRestart() {
        super.onRestart();
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        bottomTabLayout.refreshSelect();
    }
}

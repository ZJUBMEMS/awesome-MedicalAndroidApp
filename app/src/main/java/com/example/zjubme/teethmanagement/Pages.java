package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Pages extends AppCompatActivity {
    private static final String[] titles = {"矫正入门", "牙套生活", "效果保持"};
    private static final int[] views = {R.layout.recyclerview_page, R.layout.recyclerview_page_1, R.layout.recyclerview_page_2};
    public static final int[] viewsId = {R.id.recyclerview_page, R.id.recyclerview_page_1, R.id.recyclerview_page_2};
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
            articles.add("apple");
            articles1.add("pear");
            articles2.add("watermelon");
        }
        article.add(new Page(articles));
        article.add(new Page(articles1));
        article.add(new Page(articles2));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);
        hideActionBar();
        initSearchView();
        initArticles();//will be replaced
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
                Intent intent = new Intent(Pages.this, Search.class);
                startActivity(intent);
            }
        });
    }

    private void initTablayoutAndViewPager(){
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout_page);
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(titles, pages);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViewPages(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for(int i = 0; i < views.length;i++){
            pages.add(layoutInflater.inflate(views[i], null, false));
        }
    }
}

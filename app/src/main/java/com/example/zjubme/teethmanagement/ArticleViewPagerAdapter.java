package com.example.zjubme.teethmanagement;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

public class ArticleViewPagerAdapter extends PagerAdapter {
    private static final int[] viewsId = {R.id.recyclerview_page, R.id.recyclerview_page_1, R.id.recyclerview_page_2};
    private static final String[] titles = {"矫正入门", "牙套生活", "效果保持"};//tab titles
    private int[] flag = {0, 0, 0};
    private List<View> pages;
    private List<ArrayList<Article>> viewPages;

    public ArticleViewPagerAdapter(List<View> pages, List<ArrayList<Article>> viewPages){
        super();
        this.pages = pages;
        this.viewPages = viewPages;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        container.addView(pages.get(position));
        onCreatePageList(container, position);
        return pages.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(pages.get(position));
    }

    @Nullable
    @Override
    //tab title is associated with
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public void onCreatePageList(final ViewGroup container, int position){
        RecyclerView recyclerView = (RecyclerView)container.findViewById(viewsId[position]);
        if(flag[position] == 0){
            recyclerView.addItemDecoration(new SpaceItemDecoration(20, 20));//manage item distance
            flag[position] = 1;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        ArticleAdapter adapter = new ArticleAdapter(viewPages.get(position));
        recyclerView.setAdapter(adapter);
    }
}

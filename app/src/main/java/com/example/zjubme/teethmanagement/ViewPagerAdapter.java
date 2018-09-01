package com.example.zjubme.teethmanagement;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private String[] titles;
    private List<View> pages;

    public ViewPagerAdapter(String[] titles, List<View> pages){
        super();
        this.titles = titles;
        this.pages = pages;
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
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(pages.get(position));
        RecyclerView recyclerView = (RecyclerView)container.findViewById(Pages.viewsId[position]);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        ArticleAdapter adapter = new ArticleAdapter(Pages.article.get(position).getArticle());
        recyclerView.setAdapter(adapter);
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

}

package com.example.zjubme.teethmanagement;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ArticleViewPagerAdapter extends PagerAdapter {
    public static final int[] viewsId = {R.id.recyclerview_page, R.id.recyclerview_page_1, R.id.recyclerview_page_2};
    private static final String[] titles = {"矫正入门", "牙套生活", "效果保持"};//tab titles
    private List<View> pages;

    public ArticleViewPagerAdapter(List<View> pages){
        super();
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(20, 20));//manage item distance
        ArticleAdapter adapter = new ArticleAdapter(ArticlePages.article.get(position).getArticle());
        recyclerView.setAdapter(adapter);

    }

}

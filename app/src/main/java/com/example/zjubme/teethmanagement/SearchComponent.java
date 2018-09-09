package com.example.zjubme.teethmanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SearchComponent {
    private SearchView searchView;
    private Context context;
    private TextView cancel;
    private RecyclerView recyclerView;
    private int flag = 0;
    private ArrayList<Article> article;

    public SearchComponent(SearchView searchView, Context context, TextView cancel, RecyclerView recyclerView){
        this.searchView = searchView;
        this.context = context;
        this.cancel = cancel;
        this.recyclerView = recyclerView;
    }

    public void init(){
        initSearchView();
        setCancelButton();
    }


    public SearchView getSearchView() {
        return searchView;
    }

    public TextView getCancel() {
        return cancel;
    }

    private void initSearchView(){
        if(searchView != null){
            //reflect get private field mSearchPlate to remove the underline
            try{
                Class<?> argClass = searchView.getClass();
                Field ownField = argClass.getDeclaredField("mSearchPlate");
                ownField.setAccessible(true);
                View mView = (View) ownField.get(searchView);
                mView.setBackgroundColor(Color.TRANSPARENT);
            }
            catch (Exception e){
                e.printStackTrace();
            }


            //listen to submit
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    AVQuery<AVObject> query = new AVQuery<AVObject>("articles");
                    query.whereContains("title", s);
                    query.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            if(list.size() == 0){
                                Toast.makeText(context, "未查到相关文章", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(e == null){
                                article = new ArrayList<>();
                                for(int i = 0; i < list.size(); i++){
                                    AVObject avObject = list.get(i);
                                    article.add(new Article(avObject.getString("title"), avObject.getString("date"),
                                            avObject.getString("author"), avObject.getString("url"), avObject.getString("image")));
                                }
                                if(flag == 0){
                                    recyclerView.addItemDecoration(new SpaceItemDecoration(20, 20));//manage item distance
                                    flag = 1;
                                }
                                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                                recyclerView.setLayoutManager(layoutManager);
                                ArticleAdapter adapter = new ArticleAdapter(article);
                                recyclerView.setAdapter(adapter);
                            }else{
                                Toast.makeText(context, "未查到相关文章", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                    searchView.clearFocus();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }
    }

    private void setCancelButton(){
        if(cancel != null){
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ArticlePages.class);
                    context.startActivity(intent);
                }
            });
        }
    }

}

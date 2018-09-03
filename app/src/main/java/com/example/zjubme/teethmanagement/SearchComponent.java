package com.example.zjubme.teethmanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class SearchComponent {
    private SearchView searchView;
    private Context context;
    private TextView cancel;

    public SearchComponent(SearchView searchView, Context context, TextView cancel){
        this.searchView = searchView;
        this.context = context;
        this.cancel = cancel;
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
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
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

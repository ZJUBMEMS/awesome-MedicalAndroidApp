package com.example.zjubme.teethmanagement;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class ButtomTabLayout extends LinearLayout{
    public ButtomTabLayout(final Context context, AttributeSet attrs){
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.buttomtab_change, this);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.buttom_tab);

        if(getContext().getClass() == HomePage.class){
            tabLayout.getTabAt(0).setIcon(R.drawable.teeth);
            tabLayout.getTabAt(0).select();
        }
        else if(getContext().getClass() == ArticlePages.class){
            tabLayout.getTabAt(1).setIcon(R.drawable.articles);
            tabLayout.getTabAt(1).select();
        }
        else if(getContext().getClass() == Mine.class){
            tabLayout.getTabAt(2).setIcon(R.drawable.user);
            tabLayout.getTabAt(2).select();
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        if(getContext().getClass() == HomePage.class){
                            break;
                        }
                        Intent intent = new Intent(getContext(), HomePage.class);
                        getContext().startActivity(intent);
                        break;
                    case 1:
                        if(getContext().getClass() == ArticlePages.class){
                            break;
                        }
                        Intent intent1 = new Intent(getContext(), ArticlePages.class);
                        getContext().startActivity(intent1);
                        break;
                    case 2:
                        if(getContext().getClass() == Mine.class){
                            break;
                        }
                        Intent intent2 = new Intent(getContext(), Mine.class);
                        getContext().startActivity(intent2);
                        break;
                        default:
                            break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        if(getContext().getClass() == HomePage.class){
                            break;
                        }
                        Intent intent = new Intent(getContext(), HomePage.class);
                        getContext().startActivity(intent);
                        break;
                    case 1:
                        if(getContext().getClass() == ArticlePages.class){
                            break;
                        }
                        Intent intent1 = new Intent(getContext(), ArticlePages.class);
                        getContext().startActivity(intent1);
                        break;
                    case 2:
                        if(getContext().getClass() == Mine.class){
                            break;
                        }
                        Intent intent2 = new Intent(getContext(), Mine.class);
                        getContext().startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

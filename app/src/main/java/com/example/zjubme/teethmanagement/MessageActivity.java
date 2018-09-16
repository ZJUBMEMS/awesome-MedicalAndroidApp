package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class MessageActivity extends AppCompatActivity {
    private ArrayList<DiagnoseMessage> diagnoseMessages;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_display);
        hideActionBar();
        setBack();
        initNotification();
    }

    private void initNotification(){
        diagnoseMessages = new ArrayList<>();
        AVQuery<AVObject> query = new AVQuery<AVObject>("DiagnoseBack");
        SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
        query.whereEqualTo("phone", phone.getString("phone", ""));
        query.orderByDescending("createdAt");//可能有问题
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if(list.size() == 0){
                    return;
                }
                if(e == null){
                    for(int i = 0; i < list.size(); i++){
                        AVObject avObject = list.get(i);
                        diagnoseMessages.add(new DiagnoseMessage(avObject.getString("message"), sdf.format(avObject.getDate("createdAt"))));
                    }
                    initRecycleView();
                }
            }
        });
    }

    private void initRecycleView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.message_display);
        if(flag == 0){
            recyclerView.addItemDecoration(new SpaceItemDecoration(20, 20));//manage item distance
            flag = 1;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(MessageActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter adapter = new MessageAdapter(diagnoseMessages);
        recyclerView.setAdapter(adapter);
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    private void setBack(){
        ImageButton back = (ImageButton)findViewById(R.id.messagelist_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageActivity.this, HomePage.class);
                startActivity(intent);
            }
        });
    }

}

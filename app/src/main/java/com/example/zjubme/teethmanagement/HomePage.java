package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

public class HomePage extends AppCompatActivity {
    private String tipPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        setWhichTeethSockets();
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        bottomTabLayout.refreshSelect();

        //进入通知界面

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tips tipCreater = new Tips();
                String tip1 = "";
                do{
                    tip1 = tipCreater.getTips();
                }
                while (tip1.equals(tipPre));
                tipPre = tip1;
                TextView textView = (TextView)findViewById(R.id.textView6);
                textView.setText(String.valueOf(tip1));
                AnimationUtils Animation = new AnimationUtils();
                Animation.showAndHiddenAnimation(textView, AnimationUtils.AnimationState.STATE_SHOW,2000);
            }
        });

        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,MessageActivity.class);
                startActivity(intent);
            }
        });

        //进入calendar界面
        ImageButton todoButton = (ImageButton)findViewById(R.id.todo_button);
        todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

//    private void initSharedPreferences(){
//        SharedPreferences.Editor logInFlag = getSharedPreferences("data", MODE_PRIVATE).edit();
//        logInFlag.putString("phone", "");
//        logInFlag.apply();
//    }

    @Override
    protected void onRestart() {
        super.onRestart();
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        setWhichTeethSockets();
        bottomTabLayout.refreshSelect();
    }

    private void setWhichTeethSockets(){
        final TextView whichTeeth = (TextView)findViewById(R.id.textView4);
        AVQuery<AVObject> findWhichTeeth = new AVQuery<AVObject>("TeethSocketsSettings");
        SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
        findWhichTeeth.whereEqualTo("phone", phone.getString("phone", ""));
        findWhichTeeth.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if(avObject == null){
                    whichTeeth.setText("您正在佩戴XXXX");
                    return;
                }
                if(e == null){
                    whichTeeth.setText("您正在佩戴"+avObject.getString("types"));
                    return;
                }
            }
        });
    }
}

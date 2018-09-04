package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mine);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        ImageButton image_Setting = (ImageButton)findViewById(R.id.image_Setting);
        image_Setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mine.this,Mine.class);
                startActivity(intent);
            }
        });

        ImageButton image_Backing = (ImageButton)findViewById(R.id.image_Backing);
        image_Backing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mine.this,Mine.class);
                startActivity(intent);
            }
        });

        ImageButton image_Avatar = (ImageButton)findViewById(R.id.image_Avatar);
        image_Avatar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mine.this,Mine.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayoutlinearLayout_loginstatus = (LinearLayout)findViewById(R.id.linearLayout_loginstatus);
        linearLayoutlinearLayout_loginstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mine.this, Mine.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout_loginstatus = (LinearLayout)findViewById(R.id.linearLayout_personalinformation);
        linearLayout_loginstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mine.this, Mine.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout_selfhelp = (LinearLayout)findViewById(R.id.linearLayout_selfhelp);
        linearLayout_selfhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mine.this, Mine.class);
                startActivity(intent);
            }
        });


        LinearLayout linearLayoutlinearLayout_braces = (LinearLayout)findViewById(R.id.linearLayout_braces);
        linearLayoutlinearLayout_braces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mine.this, SelectTypeActivity.class);
                startActivity(intent);
            }
        });

    }
}

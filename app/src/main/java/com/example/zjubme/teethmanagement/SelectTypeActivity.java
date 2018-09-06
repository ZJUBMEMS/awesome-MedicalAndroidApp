package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class SelectTypeActivity extends AppCompatActivity {
    private String[] processName = {"totalSteps", "nowSteps", "todayHours"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_type);
        hideActionBar();
        setBack();
        Button button = (Button)findViewById(R.id.button_SelectAc_to_DaysAc_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectTypeActivity.this, DaysActivity.class);
                intent.putExtra("types", "invisible");
                startActivity(intent);
            }
        });

        Button button1 = (Button) findViewById(R.id.button_SelectAc_to_DurationAc);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectTypeActivity.this, DurationActivity.class);
                intent.putExtra("types", "brace");
                for(int i = 0;i < processName.length;i++){
                    intent.putExtra(processName[i], "");
                }
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button_SelectAc_to_DurationAc_1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectTypeActivity.this, DurationActivity.class);
                intent.putExtra("types", "keep");
                for(int i = 0;i < processName.length;i++){
                    intent.putExtra(processName[i], "");
                }
                startActivity(intent);
            }
        });

    }

    private void setBack(){
        ImageButton back = (ImageButton)findViewById(R.id.select_type_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectTypeActivity.this, Mine.class);
                startActivity(intent);
            }
        });
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }
}

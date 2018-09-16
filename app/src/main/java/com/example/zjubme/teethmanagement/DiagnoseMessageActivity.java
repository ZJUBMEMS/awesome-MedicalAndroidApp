package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DiagnoseMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_message);
        hideAction();
        setBack();
        init();
    }

    private void init(){
        TextView result = (TextView)findViewById(R.id.yourProblems);
        result.setText(getIntent().getStringExtra("message"));
    }

    private void setBack(){
        ImageButton back = (ImageButton)findViewById(R.id.dianose_message_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiagnoseMessageActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void hideAction(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }
}

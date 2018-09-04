package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SelectTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_type);
        Button button = (Button)findViewById(R.id.button_SelectAc_to_DaysAc_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectTypeActivity.this, DaysActivity.class);
                startActivity(intent);
            }
        });

        Button button1 = (Button) findViewById(R.id.button_SelectAc_to_DurationAc);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectTypeActivity.this, DurationActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button_SelectAc_to_DurationAc_1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectTypeActivity.this, DurationActivity.class);
                startActivity(intent);
            }
        });

    }
}

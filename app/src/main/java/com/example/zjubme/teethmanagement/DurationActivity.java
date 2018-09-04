package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duration);
        Button button = (Button) findViewById(R.id.finishsettings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "完成设置", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DurationActivity.this, Mine.class);
                startActivity(intent);
            }
        });
    }
}

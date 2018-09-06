package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

import java.util.ArrayList;

public class DurationActivity extends AppCompatActivity {
    private String[] processName = {"types", "totalSteps", "nowSteps", "todayHours","startTime", "termination"};
    private ArrayList<String> tabRowName = new ArrayList<>();
    private int[] textId = {R.id.startTime, R.id.termination};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duration);
        hideActionBar();
        setBack();
        Button button = (Button) findViewById(R.id.finishsettings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0;i < 4;i++){
                    tabRowName.add(getIntent().getStringExtra(processName[i]));
                }
                Intent intent = new Intent(DurationActivity.this, Mine.class);
                for(int i = 0;i < textId.length;i++){
                    EditText editText = (EditText)findViewById(textId[i]);
                    if(editText.getText().toString().equals("")){
                        Toast.makeText(v.getContext(), "信息填写不完整", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tabRowName.add(editText.getText().toString());
                }
                updateDateBase();
                Toast.makeText(v.getContext(), "完成设置", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    private void updateDateBase(){
        SharedPreferences storge = getSharedPreferences("data", MODE_PRIVATE);
        String phone = storge.getString("phone", "");
        AVQuery<AVObject> teethSocketsSettings = new AVQuery<>("TeethSocketsSettings");
        teethSocketsSettings.whereEqualTo("phone", phone);
        teethSocketsSettings.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if(avObject == null){
                    return;
                }
                if(e == null){
                    avObject.deleteInBackground();
                }
            }
        });
        AVObject newRecords = new AVObject("TeethSocketsSettings");
        newRecords.put("phone", phone);
        for(int i = 0;i < processName.length;i++){
            newRecords.put(processName[i], tabRowName.get(i));
        }
        newRecords.saveInBackground();
    }

    private void setBack(){
        ImageButton back = (ImageButton)findViewById(R.id.duration_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //according to types
                if(getIntent().getStringExtra("types").equals("invisible")){
                    Intent intent = new Intent(DurationActivity.this, DaysActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(DurationActivity.this, SelectTypeActivity.class);
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

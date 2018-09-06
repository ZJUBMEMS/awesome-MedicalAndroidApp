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

public class DentistDiagnosisActivity extends AppCompatActivity {
    private int[] editName = {R.id.editText_name, R.id.editText_birthday};
    private String[] tabName = {"name", "birthday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dentist_diagnosis_activity);
        hideActionBar();
        setBack();
        searchDataBaseUsers();
        setSubmit();
        ImageButton imageButton_1 = (ImageButton)findViewById(R.id.imageButton_1);
        imageButton_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DentistDiagnosisActivity.this,Mine.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton_2 = (ImageButton)findViewById(R.id.imageButton_2);
        imageButton_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DentistDiagnosisActivity.this,Mine.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton_3 = (ImageButton)findViewById(R.id.imageButton_3);
        imageButton_3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DentistDiagnosisActivity.this,Mine.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton_4 = (ImageButton)findViewById(R.id.imageButton_4);
        imageButton_4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DentistDiagnosisActivity.this,Mine.class);
                startActivity(intent);
            }
        });
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    private void setBack(){
        ImageButton back = (ImageButton)findViewById(R.id.self_help_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DentistDiagnosisActivity.this, Mine.class);
                startActivity(intent);
            }
        });
    }

    private void setSubmit(){
        Button button = (Button)findViewById(R.id.submit_self_help);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "提交成功，请耐心等待医生回复", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DentistDiagnosisActivity.this, Mine.class);
                startActivity(intent);
            }
        });
    }

    private void searchDataBaseUsers(){
        AVQuery<AVObject> query = new AVQuery<>("Users");
        SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
        query.whereEqualTo("phone", phone.getString("phone", ""));
        query.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if(avObject == null){
                    return;
                }

                if(e == null){
                   for(int i = 0;i < editName.length;i++){
                       EditText editText = (EditText)findViewById(editName[i]);
                       editText.setText(avObject.getString(tabName[i]));
                   }
                }
            }
        });
    }
}

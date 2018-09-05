package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.HashMap;


public class RegisterInformation extends AppCompatActivity {
    private final int[] buttonId = {R.id.clearName, R.id.clearOtherName, R.id.clearGender, R.id.clearBirthDay, R.id.clearPhone};
    private HashMap<Integer, Integer> textId = new HashMap<>();
    private final String[] tabRowName = {"name", "othername", "gender", "birthday", "phone"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_information);
        hideActionBar();
        searchDataBaseUsers();
        setBack();
        setSubmit();
        initTextIdWithButton();
        setCancelEvent();
    }

    private void setBack(){
        ImageButton back = (ImageButton) findViewById(R.id.personal_information_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterInformation.this, Mine.class);
                startActivity(intent);
            }
        });
    }

    private void setSubmit(){
        Button button = (Button) findViewById(R.id.button_mainAc_to_SelectAc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkNullExist()){
                    Toast.makeText(view.getContext(), "信息填写不完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                updateDateBaseUsers();
                Intent intent = new Intent(RegisterInformation.this, Mine.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkNullExist(){
        for(int i = 0; i < buttonId.length; i++){
            EditText editText = (EditText) findViewById(textId.get(buttonId[i]));
            if(editText.getText().toString().equals("")){
                return false;
            }
        }
        return true;
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
                    for(int i = 0;i < buttonId.length;i++){
                        EditText editText = (EditText)findViewById(textId.get(buttonId[i]));
                        editText.setText(avObject.getString(tabRowName[i]));
                    }
                }
            }
        });
    }

    private void updateDateBaseUsers(){
        AVObject User = new AVObject("Users");
        EditText phoneText = (EditText)findViewById(textId.get(buttonId[4]));
        String phone = phoneText.getText().toString();
        for(int i = 0;i < buttonId.length;i++){
            EditText editText = (EditText)findViewById(textId.get(buttonId[i]));
            User.put(tabRowName[i], editText.getText().toString());
        }
        AVQuery<AVObject> oldUser = new AVQuery<>("Users");
        oldUser.whereEqualTo("phone", phone);
        oldUser.getFirstInBackground(new GetCallback<AVObject>() {
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
        User.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setCancelEvent(){
        for(int i = 0;i < buttonId.length;i++){
            ImageView button = (ImageView) findViewById(buttonId[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText editText = (EditText)findViewById(textId.get(view.getId()));
                    editText.setText("");
                }
            });
        }
    }

    private void initTextIdWithButton(){
        textId.put(R.id.clearName, R.id.et_name);
        textId.put(R.id.clearOtherName, R.id.other_name);
        textId.put(R.id.clearGender, R.id.gender);
        textId.put(R.id.clearBirthDay, R.id.birthDay);
        textId.put(R.id.clearPhone, R.id.et_phone);
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }
}

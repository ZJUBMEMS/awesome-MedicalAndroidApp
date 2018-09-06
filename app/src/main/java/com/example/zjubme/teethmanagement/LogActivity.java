package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

public class LogActivity extends AppCompatActivity {
//    private boolean connectFlag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        hideActionBar();
        setBack();
        setLogin();
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    private void setBack(){
        ImageButton back = (ImageButton)findViewById(R.id.log_in_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogActivity.this, Mine.class);
                startActivity(intent);
            }
        });
    }

    private void setLogin(){
        Button button = (Button)findViewById(R.id.button_LogAc_to_MainAc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor logInFlag = getSharedPreferences("data", MODE_PRIVATE).edit();
                EditText editText = (EditText)findViewById(R.id.mobile_login);
                if(editText.getText().toString().equals("")){
                    Toast.makeText(view.getContext(), "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!checkConnect(view)){
                    return;
                }
                logInFlag.putString("phone", editText.getText().toString());
                logInFlag.apply();
                Toast.makeText(view.getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LogActivity.this, Mine.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkConnect(View view){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        // 检查网络连接，如果无网络可用，就不需要进行连网操作等
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            Toast.makeText(view.getContext(), "网络异常", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
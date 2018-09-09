package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mine);
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        bottomTabLayout.refreshSelect();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        if(checkWhetherLogin()){
            TextView logStatus = (TextView)findViewById(R.id.text_loginstatus);
            logStatus.setText(R.string.log_out);
        }

        LinearLayout linearLayoutlinearLayout_loginstatus = (LinearLayout)findViewById(R.id.linearLayout_loginstatus);
        linearLayoutlinearLayout_loginstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkWhetherLogin()){
                    TextView logStatus = (TextView)findViewById(R.id.text_loginstatus);
                    logStatus.setText(R.string.text_loginstatus);
                    initSharedPreferences();
                    return;
                }
                Intent intent = new Intent(Mine.this, LogActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout_loginstatus = (LinearLayout)findViewById(R.id.linearLayout_personalinformation);
        linearLayout_loginstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkWhetherLogin() || !checkConnect(v)){
                    Intent intent = new Intent(Mine.this, CuteActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(Mine.this, RegisterInformation.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout_selfhelp = (LinearLayout)findViewById(R.id.linearLayout_selfhelp);
        linearLayout_selfhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkWhetherLogin() || !checkConnect(v)){
                    Intent intent = new Intent(Mine.this, CuteActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(Mine.this, DentistDiagnosisActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout linearLayoutlinearLayout_braces = (LinearLayout)findViewById(R.id.linearLayout_braces);
        linearLayoutlinearLayout_braces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkWhetherLogin() || !checkConnect(v)){
                    Intent intent = new Intent(Mine.this, CuteActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(Mine.this, SelectTypeActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkWhetherLogin(){
        SharedPreferences storge = getSharedPreferences("data", MODE_PRIVATE);
        String phone = storge.getString("phone", "");
        if(phone.equals("")){
            return false;
        }

        return true;
    }

    private void initSharedPreferences(){
        SharedPreferences.Editor logInFlag = getSharedPreferences("data", MODE_PRIVATE).edit();
        logInFlag.putString("phone", "");
        logInFlag.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        bottomTabLayout.refreshSelect();
        if(!checkConnect(bottomTabLayout)){
            TextView logStatus = (TextView)findViewById(R.id.text_loginstatus);
            logStatus.setText(R.string.text_loginstatus);
        }
        if(checkWhetherLogin()){
            TextView logStatus = (TextView)findViewById(R.id.text_loginstatus);
            logStatus.setText(R.string.log_out);
        }
    }

    private boolean checkConnect(View view){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        // 检查网络连接，如果无网络可用，就不需要进行连网操作等
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            Toast.makeText(view.getContext(), "网络异常", Toast.LENGTH_SHORT).show();
            clearSavedData();
            return false;
        }
        return true;
    }

    private void clearSavedData(){
        SharedPreferences.Editor logInFlag = getSharedPreferences("data", MODE_PRIVATE).edit();
        logInFlag.putString("phone", "");
        logInFlag.apply();
    }
}

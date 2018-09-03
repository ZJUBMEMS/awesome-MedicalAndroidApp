package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;




public class RegisterInformation extends AppCompatActivity {
    private EditText mEtName;
    private EditText mIdCard;
    private EditText mPhone;
    private EditText mCaptcha;
    private ImageView clearName;
    private ImageView clearIdCard;
    private ImageView clearPhone;
    private ImageView clearCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_information);
        Button button = (Button) findViewById(R.id.button_mainAc_to_SelectAc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterInformation.this, SelectTypeActivity.class);
                startActivity(intent);
            }
        });
    }
}

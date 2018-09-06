package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class DaysActivity extends AppCompatActivity {
    private int[] textId = {R.id.totalSteps, R.id.nowSteps, R.id.todayHours};
    private String[] processName = {"totalSteps", "nowSteps", "todayHours"};
    private String total = "";
    private String now = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        hideActionBar();
        setBack();
        final String types = getIntent().getStringExtra("types");
        Button button = (Button)findViewById(R.id.button_DaysAc_to_DurationAc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaysActivity.this, DurationActivity.class);
                intent.putExtra("types", types);
                for(int i = 0;i < textId.length;i++){
                    EditText editText = (EditText)findViewById(textId[i]);
                    if(editText.getText().toString().equals("")){
                        Toast.makeText(view.getContext(), "信息填写不完整", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(textId[i] == R.id.todayHours){
                        if(Integer.parseInt(editText.getText().toString()) > 24 || Integer.parseInt(editText.getText().toString()) < 0){
                            Toast.makeText(view.getContext(), "佩戴时间填写错误", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    intent.putExtra(processName[i], editText.getText().toString());
                }
                total = ((EditText)findViewById(textId[0])).getText().toString();
                now = ((EditText)findViewById(textId[1])).getText().toString();
                if(Integer.parseInt(total) < Integer.parseInt(now)){
                    Toast.makeText(view.getContext(), "信息填写错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(intent);
            }
        });
    }

    private void setBack(){
        ImageButton back = (ImageButton)findViewById(R.id.days_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaysActivity.this, SelectTypeActivity.class);
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

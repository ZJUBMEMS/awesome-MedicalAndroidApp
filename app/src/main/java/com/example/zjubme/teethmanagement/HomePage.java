package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends AppCompatActivity {
    private String tipPre = "";
    private String tip = "";
    public DateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    public void save(long CuTime, int Switch, long AcTime, String phone){
        AVObject avTime = new AVObject("Time");
        avTime.put("phone",phone);
        avTime.put("Switch",Switch);
        avTime.put("CuTime",CuTime);
        avTime.put("AcTime", AcTime);
        avTime.saveInBackground();
    }

    public void save_data(long WeekData, long DaTime, String phone, int WeekDay){
        AVObject avTime = new AVObject("TimeData");
        avTime.put("phone",phone);
        avTime.put("WeekData",WeekData);
        avTime.put("DaTime",DaTime);
        avTime.put("WeekDay",WeekDay);
        avTime.saveInBackground();
    }

    public void load_b() {
        AVQuery<AVObject> avQueryTime = new AVQuery<>("Time");
        final SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
        if(phone.getString("phone", "").equals("")){
            Intent intent = new Intent(HomePage.this, CuteActivity.class);
            startActivity(intent);
            return;
        }
        avQueryTime.whereEqualTo("phone", phone.getString("phone", ""));
        avQueryTime.orderByDescending("createdAt");
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        avQueryTime.getFirstInBackground(new GetCallback<AVObject>() {

            @Override
            public void done(AVObject avObject, AVException e) {
                if (e==null){
                    long NextDay;
                    long ZoTime;
                    long Weekdata;
                    long AcTime = avObject.getLong("AcTime");
                    long CuTime = avObject.getLong("CuTime");
                    int Switch = avObject.getInt("Switch");
                    int WeekDay;
                    Date NowTime = new Date();
                    ZoTime = NowTime.getTime()/1000/60/60/24;
                    ZoTime = ZoTime*60*24-480;
                    if(ZoTime > CuTime){
                        NextDay = 1;
                    }else {
                        NextDay = 0;
                    }
                    Button button = (Button) findViewById(R.id.button);
                    if (NextDay == (long)0){
                        if (Switch == 1){
                            Switch = 0;
                            AcTime = AcTime + (NowTime.getTime()/1000/60 - CuTime);
                            button.setText(String.valueOf("开始"));
                        }else {
                            Switch = 1;
                            button.setText(String.valueOf("停止"));
                        }
                    }else {
                        if (Switch == 1){
                            Switch = 0;
                            AcTime = AcTime + (ZoTime - CuTime);

                            long Correct = CuTime/60/24 + 4;
                            Weekdata = Correct/7;
                            WeekDay = (int)(Correct%Weekdata);
                            save_data(Weekdata, AcTime, phone.getString("phone", ""),WeekDay);

                            AcTime = (NowTime.getTime()/1000/60 - ZoTime);
                            button.setText(String.valueOf("开始"));
                        }else {
                            Switch = 1;

                            long Correct = CuTime/60/24 + 4;
                            Weekdata = Correct/7;
                            WeekDay = (int)(Correct%Weekdata);
                            save_data(Weekdata, AcTime, phone.getString("phone", ""),WeekDay);

                            AcTime = (long) 0;
                            button.setText(String.valueOf("停止"));
                        }
                    }
                    CuTime = NowTime.getTime()/1000/60;
//                发送今日进度信息Autime
                    progressBar.setProgress((int)AcTime);
                    save(CuTime, Switch, AcTime, phone.getString("phone", ""));
                }
            }
        });
    }

    public void load_l() {
        AVQuery<AVObject> avQueryTime = new AVQuery<>("Time");
        final SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
        if(phone.getString("phone", "").equals("")){
            return;
        }
        avQueryTime.whereEqualTo("phone", phone.getString("phone", ""));
        avQueryTime.orderByDescending("createdAt");
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        avQueryTime.getFirstInBackground(new GetCallback<AVObject>() {

            @Override
            public void done(AVObject avObject, AVException e) {
                Button button = (Button) findViewById(R.id.button);
                if (avObject==null){
                    Date NowTime = new Date();
                    save(NowTime.getTime()/1000/60, 0, (long)0, phone.getString("phone", ""));
                    return;
                }
                if (e==null){
                    long AcTime;
                    long CuTime;
                    int Switch;
                    long NextDay;
                    long ZoTime;
                    Switch = avObject.getInt("Switch");
                    AcTime = avObject.getLong("AcTime");
                    CuTime = avObject.getLong("CuTime");
                    Date NowTime = new Date();
                    ZoTime = NowTime.getTime()/1000/60/60/24;
                    ZoTime = ZoTime*60*24-480;
                    if(ZoTime > CuTime){
                        NextDay = 1;
                    }else {
                        NextDay = 0;
                    }
                    if (NextDay == (long)0){
                        if (Switch == 1){
                            AcTime = AcTime + (NowTime.getTime()/1000/60 - CuTime);
                            button.setText(String.valueOf("停止"));
                        }else {
                            button.setText(String.valueOf("开始"));
                        }
                    }else {
                        if (Switch == 1){
                            AcTime = (NowTime.getTime()/1000/60 - ZoTime);
                            button.setText(String.valueOf("停止"));
                        }else {
                            AcTime = (long) 0;
                            button.setText(String.valueOf("开始"));
                        }
                    }
                    progressBar.setProgress((int)AcTime);
//                发送今日进度信息Actime
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        load_l();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        setWhichTeethSockets();
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        bottomTabLayout.refreshSelect();
        //进入通知界面

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tips tipCreater = new Tips();
                do{
                    tip = tipCreater.getTips();
                }
                while (tip.equals(tipPre));
                tipPre = tip;
                TextView textView = (TextView)findViewById(R.id.textView6);
                textView.setText(String.valueOf(tip));
                AnimationUtils Animation = new AnimationUtils();
                Animation.showAndHiddenAnimation(textView, AnimationUtils.AnimationState.STATE_SHOW,2000);
            }
        });

        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,MessageActivity.class);
                startActivity(intent);
            }
        });

        //进入calendar界面
        ImageButton todoButton = (ImageButton)findViewById(R.id.todo_button);
        todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
                if(phone.getString("phone", "").equals("")){
                    Intent intent = new Intent(HomePage.this, CuteActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(HomePage.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
                Log.d("phone", phone.getString("phone", ""));
                if(phone.getString("phone", "").equals("")){
                    Intent intent = new Intent(HomePage.this, CuteActivity.class);
                    startActivity(intent);
                    return;
                }
                load_b();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        BottomTabLayout bottomTabLayout = (BottomTabLayout)findViewById(R.id.bottom_layout);
        setWhichTeethSockets();
        bottomTabLayout.refreshSelect();
        SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
        if(phone.getString("phone", "").equals("")){
            return;
        }
        load_l();
    }


    private void setWhichTeethSockets(){
        final Date nowTime = new Date();
        final TextView whichTeeth = (TextView)findViewById(R.id.textView4);
        final TextView hasGoneTime = (TextView)findViewById(R.id.textView3);
        final TextView hasWearTime = (TextView)findViewById(R.id.textView5);
        AVQuery<AVObject> findWhichTeeth = new AVQuery<AVObject>("TeethSocketsSettings");
        SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
        findWhichTeeth.whereEqualTo("phone", phone.getString("phone", ""));
        findWhichTeeth.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if(avObject == null){
                    whichTeeth.setText("您正在佩戴XXXX");
                    hasGoneTime.setText("0天");
                    hasWearTime.setText("已经佩戴0/0天");
                    return;
                }

                if(e == null){
                    String type = avObject.getString("types");
                    String startTime = avObject.getString("startTime");
                    String termination = avObject.getString("termination");
                    Date startDate;
                    Date endDate;
                    try {
                        startDate = format.parse(startTime);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        return;
                    }
                    try {
                        endDate = format.parse(termination);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        return;
                    }

                    long totalTime = 0;
                    totalTime = (endDate.getTime() - startDate.getTime())/1000/60/60/24;
                    long goneTime = 0;
                    goneTime = (nowTime.getTime() - startDate.getTime())/1000/60/60/24;
                    if(goneTime <= totalTime){
                        hasGoneTime.setText(goneTime + "天");
                    }else{
                        hasGoneTime.setText(totalTime + "天");
                    }

                    if(type.equals("invisible")){
                        type = "隐形牙套";
                        int daysPerStep = Integer.parseInt(avObject.getString("daysPerStep"));
                        int totalSteps = Integer.parseInt(avObject.getString("totalSteps"));
                        if(goneTime <= totalTime){
                            hasWearTime.setText("已经佩戴" + (goneTime/daysPerStep) + "/" + totalSteps + "步");
                        }else{
                            hasWearTime.setText("已经佩戴" + totalSteps + "/" + totalSteps + "步");
                        }
                    }
                    else if(type.equals("brace")){
                        type = "固定牙套";
                        if(goneTime <= totalTime){
                            hasWearTime.setText("已经佩戴" + goneTime + "/" + totalTime+ "天");
                        }else{
                            hasWearTime.setText("已经佩戴" + totalTime + "/" + totalTime+ "天");
                        }
                    } else {
                        type = "保持器";
                        if(goneTime <= totalTime){
                            hasWearTime.setText("已经佩戴" + goneTime + "/" + totalTime+ "天");
                        }else{
                            hasWearTime.setText("已经佩戴" + totalTime + "/" + totalTime+ "天");
                        }
                    }
                    whichTeeth.setText("您正在佩戴"+ type);
                    return;
                }
            }
        });
    }
}

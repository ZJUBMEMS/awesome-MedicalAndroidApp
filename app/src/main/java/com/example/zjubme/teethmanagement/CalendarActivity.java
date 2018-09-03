package com.example.zjubme.teethmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.zjubme.teethmanagement.R;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_calendar_layout);
        CalendarView mCalendar = (CalendarView)findViewById(R.id.calendarView);
        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {//对日历添加点击事件

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int day) {

            }
        });
    }
}

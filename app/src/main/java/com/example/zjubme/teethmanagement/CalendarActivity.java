package com.example.zjubme.teethmanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {
    private MaterialCalendarView materialCalendarView;
    private CalendarViewDate calendarViewDate = new CalendarViewDate(0, new Date());
    private List<CalendarViewDate> calendarViewDateList = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_calendar_layout);
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar_view);
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        SimpleDateFormat dff = new SimpleDateFormat("yyyy:MM:dd");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String curDate = dff.format(new Date());
        final String currentDate[] = curDate.split(":");
        calendarViewDate.setDate(new Date());
        calendarViewDate.setDATE_SELECTED(0);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
              int year = Integer.parseInt(currentDate[0]);
              int month = Integer.parseInt(currentDate[1]);
              int day = Integer.parseInt(currentDate[2]);
              if ((day == calendarDay.getDay())&&(month == (calendarDay.getMonth()+1))&&(year == calendarDay.getYear())){
                  materialCalendarView.setSelectedDate(calendarDay);
                  materialCalendarView.setSelectionColor(0xff66ccff);
                  calendarViewDate.setDATE_SELECTED(1);
                  calendarViewDate.setDate(calendarDay.getDate());
                  calendarViewDateList.add(calendarViewDate);
              }
              else if (calendarViewDate.getDATE_SELECTED() == 1){
                  materialCalendarView.setSelectionColor(0x0066ccff);
                  materialCalendarView.setSelectedDate(new Date());
                  materialCalendarView.setSelectionColor(0xff66ccff);
              }
              else {
                  materialCalendarView.setSelectionColor(0x0066ccff);
              }

                Log.v("CalendarActivity", ""+calendarDay.getYear());
                Log.v("CalendarActivity", ""+(calendarDay.getMonth()+1));
                Log.v("CalendarActivity", ""+calendarDay.getDay());
            }
        });
        Log.v("CalendarActivity", currentDate[0]);
        Log.v("CalendarActivity", currentDate[1]);
        Log.v("CalendarActivity", currentDate[2]);
    }
}

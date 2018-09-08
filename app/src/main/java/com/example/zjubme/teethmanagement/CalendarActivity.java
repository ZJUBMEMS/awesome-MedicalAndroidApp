package com.example.zjubme.teethmanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
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
    private List<CalendarDay> calendarDayList = new ArrayList<>();
    private final SignDayDecorator signDayDecorator = new SignDayDecorator(CalendarActivity.this);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_calendar_layout);
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        SimpleDateFormat dff = new SimpleDateFormat("yyyy:MM:dd");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String curDate = dff.format(new Date());
        final String currentDate[] = curDate.split(":");
        calendarViewDate.setDate(new Date());
        calendarViewDate.setDATE_SELECTED(0);
        CalendarDay calendarDay1 = calendarViewDateList.get(calendarViewDateList.size()-1).getCalendarDay();
        Date date = new Date();
        CalendarDay calendarDay2 = CalendarDay.from(date);
        int d = calendarDay2.getDay()-calendarDay1.getDay();
        if (d>=2){
            for (int i=1; i<d; i++){
                CalendarDay calendarDay = CalendarDay.from(calendarDay1.getYear(), calendarDay1.getMonth(), calendarDay1.getDay()+i);
                NotSignDayDecorator notSignDayDecorator = new NotSignDayDecorator(CalendarActivity.this, calendarDay);
                materialCalendarView.addDecorator(notSignDayDecorator);
            }
        }
        for(int i = 0 ; i < calendarDayList.size() ; i++) {
            materialCalendarView.setDateSelected(calendarDayList.get(i), true);
            materialCalendarView.setSelectionColor(0xff66ccff);}
            materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {

              int year = Integer.parseInt(currentDate[0]);
              int month = Integer.parseInt(currentDate[1]);
              int day = Integer.parseInt(currentDate[2]);
              if ((day == calendarDay.getDay())&&(month == (calendarDay.getMonth()+1))&&(year == calendarDay.getYear())&&(calendarViewDate.getDATE_SELECTED()==0)) {
                  materialCalendarView.addDecorator(signDayDecorator);
                  calendarViewDate.setDATE_SELECTED(1);
                  calendarViewDate.setDate(calendarDay.getDate());
                  calendarViewDate.setCalendarDay(calendarDay);
                  calendarViewDateList.add(calendarViewDate);
                  calendarDayList.add(calendarDay);
              }
              else if ((day == calendarDay.getDay())&&(month == (calendarDay.getMonth()+1))&&(year == calendarDay.getYear())&&(calendarViewDate.getDATE_SELECTED()!=0)){
                  materialCalendarView.addDecorator(signDayDecorator);
              }
              else if (calendarViewDate.getDATE_SELECTED()!=2)
              {
                  ToBeSignedDayDecorator toBeSignedDayDecorator = new ToBeSignedDayDecorator(CalendarActivity.this, calendarDay);
                  materialCalendarView.addDecorator(toBeSignedDayDecorator);
              }
              else {
                  NotSignDayDecorator notSignDayDecorator = new NotSignDayDecorator(CalendarActivity.this, calendarDay);
                  materialCalendarView.addDecorator(notSignDayDecorator);
              }
              for (int i =0; i<calendarViewDateList.size(); i++){
                  Log.v("DATE_SE:ECTED", calendarViewDateList.get(i).getDATE_SELECTED()+"");
              }
            }
        });

    }
}

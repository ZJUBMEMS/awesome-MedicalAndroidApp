package com.example.zjubme.teethmanagement;

import android.content.Context;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jlx on 07/09/2018.
 */

public class ToBeSignedDayDecorator implements DayViewDecorator {
    private final Context context;
    private CalendarDay calendarDay;

    public ToBeSignedDayDecorator(Context context, CalendarDay calendarDay) {
        this.context = context;
        this.calendarDay = calendarDay;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        if ((day.getYear()==calendarDay.getYear())&&(day.getMonth()==calendarDay.getMonth())&&(day.getDay()==calendarDay.getDay())){
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(context.getResources().getDrawable(R.drawable.tobe_signed_selector));
    }
}

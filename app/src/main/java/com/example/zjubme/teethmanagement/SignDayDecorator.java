package com.example.zjubme.teethmanagement;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jlx on 06/09/2018.
 */

public class SignDayDecorator implements DayViewDecorator {

    private final Context context;
    private CalendarDay calendarDay;

    public SignDayDecorator(Context context) {
        this.context = context;
    }
    public SignDayDecorator(Context context, CalendarDay calendarDay) {
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
        view.setSelectionDrawable(context.getResources().getDrawable(R.drawable.sign_selector));
    }
}



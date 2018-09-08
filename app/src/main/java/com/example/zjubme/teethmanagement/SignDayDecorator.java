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

    public SignDayDecorator(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy:MM:dd");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String curDate = dff.format(new Date());
        final String currentDate[] = curDate.split(":");
        int [] week = new int[3];
        for (int i=0 ; i<currentDate.length ; i++ ){
            week[i] = Integer.parseInt(currentDate[i]);
        }
        int [] date= new int[3];
        date[0] = day.getYear();
        date[1] = day.getMonth()+1;
        date[2] = day.getDay();
        boolean isEqual = false;
        for (int i = 0 ; i<currentDate.length; i++){
            if (week[i] != date[i]){
                return isEqual;
            }
        }
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(context.getResources().getDrawable(R.drawable.sign_selector));
    }
}



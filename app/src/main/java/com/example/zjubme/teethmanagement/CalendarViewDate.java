package com.example.zjubme.teethmanagement;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;

/**
 * Created by jlx on 06/09/2018.
 */

public class CalendarViewDate {
    private int DATE_SELECTED;
    private Date date;
    private CalendarDay calendarDay;

    public CalendarDay getCalendarDay() {
        return calendarDay;
    }

    public void setCalendarDay(CalendarDay calendarDay) {
        this.calendarDay = calendarDay;
    }

    public CalendarViewDate() {
    }

    public CalendarViewDate(int DATE_SELECTED, Date date) {
        this.DATE_SELECTED = DATE_SELECTED;
        this.date = date;
    }

    public int getDATE_SELECTED() {
        return DATE_SELECTED;
    }

    public void setDATE_SELECTED(int DATE_SELECTED) {
        this.DATE_SELECTED = DATE_SELECTED;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

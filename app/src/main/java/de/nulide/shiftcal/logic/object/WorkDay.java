package de.nulide.shiftcal.logic.object;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;

public class WorkDay {
    private CalendarDay date;
    private Shift shift;

    public WorkDay(CalendarDay date, Shift shift) {
        this.date = date;
        this.shift = shift;
    }

    public CalendarDay getDate() {
        return date;
    }

    public void setDate(CalendarDay date) {
        this.date = date;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public boolean checkDate(CalendarDay d){
        if(this.date.getYear() == d.getYear()){
            if(this.date.getMonth() == d.getMonth()){
                if(this.date.getDay() == d.getDay()) {
                    return true;
                }
            }
        }
        return false;
    }
}

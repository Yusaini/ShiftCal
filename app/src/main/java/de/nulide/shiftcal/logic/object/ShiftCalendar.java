package de.nulide.shiftcal.logic.object;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

public class ShiftCalendar implements Serializable {

    private LinkedList<WorkDay> calendar;
    private LinkedList<Shift> shifts;

    public ShiftCalendar() {
        calendar = new LinkedList<>();
        shifts = new LinkedList<>();
    }

    public LinkedList<WorkDay> getCalendar() {
        return calendar;
    }

    public void setCalendar(LinkedList<WorkDay> calendar) {
        this.calendar = calendar;
    }

    public void addWday(WorkDay wd) {
        this.calendar.add(wd);
    }

    public LinkedList<Shift> getShifts() {
        return shifts;
    }

    public Shift getShiftBySname(String sname){
        Shift s = new Shift();
        for(int i = 0; i<this.shifts.size(); i++){
            if(this.shifts.get(i).getShort_name().equals(sname)){
                s = this.shifts.get(i);
                i = this.shifts.size();
            }
        }
        return s;
    }

    public boolean checkIfWork(CalendarDay day){
        for(int i=0; i< this.calendar.size(); i++){
            if(this.calendar.get(i).checkDate(day)){
                return true;
            }
        }

        return false;
    }

    public boolean checkIfShift(CalendarDay day, Shift s){
        for(int i=0; i< this.calendar.size(); i++){
            if(this.calendar.get(i).checkDate(day)){
                if(this.calendar.get(i).getShift().getName().equals(s.getName())){
                    return true;
                }
            }
        }

        return false;
    }

    public Shift getShiftByDate(CalendarDay day){
        for(int i=0; i< this.calendar.size(); i++){
            if(this.calendar.get(i).checkDate(day)){
                return this.calendar.get(i).getShift();
            }
        }

        return null;
    }

    public void setShifts(LinkedList<Shift> shifts) {
        this.shifts = shifts;
    }

    public void addShift(Shift s){
        shifts.add(s);
    }
}

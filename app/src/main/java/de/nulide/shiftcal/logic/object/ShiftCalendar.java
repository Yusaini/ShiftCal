package de.nulide.shiftcal.logic.object;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

public class ShiftCalendar {

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

    public int getShiftIDBySName(String sname){
        Shift s = new Shift();
        for(int i=0; i<shifts.size(); i++){
            if(shifts.get(i).getShort_name().equals(sname)){
                return i;
            }
        }
        return -1;
    }

    public Shift getShiftById(int id){
        return shifts.get(id);
    }

    public boolean checkIfShift(CalendarDay day, Shift s){
        for(int i=0; i< this.calendar.size(); i++){
            if(this.calendar.get(i).checkDate(day)){
                if(getShiftById(this.calendar.get(i).getShift()).getName().equals(s.getName())){
                    return true;
                }
            }
        }

        return false;
    }

    public Shift getShiftByDate(CalendarDay day){
        for(int i=0; i< this.calendar.size(); i++){
            if(this.calendar.get(i).checkDate(day)){
                return getShiftById(this.calendar.get(i).getShift());
            }
        }

        return null;
    }

    public int getWdayIDByDate(CalendarDay date){
        for(int i =0; i<calendar.size(); i++){
            if(calendar.get(i).checkDate(date)){
                return i;
            }
        }
        return -1;
    }

    public void deleteWday(CalendarDay date){
        calendar.remove(getWdayIDByDate(date));
    }

    public boolean hasWork(CalendarDay date){
        for(int i=0; i<calendar.size(); i++){
            if(calendar.get(i).checkDate(date)){
                return true;
            }
        }
        return false;
    }
    public void setShifts(LinkedList<Shift> shifts) {
        this.shifts = shifts;
    }

    public void addShift(Shift s){
        shifts.add(s);
    }
}

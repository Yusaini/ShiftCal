package de.nulide.shiftcal.logic.object;

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

    public void addCalendar(WorkDay wd) {
        this.calendar.add(wd);
    }

    public LinkedList<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(LinkedList<Shift> shifts) {
        this.shifts = shifts;
    }

    public void addShift(Shift s){
        shifts.add(s);
    }
}

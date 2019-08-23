package de.nulide.shiftcal.logic.io.object;

import java.io.Serializable;
import java.util.LinkedList;


public class SerialShiftCalendar implements Serializable {

    private LinkedList<SerialWorkDay> calendar;
    private LinkedList<SerialShift> shifts;

    public SerialShiftCalendar() {
        calendar = new LinkedList<>();
        shifts = new LinkedList<>();
    }

    public LinkedList<SerialWorkDay> getCalendar() {
        return calendar;
    }

    public void setCalendar(LinkedList<SerialWorkDay> calendar) {
        this.calendar = calendar;
    }

    public LinkedList<SerialShift> getShifts() {
        return shifts;
    }

    public void setShifts(LinkedList<SerialShift> shifts) {
        this.shifts = shifts;
    }
}

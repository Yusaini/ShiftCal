package de.nulide.shiftcal.logic.io.object;

import java.io.Serializable;

public class SerialWorkDay implements Serializable {

    private SerialCalendarDate date;
    private int shift;

    public SerialWorkDay(SerialCalendarDate date, int shift) {
        this.date = date;
        this.shift = shift;
    }

    public SerialCalendarDate getDate() {
        return date;
    }

    public void setDate(SerialCalendarDate date) {
        this.date = date;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
}

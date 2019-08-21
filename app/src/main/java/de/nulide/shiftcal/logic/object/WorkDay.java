package de.nulide.shiftcal.logic.object;

import java.util.Date;

public class WorkDay {
    private Date date;
    private Shift shift;

    public WorkDay(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}

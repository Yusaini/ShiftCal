package de.nulide.shiftcal.logic.object;

import android.graphics.Color;

public class Shift {
    private String name;
    private String short_name;
    private ShiftTime startTime;
    private ShiftTime endTime;
    private int color;

    public Shift(String name, String short_name, ShiftTime startTime, ShiftTime endTime, int color) {
        this.name = name;
        this.short_name = short_name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.color = color;
    }

    public Shift() {
        this.name = "Error";
        this.short_name = "err";
        startTime = new ShiftTime(0, 0);
        endTime = new ShiftTime(0, 0);
        this.color = Color.BLACK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ShiftTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ShiftTime startTime) {
        this.startTime = startTime;
    }

    public ShiftTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ShiftTime endTime) {
        this.endTime = endTime;
    }
}


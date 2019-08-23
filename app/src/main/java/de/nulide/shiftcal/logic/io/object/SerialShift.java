package de.nulide.shiftcal.logic.io.object;

import java.io.Serializable;

public class SerialShift implements Serializable {

    private String name;
    private String short_name;
    private int id;
    private SerialShiftTime startTime;
    private SerialShiftTime endTime;
    private int color;

    public SerialShift(String name, String short_name, int id, SerialShiftTime startTime, SerialShiftTime endTime, int color) {
        this.name = name;
        this.short_name = short_name;
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.color = color;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SerialShiftTime getStartTime() {
        return startTime;
    }

    public void setStartTime(SerialShiftTime startTime) {
        this.startTime = startTime;
    }

    public SerialShiftTime getEndTime() {
        return endTime;
    }

    public void setEndTime(SerialShiftTime endTime) {
        this.endTime = endTime;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

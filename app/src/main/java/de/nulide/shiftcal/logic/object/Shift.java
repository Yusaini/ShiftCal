package de.nulide.shiftcal.logic.object;

import android.provider.CalendarContract;

public class Shift {
    private String name;
    private String short_name;
    private String color;

    public Shift(String name, String short_name, String color) {
        this.name = name;
        this.short_name = short_name;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}


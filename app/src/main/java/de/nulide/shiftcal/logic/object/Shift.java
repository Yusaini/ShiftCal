package de.nulide.shiftcal.logic.object;

import android.graphics.Color;

import java.io.Serializable;

public class Shift implements Serializable {
    private String name;
    private String short_name;
    private int color;

    public Shift(String name, String short_name, int color) {
        this.name = name;
        this.short_name = short_name;
        this.color = color;
    }

    public Shift(){
        this.name = "Error";
        this.short_name = "err";
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


}


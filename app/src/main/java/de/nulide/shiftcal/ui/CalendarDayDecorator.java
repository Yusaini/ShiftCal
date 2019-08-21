package de.nulide.shiftcal.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.LinkedList;

import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.object.WorkDay;


public class CalendarDayDecorator implements DayViewDecorator{

    ShiftCalendar sc;
    static Shift shift;

    public CalendarDayDecorator(ShiftCalendar sc){
        this.sc = sc;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        shift = sc.getShiftOf(day);
        if(shift != null){
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, Color.RED));
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new RelativeSizeSpan(1.4f));
    }
}

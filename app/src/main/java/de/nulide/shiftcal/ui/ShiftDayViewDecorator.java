package de.nulide.shiftcal.ui;

import android.graphics.Typeface;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;

public class ShiftDayViewDecorator implements DayViewDecorator {

    private Shift shift;
    private ShiftCalendar sc;

    public ShiftDayViewDecorator(Shift shift, ShiftCalendar sc) {
        this.shift = shift;
        this.sc = sc;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        if(sc.checkIfShift(day, shift)){
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, shift.getColor()));
    }
}

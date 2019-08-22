package de.nulide.shiftcal.ui;

import androidx.annotation.NonNull;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;

import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;

public class ShiftDayFormatter implements DayFormatter {

    private ShiftCalendar sc;

    public ShiftDayFormatter(ShiftCalendar sc) {
        this.sc = sc;
    }

    @NonNull
    @Override
    public String format(@NonNull CalendarDay day) {
        String format = "" +day.getDay();
        Shift s = sc.getShiftByDate(day);
        if(s != null) {
            format += "\n" + s.getShort_name();
        }
        return format;
    }
}

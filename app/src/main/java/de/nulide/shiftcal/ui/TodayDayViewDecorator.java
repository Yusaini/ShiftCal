package de.nulide.shiftcal.ui;

import android.graphics.Typeface;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

public class TodayDayViewDecorator implements DayViewDecorator {

    public TodayDayViewDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return CalendarDay.today().equals(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new StyleSpan(Typeface.BOLD));
    }
}

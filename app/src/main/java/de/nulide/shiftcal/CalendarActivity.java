package de.nulide.shiftcal;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.object.WorkDay;
import de.nulide.shiftcal.ui.ShiftDayViewDecorator;
import de.nulide.shiftcal.ui.TodayDayViewDecorator;

public class CalendarActivity extends AppCompatActivity {

    public static ShiftCalendar sc;

    MaterialCalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sc = new ShiftCalendar();
        Shift f1 = new Shift("Frühschicht", "F1", Color.BLUE);
        Shift s1 = new Shift("Spätschicht", "S1", Color.RED);
        sc.addShift(f1);
        sc.addShift(s1);
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 26), s1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 25), s1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 24), s1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 18), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 21), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 20), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 19), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 18), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 17), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 16), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 15), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 14), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 13), f1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 12), s1));
        sc.addWday(new WorkDay(CalendarDay.from(2019, 8, 11), s1));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replacur own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        calendar = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendar.setDateSelected(CalendarDay.today(), true);
        calendar.addDecorator(new ShiftDayViewDecorator(f1, sc));
        calendar.addDecorator(new ShiftDayViewDecorator(s1, sc));
        calendar.addDecorator(new TodayDayViewDecorator());


        //updateCalendar();
    }

}

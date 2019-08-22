package de.nulide.shiftcal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import de.nulide.shiftcal.logic.object.CalendarDate;
import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.object.WorkDay;
import de.nulide.shiftcal.logic.utils.CalendarIO;
import de.nulide.shiftcal.ui.ShiftDayViewDecorator;
import de.nulide.shiftcal.ui.TodayDayViewDecorator;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, OnDateSelectedListener {

    static ShiftCalendar sc;
    static TextView tvName;

    MaterialCalendarView calendar;

    static final int PER_WRITE_STORAGE = 345;
    static final int PER_READ_STORAGE = 346;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PER_WRITE_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PER_READ_STORAGE);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        calendar = findViewById(R.id.calendarView);
        calendar.setDateSelected(CalendarDay.today(), true);
        calendar.setOnDateChangedListener(this);
        tvName = findViewById(R.id.cTextViewName);
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);

        updateCalendar();
        updateTextView();
    }

    public void updateCalendar(){
        sc = new ShiftCalendar();
        Shift f1 = new Shift("Frühschicht", "F1", Color.BLUE);
        Shift s1 = new Shift("Spätschicht", "S1", Color.RED);
        Shift sch = new Shift("School", "SC", Color.GREEN);
        Shift s11 = new Shift("Spätschicht-1", "S1-1", Color.RED);

        sc.addShift(f1);
        sc.addShift(s1);
        sc.addShift(s11);
        sc.addShift(sch);
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 26), s1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 25), s1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 24), s1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 18), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 21), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 20), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 19), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 18), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 17), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 16), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 15), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 14), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 13), f1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 12), s1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 11), s1));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 28), s11));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 29), s11));
        sc.addWday(new WorkDay(new CalendarDate(2019, 8, 30), s11));
        sc.addWday(new WorkDay(new CalendarDate(2019, 9, 13), sch));
        sc.addWday(new WorkDay(new CalendarDate(2019, 9, 12), sch));
        sc.addWday(new WorkDay(new CalendarDate(2019, 9, 11), sch));
        sc.addWday(new WorkDay(new CalendarDate(2019, 9, 10), sch));
        sc.addWday(new WorkDay(new CalendarDate(2019, 9, 9), sch));
        sc.addWday(new WorkDay(new CalendarDate(2019, 9, 8), f1));



        CalendarIO.writeShiftVal(sc);

        sc = CalendarIO.readShiftCal();
        calendar.addDecorator(new ShiftDayViewDecorator(f1, sc));
        calendar.addDecorator(new ShiftDayViewDecorator(s1, sc));
        calendar.addDecorator(new ShiftDayViewDecorator(s11, sc));
        calendar.addDecorator(new ShiftDayViewDecorator(sch, sc));
        calendar.addDecorator(new TodayDayViewDecorator());

    }

    public void updateTextView(){
        Shift sel = sc.getShiftByDate(calendar.getSelectedDate());
        if(sel != null) {
            tvName.setTextColor(sel.getColor());
            tvName.setText(sel.getName());
        }else{
            tvName.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(this, ShiftsActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateCalendar();
                } else {
                    System.exit(0);
                }
                return;
            }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        updateTextView();
    }
}

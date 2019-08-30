package de.nulide.shiftcal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;

import de.nulide.shiftcal.logic.io.CalendarIO;
import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.object.WorkDay;
import de.nulide.shiftcal.ui.ShiftAdapter;
import de.nulide.shiftcal.ui.ShiftDayFormatter;
import de.nulide.shiftcal.ui.ShiftDayViewDecorator;
import de.nulide.shiftcal.ui.TodayDayViewDecorator;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, OnDateSelectedListener, AdapterView.OnItemClickListener, FloatingActionsMenu.OnFloatingActionsMenuUpdateListener {

    static final int PER_WRITE_STORAGE = 345;
    static final int PER_READ_STORAGE = 346;
    static ShiftCalendar sc;
    static TextView tvName;
    static TextView tvST;
    static TextView tvET;
    static Switch switchEdit;
    private static AlertDialog dialog;
    MaterialCalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionsMenu fabMenu = findViewById(R.id.fab_menu);
        fabMenu.setOnFloatingActionsMenuUpdateListener(this);

        FloatingActionButton fab = findViewById(R.id.shiftsfab);
        fab.setOnClickListener(this);
        calendar = findViewById(R.id.calendarView);
        calendar.setDateSelected(CalendarDay.today(), true);
        calendar.setOnDateChangedListener(this);
        tvName = findViewById(R.id.cTextViewName);
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        tvST = findViewById(R.id.cTextViewST);
        tvET = findViewById(R.id.cTextViewET);

        switchEdit = findViewById(R.id.editSwitch);

        updateCalendar();
        updateTextView();
    }

    public void updateCalendar() {
        sc = CalendarIO.readShiftCal(getFilesDir());
        calendar.removeDecorators();
        for (int i = 0; i < sc.getShiftsSize(); i++) {
            calendar.addDecorator(new ShiftDayViewDecorator(sc.getShiftByIndex(i), sc));
        }
        calendar.addDecorator(new TodayDayViewDecorator());
        calendar.setDayFormatter(new ShiftDayFormatter(sc));

    }

    public void updateTextView() {
        Shift sel = sc.getShiftByDate(calendar.getSelectedDate());
        if (sel != null) {
            tvName.setTextColor(sel.getColor());
            tvName.setText(sel.getName());
            tvST.setText("Start Time: " + sel.getStartTime().toString());
            tvET.setText("End Time: " + sel.getEndTime().toString());
        } else {
            tvName.setText("");
            tvST.setText("");
            tvET.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(this, ShiftsActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        if (switchEdit.isChecked()) {
            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.dialog_shift_selector, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            ListView listViewShifts = (ListView) dialoglayout;
            ShiftAdapter adapter = new ShiftAdapter(this, new ArrayList<Shift>(sc.getShiftList()));
            listViewShifts.setAdapter(adapter);
            adapter.add(new Shift("Delete", "D", -1, null, null, Color.RED));
            listViewShifts.setOnItemClickListener(this);
            builder.setView(dialoglayout);
            dialog = builder.create();
            dialog.show();

        } else {
            updateTextView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCalendar();
        updateTextView();
        switchEdit.setChecked(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i < sc.getShiftsSize()) {
            if (!sc.hasWork(calendar.getSelectedDate())) {
                sc.addWday(new WorkDay(calendar.getSelectedDate(), sc.getShiftByIndex(i).getId()));
            }else{
                sc.deleteWday(calendar.getSelectedDate());
                sc.addWday(new WorkDay(calendar.getSelectedDate(), sc.getShiftByIndex(i).getId()));
            }
        } else {
            if (sc.hasWork(calendar.getSelectedDate())) {
                sc.deleteWday(calendar.getSelectedDate());
            }
        }
        dialog.cancel();
        CalendarIO.writeShiftVal(getFilesDir(), sc);
        updateCalendar();
        updateTextView();
    }


    @Override
    public void onMenuExpanded() {
        FrameLayout fl = (FrameLayout) findViewById(R.id.CalendarTopLayer);
        fl.setBackgroundColor(Color.argb(200, 255, 255, 255));

    }

    @Override
    public void onMenuCollapsed() {
        FrameLayout fl = (FrameLayout) findViewById(R.id.CalendarTopLayer);
        fl.setBackgroundColor(Color.TRANSPARENT);

    }

}

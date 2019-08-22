package de.nulide.shiftcal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.CalendarContract;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.object.WorkDay;
import de.nulide.shiftcal.logic.utils.CalendarIO;
import de.nulide.shiftcal.ui.ShiftAdapter;
import de.nulide.shiftcal.ui.ShiftDayViewDecorator;
import de.nulide.shiftcal.ui.TodayDayViewDecorator;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, OnDateSelectedListener, AdapterView.OnItemClickListener {

    static ShiftCalendar sc;
    static TextView tvName;
    static Switch switchEdit;
    private static AlertDialog dialog;

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

        switchEdit = findViewById(R.id.editSwitch);
        switchEdit.setChecked(false);

        updateCalendar();
        updateTextView();
    }

    public void updateCalendar(){
        sc = CalendarIO.readShiftCal();
        calendar.removeDecorators();
        for(int i=0; i<sc.getShifts().size();i++){
            calendar.addDecorator(new ShiftDayViewDecorator(sc.getShifts().get(i), sc));
        }
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
        if(switchEdit.isChecked()){
            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.dialog_shift_selector, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            ListView listViewShifts = (ListView) dialoglayout;
            ShiftAdapter adapter = new ShiftAdapter(this, new ArrayList<Shift>(sc.getShifts()));
            listViewShifts.setAdapter(adapter);
            adapter.add(new Shift("Delete", "D", Color.RED));
            listViewShifts.setOnItemClickListener(this);
            builder.setView(dialoglayout);
            dialog = builder.create();
            dialog.show();


        }else {
            updateTextView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCalendar();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(i < sc.getShifts().size()) {
            sc.getCalendar().add(new WorkDay(calendar.getSelectedDate(), i));
        }else{
            sc.deleteWday(calendar.getSelectedDate());
        }
        dialog.cancel();
        CalendarIO.writeShiftVal(sc);
        updateCalendar();
        updateTextView();
    }
}

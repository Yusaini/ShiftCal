package de.nulide.shiftcal;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import de.nulide.shiftcal.logic.io.CalendarIO;
import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.object.ShiftTime;

public class ShiftCreatorActivity extends AppCompatActivity implements View.OnClickListener, OnColorSelectedListener, TimePickerDialog.OnTimeSetListener {

    private ShiftCalendar sc;
    private int toEditShift = -1;
    private boolean colorSelected;
    private ShiftTime stStart;
    private ShiftTime stEnd;
    private boolean setStartTime = true;

    private FloatingActionButton fab;
    private EditText etViewName;
    private EditText etViewSName;
    private TextView tvStartTime;
    private Button btnStartTime;
    private Button btnEndTime;
    private TextView tvEndTime;
    private Button btnCP;
    private TimePickerDialog timePickerST;
    private TimePickerDialog timePickerET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_creator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        toEditShift = bundle.getInt("toedit");

        sc = CalendarIO.readShiftCal(getFilesDir());
        stStart = null;
        stEnd = null;


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        etViewName = findViewById(R.id.scEditTextName);
        etViewSName = findViewById(R.id.scEditTextSName);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        btnStartTime = findViewById(R.id.setStartTime);
        btnStartTime.setOnClickListener(this);
        btnEndTime = findViewById(R.id.setEndTime);
        btnEndTime.setOnClickListener(this);
        btnCP = findViewById(R.id.colorPickerBtn);
        btnCP.setOnClickListener(this);
        btnCP.setTextColor(Color.GREEN);

        if (toEditShift != -1) {
            etViewName.setText(sc.getShiftById(toEditShift).getName());
            etViewSName.setText(sc.getShiftById(toEditShift).getShort_name());
            btnCP.setTextColor(sc.getShiftById(toEditShift).getColor());
            stStart = sc.getShiftById(toEditShift).getStartTime();
            stEnd = sc.getShiftById(toEditShift).getEndTime();
            colorSelected = true;
        }
    }

    @Override
    public void onClick(View view) {
        if (view == fab) {
            String name = etViewName.getText().toString();
            String sname = etViewSName.getText().toString();

            if (!name.isEmpty() && !sname.isEmpty() && stStart != null && stEnd != null && colorSelected) {
                Shift nS = new Shift(name, sname, sc.getNextShiftId(), stStart, stEnd, btnCP.getCurrentTextColor());
                if (toEditShift != -1) {
                    nS.setId(sc.getShiftById(toEditShift).getId());
                    sc.setShift(toEditShift, nS);
                } else {
                    sc.addShift(nS);
                }
                CalendarIO.writeShiftVal(getFilesDir(), sc);
                this.finish();
            } else {
                Snackbar.make(view, "Error: Not enough Information!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        } else if (view == btnStartTime) {
            setStartTime = true;
            if (toEditShift != -1) {
                timePickerST = new TimePickerDialog(this, this, sc.getShiftById(toEditShift).getStartTime().getHour(), sc.getShiftById(toEditShift).getStartTime().getMinute(), true);
            } else {
                timePickerST = new TimePickerDialog(this, this, 12, 0, true);
            }
            timePickerST.show();
        } else if (view == btnEndTime) {
            setStartTime = false;
            if (toEditShift != -1) {
                timePickerET = new TimePickerDialog(this, this, sc.getShiftById(toEditShift).getEndTime().getHour(), sc.getShiftById(toEditShift).getEndTime().getMinute(), true);
            } else {
                timePickerET = new TimePickerDialog(this, this, 12, 0, true);
            }
            timePickerET.show();
        } else {
            ColorPickerDialogBuilder
                    .with(this)
                    .setTitle("Choose color")
                    .initialColor(btnCP.getCurrentTextColor())
                    .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                    .density(12)
                    .setOnColorSelectedListener(this)
                    .setPositiveButton("ok", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int lastSelectedColor, Integer[] allColors) {
                            onColorSelected(lastSelectedColor);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .build()
                    .show();
        }
    }

    @Override
    public void onColorSelected(int selectedColor) {
        btnCP.setTextColor(selectedColor);
        this.colorSelected = true;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        if (setStartTime) {
            stStart = new ShiftTime(hour, minute);
            tvStartTime.setText(stStart.toString());
        } else {
            stEnd = new ShiftTime(hour, minute);
            tvEndTime.setText(stEnd.toString());
        }
    }
}

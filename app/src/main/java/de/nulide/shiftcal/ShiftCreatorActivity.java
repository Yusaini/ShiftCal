package de.nulide.shiftcal;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.utils.CalendarIO;

public class ShiftCreatorActivity extends AppCompatActivity implements View.OnClickListener, OnColorSelectedListener {

    private ShiftCalendar sc;
    private int toEditShift = -1;
    private EditText etViewName;
    private EditText etViewSName;
    private Button btnCP;
    private boolean colorSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_creator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        toEditShift = bundle.getInt("toedit");

        sc = CalendarIO.readShiftCal();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etViewName.getText().toString();
                String sname = etViewSName.getText().toString();

                if(!name.isEmpty() && !sname.isEmpty() && colorSelected) {
                    Shift nS = new Shift(name, sname, btnCP.getCurrentTextColor());
                    if (toEditShift != -1) {
                        sc.getShifts().set(toEditShift, nS);
                    } else {
                        sc.getShifts().add(nS);
                    }
                    CalendarIO.writeShiftVal(sc);
                }else{
                    Snackbar.make(view, "Error: Not enough Informations!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        etViewName = findViewById(R.id.scEditTextName);
        etViewSName = findViewById(R.id.scEditTextSName);
        btnCP = findViewById(R.id.colorPickerBtn);
        btnCP.setOnClickListener(this);
        btnCP.setTextColor(Color.GREEN);

        if(toEditShift != -1){
            etViewName.setText(sc.getShifts().get(toEditShift).getName());
            etViewSName.setText(sc.getShifts().get(toEditShift).getShort_name());
            btnCP.setTextColor(sc.getShifts().get(toEditShift).getColor());
            colorSelected = true;
        }
    }

    @Override
    public void onClick(View view) {
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

    @Override
    public void onColorSelected(int selectedColor) {
        btnCP.setTextColor(selectedColor);
        this.colorSelected = true;
    }
}

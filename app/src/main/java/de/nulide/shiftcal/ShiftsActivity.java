package de.nulide.shiftcal;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.utils.CalendarIO;
import de.nulide.shiftcal.ui.ShiftAdapter;

public class ShiftsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    static ShiftCalendar sc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shifts);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        updateShifts();

        ShiftAdapter adapter = new ShiftAdapter(this, new ArrayList<Shift>(sc.getShifts()));
        ListView listViewShifts = (ListView) findViewById(R.id.listViewShifts);
        listViewShifts.setAdapter(adapter);
        listViewShifts.setOnItemClickListener(this);
    }

    public void updateShifts(){
        sc = CalendarIO.readShiftCal();

    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(this, ShiftCreatorActivity.class);
        myIntent.putExtra("toedit", -1);
        startActivity(myIntent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent myIntent = new Intent(this, ShiftCreatorActivity.class);
        myIntent.putExtra("toedit", i);
        startActivity(myIntent);
    }
}

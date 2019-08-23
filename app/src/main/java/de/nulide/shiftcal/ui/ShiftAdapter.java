package de.nulide.shiftcal.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import de.nulide.shiftcal.R;
import de.nulide.shiftcal.logic.object.Shift;

public class ShiftAdapter extends ArrayAdapter<Shift> {

    private final Context context;
    private final ArrayList<Shift> shifts;

    public ShiftAdapter(@NonNull Context context, ArrayList<Shift> shifts) {
        super(context, R.layout.row_shift, shifts);
        this.context = context;
        this.shifts = shifts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_shift, parent, false);

        TextView tvSName = rowView.findViewById(R.id.textViewSName);
        TextView tvName = rowView.findViewById(R.id.textViewName);

        tvSName.setText(shifts.get(position).getShort_name() + " ");
        tvSName.setTextColor(shifts.get(position).getColor());
        tvSName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 39f);
        tvSName.setTypeface(null, Typeface.BOLD);
        tvName.setText(shifts.get(position).getName());
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);


        return rowView;
    }

}

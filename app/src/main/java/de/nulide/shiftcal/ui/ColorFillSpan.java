package de.nulide.shiftcal.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import androidx.annotation.NonNull;

public class ColorFillSpan implements LineBackgroundSpan {

    private int color;

    public ColorFillSpan(int color) {
        this.color = color;
    }

    @Override
    public void drawBackground(@NonNull Canvas canvas, @NonNull Paint paint, int left, int right, int top, int baseline, int bottom,
                               CharSequence charSequence,
                               int start, int end, int lineNum) {
        canvas.drawColor(color);
    }
}

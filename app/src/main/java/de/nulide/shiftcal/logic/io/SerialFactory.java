package de.nulide.shiftcal.logic.io;

import de.nulide.shiftcal.logic.io.object.SerialCalendarDate;
import de.nulide.shiftcal.logic.io.object.SerialShift;
import de.nulide.shiftcal.logic.io.object.SerialShiftCalendar;
import de.nulide.shiftcal.logic.io.object.SerialShiftTime;
import de.nulide.shiftcal.logic.io.object.SerialWorkDay;
import de.nulide.shiftcal.logic.object.CalendarDate;
import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;
import de.nulide.shiftcal.logic.object.ShiftTime;
import de.nulide.shiftcal.logic.object.WorkDay;

public class SerialFactory {

    public static SerialShiftCalendar convertShiftCalendarToSerial(ShiftCalendar sc) {
        SerialShiftCalendar ssc = new SerialShiftCalendar();
        for (int i = 0; i < sc.getShifts().size(); i++) {
            ssc.getShifts().add(convertShiftToSerial(sc.getShifts().get(i)));
        }
        for (int i = 0; i < sc.getCalendar().size(); i++) {
            ssc.getCalendar().add(convertWorkDayToSerial(sc.getCalendar().get(i)));
        }
        return ssc;
    }

    public static ShiftCalendar convertSerialToShiftCalendar(SerialShiftCalendar ssc) {
        ShiftCalendar sc = new ShiftCalendar();
        for (int i = 0; i < ssc.getShifts().size(); i++) {
            sc.getShifts().add(convertSerialToShift(ssc.getShifts().get(i)));
        }
        for (int i = 0; i < ssc.getCalendar().size(); i++) {
            sc.getCalendar().add(convertSerialToWorkDay(ssc.getCalendar().get(i)));
        }
        return sc;
    }

    public static SerialCalendarDate convertCalendarDateToSerial(CalendarDate cd) {
        return new SerialCalendarDate(cd.getYear(), cd.getMonth(), cd.getDay());
    }

    public static CalendarDate convertSerialToCalendarDate(SerialCalendarDate scd) {
        return new CalendarDate(scd.getYear(), scd.getMonth(), scd.getDay());
    }

    public static SerialShiftTime convertShiftTimeToSerial(ShiftTime st) {
        return new SerialShiftTime(st.getHour(), st.getMinute());
    }

    public static ShiftTime convertSerialToShiftTime(SerialShiftTime sst) {
        return new ShiftTime(sst.getHour(), sst.getMinute());
    }

    public static SerialShift convertShiftToSerial(Shift s) {
        return new SerialShift(s.getName(), s.getShort_name(),
                convertShiftTimeToSerial(s.getStartTime()), convertShiftTimeToSerial(s.getEndTime()),
                s.getColor());
    }

    public static Shift convertSerialToShift(SerialShift ss) {
        return new Shift(ss.getName(), ss.getShort_name(),
                convertSerialToShiftTime(ss.getStartTime()), convertSerialToShiftTime(ss.getEndTime()),
                ss.getColor());
    }

    public static SerialWorkDay convertWorkDayToSerial(WorkDay wd) {
        return new SerialWorkDay(convertCalendarDateToSerial(wd.getDate()), wd.getShift());
    }

    public static WorkDay convertSerialToWorkDay(SerialWorkDay swd) {
        return new WorkDay(convertSerialToCalendarDate(swd.getDate()), swd.getShift());
    }

}

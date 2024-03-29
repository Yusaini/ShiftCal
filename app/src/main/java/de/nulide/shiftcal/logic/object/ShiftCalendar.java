package de.nulide.shiftcal.logic.object;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.LinkedList;

public class ShiftCalendar {

    private LinkedList<WorkDay> calendar;
    private LinkedList<Shift> shifts;
    private int nextShiftId;

    public ShiftCalendar() {
        calendar = new LinkedList<>();
        shifts = new LinkedList<>();
        nextShiftId = 0;
    }

    public LinkedList<Shift> getShiftList() {
        return shifts;
    }

    public void addWday(WorkDay wd) {
        this.calendar.add(wd);
    }

    public void deleteWorkDaysWithShift(int id) {
        for (int i = calendar.size() - 1; i >= 0; i--) {
            if (calendar.get(i).getShift() == id) {
                calendar.remove(i);
            }
        }
    }

    public Shift getShiftById(int id) {
        Shift s = new Shift();
        for (int i = 0; i < shifts.size(); i++) {
            if (shifts.get(i).getId() == id) {
                return shifts.get(i);
            }
        }
        return s;
    }

    public Shift getShiftByIndex(int i) {
        return shifts.get(i);
    }

    public Shift getShiftByDate(CalendarDay day) {
        for (int i = 0; i < this.calendar.size(); i++) {
            if (this.calendar.get(i).checkDate(day)) {
                return getShiftById(this.calendar.get(i).getShift());
            }
        }

        return null;
    }

    public void deleteShift(int id) {
        for (int i = 0; i < shifts.size(); i++) {
            if (shifts.get(i).getId() == id) {
                shifts.remove(i);
                return;
            }
        }
    }

    public void deleteShiftByIndex(int i) {
        shifts.remove(i);
    }

    public void setShift(int id, Shift s) {
        for (int i = 0; i < shifts.size(); i++) {
            if (shifts.get(i).getId() == id) {
                shifts.set(i, s);
                return;
            }
        }
    }

    public void addShift(Shift s) {
        shifts.add(s);
        if (s.getId() >= nextShiftId) {
            nextShiftId++;
        }
    }

    public int getShiftsSize() {
        return shifts.size();
    }

    public int getNextShiftId() {
        return nextShiftId;
    }

    public boolean checkIfShift(CalendarDay day, Shift s) {
        for (int i = 0; i < this.calendar.size(); i++) {
            if (this.calendar.get(i).checkDate(day)) {
                if (getShiftById(this.calendar.get(i).getShift()).getId() == s.getId()) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getCalendarSize() {
        return calendar.size();
    }

    public WorkDay getWdayByIndex(int i) {
        return calendar.get(i);
    }

    public int getWdayIDByDate(CalendarDay date) {
        for (int i = 0; i < calendar.size(); i++) {
            if (calendar.get(i).checkDate(date)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteWday(CalendarDay date) {
        calendar.remove(getWdayIDByDate(date));
    }

    public boolean hasWork(CalendarDay date) {
        for (int i = 0; i < calendar.size(); i++) {
            if (calendar.get(i).checkDate(date)) {
                return true;
            }
        }
        return false;
    }

}

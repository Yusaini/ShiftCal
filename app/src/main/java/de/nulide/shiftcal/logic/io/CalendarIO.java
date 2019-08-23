package de.nulide.shiftcal.logic.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import de.nulide.shiftcal.logic.io.object.SerialShiftCalendar;
import de.nulide.shiftcal.logic.object.ShiftCalendar;

public class CalendarIO {

    private static final String FILE_NAME = "sc.o";

    public static ShiftCalendar readShiftCal(File dir) {
        ObjectInputStream input;
        try {
            input = new ObjectInputStream(new FileInputStream(new File(dir, FILE_NAME)));
            SerialShiftCalendar readSC = (SerialShiftCalendar) input.readObject();
            input.close();
            return SerialFactory.convertSerialToShiftCalendar(readSC);
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ShiftCalendar();
    }

    public static void writeShiftVal(File dir, ShiftCalendar sc) {
        File file = new File(dir, FILE_NAME);
        ObjectOutput out = null;
        try {
            if (file.exists()) {
                file.delete();
            } else {
                file.createNewFile();
            }
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(SerialFactory.convertShiftCalendarToSerial(sc));
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

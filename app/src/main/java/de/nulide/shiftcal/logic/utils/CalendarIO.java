package de.nulide.shiftcal.logic.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import de.nulide.shiftcal.logic.object.Shift;
import de.nulide.shiftcal.logic.object.ShiftCalendar;

public class CalendarIO {

    static final String FILE_NAME = "sc.o";

    public static ShiftCalendar readShiftCal(){
        File file = new File(new File(Environment.getExternalStorageDirectory(), "ShiftCal"), FILE_NAME);
        ObjectInputStream input;
        try {
            input = new ObjectInputStream(new FileInputStream(file));
            ShiftCalendar readSC = (ShiftCalendar) input.readObject();
            input.close();
            return readSC;
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

    public static void writeShiftVal(ShiftCalendar sc){
        File file = new File(new File(Environment.getExternalStorageDirectory(), "ShiftCal"), FILE_NAME);
        ObjectOutput out = null;
        try {
            if(file.exists()){
                file.delete();
            }else{
                if(!file.getParentFile().exists()){
                    file.mkdirs();
                }
                file.createNewFile();
            }
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(sc);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

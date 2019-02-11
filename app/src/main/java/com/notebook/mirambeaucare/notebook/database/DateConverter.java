package com.notebook.mirambeaucare.notebook.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Date converter class needed for Room DB, to be able to store Date format in the DB.
 */
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
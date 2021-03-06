package com.notebook.mirambeaucare.notebook.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "glycemia_table")
public class Glycemia {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @TypeConverters(DateConverter.class)
    @ColumnInfo(name ="date")
    @NonNull
    private Date date;
    private float insulin;
    private float glycemia;



    //constructor used by room data persistence
    public Glycemia(int id, Date date, float insulin, float glycemia) {
        this.id = id;
        this.date = date;
        this.insulin = insulin;
        this.glycemia = glycemia;

    }


    @Ignore
    public Glycemia(Date date, float insulin, float glycemia) {
        this.date = date;
        this.insulin = insulin;
        this.glycemia = glycemia;
    }

    @Ignore
    //empty constructor for reflection operation
    public Glycemia(int id) {
        this.id = id;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public float getInsulin() {return insulin;}

    public void setInsulin(float insulin) {this.insulin = insulin;}

    public float getGlycemia() {return glycemia; }

    public void setGlycemia(float glycemia) {this.glycemia = glycemia;}
}

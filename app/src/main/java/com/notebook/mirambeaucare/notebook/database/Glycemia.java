package com.notebook.mirambeaucare.notebook.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "glycemia")
public class Glycemia {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;
    private float insulin;


    //constructor used by room data persistence
    public Glycemia(int id, Date date, float insulin) {
        this.id = id;
        this.date = date;
        this.insulin = insulin;
    }


    @Ignore
    public Glycemia(Date date, float insulin) {
        this.date = date;
        this.insulin = insulin;
    }


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
}

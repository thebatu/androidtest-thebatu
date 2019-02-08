package com.notebook.mirambeaucare.notebook.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "insulin")
public class Insulin {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;

    //constructor used by room data persistence
    public Insulin(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    //empty constructor for reflection operation
    @Ignore
    public Insulin(){}

    public Insulin(Date date){
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.notebook.mirambeaucare.notebook.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * {@link Dao provides an api for CRUD operations with the {@link NotebookDatabase }
 */
@Dao
public interface GlycemiaDao {

    @Query("SELECT * FROM glycemia_table  ORDER BY date DESC ")
    LiveData<List<Glycemia>> getAllGlycemia();

    @Insert
    void insert(Glycemia glycemia);

    @Update
    void update(Glycemia glycemia);

    @Delete
    void delete(Glycemia glycemia);

    @Query("DELETE FROM glycemia_table")
    void deleteAllGlycemia();

}

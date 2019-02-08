package com.notebook.mirambeaucare.notebook.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * {@link Dao provides an api for CRUD operations with the {@link NotebookDatabase }
 */
@Dao
public interface GlycemiaDao {

    @Query("SELECT * FROM glycemia")
    List<Glycemia> getAllGlycemia;


}

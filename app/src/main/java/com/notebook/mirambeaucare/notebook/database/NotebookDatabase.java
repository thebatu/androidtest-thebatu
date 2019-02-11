package com.notebook.mirambeaucare.notebook.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

/**
 * application database
 */
@Database(entities = {Glycemia.class}, version = 2)
@TypeConverters({DateConverter.class})
public abstract class NotebookDatabase extends RoomDatabase {
    private static final String LOG_TAG = NotebookDatabase.class.getSimpleName();

    private static final String DATABASE_NAME = "notebookDB";

    //Singleton instantiation
    private static final Object LOCK = new Object();
    private static NotebookDatabase sInstance;

    public static NotebookDatabase getInstance(Context context){
        Log.d(LOG_TAG, "Getting notebookDB");
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        NotebookDatabase.class, NotebookDatabase.DATABASE_NAME).
                        allowMainThreadQueries().build();
            }
        }
        return sInstance;
    }

    //the associated DAOs for the database
    public abstract GlycemiaDao glycemiaDao();






}

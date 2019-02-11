package com.notebook.mirambeaucare.notebook.database;


import android.util.Log;

/**
 * Application repository. servers as handler to database operations
 */
public class NotebookRepository {

    private static final Object LOCK = new Object();
    private static NotebookRepository sInstance;
    private GlycemiaDao glycemiaDao;

    public NotebookRepository(final GlycemiaDao glycemiaDao) {
        this.glycemiaDao = glycemiaDao;
    }


    public synchronized static NotebookRepository getInstance(GlycemiaDao glycemiaDao) {
        Log.d("TAG", "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NotebookRepository(glycemiaDao);
            }
        }
        return sInstance;
    }

    public void insert(Glycemia date) {
        glycemiaDao.insert(date);
    }

}
